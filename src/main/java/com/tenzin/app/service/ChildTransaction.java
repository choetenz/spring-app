package com.tenzin.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tenzin.app.entity.CustomerEntity;
import com.tenzin.app.repo.CustomerRepo;

@Service("ctTransaction")
@Transactional(propagation = Propagation.SUPPORTS)
public class ChildTransaction implements CustomerTransaction{
	
	@Autowired
	private CustomerRepo repo;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void createCustomer() {
		save("Child", "Transaction");
		//throw new RuntimeException("roll back");
	}
	
	private void save(final String firstName, final String lastName) {
		CustomerEntity entity = new CustomerEntity();
		entity.setFirstName(firstName);
		entity.setLastName(lastName);
		repo.save(entity);
	}
	

}
