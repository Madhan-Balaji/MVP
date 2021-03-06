package com.carshop.dao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.mongodb.gridfs.*;
import com.carshop.model.CarModel;
import com.carshop.model.ResponseWithCarCollection;
import com.carshop.model.ResponseWithCarData;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import com.sun.jersey.core.header.FormDataContentDisposition;

public class CarDetailsDaoImplement implements CarDetailsDao {
	public CarModel allDataSetter(BasicDBObject handler) {
		CarModel car = new CarModel();
		String id = handler.get("_id").toString();
		car.setId(id);
		car.setBrand(handler.getString("brand"));
		car.setType(handler.getString("type"));
		car.setName(handler.getString("name"));
		car.setModel(handler.getString("model"));
		car.setGear(handler.getString("gear"));
		car.setSeat(handler.getString("seat"));
		car.setColor(handler.getString("color"));
		car.setFuel(handler.getString("fule"));
		car.setMilage(handler.getString("milage"));
		car.setOwner(handler.getString("owner"));
		car.setCc(handler.getString("cc"));
		car.setPrice(handler.getString("price"));
		car.setCarEntry(handler.getString("entry"));
		car.setUser(handler.getString("user"));
		car.setUsage(handler.getString("usage"));
		car.setAddress(handler.getString("address"));
		car.setYear(handler.getString("year"));
		car.setImageUrl("http://localhost:8080/carshop/Jserv/cars/media/"
				+ car.getId());
		return car;
	}

	@Override
	public DBCollection getCarDetailsCollection() throws UnknownHostException {
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB mongoDB = mongo.getDB("carshop");
		return mongoDB.getCollection("car_details");
	}

	public DBCollection getCarMediaDetailsCollection()
			throws UnknownHostException {
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB mongoDB = mongo.getDB("carshop");
		return mongoDB.getCollection("media");
	}

	@Override
	public CarModel addUsedCarDetails(CarModel carModel)
			throws UnknownHostException {
		DBCollection collection = getCarDetailsCollection();
		BasicDBObject dummy = new BasicDBObject();
		dummy.put("user", carModel.getUser());
		int count = collection.find(dummy).count();
		carModel.setCarEntry("" + (count + 1));
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
	public Boolean addMedia(CarModel carModel, InputStream fis,
			FormDataContentDisposition fi) throws UnknownHostException {
		MongoClient mongo = new MongoClient("localhost", 27017);
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
		File file = new File(id + ".jpg");
		gridFile.writeTo(file);
		return file;
	}

	@Override
	public ResponseWithCarCollection fetchAllCars() throws UnknownHostException {
		DBCollection collection = getCarDetailsCollection();
		ResponseWithCarCollection response = new ResponseWithCarCollection();
		int count = collection.find().count();
		if (count > 15) {
			count = 15;
		}
		if (count != 0) {
			CarModel[] cars = new CarModel[count];
			DBCursor cursor = collection.find()
					.sort(new BasicDBObject("_id", -1)).limit(15);
			int i = 0;
			while (cursor.hasNext()) {
				BasicDBObject handler = (BasicDBObject) cursor.next();
				cars[i] = new CarModel();
				cars[i] = allDataSetter(handler);
				cars[i].setVideo("http://localhost:8080/carshop/Jserv/cars/video/"
						+ cars[i].getId());
				i++;
			}
			response.status = "success";
			response.cars = cars;
			response.rows = count;
			return response;
		} else {
			response.status = "failure";
			return response;
		}
	}

	@Override
	public ResponseWithCarData getCarDetail(String id)
			throws UnknownHostException {
		DBCollection collection = getCarDetailsCollection();
		BasicDBObject obj = new BasicDBObject();
		ResponseWithCarData response = new ResponseWithCarData();
		CarModel carModel = new CarModel();
		obj.put("_id", new ObjectId(id));
		DBCursor cursor = collection.find(obj);
		if (cursor.hasNext()) {
			BasicDBObject handler = (BasicDBObject) cursor.next();
			carModel = allDataSetter(handler);
			response.status = "success";
			carModel.setVideo("http://localhost:8080/carshop/Jserv/cars/video/"
					+ carModel.getId());
			response.car = carModel;
		} else {
			response.status = "failed";
		}
		return response;
	}

	@Override
	public ResponseWithCarCollection searchInStrings(String term)
			throws UnknownHostException {
		DBCollection collection = getCarDetailsCollection();
		ResponseWithCarCollection response = new ResponseWithCarCollection();
		BasicDBObject search = new BasicDBObject();
		List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
		obj.add(new BasicDBObject("model", java.util.regex.Pattern
				.compile(term)));
		obj.add(new BasicDBObject("name", java.util.regex.Pattern.compile(term)));
		obj.add(new BasicDBObject("brand", java.util.regex.Pattern
				.compile(term)));
		search.put("$or", obj);
		int count = collection.find(search).count();
		if (count != 0) {
			CarModel[] cars = new CarModel[count];
			DBCursor cursor = collection.find(search);
			int i = 0;
			while (cursor.hasNext()) {
				BasicDBObject handler = (BasicDBObject) cursor.next();
				cars[i] = new CarModel();
				cars[i] = allDataSetter(handler);
				cars[i].setVideo("http://localhost:8080/carshop/Jserv/cars/video/"
						+ cars[i].getId());
				i++;
			}
			response.status = "success";
			response.cars = cars;
			response.rows = count;
			return response;
		} else {
			response.status = "failure";
			return response;
		}
	}

	@Override
	public ResponseWithCarCollection getAllUserCars(String id)
			throws UnknownHostException {
		DBCollection collection = getCarDetailsCollection();
		ResponseWithCarCollection cars = new ResponseWithCarCollection();
		BasicDBObject search = new BasicDBObject();
		search.put("user", id);
		int count = collection.find(search).count();
		DBCursor cursor = collection.find(search).sort(
				new BasicDBObject("_id", -1));
		int i = 0;
		if (cursor.hasNext()) {
			CarModel carModel[] = new CarModel[count];
			while (cursor.hasNext()) {
				carModel[i] = new CarModel();
				BasicDBObject handler = (BasicDBObject) cursor.next();
				carModel[i] = allDataSetter(handler);
				i++;

			}
			cars.status = "success";
			cars.cars = carModel;
		} else {
			cars.status = "failed";
		}
		return cars;
	}

	@Override
	public String removeCar(String id) throws UnknownHostException {
		DBCollection collection = getCarDetailsCollection();
		BasicDBObject search = new BasicDBObject();
		search.put("_id", new ObjectId(id));
		collection.remove(search);
		search.clear();
		DBCollection media = getCarMediaDetailsCollection();
		search.put("_id", id);
		media.remove(search);
		return "success";
	}

	public String addReview(String carId, String userId, String review) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addVideo(InputStream videoInputStream,
			FormDataContentDisposition videoInputDetails, String id)
			throws UnknownHostException {
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB mongoDB = mongo.getDB("carshop");
		GridFS fileStore = new GridFS(mongoDB, "video");
		GridFSInputFile inputFile = fileStore.createFile(videoInputStream);
		inputFile.setId(id);
		inputFile.setFilename(videoInputDetails.getFileName());
		inputFile.save();
		return true;
	}

	@Override
	public File getVideo(String id) throws IOException {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		DB mongoDB = mongoClient.getDB("carshop");
		BasicDBObject query = new BasicDBObject();
		query.put("_id", id);
		GridFS fileStore = new GridFS(mongoDB, "video");
		GridFSDBFile gridFile = fileStore.findOne(query);
		File file = new File(id + ".wmv");
		gridFile.writeTo(file);
		return file;
	}

}
