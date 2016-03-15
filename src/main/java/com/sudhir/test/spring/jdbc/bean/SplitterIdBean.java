package com.sudhir.test.spring.jdbc.bean;

public class SplitterIdBean {
	private final int id;
	private String name;
	private String addres;

	public SplitterIdBean(int id, String name, String address) {
		this.id = id;
		this.name = name;
		this.addres = address;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return addres;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.addres = address;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof SplitterIdBean)) {
			return false;
		}
		SplitterIdBean bean = (SplitterIdBean) obj;
		return id == bean.id && (name == bean.name || (name != null && name.equals(bean.name))) && (addres == bean.addres || (addres != null && addres.equals(bean.addres)));

	}
	@Override
	public int hashCode(){
		int result=17;
		result=31*result+id;
		return result;
	}
}
