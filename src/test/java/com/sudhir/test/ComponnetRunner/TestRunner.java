package com.sudhir.test.ComponnetRunner;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sudhir.test.spring.beans.CustomerService;

public class TestRunner {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = null;
		try {
			applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
			CustomerService customerService = (CustomerService) applicationContext.getBean("customerService");
			System.out.println(customerService);
			DataSource dataSource=(DataSource)applicationContext.getBean("dataSource");
			if(dataSource!=null){
				System.out.println("DataSource is not null,So getting Conenction");
				try {
					Connection con=dataSource.getConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} finally {
			if (applicationContext != null) {
				applicationContext.close();
			}
		}

	}

}
