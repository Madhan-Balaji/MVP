package com.carshop.controller;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.carshop.model.ResponseWithUserData;
import com.carshop.model.UserModel;
import com.carshop.service.UserService;
import com.carshop.service.UserServiceImplement;

@Path("/control")
public class UserController {
	@Path("/newUser")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public ResponseWithUserData addingNewUser(
			@FormParam("name") String name,
			@FormParam("email") String email,
			@FormParam("pwd") String pwd,
			@FormParam("region") String region,
			@FormParam("phone") String phone
			) throws UnknownHostException, UnsupportedEncodingException, NoSuchAlgorithmException, URISyntaxException{
		UserService userService = new UserServiceImplement();
		UserModel userModel = new UserModel();
		userModel.setName(name);
		userModel.setEmail(email);
		userModel.setPassword(pwd);
		userModel.setRegion(region);
		userModel.setPhone(phone);
		return userService.addNewUser(userModel);
	}
	@POST
	@Path("/userLogin")
	public ResponseWithUserData userLogin(
			@FormParam("mail") String email,
			@FormParam("pwd") String password
			) throws UnknownHostException, NoSuchAlgorithmException, UnsupportedEncodingException, URISyntaxException{
		UserModel user = new UserModel();
		UserService userService = new UserServiceImplement();
		user.setEmail(email);
		user.setPassword(password);
		
		return userService.userLoginCheck(user);
	}
}
