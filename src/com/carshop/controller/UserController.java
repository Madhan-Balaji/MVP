package com.carshop.controller;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.carshop.model.CarModel;
import com.carshop.model.ResponseWithCarData;
import com.carshop.model.ResponseWithUserData;
import com.carshop.model.UserModel;
import com.carshop.service.CarService;
import com.carshop.service.CarServiceImplement;
import com.carshop.service.UserService;
import com.carshop.service.UserServiceImplement;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

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
			@FormParam("phone") String phone,
			@Context HttpServletRequest req
			) throws UnknownHostException, UnsupportedEncodingException, NoSuchAlgorithmException, URISyntaxException{
		UserService userService = new UserServiceImplement();
		UserModel userModel = new UserModel();
		userModel.setName(name);
		userModel.setEmail(email);
		userModel.setPassword(pwd);
		userModel.setRegion(region);
		userModel.setPhone(phone);
		return userService.addNewUser(userModel,req);
	}
	@POST
	@Path("/userLogin")
	@Produces("application/json")
	public ResponseWithUserData userLogin(
			@FormParam("mail") String email,
			@FormParam("pwd") String password,
			@Context HttpServletRequest req
			) throws UnknownHostException, NoSuchAlgorithmException, UnsupportedEncodingException, URISyntaxException{
		UserModel user = new UserModel();
		UserService userService = new UserServiceImplement();
		user.setEmail(email);
		user.setPassword(password);
		
		return userService.userLoginCheck(user,req);
	}
	@POST
	@Path("/checkSession")
	public String checkSession(
			@FormParam("session") String availedSession,
			@Context HttpServletRequest req
			){
		UserService userService = new UserServiceImplement();
		return userService.checkUserSession(availedSession,req);
	}
	@POST
	@Path("/newUsedCar")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces("application/json")
	public ResponseWithCarData uploadFile(		
			@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition fileInputDetails,
			@FormDataParam("name") String  name,
			@FormDataParam("model") String  model,
			@FormDataParam("year") String  year,
			@FormDataParam("gear") String  gear,
			@FormDataParam("seat") String  seat,
			@FormDataParam("color") String  color,
			@FormDataParam("owner") String  owner,
			@FormDataParam("fuelType") String  fuel,
			@FormDataParam("milage") String  milage,
			@FormDataParam("cc") String  cc,
			@FormDataParam("address") String  address,
			@FormDataParam("brand") String  brand,
			@FormDataParam("type") String  type,
			@FormDataParam("price") String  price,
			@Context HttpServletRequest req
			) throws UnknownHostException {
		
		System.out.println(" File name is :"+fileInputDetails.getFileName());
		
		CarService carService = new CarServiceImplement();
		CarModel carModel = new CarModel();
		carModel.setBrand(brand);
		carModel.setType(type);
		carModel.setName(name);
		carModel.setModel(model);
		carModel.setYear(year);
		carModel.setGear(gear);
		carModel.setSeat(seat);
		carModel.setColor(color);
		carModel.setOwner(owner);
		carModel.setFuel(fuel);
		carModel.setMilage(milage);
		carModel.setCc(cc);
		carModel.setAddress(address);
		carModel.setPrice(price);
		return carService.addNewUsedCar(carModel, fileInputStream, fileInputDetails,req);
	}
	@GET
	@Produces("video/mp4")
	@Path("/media/{id}")
	public Response streamMedia(@HeaderParam("Range") String range,
			@PathParam ("id") String id
			) throws Exception{
		CarService carService = new CarServiceImplement();
		return carService.getCarMedia(id, range);
	}

}
