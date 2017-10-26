package com.carshop.service;

import java.net.UnknownHostException;

import com.carshop.model.InsuranceModel;

public interface InsuranceService {
	public String addNewInsurance(InsuranceModel insuranceModel) throws UnknownHostException;
	
}
