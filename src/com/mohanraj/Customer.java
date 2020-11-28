package com.mohanraj;

import java.io.Serializable;

public class Customer implements Serializable {
	private int balance,cusID,accountNo;
	private String name;
	private String password;

	public Customer( int accountNo, int cusID,String name, String password, int balance) {
		super();
		this.balance = balance;
		this.cusID = cusID;
		this.accountNo = accountNo;
		this.name = name;
		this.password = password;
	}
	public Customer(){ }

	public int getCusID() {
		return cusID;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCusID(int cusID) {
		this.cusID = cusID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Customer [balance=" + balance + ", cusID=" + cusID + ", accountNo=" + accountNo + ", name=" + name
				+ ", password=" + password + "]";
	}
}

