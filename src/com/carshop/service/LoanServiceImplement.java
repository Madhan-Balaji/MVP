package com.carshop.service;

import java.net.UnknownHostException;

import com.carshop.dao.LoanDetailsDao;
import com.carshop.dao.LoanDetailsDaoImplement;
import com.carshop.model.LoanModel;
import com.carshop.model.ResponseWithLoanCollection;

public class LoanServiceImplement implements LoanService {
	LoanDetailsDao loanDetails = new LoanDetailsDaoImplement();

	@Override
	public String setLoan(LoanModel loanModel) throws UnknownHostException {
		return loanDetails.saveLoan(loanModel);
	}

	@Override
	public ResponseWithLoanCollection obtainLoans(String brand) throws UnknownHostException {
		return loanDetails.fetchLoans(brand);
	}

}
