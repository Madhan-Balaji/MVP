package com.carshop.controller;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.carshop.model.CarModel;
import com.carshop.model.ResponseWithUserData;
import com.carshop.model.UserModel;
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
	@Produces("text/html")
	public Response uploadFile(		
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
			@FormDataParam("address") String  address
			) throws UnknownHostException {
		
		System.out.println(" File name is :"+fileInputDetails.getFileName());
		
		UserService userService = new UserServiceImplement();
		CarModel carModel = new CarModel();
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
		return userService.addNewUserCar();
//		MongoClient mongoClient = new MongoClient("localhost", 27017);
//		DB mongoDB = mongoClient.getDB("Apple");
//
//		
//		DBCollection collection = mongoDB.getCollection("mydb");
//
//		BasicDBObject query = new BasicDBObject();
//		query.put("_id", fileId);
//		DBCursor cursor = collection.find(query);
//
//		if (!cursor.hasNext()) {
//		
//			BasicDBObject document = new BasicDBObject();
//			document.append("_id", fileId);
//			document.append("filename", fileInputDetails.getFileName());
//
//
//		 
//			collection.insert(document);
//
//		  
//			GridFS fileStore = new GridFS(mongoDB, "mydb");
//			GridFSInputFile inputFile = fileStore.createFile(fileInputStream);
//			inputFile.setId(fileId);
//			inputFile.setFilename(fileInputDetails.getFileName());
//			inputFile.save();
//
//			 status = "Sucessfully Uploaded!";
//
//		
//			
//		} else
//		{
//			 status = "Unable to insert record with ID: " + fileId +" as record already exists!!!";
//								
//		}
//		
		//return Response.status(200).entity(fileInputDetails.getFileName()).build();
	}

}
