package com.carshop.dao;

import java.net.UnknownHostException;

import org.bson.types.ObjectId;

import com.carshop.model.InsuranceModel;
import com.carshop.model.ResponseWithInsurance;
import com.carshop.model.ResponseWithInsuranceCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class InsuranceDetailsDaoImplement implements InsuranceDetailsDao {
	
	public DBCollection getInsuranceDetailsCollection() throws UnknownHostException{
		MongoClient mongo = new MongoClient("localhost",27017);
		DB mongoDB = mongo.getDB("carshop");
		return mongoDB.getCollection("insurance_details");
	}
	
	public InsuranceModel allDataSetter(BasicDBObject handler){
		InsuranceModel insurance = new InsuranceModel();
		insurance.setName(handler.getString("name"));
		insurance.setVal(handler.getString("value"));
		insurance.setId(handler.getString("_id"));
		insurance.setCd(handler.getString("cd"));
		insurance.setClaim(handler.getString("claim"));;
		insurance.setCompany(handler.getString("company"));
		insurance.setLib(handler.getString("lib"));
		insurance.setOwn(handler.getString("own"));
		insurance.setOwner(handler.getString("owner"));
		insurance.setPostby(handler.getString("postBy"));
		insurance.setPrem(handler.getString("prem"));
		insurance.setzDep(handler.getString("zDep"));
		return insurance;
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

	@Override
	public ResponseWithInsuranceCollection get8Insurance() throws UnknownHostException {
		DBCollection collection = getInsuranceDetailsCollection();
		ResponseWithInsuranceCollection response = new ResponseWithInsuranceCollection();
		int count = collection.find().count();
		if(count>8){
			count=8;
		}
		if(count != 0){
			InsuranceModel[] insurance = new InsuranceModel[count]; 
			DBCursor cursor = collection.find().limit(8);
			int i = 0;
			while(cursor.hasNext()){
				BasicDBObject handler = (BasicDBObject) cursor.next();
				insurance[i] = new InsuranceModel();
				insurance[i] = allDataSetter(handler);
				i++;
			}
			response.status="success";
			response.insurances = insurance;
			return response;
		}
		else{
			response.status="failure";
			return response;
		}
		
	}

	@Override
	public ResponseWithInsurance getData(String id) throws UnknownHostException {
		DBCollection collection = getInsuranceDetailsCollection();
		ResponseWithInsurance response = new ResponseWithInsurance();
		InsuranceModel insuranceModel = new InsuranceModel();
		BasicDBObject search = new BasicDBObject();
		search.put("_id", new ObjectId(id));
		DBCursor cursor = collection.find(search);
		if(cursor.hasNext()){
			BasicDBObject handler = (BasicDBObject) cursor.next();
			insuranceModel = allDataSetter(handler);
			response.status = "success";
			response.insurance = insuranceModel;
		}
		else{
			response.status="failed";
		}
		return response;
	}

}
