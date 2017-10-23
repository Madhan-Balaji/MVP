package com.carshop.dao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.gridfs.*;
import com.carshop.model.CarModel;
import com.carshop.model.ResponseWithCarCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import com.sun.jersey.core.header.FormDataContentDisposition;

public class CarDetailsDaoImplement implements CarDetailsDao {
	@Override
	public DBCollection getCarDetailsCollection() throws UnknownHostException{
		MongoClient mongo = new MongoClient("localhost",27017);
		DB mongoDB = mongo.getDB("carshop");
		return mongoDB.getCollection("car_details");
	}
	@Override
	public CarModel addUsedCarDetails(CarModel carModel) throws UnknownHostException {
		DBCollection collection = getCarDetailsCollection();
		BasicDBObject dummy = new BasicDBObject();
		dummy.put("user", carModel.getUser());
		int count = collection.find(dummy).count();
		carModel.setCarEntry(""+(count+1));
		carModel.setUsage("used");
		BasicDBObject document = new BasicDBObject();
		document.append("brand", carModel.getBrand());
		document.append("type", carModel.getType());
		document.append("name", carModel.getName());
		document.append("model", carModel.getModel());
		document.append("year", carModel.getYear());
		document.append("gear", carModel.getGear());
		document.append("seat", carModel.getSeat());
		document.append("color", carModel.getColor());
		document.append("owner", carModel.getOwner());
		document.append("fule", carModel.getFuel());
		document.append("milage", carModel.getMilage());
		document.append("cc", carModel.getCc());
		document.append("address", carModel.getAddress());
		document.append("usage", carModel.getUsage());
		document.append("user", carModel.getUser());
		document.append("entry", carModel.getCarEntry());
		document.append("price", carModel.getPrice());
		collection.insert(document);
		BasicDBObject search = new BasicDBObject();
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject("entry", carModel.getCarEntry()));
		obj.add(new BasicDBObject("user", carModel.getUser()));
		search.put("$and", obj);
		DBCursor cursor = collection.find(search);
		dummy = (BasicDBObject) cursor.next();
		carModel.setId(dummy.getString("_id"));
		return carModel;
	}
	@Override
	public Boolean addMedia(CarModel carModel,InputStream fis, FormDataContentDisposition fi) throws UnknownHostException {
		MongoClient mongo = new MongoClient("localhost",27017);
		DB mongoDB = mongo.getDB("carshop");
		GridFS fileStore = new GridFS(mongoDB, "media");
		GridFSInputFile inputFile = fileStore.createFile(fis);
		inputFile.setId(carModel.getId());
		inputFile.setFilename(fi.getFileName());
		inputFile.save();
		return true;
	}
	@Override
	public File getMedia(String id) throws IOException {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		DB mongoDB = mongoClient.getDB("carshop");
		BasicDBObject query = new BasicDBObject();
		query.put("_id", id);
		GridFS fileStore = new GridFS(mongoDB, "media");
		GridFSDBFile gridFile = fileStore.findOne(query);
		File file = new File(" .wmv");
		gridFile.writeTo(file);
		return file;
	}
	@Override
	public ResponseWithCarCollection fetchAllCars() throws UnknownHostException {
		DBCollection collection = getCarDetailsCollection();
		ResponseWithCarCollection response = new ResponseWithCarCollection();
		int count = collection.find().count();
		if(count != 0){
			CarModel[] cars = new CarModel[count]; 
			DBCursor cursor = collection.find();
			int i = 0;
			while(cursor.hasNext()){
				BasicDBObject handler = (BasicDBObject) cursor.next();
				cars[i] = new CarModel();
				String id = handler.get("_id").toString();
				cars[i].setId(id);
				cars[i].setBrand(handler.getString("brand"));
				cars[i].setType(handler.getString("type"));
				cars[i].setName(handler.getString("name"));
				cars[i].setModel(handler.getString("model"));
				cars[i].setGear(handler.getString("gear"));
				cars[i].setSeat(handler.getString("seat"));
				cars[i].setColor(handler.getString("color"));
				cars[i].setFuel(handler.getString("fule"));
				cars[i].setMilage(handler.getString("milage"));
				cars[i].setOwner(handler.getString("owner"));
				cars[i].setCc(handler.getString("cc"));
				cars[i].setPrice(handler.getString("price"));
				cars[i].setCarEntry(handler.getString("entry"));
				cars[i].setUser(handler.getString("user"));
				cars[i].setUsage(handler.getString("usage"));
				cars[i].setAddress(handler.getString("address"));
				cars[i].setYear(handler.getString("year"));
				i++;
			}
			response.status="success";
			response.cars = cars;
			response.rows = count;
			return response;
		}
		else{
			response.status="failure";
			return response;
		}
	}
	
}
