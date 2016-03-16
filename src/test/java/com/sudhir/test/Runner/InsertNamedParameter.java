package com.sudhir.test.Runner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sudhir.test.spring.jdbc.DAO.SplitterDAO;
import com.sudhir.test.spring.jdbc.bean.Splitter;

public class InsertNamedParameter {
	private static final ApplicationContext applicationContext;


	static {
		applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
		
	}

	public static void main(String[] args) {
		SplitterDAO namedSplitterDAO=(SplitterDAO)applicationContext.getBean("namedParmSplitterDAO");
		Splitter splitter=new Splitter("sudhiris062","password","test12345");
		//namedSplitterDAO.addSplitter(splitter);
		System.out.println("Splitter Added");
		System.out.println(namedSplitterDAO.getSplitter("sudhiris057"));
		namedSplitterDAO.updateSplitter(splitter);
		System.out.println("List size:::"+namedSplitterDAO.getAllSpliter("test123"));
		System.out.println("Number of people:::"+namedSplitterDAO.getSplitterCount("test123"));
		
		Splitter splitter1=new Splitter("sudhiris063", "password", "test1234");
		SplitterDAO splitterDAO=(SplitterDAO)applicationContext.getBean("namedParamTemplateSupport");
		splitterDAO.addSplitter(splitter1);
	}

}
