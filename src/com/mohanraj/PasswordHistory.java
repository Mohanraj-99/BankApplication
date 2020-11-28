package com.mohanraj;
import java.io.Serializable;
import java.util.*;

public class PasswordHistory implements Serializable {
	private int UserID;
	private ArrayList<String> arrlist;

	public PasswordHistory(int userID, ArrayList<String> arrlist) {
		super();
		UserID = userID;
		this.arrlist = arrlist;
	}
	public PasswordHistory(){ }

	public int getUserID() {
		return UserID;
	}
	public void setUserID(int UserID) {
		this.UserID = UserID;
	}
	public  ArrayList<String> getArrList() {
		return arrlist;
	}
	public void setArrList(ArrayList<String> arrlist) {
		this.arrlist = arrlist;
	}
}
