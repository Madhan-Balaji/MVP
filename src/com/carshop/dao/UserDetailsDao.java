package com.carshop.dao;

import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;

import com.carshop.model.UserModel;
import com.mongodb.DBCollection;

public interface UserDetailsDao {
	public DBCollection getUserDetailsCollection() throws UnknownHostException;

	public Boolean insertDataForSignUp(UserModel user)
			throws UnknownHostException, NoSuchAlgorithmException,
			UnsupportedEncodingException;

	public UserModel fetchRowByEmail(UserModel user)
			throws UnknownHostException;

	public String getUserCompany(String id) throws UnknownHostException;

	public UserModel fetchUserById(String id) throws UnknownHostException;

	public Boolean changePassword(String password, String uid)
			throws UnknownHostException;

}
