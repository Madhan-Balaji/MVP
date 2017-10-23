package com.carshop.service;

import java.io.InputStream;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import com.carshop.model.CarModel;
import com.carshop.model.ResponseWithCarData;
import com.sun.jersey.core.header.FormDataContentDisposition;

public interface CarService {
	public ResponseWithCarData addNewUsedCar(CarModel carModel, InputStream fileInputStream, FormDataContentDisposition fileInputDetails, HttpServletRequest req) throws UnknownHostException;
	public Boolean addNewUsedCarImage(CarModel carModel,InputStream fis, FormDataContentDisposition fi) throws UnknownHostException;
	public Response getCarMedia(String id, String Range) throws Exception;
	public CarModel[] getAllCarDetails() throws UnknownHostException;
}
