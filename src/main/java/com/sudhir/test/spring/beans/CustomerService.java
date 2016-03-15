package com.sudhir.test.spring.beans;

public class CustomerService {
	
	private CustomerDAO customerDao;
	public void setCustomerDao(CustomerDAO customerDao){
		this.customerDao=customerDao;
	}
	
	public String toString(){
		return "This is CustomerService["+customerDao+"]";
	}
}
