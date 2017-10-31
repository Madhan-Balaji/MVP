package com.carshop.controller;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.carshop.model.CarModel;
import com.carshop.model.InsuranceModel;
import com.carshop.model.NewsModel;
import com.carshop.model.ResponseWithCarCollection;
import com.carshop.model.ResponseWithCarData;
import com.carshop.model.ResponseWithInsurance;
import com.carshop.model.ResponseWithInsuranceCollection;
import com.carshop.model.ResponseWithNewsCollection;
import com.carshop.model.ResponseWithNewsData;
import com.carshop.model.ResponseWithUserData;
import com.carshop.model.UserModel;
import com.carshop.service.CarService;
import com.carshop.service.CarServiceImplement;
import com.carshop.service.InsuranceService;
import com.carshop.service.InsuranceServiceImplement;
import com.carshop.service.NewsService;
import com.carshop.service.NewsServiceImplement;
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
		carModel.setUsage("used");
		return carService.addNewUsedCar(carModel, fileInputStream, fileInputDetails,req);
	}
	
	@POST
	@Path("/newCar")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces("application/json")
	public ResponseWithCarData uploadNewFile(		
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
		carModel.setUsage("new");
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
	
	@GET
	@Produces("application/json")
	@Path("/getAllCars")
	public ResponseWithCarCollection getAllCars() throws UnknownHostException{
		CarService carService = new CarServiceImplement();
		return carService.getAllCarDetails();
	}
	@GET
	@Produces("application/json")
	@Path("/getCarDetails")
	public ResponseWithCarData getCarDetails(
			@QueryParam("carid") String id
			) throws UnknownHostException{
		CarService carService = new CarServiceImplement();
		return carService.getCarData(id);
	}
	
	@Path("/getRidOfSession")
	@GET
	public Response signOutUser(
			@Context HttpServletRequest req
			){
		UserService userService = new UserServiceImplement();
		return userService.lossSession(req);
	}
	
	@GET
	@Path("searchByTerm")
	@Produces("application/json")
	public ResponseWithCarCollection searchByTerm(
			@QueryParam("term") String term
			) throws UnknownHostException{
		CarService carService = new CarServiceImplement();
		return carService.searchCarTerm(term);
	}
	
	@POST
	@Path("/saveNewInsurance")
	@Produces("application/json")
	public String addInsurance(
			@FormParam("name") String name,
			@FormParam("val") String val,
			@FormParam("prem") String prem,
			@FormParam("zDep") String zDep,
			@FormParam("claim") String claim,
			@FormParam("own") String own,
			@FormParam("owner") String owner,
			@FormParam("lib") String lib,
			@FormParam("cd") String cd,
			@Context HttpServletRequest req
			) throws UnknownHostException{
		InsuranceService InsuranceService = new InsuranceServiceImplement();
		InsuranceModel insuranceModel = new InsuranceModel();
		HttpSession session = req.getSession();
		insuranceModel.setPostby((String)session.getAttribute("user"));
		insuranceModel.setName(name);
		insuranceModel.setVal(val);
		insuranceModel.setPrem(prem);
		insuranceModel.setzDep(zDep);
		insuranceModel.setClaim(claim);
		insuranceModel.setOwn(own);
		insuranceModel.setOwner(owner);
		insuranceModel.setLib(lib);
		insuranceModel.setCd(cd);
		return InsuranceService.addNewInsurance(insuranceModel);
	}
	
	@POST
	@Path("/saveNewNews")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces("application/json")
	public String saveNews(		
			@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition fileInputDetails,
			@FormDataParam("head") String  head,
			@FormDataParam("content") String  content,
			@Context HttpServletRequest req
			) throws UnknownHostException {
		
		System.out.println(" File name is :"+fileInputDetails.getFileName());
		HttpSession session = req.getSession();
		NewsModel newsModel = new NewsModel();
		NewsService newService = new NewsServiceImplement();
		newsModel.setHeading(head);
		newsModel.setContent(content);
		newsModel.setNewsBy((String) session.getAttribute("user"));
		return newService.addNews(newsModel, fileInputStream, fileInputDetails);
	}
	
	@GET
	@Path("/getStarterNews")
	@Produces("application/json")
	public ResponseWithNewsCollection getStarterNews() throws UnknownHostException{
		NewsService newsService = new NewsServiceImplement();
		return newsService.getSomeNews();
	}
	@POST
	@Path("/getNews")
	@Produces("application/json")
	public ResponseWithNewsData getNews(
			@FormParam("id") String id
			) throws UnknownHostException{
		NewsService newsService = new NewsServiceImplement();
		return newsService.getNews(id);
	}
	@GET
	@Path("/getInsurances")
	@Produces("application/json")
	public ResponseWithInsuranceCollection getInsurances() throws UnknownHostException{
		InsuranceService insuranceService = new InsuranceServiceImplement();
		return insuranceService.getSomeInsurances();
	}
	@POST
	@Path("/getInsu")
	@Produces("application/json")
	public ResponseWithInsurance getInsurance(
			@FormParam("id") String id
			) throws UnknownHostException{
		InsuranceService insuranceService = new InsuranceServiceImplement();
		return insuranceService.getInsu(id);
	}
	@POST
<<<<<<< HEAD
	@Path("/getMyCars")
	@Produces("application/json")
	public ResponseWithCarCollection getMyCars(
			@Context HttpServletRequest req
			) throws UnknownHostException {
		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("user");
		CarService carService = new CarServiceImplement();
		return carService.getCarsUser(id);
	}
	@POST
	@Path("/removeCar")
	@Produces("application/json")
	public String removeCar(
			@FormParam("id") String id
			) throws UnknownHostException{
		CarService carService = new CarServiceImplement();
		return carService.removeCar(id);
=======
	@Path("/addReview")
	@Produces("application/json")
	public String addReview(
			@FormParam("id") String carId,
			@FormParam("review") String review,
			@Context HttpServletRequest req
			){
		CarService carService = new CarServiceImplement();
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("user");
		return carService.addReview(carId, userId, review);
>>>>>>> branch 'master' of https://github.com/Madhan-Balaji/MVP.git
	}
}
