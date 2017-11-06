package com.carshop.service;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

import com.carshop.dao.UserDetailsDao;
import com.carshop.dao.UserDetailsDaoImplement;
import com.carshop.model.ResponseWithUserData;
import com.carshop.model.UserModel;

public class UserServiceImplement implements UserService {
	UserDetailsDao userDetails = new UserDetailsDaoImplement();
	@Override
	public ResponseWithUserData addNewUser(UserModel user, HttpServletRequest req) throws UnknownHostException,
	UnsupportedEncodingException, NoSuchAlgorithmException, URISyntaxException{
		ResponseWithUserData resp = new ResponseWithUserData();
		if(userDetails.insertDataForSignUp(user)){
			UserModel gotUser = userDetails.fetchRowByEmail(user);
			resp.status = "success";
			String encyPassword = Md5Encrypt(user.getPassword());
			user.setPassword(encyPassword);
			resp.user = gotUser;
			setUserSession(req, gotUser);
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
	public ResponseWithUserData userLoginCheck(UserModel user, HttpServletRequest req) throws UnknownHostException,
	NoSuchAlgorithmException, UnsupportedEncodingException, URISyntaxException{
		UserModel gotUser = new UserModel();
		gotUser = userDetails.fetchRowByEmail(user);
		String checkPassword = Md5Encrypt(user.getPassword());
		ResponseWithUserData returnUser = new ResponseWithUserData();
		if(checkPassword.equals(gotUser.getPassword())){
			returnUser.status = "success";
			returnUser.user = gotUser;
			setUserSession(req, gotUser);
		}
		else{
			returnUser.status = "failed";
		}
		return returnUser;
		
	}
	@Override
	public void setUserSession(HttpServletRequest req,UserModel user){
		HttpSession session = req.getSession();
		session.setAttribute("user", user.getId());
	}
	@Override
	public String checkUserSession(String availedSession, HttpServletRequest req){
		HttpSession session = req.getSession();
		String serverSession = (String) session.getAttribute("user");
		if(availedSession != null) {

			if(serverSession.equals(availedSession)){
				return "success";
			}
			else{
				return "failed";
			}
		}
		else {
			return "failed";
		}
	}


	@Override
	public Response lossSession(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.setAttribute("user","");
		return null;
	}


	@Override
	public String getCompany(String id) throws UnknownHostException {
		return userDetails.getUserCompany(id);
	}


	@Override
	public UserModel getUserById(String id) throws UnknownHostException {
		return userDetails.fetchUserById(id);
	}


	@Override
	public String changePassword(String uid, String newPass, String oldPass)
			throws UnknownHostException, NoSuchAlgorithmException, UnsupportedEncodingException {
		UserModel userModel = getUserById(uid);
		String old = Md5Encrypt(oldPass);
		String newPwd = Md5Encrypt(newPass);
		String exist = userModel.getPassword();
		if(exist.equals(old)){
			if(userDetails.changePassword(newPwd, uid)){
				return "success";
			}
			else{
				return "false";
			}
		}
		else{
			return "false";
		}
	}
	
}
