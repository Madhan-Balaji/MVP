package com.carshop.controller;

import java.net.UnknownHostException;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import com.carshop.model.InsuranceModel;
import com.carshop.model.ResponseWithInsurance;
import com.carshop.model.ResponseWithInsuranceCollection;
import com.carshop.service.InsuranceService;
import com.carshop.service.InsuranceServiceImplement;

@Path("/insurance")
public class InsuranceController {
	@POST
	@Path("/saveNewInsurance")
	@Produces("application/json")
	public String addInsurance(@FormParam("name") String name,
			@FormParam("val") String val, @FormParam("prem") String prem,
			@FormParam("zDep") String zDep, @FormParam("claim") String claim,
			@FormParam("own") String own, @FormParam("owner") String owner,
			@FormParam("lib") String lib, @FormParam("cd") String cd,
			@FormParam("postBy") String postBy) throws UnknownHostException {
		InsuranceService InsuranceService = new InsuranceServiceImplement();
		InsuranceModel insuranceModel = new InsuranceModel();
		insuranceModel.setPostby(postBy);
		insuranceModel.setName(name);
		insuranceModel.setVal(val);
		insuranceModel.setPrem(prem);
		insuranceModel.setzDep(zDep);
		insuranceModel.setClaim(claim);
		insuranceModel.setOwn(own);
		insuranceModel.setOwner(owner);
		insuranceModel.setLib(lib);
		insuranceModel.setCd(cd);
		return InsuranceService.addNewInsurance(insuranceModel);
	}
	
	@GET
	@Path("/getInsurances")
	@Produces("application/json")
	public ResponseWithInsuranceCollection getInsurances()
			throws UnknownHostException {
		InsuranceService insuranceService = new InsuranceServiceImplement();
		return insuranceService.getSomeInsurances();
	}

	@POST
	@Path("/getInsu")
	@Produces("application/json")
	public ResponseWithInsurance getInsurance(@FormParam("id") String id)
			throws UnknownHostException {
		InsuranceService insuranceService = new InsuranceServiceImplement();
		return insuranceService.getInsu(id);
	}
	
	@GET
	@Path("/getAllInsurance")
	@Produces("application/json")
	public ResponseWithInsuranceCollection getAllInsurance(
			@QueryParam("user") String user) throws UnknownHostException {
		InsuranceService insuranceService = new InsuranceServiceImplement();
		return insuranceService.getAllInsurance(user);
	}

	@POST
	@Path("/removeInsurance")
	@Produces("application/json")
	public String removeInsurance(@FormParam("id") String id)
			throws UnknownHostException {
		InsuranceService insuranceService = new InsuranceServiceImplement();
		return insuranceService.removeInsurance(id);
	}
}
