package com.carshop.model;

public class NewsModel {
	private String id;
	private String heading;
	private String content;
	private String image;
	private String NewsBy;
	public String getHeading() {
		return heading;
	}
	public void setHeading(String heading) {
		this.heading = heading;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getNewsBy() {
		return NewsBy;
	}
	public void setNewsBy(String newsBy) {
		NewsBy = newsBy;
	}
}
