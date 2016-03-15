package com.sudhir.test.spring.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerDaoService {

	@Autowired
	private CustomerDAOComponent customerDAOComponent;

	@Override
	public String toString() {
		return "This is CustomerDaoService[" + customerDAOComponent + "]";
	}

	public void display() {
		System.out.println(customerDAOComponent.getCustomerDao("Whatsppp"));
	}
}
