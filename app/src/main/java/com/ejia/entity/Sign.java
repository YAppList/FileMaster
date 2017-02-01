package com.ejia.entity;

import java.io.Serializable;

public class Sign extends EJAMessage implements Serializable{

	public Sign(){
		
	}
	
	public Sign(String errorCode, String errorMsg) {
		super(errorCode, errorMsg);
	}
	public int signId; 
	
	public String userPhone;
	
	public String signStatus;
	
	public String recommenderPhone;
	
	public int recommenderSignId;
	
	public int cash;//�������
	
	public int cashpay;//�Ѹ����
	
	
	public String zoneName;
	
	public String city;
	
	public String floor ;
	
	public String department;
	
	public String roomNum;
	
	public long signTime;
	
	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}
	public String getSignStatus() {
		return signStatus;
	}

	public void setSignStatus(String signStatus) {
		this.signStatus = signStatus;
	}

	public String getRecommenderPhone() {
		return recommenderPhone;
	}

	public void setRecommenderPhone(String recommenderPhone) {
		this.recommenderPhone = recommenderPhone;
	}
	
	public int getCashpay() {
		return cashpay;
	}

	public void setCashpay(int cashpay) {
		this.cashpay = cashpay;
	}

	public int getRecommenderSignId() {
		return recommenderSignId;
	}

	public void setRecommenderSignId(int recommenderSignId) {
		this.recommenderSignId = recommenderSignId;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public long getSignTime() {
		return signTime;
	}

	public void setSignTime(long signTime) {
		this.signTime = signTime;
	}

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
	
}
