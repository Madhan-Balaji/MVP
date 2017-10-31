package com.carshop.dao;

import java.net.UnknownHostException;

import com.carshop.model.LoanModel;
import com.carshop.model.ResponseWithLoanCollection;

public interface LoanDetailsDao {
	public String saveLoan(LoanModel loanModel) throws UnknownHostException;

	public ResponseWithLoanCollection fetchLoans(String brand) throws UnknownHostException;
}
