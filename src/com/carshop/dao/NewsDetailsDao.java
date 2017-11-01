package com.carshop.dao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;

import com.carshop.model.NewsModel;
import com.carshop.model.ResponseWithNewsCollection;
import com.carshop.model.ResponseWithNewsData;
import com.sun.jersey.core.header.FormDataContentDisposition;

public interface NewsDetailsDao {
	public String insertNew(NewsModel newsModel) throws UnknownHostException;

	public String addMedia(NewsModel carModel, InputStream fis,
			FormDataContentDisposition fi) throws UnknownHostException;

	public File getMedia(String id) throws IOException;

	public ResponseWithNewsCollection get8News() throws UnknownHostException;

	public ResponseWithNewsData getData(String id) throws UnknownHostException;

	public ResponseWithNewsCollection fetchAllNews() throws UnknownHostException;

	public String removeNewsDetails(String id) throws UnknownHostException;
}
