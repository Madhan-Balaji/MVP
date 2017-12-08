package com.carshop.controller;

import java.io.InputStream;
import java.net.UnknownHostException;

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
import com.carshop.model.ResponseWithCarCollection;
import com.carshop.model.ResponseWithCarData;
import com.carshop.model.ResponseWithUserReviews;
import com.carshop.service.CarService;
import com.carshop.service.CarServiceImplement;
import com.carshop.service.UserReviewService;
import com.carshop.service.UserReviewServiceImplement;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/cars")
public class CarController {
	@POST
	@Path("/newUsedCar")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces("application/json")
	public ResponseWithCarData uploadUsedCar(
			@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition fileInputDetails,
			@FormDataParam("name") String name,
			@FormDataParam("model") String model,
			@FormDataParam("year") String year,
			@FormDataParam("gear") String gear,
			@FormDataParam("seat") String seat,
			@FormDataParam("color") String color,
			@FormDataParam("owner") String owner,
			@FormDataParam("fuelType") String fuel,
			@FormDataParam("milage") String milage,
			@FormDataParam("cc") String cc,
			@FormDataParam("address") String address,
			@FormDataParam("brand") String brand,
			@FormDataParam("type") String type,
			@FormDataParam("price") String price,
			@FormDataParam("user") String userId) throws UnknownHostException {

		System.out.println(" File name is :" + fileInputDetails.getFileName());

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
		carModel.setUser(userId);
		return carService.addNewUsedCar(carModel, fileInputStream,
				fileInputDetails);
	}

	@POST
	@Path("/newCar")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces("application/json")
	public ResponseWithCarData uploadNewCar(
			@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition fileInputDetails,
			@FormDataParam("video") InputStream videoInputStream,
			@FormDataParam("video") FormDataContentDisposition videoInputDetails,
			@FormDataParam("name") String name,
			@FormDataParam("model") String model,
			@FormDataParam("year") String year,
			@FormDataParam("gear") String gear,
			@FormDataParam("seat") String seat,
			@FormDataParam("color") String color,
			@FormDataParam("owner") String owner,
			@FormDataParam("fuelType") String fuel,
			@FormDataParam("milage") String milage,
			@FormDataParam("cc") String cc,
			@FormDataParam("address") String address,
			@FormDataParam("brand") String brand,
			@FormDataParam("type") String type,
			@FormDataParam("price") String price,
			@FormDataParam("user") String userId) throws UnknownHostException {

		System.out.println(" File name is :" + fileInputDetails.getFileName());
		System.out
				.println(" video name is :" + videoInputDetails.getFileName());
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
		carModel.setUser(userId);
		ResponseWithCarData car = carService.addNewUsedCar(carModel,
				fileInputStream, fileInputDetails);
		carService.uploadVideo(videoInputStream, videoInputDetails,
				car.car.getId());
		return car;
	}

	@GET
	@Produces("video/mp4")
	@Path("/media/{id}")
	public Response streamMedia(@HeaderParam("Range") String range,
			@PathParam("id") String id) throws Exception {
		CarService carService = new CarServiceImplement();
		return carService.getCarMedia(id, range);
	}

	@GET
	@Produces("video/mp4")
	@Path("/video/{id}")
	public Response streamVideo(@HeaderParam("Range") String range,
			@PathParam("id") String id) throws Exception {
		CarService carService = new CarServiceImplement();
		return carService.getCarVideo(id, range);
	}
	
	@GET
	@Produces("application/json")
	@Path("/getAllCars")
	public ResponseWithCarCollection getAllCars() throws UnknownHostException {
		CarService carService = new CarServiceImplement();
		return carService.getAllCarDetails();
	}

	@GET
	@Produces("application/json")
	@Path("/getCarDetails")
	public ResponseWithCarData getCarDetails(@QueryParam("carid") String id)
			throws UnknownHostException {
		CarService carService = new CarServiceImplement();
		return carService.getCarData(id);
	}
	
	@GET
	@Path("searchByTerm")
	@Produces("application/json")
	public ResponseWithCarCollection searchCarByTerm(
			@QueryParam("term") String term) throws UnknownHostException {
		CarService carService = new CarServiceImplement();
		return carService.searchCarTerm(term);
	}
	
	@POST
	@Path("/getMyCars")
	@Produces("application/json")
	public ResponseWithCarCollection getMyCars(@FormParam("id") String id)
			throws UnknownHostException {
		String ids = id;
		CarService carService = new CarServiceImplement();
		return carService.getCarsUser(ids);
	}

	@POST
	@Path("/removeCar")
	@Produces("application/json")
	public String removeCar(@FormParam("id") String id)
			throws UnknownHostException {
		CarService carService = new CarServiceImplement();
		return carService.removeCar(id);
	}
	
	@POST
	@Path("/addReview")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("application/json")
	public String addReview(@FormParam("carId") String carId,
			@FormParam("review") String review,
			@FormParam("rating") String rating, @Context HttpServletRequest req)
			throws UnknownHostException {
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("user");
		UserReviewService userReviewService = new UserReviewServiceImplement();
		return userReviewService.addUserReview(carId, userId, review, rating);
	}

	@POST
	@Path("/getReview")
	@Produces("application/json")
	public ResponseWithUserReviews getReviews(@FormParam("carId") String carId,
			@Context HttpServletRequest req) throws UnknownHostException {
		UserReviewService userReviewService = new UserReviewServiceImplement();
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("user");
		return userReviewService.getUserReviews(carId, userId);
	}
}
