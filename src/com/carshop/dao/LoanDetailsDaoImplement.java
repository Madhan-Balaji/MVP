package com.carshop.dao;

import java.net.UnknownHostException;

import com.carshop.model.LoanModel;
import com.carshop.model.ResponseWithLoanCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class LoanDetailsDaoImplement implements LoanDetailsDao {

	public DBCollection getLoanCollectionDetails() throws UnknownHostException {
		MongoClient mongo = new MongoClient("localhost",27017);
		DB mongoDB = mongo.getDB("carshop");
		return mongoDB.getCollection("loan_details");
	}
	
	public LoanModel allDataSetter(BasicDBObject handler) {
		LoanModel loan = new LoanModel();
		loan.setAmt(handler.getString("amt"));
		loan.setBank(handler.getString("bank"));
		loan.setBrand(handler.getString("brand"));
		loan.setFee(handler.getString("fee"));
		loan.setId(handler.getString("_id"));
		loan.setiFrom(handler.getString("iFrom"));
		loan.setiTo(handler.getString("iTo"));
		loan.setTime(handler.getString("time"));
		return loan;
	}
	
	@Override
	public String saveLoan(LoanModel loanModel) throws UnknownHostException {
		DBCollection collection = getLoanCollectionDetails();
		BasicDBObject document = new BasicDBObject();
		document.append("brand", loanModel.getBrand());
		document.append("bank", loanModel.getBank());
		document.append("iFrom", loanModel.getiFrom());
		document.append("iTo", loanModel.getiTo());
		document.append("fee", loanModel.getFee());
		document.append("amt", loanModel.getAmt());
		document.append("time", loanModel.getTime());
		collection.insert(document);
		return "success";
	}
	@Override
	public ResponseWithLoanCollection fetchLoans(String brand) throws UnknownHostException {
		DBCollection collection = getLoanCollectionDetails();
		BasicDBObject search = new BasicDBObject();
		ResponseWithLoanCollection loan = new ResponseWithLoanCollection();
		search.put("brand", brand);
		int count = collection.find(search).count();
		LoanModel[] loans = new LoanModel[count];
		if(count>0) {
			DBCursor cursor = collection.find(search);
			int i=0;
			while(cursor.hasNext()) {
				BasicDBObject handler = (BasicDBObject) cursor.next();
				loans[i] = new LoanModel();
				loans[i] = allDataSetter(handler);
				i++;
			}
			loan.status = "success";
			loan.loans = loans;
		}
		else {
			loan.status = "failed";
		}
		return loan;
	}

}
