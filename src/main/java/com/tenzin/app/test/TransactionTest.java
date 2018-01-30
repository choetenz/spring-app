package com.tenzin.app.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tenzin.app.service.CustomerTransaction;

public class TransactionTest {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("SpringJPA.xml");
		CustomerTransaction pt = context.getBean("pt", CustomerTransaction.class);
		pt.createCustomer();
		
	}

}
