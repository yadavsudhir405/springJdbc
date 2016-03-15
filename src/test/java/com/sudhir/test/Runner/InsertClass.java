package com.sudhir.test.Runner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sudhir.test.spring.jdbc.DAO.SplitterDAO;
import com.sudhir.test.spring.jdbc.bean.Splitter;

public class InsertClass {
	private static final String SQL_INSERT_SPLITTER = "insert into splitter(\"userName\",password,\"fullName\") values (?,?,?)";
	private static final String SQL_UPDATE_SPLITTER = "update splitter set password=? where \"userName\"=?";
	private static final ApplicationContext applicationContext;
	private static DataSource dataSource;

	static {
		applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
		dataSource = (DataSource) applicationContext.getBean("dataSource");
	}

	public static void main(String[] args) {
		Splitter splitter = new Splitter("sudhiris056", "password", "Sudhirasdf");
		SplitterDAO splitterDAO = (SplitterDAO) applicationContext.getBean("splitterDAO");

		System.out.println("Splitter :: " + splitterDAO.getSplitterCount("Sudhir"));

		List<Splitter> list = splitterDAO.getAllSpliter("Sudhir");
		for (Splitter s : list) {
			System.out.println(s);
		}

		System.out.println("Executing Update Query");
		splitterDAO.updateSplitter(splitter);

		splitterDAO.removeSplitter(splitter);
	}

	private void add(Splitter splitter) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(SQL_INSERT_SPLITTER);
			statement.setString(1, splitter.getUserName());
			statement.setString(2, splitter.getPassword());
			statement.setString(3, splitter.getFullName());
			statement.execute();
			System.out.println("Splitter has been added");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {

			}
		}
	}

	private void saveUpdater(Splitter splitter) {
		if (splitter == null) {
			throw new IllegalArgumentException("Splitter Object is null");
		}
		Connection connection = null;
		PreparedStatement preparedStatment = null;
		try {
			connection = dataSource.getConnection();
			preparedStatment = connection.prepareStatement(SQL_UPDATE_SPLITTER);
			preparedStatment.setString(1, splitter.getPassword());
			preparedStatment.setString(2, splitter.getUserName());
			preparedStatment.execute();
			System.out.println("Password has been changed");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatment != null) {
					preparedStatment.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
