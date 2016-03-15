package com.sudhir.test.spring.components;

import org.springframework.stereotype.Component;

@Component
public class CustomerDAOComponent {
	@Override
	public String toString() {
		return "This is CustomerDAOComponent";
	}

	public String getCustomerDao(String customerName) {
		return "Hello" + customerName;
	}
}
