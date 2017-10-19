package com.carshop.service;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.ws.rs.core.Response;

import com.carshop.dao.UserDetailsDao;
import com.carshop.dao.UserDetailsDaoImplement;
import com.carshop.model.ResponseWithUserData;
import com.carshop.model.UserModel;

public class UserServiceImplement implements UserService {
	UserDetailsDao userDetails = new UserDetailsDaoImplement();
	@Override
	public ResponseWithUserData addNewUser(UserModel user) throws UnknownHostException,
	UnsupportedEncodingException, NoSuchAlgorithmException, URISyntaxException{
		ResponseWithUserData resp = new ResponseWithUserData();
		if(userDetails.insertDataForSignUp(user)){
			resp.status = "success";
			String encyPassword = Md5Encrypt(user.getPassword());
			user.setPassword(encyPassword);
			resp.user = user;
		}
		else{
			resp.status = "failed";
		}
		return resp;
	}
	
	
	@Override
	public String Md5Encrypt(String data) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		String encryptPassword = new String();
		byte[] passwordInByte = data.getBytes("UTF-8");
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		byte[] digestPassword = messageDigest.digest(passwordInByte);
		for (int i = 0; i < digestPassword.length; i++)
	        encryptPassword += Integer.toString((digestPassword[i] & 0xff) + 0x100, 16).substring(1);
		return encryptPassword;
	}
	@Override
	public ResponseWithUserData userLoginCheck(UserModel user) throws UnknownHostException,
	NoSuchAlgorithmException, UnsupportedEncodingException, URISyntaxException{
		UserModel gotUser = new UserModel();
		URI location;
		gotUser = userDetails.fetchRowByEmail(user);
		String checkPassword = Md5Encrypt(user.getPassword());
		ResponseWithUserData returnUser = new ResponseWithUserData();
		if(checkPassword.equals(gotUser.getPassword())){
			returnUser.status = "success";
			returnUser.user = gotUser;
		}
		else{
			returnUser.status = "failed";
		}
		return returnUser;
		
	}
}
