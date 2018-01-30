package com.tenzin.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tenzin.app.data.CustomerData;
import com.tenzin.app.entity.CustomerEntity;

@Component
public class CustomerMapper {

	public CustomerEntity mapToCustomerEntity(final CustomerData customerData) {
		//CustomerEntity entity = new CustomerEntity();
		return mapToCustomerEntity(new CustomerEntity(), customerData);
	}
	
	public CustomerEntity mapToCustomerEntity(final CustomerEntity entity, final CustomerData customerData) {
		entity.setPk(customerData.getId());
		entity.setFirstName(customerData.getFirstName());
		entity.setLastName(customerData.getLastName());
		entity.setSsn(customerData.getSsn());
		return entity;
	}
	
	public CustomerData mapToCustomerData(final CustomerEntity entity) {
		final CustomerData customerData = new CustomerData();
		customerData.setId(entity.getPk());
		customerData.setFirstName(entity.getFirstName());
		customerData.setLastName(entity.getLastName());
		customerData.setSsn(entity.getSsn());
		return customerData;
	}
	
	public List<CustomerData> mapToCustomerDataList(final List<CustomerEntity> source) {
		final List<CustomerData> results = new ArrayList<CustomerData>();
		for(CustomerEntity entity :  source) {
			results.add(mapToCustomerData(entity));
		}
		return results;
	}
	
}
