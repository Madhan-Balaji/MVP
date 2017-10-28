package com.carshop.service;

import java.net.UnknownHostException;

import com.carshop.model.InsuranceModel;
import com.carshop.model.ResponseWithInsurance;
import com.carshop.model.ResponseWithInsuranceCollection;

public interface InsuranceService {
	public String addNewInsurance(InsuranceModel insuranceModel) throws UnknownHostException;

	public ResponseWithInsuranceCollection getSomeInsurances() throws UnknownHostException;

	public ResponseWithInsurance getInsu(String id) throws UnknownHostException;
	
}
