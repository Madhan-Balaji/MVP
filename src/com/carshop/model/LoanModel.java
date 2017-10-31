package com.carshop.model;

public class LoanModel {
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getiFrom() {
		return iFrom;
	}
	public void setiFrom(String iFrom) {
		this.iFrom = iFrom;
	}
	public String getiTo() {
		return iTo;
	}
	public void setiTo(String iTo) {
		this.iTo = iTo;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public String getAmt() {
		return amt;
	}
	public void setAmt(String amt) {
		this.amt = amt;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	private String brand;
	private String bank;
	private String iFrom;
	private String iTo;
	private String fee;
	private String amt;
	private String time;
}
