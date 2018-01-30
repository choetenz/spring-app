package com.tenzin.app;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.tenzin.app.data.CustomerData;

@XmlRootElement
public class CustomerSearchResults {

	private List<CustomerData> customers;
	
	public CustomerSearchResults() {}
	
	public CustomerSearchResults(List<CustomerData> customers) {
		this.customers = customers;
	}

	public List<CustomerData> getCustomers() {
		return customers;
	}

	public void setCustomers(List<CustomerData> customers) {
		this.customers = customers;
	}

}
