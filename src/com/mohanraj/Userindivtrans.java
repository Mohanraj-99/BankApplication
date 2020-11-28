package com.mohanraj;

import java.io.Serializable;

public class Userindivtrans implements Serializable {
	private int tranID;
	private String tranType;
	private int amount;
	private String fromAccountId;
	private String toAccountId;
	private int balance;

	public Userindivtrans(int tranID, String tranType, int amount, String toAccountId, int balance, String fromAccountId) {
		super();
		this.tranID = tranID;
		this.tranType = tranType;
		this.amount = amount;
		this.fromAccountId = fromAccountId;
		this.toAccountId = toAccountId;
		this.balance =balance;
	}

	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getTranID() {
		return tranID;
	}
	public void setTranID(int tranID) {
		this.tranID = tranID;
	}
	public String getTranType() {
		return tranType;
	}
	public void setTranType(String tranType) {
		this.tranType = tranType;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getToAccountId() {
		return toAccountId;
	}
	public void setToAccountId(String toAccountId) {
		this.toAccountId = toAccountId;
	}
	public String getFromAccountId() { return fromAccountId; }
	public void setFromAccountId(String fromAccountId) { this.fromAccountId = fromAccountId; }

	@Override
	public String toString() {
		return "User - individual - trans{" +
				"tranID=" + tranID +
				", tranType='" + tranType + '\'' +
				", amount=" + amount +
				", fromAccountId='" + fromAccountId + '\'' +
				", toAccountId='" + toAccountId + '\'' +
				", balance=" + balance +
				'}';
	}
}
