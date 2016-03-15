package com.sudhir.test.spring.jdbc.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.sudhir.test.spring.jdbc.bean.Splitter;

public class SplitterRowMapper implements ParameterizedRowMapper<Splitter> {

	@Override
	public Splitter mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Splitter splitter=new Splitter(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
		return splitter;
		
	}

}
