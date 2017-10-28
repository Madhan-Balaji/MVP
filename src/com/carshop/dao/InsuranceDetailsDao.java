package com.carshop.dao;

import java.net.UnknownHostException;

import com.carshop.model.InsuranceModel;
import com.carshop.model.ResponseWithInsurance;
import com.carshop.model.ResponseWithInsuranceCollection;

public interface InsuranceDetailsDao {
	public String setInsuranceDetails(InsuranceModel insurance) throws UnknownHostException;

	public ResponseWithInsuranceCollection get8Insurance() throws UnknownHostException;

	public ResponseWithInsurance getData(String id) throws UnknownHostException;
}
