package com.carshop.controller;

import java.io.InputStream;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.carshop.model.NewsModel;
import com.carshop.model.ResponseWithNewsCollection;
import com.carshop.model.ResponseWithNewsData;
import com.carshop.service.NewsService;
import com.carshop.service.NewsServiceImplement;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/news")
public class NewController {
	@POST
	@Path("/saveNewNews")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces("application/json")
	public String saveNews(@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition fileInputDetails,
			@FormDataParam("head") String head,
			@FormDataParam("content") String content,
			@Context HttpServletRequest req) throws UnknownHostException {

		System.out.println(" File name is :" + fileInputDetails.getFileName());
		HttpSession session = req.getSession();
		NewsModel newsModel = new NewsModel();
		NewsService newService = new NewsServiceImplement();
		newsModel.setHeading(head);
		newsModel.setContent(content);
		newsModel.setNewsBy((String) session.getAttribute("user"));
		return newService.addNews(newsModel, fileInputStream, fileInputDetails);
	}

	@GET
	@Path("/getStarterNews")
	@Produces("application/json")
	public ResponseWithNewsCollection getStarterNews()
			throws UnknownHostException {
		NewsService newsService = new NewsServiceImplement();
		return newsService.getSomeNews();
	}

	@POST
	@Path("/getNews")
	@Produces("application/json")
	public ResponseWithNewsData getNews(@FormParam("id") String id)
			throws UnknownHostException {
		NewsService newsService = new NewsServiceImplement();
		return newsService.getNews(id);
	}

	@GET
	@Path("/getAllNews")
	@Produces("application/json")
	public ResponseWithNewsCollection getAllNews() throws UnknownHostException {
		NewsService newsService = new NewsServiceImplement();
		return newsService.obtainAllNews();
	}

	@GET
	@Path("/removeNews")
	@Produces("application/json")
	public String removeNews(@QueryParam("id") String id)
			throws UnknownHostException {
		NewsService newsService = new NewsServiceImplement();
		return newsService.removeLoan(id);
	}
}
