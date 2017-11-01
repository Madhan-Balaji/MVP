package com.carshop.service;

import java.io.InputStream;
import java.net.UnknownHostException;

import com.carshop.model.NewsModel;
import com.carshop.model.ResponseWithNewsCollection;
import com.carshop.model.ResponseWithNewsData;
import com.sun.jersey.core.header.FormDataContentDisposition;

public interface NewsService {
	public String addNews(NewsModel newsModel, InputStream file, FormDataContentDisposition fis) throws UnknownHostException;
	public ResponseWithNewsCollection getSomeNews() throws UnknownHostException;
	public ResponseWithNewsData getNews(String id) throws UnknownHostException;
	public ResponseWithNewsCollection obtainAllNews() throws UnknownHostException;
	public String removeLoan(String id) throws UnknownHostException;
}
