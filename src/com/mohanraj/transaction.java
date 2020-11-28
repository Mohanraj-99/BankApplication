package com.mohanraj;
import java.io.Serializable;
import java.util.*;
public class transaction implements Serializable{
	private int userId;
	private ArrayList<Userindivtrans> arrl = new ArrayList<>();

	public transaction(int userID, ArrayList<Userindivtrans> arrlist) {
		super();
		userId = userID;
		this.arrl = arrlist;
	}
	public transaction(){ }

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public  ArrayList<Userindivtrans> getArrList() {
		return arrl;
	}
	public void setArrList(ArrayList<Userindivtrans> arrlist) { this.arrl = arrlist; }
}
