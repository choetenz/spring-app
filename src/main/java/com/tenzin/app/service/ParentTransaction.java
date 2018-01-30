package com.tenzin.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tenzin.app.entity.CustomerEntity;
import com.tenzin.app.repo.CustomerRepo;

@Service("pt")
@Transactional(propagation = Propagation.SUPPORTS)
public class ParentTransaction implements CustomerTransaction{
	
	@Autowired
	private CustomerRepo repo;
	
	@Autowired
	@Qualifier("ctTransaction")
	private ChildTransaction child;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void createCustomer() {
		save("Parent", "Transaction");
		child.createCustomer();
		//throw new RuntimeException("roll back");
	}
	
	private void save(final String firstName, final String lastName) {
		CustomerEntity entity = new CustomerEntity();
		entity.setFirstName(firstName);
		entity.setLastName(lastName);
		repo.save(entity);
	}
	

}
