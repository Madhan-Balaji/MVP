package com.carshop.service;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;

import com.carshop.model.ResponseWithUserData;
import com.carshop.model.UserModel;

public interface UserService {
	public ResponseWithUserData addNewUser(UserModel user) throws UnknownHostException, UnsupportedEncodingException, NoSuchAlgorithmException, URISyntaxException ;
	public String Md5Encrypt(String data) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	public ResponseWithUserData userLoginCheck(UserModel user) throws UnknownHostException, NoSuchAlgorithmException, UnsupportedEncodingException, URISyntaxException;
}
