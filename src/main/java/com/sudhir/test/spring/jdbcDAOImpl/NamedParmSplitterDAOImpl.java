package com.sudhir.test.spring.jdbcDAOImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.sudhir.test.spring.jdbc.DAO.SplitterDAO;
import com.sudhir.test.spring.jdbc.bean.Splitter;
import com.sudhir.test.spring.jdbc.rowMapper.SplitterRowMapper;

public class NamedParmSplitterDAOImpl implements SplitterDAO {
	private static final String SQL_INSERT = "insert into splitter values(:temp,:password,:fullName)";
	private static final String SQL_QUERY_SPLITTER = "select * from splitter where \"userName\"=:user_Name";
	private static final String SQL_QUERY_UPDATE = "update splitter set \"fullName\"=:fullName where \"userName\"=:userName";
	private static final String SQL_QUERY_ALL = "select * from splitter where \"fullName\"=:fullName";
	private static final String SQL_COUNT = "select count(*) from splitter where \"fullName\"=:fullName";
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public void addSplitter(Splitter splitter) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("temp", splitter.getUserName());
		map.put("password", splitter.getPassword());
		map.put("fullName", splitter.getFullName());
		namedParameterJdbcTemplate.update(SQL_INSERT, map);

	}

	@Override
	public Splitter getSplitter(String userName) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("user_Name", userName);
		return namedParameterJdbcTemplate.queryForObject(SQL_QUERY_SPLITTER, map, new SplitterRowMapper());

	}

	@Override
	public void updateSplitter(Splitter splitter) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("fullName", splitter.getFullName());
		map.put("userName", splitter.getUserName());
		namedParameterJdbcTemplate.update(SQL_QUERY_UPDATE, map);

	}

	@Override
	public List<Splitter> getAllSpliter(String fullName) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("fullName", fullName);
		return namedParameterJdbcTemplate.query(SQL_QUERY_ALL, map, new SplitterRowMapper());
	}

	@Override
	public int getSplitterCount(String fullName) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("fullName", fullName);
		return namedParameterJdbcTemplate.queryForObject(SQL_COUNT, map, Integer.class);
	}

	@Override
	public void removeSplitter(Splitter splitter) {
		// TODO Auto-generated method stub

	}

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

}
