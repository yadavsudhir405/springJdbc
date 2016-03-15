package com.sudhir.test.spring.jdbcDAOImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.sudhir.test.spring.jdbc.DAO.SplitterDAO;
import com.sudhir.test.spring.jdbc.bean.Splitter;

public class SplitterDAOImpl implements SplitterDAO {
	private static final String SQL_INSERT_SPLITTER = "insert into splitter(\"userName\",password,\"fullName\") values (?,?,?)";
	private static final String SQL_QUERY_SPLITTER = "select * from splitter where \"userName\"=?";
	private static final String SQL_UPDATE_SPLITTER = "update splitter set \"fullName\"=? where \"userName\"=?";
	private static final String SQL_ALL_SPLITTER = "select * from splitter where \"fullName\"=?";
	private static final String SQL_COUNT = "select count(*) from splitter where \"fullName\"=?";
	private static final String SQL_DELETE_SPLITTER="delete  from splitter where \"userName\"=?";
	private JdbcTemplate jdbcTemplate;

	@Override
	public void addSplitter(Splitter splitter) {
		jdbcTemplate.update(SQL_INSERT_SPLITTER, splitter.getUserName(), splitter.getPassword(), splitter.getPassword());

	}

	@Override
	public Splitter getSplitter(String userName) {
		return jdbcTemplate.queryForObject(SQL_QUERY_SPLITTER, new Object[] { userName }, new ParameterizedRowMapper<Splitter>() {

			@Override
			public Splitter mapRow(ResultSet result, int arg1) throws SQLException {
				Splitter splitter = new Splitter();
				splitter.setUserName(result.getString(1));
				splitter.setPassword(result.getString(2));
				splitter.setFullName(result.getString(3));
				return splitter;
			}
		});
	}

	public void setJdbcTemplate(JdbcTemplate simpleJdbcTemplate) {
		this.jdbcTemplate = simpleJdbcTemplate;
	}

	@Override
	public void updateSplitter(Splitter splitter) {
		//jdbcTemplate.update(SQL_UPDATE_SPLITTER, new Object[] { splitter.getUserName(), splitter.getFullName() });
		jdbcTemplate.update(SQL_UPDATE_SPLITTER,splitter.getFullName(),splitter.getUserName());
	}

	@Override
	public int getSplitterCount(String fullName) {

		return jdbcTemplate.queryForObject(SQL_COUNT, new Object[] { fullName }, Integer.class);

	}

	@Override
	public List<Splitter> getAllSpliter(String fullName) {
		return jdbcTemplate.query(SQL_ALL_SPLITTER, new ParameterizedRowMapper<Splitter>() {
			@Override
			public Splitter mapRow(ResultSet result, int arg1) throws SQLException {
				Splitter splitter = new Splitter();
				splitter.setUserName(result.getString(1));
				splitter.setPassword(result.getString(2));
				splitter.setFullName(result.getString(3));
				return splitter;
			}
		},fullName);

	}

	@Override
	public void removeSplitter(Splitter splitter) {
		jdbcTemplate.update(SQL_DELETE_SPLITTER, splitter.getUserName());
		
	}
}