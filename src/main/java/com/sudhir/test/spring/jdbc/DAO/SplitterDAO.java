package com.sudhir.test.spring.jdbc.DAO;

import java.util.List;

import com.sudhir.test.spring.jdbc.bean.Splitter;

public interface SplitterDAO {
	public void addSplitter(Splitter splitter);

	public Splitter getSplitter(String userName);
	
	public void updateSplitter(Splitter splitter);
	
	public List<Splitter> getAllSpliter(String fullName);
	
	public int getSplitterCount(String fullName);
	
	public void removeSplitter(Splitter splitter);
}
