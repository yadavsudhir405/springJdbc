package com.sudhir.test.ComponnetRunner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sudhir.test.spring.components.CustomerDaoService;

public class ComponentRunner {

	public static void main(String[] args) {
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("application-context.xml");
		CustomerDaoService customerDaoService=(CustomerDaoService)applicationContext.getBean("customerDaoService");
		customerDaoService.display();

	}

}
