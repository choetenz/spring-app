package com.tenzin.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.tenzin.app.data.CustomerData;
import com.tenzin.app.entity.CustomerEntity;
import com.tenzin.app.exception.InvalidDataException;
import com.tenzin.app.exception.NoRecordFoundException;
import com.tenzin.app.repo.CustomerRepo;

@Service("csSpringDataJpaImpl")
@Transactional
public class CustomerServiceDataJpaImpl implements CustomerService {
	
	@Autowired
	private CustomerRepo repo;
	//@PersistenceContext
	//private EntityManager em;
	
	@Autowired
	private CustomerMapper mapper;

	@Override
	public CustomerData createCustomer(final CustomerData customer) {
		
		if(customer == null || StringUtils.isEmpty(customer.getFirstName()) || StringUtils.isEmpty(customer.getLastName())) {
			throw new InvalidDataException("Invalid data to persist");
		}
		
		CustomerEntity entity = mapper.mapToCustomerEntity(customer);
		//em.persist(entity);
		repo.save(entity);
		customer.setId(entity.getPk());
		return customer;
	}

	@Override
	public void modifyCustomer(CustomerData customer) {
		CustomerEntity entity = repo.findOne(customer.getId());//em.find(CustomerEntity.class, customer.getId());
		entity = mapper.mapToCustomerEntity(entity, customer);
		//em.persist(entity);
		repo.save(entity);
		
	}

	@Override
	public CustomerData findCustomer(Long id) {
		final CustomerEntity entity = repo.findOne(id);//em.find(CustomerEntity.class, id);
		if(entity == null) {
			throw new NoRecordFoundException("The customer with id " + id + " is not found.");
		}
		final CustomerData result = mapper.mapToCustomerData(entity);
		return result;
	}
	
	@Override
	public void removeCustomer(Long id) {
		//final CustomerEntity entity = em.find(CustomerEntity.class, id);
		//em.remove(entity);
		repo.delete(id);
		
	}

	@Override
	public List<CustomerData> searchCustomer(String name) {
		final String searchStr = name + "%";
		List<CustomerEntity> entities = repo.searchCustomer(searchStr, searchStr);
		return  mapper.mapToCustomerDataList(entities);
		/*String search = name + "%";
		final TypedQuery<CustomerEntity> query = em.createNamedQuery(QueryConstant.CUSTOMER_SEARCH, CustomerEntity.class);
		query.setParameter("searchStr", search);
		final List<CustomerEntity> results = query.getResultList();
		return mapper.mapToCustomerDataList(results);*/
	}

	@Override
	public List<CustomerData> searchCustomer(final String firstName, final String lastName) {
		final String fName = (firstName == null) ? "%" : firstName ;
		final String lName = (lastName == null) ? "%" : lastName;
		List<CustomerEntity> entities = repo.searchCustomer(firstName, lastName);
		return  mapper.mapToCustomerDataList(entities);
		
		
	}
	
	@Override
	public List<CustomerData> getCustomers() {
		/*TypedQuery<CustomerEntity> query = em.createQuery("select c from CustomerEntity c", CustomerEntity.class);
		final List<CustomerEntity> results = query.getResultList();
		return mapper.mapToCustomerDataList(results);*/
		return null;
	}



	
		
	
	
}
