package com.carshop.service;

import java.net.UnknownHostException;

import com.carshop.dao.InsuranceDetailsDao;
import com.carshop.dao.InsuranceDetailsDaoImplement;
import com.carshop.model.InsuranceModel;
import com.carshop.model.ResponseWithInsurance;
import com.carshop.model.ResponseWithInsuranceCollection;

public class InsuranceServiceImplement implements InsuranceService {
	UserService userService = new UserServiceImplement();
	InsuranceDetailsDao insuranceDetails = new InsuranceDetailsDaoImplement(); 
	
	@Override
	public String addNewInsurance(InsuranceModel insuranceModel) throws UnknownHostException {
		String company = userService.getCompany(insuranceModel.getPostby());
		insuranceModel.setCompany(company);
		return insuranceDetails.setInsuranceDetails(insuranceModel);
	}

	@Override
	public ResponseWithInsuranceCollection getSomeInsurances() throws UnknownHostException {		
		return insuranceDetails.get8Insurance();
	}

	@Override
	public ResponseWithInsurance getInsu(String id) throws UnknownHostException {
		return insuranceDetails.getData(id);
	}

	@Override
	public ResponseWithInsuranceCollection getAllInsurance(String uid)
			throws UnknownHostException {
		return insuranceDetails.getAllInsurance(uid);
	}

	@Override
	public String removeInsurance(String id) throws UnknownHostException {
		return insuranceDetails.removeInsurance(id);
	}

}
