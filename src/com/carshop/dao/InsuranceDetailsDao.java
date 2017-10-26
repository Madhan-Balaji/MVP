package com.carshop.dao;

import java.net.UnknownHostException;

import com.carshop.model.InsuranceModel;

public interface InsuranceDetailsDao {
	public String setInsuranceDetails(InsuranceModel insurance) throws UnknownHostException;
}
