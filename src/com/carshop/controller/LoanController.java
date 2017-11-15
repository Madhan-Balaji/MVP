package com.carshop.controller;

import java.net.UnknownHostException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.carshop.model.LoanModel;
import com.carshop.model.ResponseWithLoanCollection;
import com.carshop.service.LoanService;
import com.carshop.service.LoanServiceImplement;
import com.sun.jersey.multipart.FormDataParam;

@Path("/loan")
public class LoanController {
	@Path("/saveNewLoan")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces("application/json")
	public String saveNewLoan(@FormDataParam("brand") String brand,
			@FormDataParam("bank") String bank,
			@FormDataParam("iFrom") String iFrom,
			@FormDataParam("iTo") String iTo, @FormDataParam("fee") String fee,
			@FormDataParam("amount") String amt,
			@FormDataParam("time") String time) throws UnknownHostException {
		LoanModel loanModel = new LoanModel();
		loanModel.setBrand(brand);
		loanModel.setBank(bank);
		loanModel.setiFrom(iFrom);
		loanModel.setiTo(iTo);
		loanModel.setFee(fee);
		loanModel.setAmt(amt);
		loanModel.setTime(time);
		LoanService loanService = new LoanServiceImplement();
		return loanService.setLoan(loanModel);
	}

	@GET
	@Path("/getLoans")
	@Produces("application/json")
	public ResponseWithLoanCollection getLoans(@QueryParam("brand") String brand)
			throws UnknownHostException {
		LoanService loanService = new LoanServiceImplement();
		return loanService.obtainLoans(brand);
	}

	@GET
	@Path("/getAllLoans")
	@Produces("application/json")
	public ResponseWithLoanCollection getAllLoans() throws UnknownHostException {
		LoanService loanService = new LoanServiceImplement();
		return loanService.obtainAllLoans();
	}

	@GET
	@Path("/removeLoan")
	@Produces("application/json")
	public String removeLoan(@QueryParam("id") String id)
			throws UnknownHostException {
		LoanService loanService = new LoanServiceImplement();
		return loanService.removeLoan(id);
	}
}
