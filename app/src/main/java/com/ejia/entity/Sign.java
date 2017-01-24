package com.ejia.entity;

public class Sign extends EJAMessage {

	public int signId; 
	

	public String userPhone;
	
	public int getSignId() {
		return signId;
	}
	public void setSignId(int signId) {
		this.signId = signId;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public Sign(String id,String phone) {
         signId = Integer.parseInt(id);
      
       userPhone  = phone;
	}

}
