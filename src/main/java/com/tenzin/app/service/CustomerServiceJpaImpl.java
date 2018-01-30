package com.tenzin.app.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.tenzin.app.data.CustomerData;
import com.tenzin.app.entity.CustomerEntity;
import com.tenzin.app.entity.QueryConstant;
import com.tenzin.app.exception.InvalidDataException;
import com.tenzin.app.exception.NoRecordFoundException;

@Service("csJpaImpl")
@Transactional
public class CustomerServiceJpaImpl implements CustomerService {
	
	private final static Logger logger = LoggerFactory.getLogger(CustomerServiceJpaImpl.class.getName());
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private CustomerMapper mapper;

	@Override
	public CustomerData createCustomer(final CustomerData customer) {
		logger.debug("Entering CustomerServiceJpaImpl.createCustomer");
		logger.debug("Customer Data inside service = {}", customer);
		
		if(customer == null || StringUtils.isEmpty(customer.getFirstName()) || StringUtils.isEmpty(customer.getLastName())) {
			throw new InvalidDataException("Invalid data to persist");
		}
		
		CustomerEntity entity = mapper.mapToCustomerEntity(customer);
		
		logger.debug("Start saving customer");
		
		em.persist(entity);
		customer.setId(entity.getPk());
		
		logger.debug("Customer saved success. Customer ID = {} ", entity.getPk());
		logger.debug("Exiting CustomerServiceSpringData Impl.createCustomer");
		
		return customer;
	}

	@Override
	public void modifyCustomer(CustomerData customer) {
		CustomerEntity entity = em.find(CustomerEntity.class, customer.getId());
		entity = mapper.mapToCustomerEntity(entity, customer);
		em.persist(entity);
		
	}

	@Override
	public CustomerData findCustomer(Long id) {
		final CustomerEntity entity = em.find(CustomerEntity.class, id);
		if(entity == null) {
			throw new NoRecordFoundException("The customer with id " + id + " is not found.");
		}
		final CustomerData result = mapper.mapToCustomerData(entity);
		return result;
	}
	
	@Override
	public void removeCustomer(Long id) {
		final CustomerEntity entity = em.find(CustomerEntity.class, id);
		em.remove(entity);
		
	}

	@Override
	public List<CustomerData> searchCustomer(String name) {
		String search = name + "%";
		final TypedQuery<CustomerEntity> query = em.createNamedQuery(QueryConstant.CUSTOMER_SEARCH, CustomerEntity.class);
		query.setParameter("searchStr", search);
		final List<CustomerEntity> results = query.getResultList();
		return mapper.mapToCustomerDataList(results);
	}
	

	@Override
	public List<CustomerData> getCustomers() {
		TypedQuery<CustomerEntity> query = em.createQuery("select c from CustomerEntity c", CustomerEntity.class);
		final List<CustomerEntity> results = query.getResultList();
		return mapper.mapToCustomerDataList(results);
	}

	@Override
	public List<CustomerData> searchCustomer(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	
		
	
	
}
