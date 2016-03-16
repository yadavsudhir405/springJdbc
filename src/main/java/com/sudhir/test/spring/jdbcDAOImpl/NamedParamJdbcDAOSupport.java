package com.sudhir.test.spring.jdbcDAOImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import com.sudhir.test.spring.jdbc.DAO.SplitterDAO;
import com.sudhir.test.spring.jdbc.bean.Splitter;

public class NamedParamJdbcDAOSupport extends NamedParameterJdbcDaoSupport implements SplitterDAO {
	private static final String SQL_INSERT = "insert into splitter values(:temp,:password,:fullName)";
	private static final String SQL_QUERY_SPLITTER = "select * from splitter where \"userName\"=:user_Name";
	private static final String SQL_QUERY_UPDATE = "update splitter set \"fullName\"=:fullName where \"userName\"=:userName";
	private static final String SQL_QUERY_ALL = "select * from splitter where \"fullName\"=:fullName";
	private static final String SQL_COUNT = "select count(*) from splitter where \"fullName\"=:fullName";
	@Override
	public void addSplitter(Splitter splitter) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("temp", splitter.getUserName());
		map.put("password", splitter.getPassword());
		map.put("fullName", splitter.getFullName());
		getNamedParameterJdbcTemplate().update(SQL_INSERT,map);
	}

	@Override
	public Splitter getSplitter(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateSplitter(Splitter splitter) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Splitter> getAllSpliter(String fullName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSplitterCount(String fullName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeSplitter(Splitter splitter) {
		// TODO Auto-generated method stub

	}

}
