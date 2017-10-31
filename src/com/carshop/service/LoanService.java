package com.carshop.service;

import java.net.UnknownHostException;

import com.carshop.model.LoanModel;
import com.carshop.model.ResponseWithLoanCollection;

public interface LoanService {
	public String setLoan(LoanModel loanModel) throws UnknownHostException;

	public ResponseWithLoanCollection obtainLoans(String brand) throws UnknownHostException;
}
