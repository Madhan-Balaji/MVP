package com.carshop.dao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;

import com.carshop.model.CarModel;
import com.mongodb.DBCollection;
import com.sun.jersey.core.header.FormDataContentDisposition;

public interface CarDetailsDao {
	public DBCollection getCarDetailsCollection() throws UnknownHostException;
	public CarModel addUsedCarDetails(CarModel carModel) throws UnknownHostException;
	public Boolean addMedia(CarModel carModel,InputStream fis, FormDataContentDisposition fi) throws UnknownHostException;
	public File getMedia(String id) throws IOException;
	public CarModel[] fetchAllCars() throws UnknownHostException;
}
