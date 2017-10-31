package com.carshop.service;

import java.io.File;
import java.io.InputStream;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

import com.carshop.dao.CarDetailsDao;
import com.carshop.dao.CarDetailsDaoImplement;
import com.carshop.model.CarModel;
import com.carshop.model.ResponseWithCarCollection;
import com.carshop.model.ResponseWithCarData;
import com.sun.jersey.core.header.FormDataContentDisposition;

public class CarServiceImplement implements CarService {
	CarDetailsDao carDetailsDao = new CarDetailsDaoImplement(); 
	@Override
	public ResponseWithCarData addNewUsedCar(CarModel carModel, InputStream fileInputStream, FormDataContentDisposition fileInputDetails, HttpServletRequest req) throws UnknownHostException {
		HttpSession session = req.getSession();
		ResponseWithCarData response = new ResponseWithCarData();
		String userId = (String) session.getAttribute("user");
		carModel.setUser(userId);
		carModel = carDetailsDao.addUsedCarDetails(carModel);
		if(addNewUsedCarImage(carModel, fileInputStream, fileInputDetails)) {
			response.status = "success";
			response.car = carModel;
		}
		else {
			response.status = "failed";
		}
		return response;
	}
	@Override
	public Boolean addNewUsedCarImage(CarModel carModel,InputStream fis, FormDataContentDisposition fi) throws UnknownHostException {
		return carDetailsDao.addMedia(carModel,fis,fi);
	}
	@Override
	public Response getCarMedia(String id, String range) throws Exception {
		MediaStreamer mediaStreamer = new MediaStreamer();
		File media = carDetailsDao.getMedia(id);
		return mediaStreamer.buildStream(media, range);
	}
	@Override
	public ResponseWithCarCollection getAllCarDetails() throws UnknownHostException {
		ResponseWithCarCollection cars = carDetailsDao.fetchAllCars();
		return cars;
	}
	@Override
	public ResponseWithCarData getCarData(String id) throws UnknownHostException {
		return carDetailsDao.getCarDetail(id);
	}
	@Override
	public ResponseWithCarCollection searchCarTerm(String term) throws UnknownHostException {
		return carDetailsDao.searchInStrings(term);
	}
	@Override
	public ResponseWithCarCollection getCarsUser(String id) throws UnknownHostException {
		return carDetailsDao.getAllUserCars(id);
	}
	public String removeCar(String id) throws UnknownHostException {
		return carDetailsDao.removeCar(id);
	}
	public String addReview(String carId, String userId, String review) {
		return carDetailsDao.addReview(carId, userId, review);
	}
	@Override
	public boolean uploadVideo(InputStream videoInputStream,
			FormDataContentDisposition videoInputDetails, String id) throws UnknownHostException {
		return carDetailsDao.addVideo(videoInputStream, videoInputDetails, id);
		
	}
	@Override
	public Response getCarVideo(String id, String range) throws Exception {
		MediaStreamer mediaStreamer = new MediaStreamer();
		File media = carDetailsDao.getVideo(id);
		return mediaStreamer.buildStream(media, range);
	}
}
