package com.sudhir.test.spring.jdbc.bean;
/**
 * 
 * @author sudhir
 *
 */
public class Splitter {
	private String userName;
	private String password;
	private String fullName;
	
	public Splitter(){
		
	}
	public Splitter(String userName,String password,String fullName){
		this.userName=userName;
		this.password=password;
		this.fullName=fullName;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "userName: " + userName + " password: " + password + " fullName: " + fullName;
	}

	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof Splitter)){
			return false;
		}
		Splitter splitter=(Splitter)obj;
		return (userName==splitter.userName||(userName!=null && userName.equals(splitter.userName)))&&(password==splitter.password||(password!=null&&password.equals(splitter.password)))
				&&(fullName==splitter.fullName||(fullName!=null&&fullName.equals(splitter.fullName)));
	}
}
