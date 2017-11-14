package com.carshop.dao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;

import org.bson.types.ObjectId;

import com.carshop.model.NewsModel;
import com.carshop.model.ResponseWithNewsCollection;
import com.carshop.model.ResponseWithNewsData;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.sun.jersey.core.header.FormDataContentDisposition;

public class NewsDetailsDaoImplement implements NewsDetailsDao {
	public DBCollection getNewsDetailsCollection() throws UnknownHostException {
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB mongoDB = mongo.getDB("carshop");
		return mongoDB.getCollection("daily_news");
	}

	public NewsModel allDataSetter(BasicDBObject handler) {
		NewsModel newsModel = new NewsModel();
		newsModel.setContent(handler.getString("content"));
		newsModel.setHeading(handler.getString("heading"));
		newsModel.setId(handler.getString("_id"));
		newsModel.setNewsBy(handler.getString("newsBy"));
		newsModel.setImage("http://localhost:8080/carshop/Jserv/control/media/"
				+ newsModel.getId());
		return newsModel;
	}

	@Override
	public String insertNew(NewsModel newsModel) throws UnknownHostException {
		DBCollection collection = getNewsDetailsCollection();
		BasicDBObject doc = new BasicDBObject();
		doc.append("heading", newsModel.getHeading());
		doc.append("content", newsModel.getContent());
		doc.append("newsBy", newsModel.getNewsBy());
		collection.insert(doc);
		String id = doc.getString("_id");
		return id;
	}

	@Override
	public String addMedia(NewsModel carModel, InputStream fis,
			FormDataContentDisposition fi) throws UnknownHostException {
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB mongoDB = mongo.getDB("carshop");
		GridFS fileStore = new GridFS(mongoDB, "media");
		GridFSInputFile inputFile = fileStore.createFile(fis);
		inputFile.setId(carModel.getId());
		inputFile.setFilename(fi.getFileName());
		inputFile.save();
		return "success";
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
	public ResponseWithNewsCollection get8News() throws UnknownHostException {
		DBCollection collection = getNewsDetailsCollection();
		ResponseWithNewsCollection response = new ResponseWithNewsCollection();
		int count = collection.find().count();
		if (count > 8) {
			count = 8;
		}
		if (count != 0) {
			NewsModel[] news = new NewsModel[count];
			DBCursor cursor = collection.find()
					.sort(new BasicDBObject("_id", -1)).limit(8);
			int i = 0;
			while (cursor.hasNext()) {
				BasicDBObject handler = (BasicDBObject) cursor.next();
				news[i] = new NewsModel();
				news[i] = allDataSetter(handler);
				i++;
			}
			response.status = "success";
			response.news = news;
			return response;
		} else {
			response.status = "failure";
			return response;
		}
	}

	@Override
	public ResponseWithNewsData getData(String id) throws UnknownHostException {
		DBCollection collection = getNewsDetailsCollection();
		ResponseWithNewsData response = new ResponseWithNewsData();
		NewsModel newsModel = new NewsModel();
		BasicDBObject search = new BasicDBObject();
		search.put("_id", new ObjectId(id));
		DBCursor cursor = collection.find(search);
		if (cursor.hasNext()) {
			BasicDBObject handler = (BasicDBObject) cursor.next();
			newsModel = allDataSetter(handler);
			response.status = "success";
			response.news = newsModel;
		} else {
			response.status = "failed";
		}
		return response;
	}

	@Override
	public ResponseWithNewsCollection fetchAllNews()
			throws UnknownHostException {
		DBCollection collection = getNewsDetailsCollection();
		ResponseWithNewsCollection response = new ResponseWithNewsCollection();
		int count = collection.find().count();
		NewsModel[] newsModel = new NewsModel[count];
		if (count > 0) {
			int i = 0;
			DBCursor cursor = collection.find();
			while (cursor.hasNext()) {
				BasicDBObject handler = (BasicDBObject) cursor.next();
				newsModel[i] = allDataSetter(handler);
				i++;
			}
			response.status = "success";
			response.news = newsModel;
		} else {
			response.status = "failed";
		}
		return response;
	}

	@Override
	public String removeNewsDetails(String id) throws UnknownHostException {
		DBCollection collection = getNewsDetailsCollection();
		BasicDBObject search = new BasicDBObject();
		search.put("_id", new ObjectId(id));
		DBCursor cursor = collection.find(search);
		if (cursor.hasNext()) {
			collection.remove(search);
			return "success";
		} else {
			return "failed";
		}
	}

}
