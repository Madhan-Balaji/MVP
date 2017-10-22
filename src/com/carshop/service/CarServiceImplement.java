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
}
