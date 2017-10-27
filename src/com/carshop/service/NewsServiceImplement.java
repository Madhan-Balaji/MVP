package com.carshop.service;

import java.io.InputStream;
import java.net.UnknownHostException;

import com.carshop.dao.NewsDetailsDao;
import com.carshop.dao.NewsDetailsDaoImplement;
import com.carshop.model.NewsModel;
import com.carshop.model.ResponseWithNewsCollection;
import com.carshop.model.ResponseWithNewsData;
import com.sun.jersey.core.header.FormDataContentDisposition;

public class NewsServiceImplement implements NewsService {
	NewsDetailsDao newsDetails = new NewsDetailsDaoImplement();
	@Override
	public String addNews(NewsModel newsModel, InputStream file,
			FormDataContentDisposition fis) throws UnknownHostException {
		String id = newsDetails.insertNew(newsModel);
		newsModel.setId(id);
		return newsDetails.addMedia(newsModel, file, fis);
	}
	@Override
	public ResponseWithNewsCollection getSomeNews() throws UnknownHostException {
		
		return newsDetails.get8News();
	}
	@Override
	public ResponseWithNewsData getNews(String id) throws UnknownHostException {
		return newsDetails.getData(id);
	}

}
