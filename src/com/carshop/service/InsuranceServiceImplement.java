package com.carshop.service;

import java.net.UnknownHostException;

import com.carshop.dao.InsuranceDetailsDao;
import com.carshop.dao.InsuranceDetailsDaoImplement;
import com.carshop.model.InsuranceModel;

public class InsuranceServiceImplement implements InsuranceService {
	UserService userService = new UserServiceImplement();
	InsuranceDetailsDao insuranceDetails = new InsuranceDetailsDaoImplement(); 
	
	@Override
	public String addNewInsurance(InsuranceModel insuranceModel) throws UnknownHostException {
		String company = userService.getCompany(insuranceModel.getPostby());
		insuranceModel.setCompany(company);
		return insuranceDetails.setInsuranceDetails(insuranceModel);
	}

}
