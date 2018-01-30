package com.tenzin.app;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.tenzin.app.data.CustomerData;
import com.tenzin.app.service.CustomerService;

@Path("/customer")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class CustomerResource {
	
	private final static Logger logger = LoggerFactory.getLogger(CustomerResource.class.getName());
	
	/*@GET
	public String welcome() {
		return "Welcome to Customer REST WebServices";
	}*/
	
	@Autowired
	@Qualifier("csJpaImpl")// //("csSpringDataJpaImpl") new for SpringDataRepo
	private CustomerService cs;
	
	/*@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCustomer() {
		final CustomerData entity = new CustomerData("Jim", "Smith", "111-222-333");
		return Response.ok().entity(entity).build();
	}*/
	
	@GET
	public Response create() {
		logger.debug("Entering CustomerResource.create");
		logger.debug("Calling Customer Service getCustomers");
		final List<CustomerData> entity  = cs.getCustomers();
		logger.debug("Exiting Customer Get Service");
		return Response.ok().entity(entity).build();
	}
	
/*	@GET
	@Produces({MediaType.APPLICATION_XML})
	public Response search(@QueryParam("searchStr") final String name) {
		final List<CustomerData> customerResult = cs.searchCustomer(name);
		CustomerSearchResults entity = new CustomerSearchResults(customerResult);
		return Response.ok().entity(entity).build();
	}*/
	
	@GET
	@Path("/{custId}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Response create(@PathParam("custId") final Long key) {
		final CustomerData entity = cs.findCustomer(key);
		return Response.ok().entity(entity).build();
	}
	
/*	@GET
	@Path("/searchByMatrix")
	public Response searchByMatrix(@MatrixParam("searchStr") final String name) {
		final List<CustomerData> customerResult = cs.searchCustomer(name);
		CustomerSearchResults entity = new CustomerSearchResults(customerResult);
		return Response.ok().entity(entity).build(); 
	}*/
	
	/*@GET
	@Path("/hdr")
	public Response sampleHdr(@HeaderParam("auth-key") final String authKey, @HeaderParam("app-version") final String appVersion) throws JSONException {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("authKey", authKey);
		jsonObj.put("appVersion", appVersion);
		return Response.ok().entity(jsonObj.toString()).build(); 
	}*/
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response create(final CustomerData customer) {
		//final CustomerData entity = new CustomerData("Jim", "Smith", "111-222-333");
		logger.debug("Entering CustomerResource.create");
		logger.debug("Customer Data = {} ", customer);
		logger.debug("Calling Customer Service");
		
		final CustomerData entity = cs.createCustomer(customer);
		
		logger.debug("Customer Created Successfully");
				
		ResponseBuilder rb = Response.ok();
		if(customer.getFirstName().startsWith("R")) {
			rb = rb.type(MediaType.APPLICATION_XML);
		} else {
			rb = rb.type(MediaType.APPLICATION_JSON);
		}
		
		logger.debug("Exiting CustomerRest.create");
		return rb.entity(entity).build();
	}
	
	@PUT
	public Response modify(final CustomerData customer) {
		//final CustomerData entity = new CustomerData("Jim", "Smith", "111-222-333");
		cs.modifyCustomer(customer);
		return Response.noContent().build();//.ok().entity(entity).build();
	} 
	
	@DELETE
	@Path("/{custId}")
	public Response remove(@PathParam("custId") final Long key) {
		cs.removeCustomer(key);
		return Response.noContent().build();
	}
	
}
