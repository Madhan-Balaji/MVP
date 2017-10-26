package com.carshop.dao;

import java.net.UnknownHostException;

import com.carshop.model.InsuranceModel;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class InsuranceDetailsDaoImplement implements InsuranceDetailsDao {
	
	public DBCollection getInsuranceDetailsCollection() throws UnknownHostException{
		MongoClient mongo = new MongoClient("localhost",27017);
		DB mongoDB = mongo.getDB("carshop");
		return mongoDB.getCollection("insurance_details");
	}

	@Override
	public String setInsuranceDetails(InsuranceModel insurance) throws UnknownHostException {
		DBCollection collection = getInsuranceDetailsCollection();
		BasicDBObject document = new BasicDBObject();
		document.append("name", insurance.getName());
		document.append("value", insurance.getVal());
		document.append("prem", insurance.getPrem());
		document.append("zDep", insurance.getzDep());
		document.append("claim", insurance.getClaim());
		document.append("own", insurance.getOwn());
		document.append("owner", insurance.getOwner());
		document.append("lib", insurance.getLib());
		document.append("cd", insurance.getCd());
		document.append("postBy", insurance.getPostby());
		document.append("company", insurance.getCompany());
		collection.insert(document);
		return "success";
	}

}
