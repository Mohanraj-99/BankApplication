package com.mohanraj;

import java.io.*;
import java.util.ArrayList;
import java.util.TreeMap;

public class readDataFromFile {
	
	public static ArrayList<Customer> readDataFromFile1(String file)  throws IOException {
		ObjectInputStream objectinputstream = null;
		ArrayList<Customer> readData = null;
		try {
			FileInputStream streamInput = new FileInputStream(file);
			objectinputstream = new ObjectInputStream(streamInput);
			readData = (ArrayList<Customer>) objectinputstream.readObject();
		}
		catch (EOFException e){
			System.out.println(" <----- Initializing Bank Application - Customer ----------->");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(objectinputstream != null){
				objectinputstream .close();
			} 
			return readData;
		}
	}
	
	public static ArrayList<transaction> readDataFromFile2(String file)  throws IOException {
		ObjectInputStream objectinputstream = null;
		ArrayList<transaction> readData = null;
		try {
			FileInputStream streamInput = new FileInputStream(file);
			objectinputstream = new ObjectInputStream(streamInput);
			readData = (ArrayList<transaction>) objectinputstream.readObject();
		}
		catch (EOFException e){
			System.out.println(" <----- Initializing Bank Application - Transaction -------->");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(objectinputstream != null){
				objectinputstream .close();
			} 
			return readData;
		}
	}
	public static ArrayList<PasswordHistory> readDataFromFile3(String file)   throws IOException{
		ObjectInputStream objectinputstream = null;
		ArrayList<PasswordHistory> readData = null;
		try {
			FileInputStream streamInput = new FileInputStream(file);
			objectinputstream = new ObjectInputStream(streamInput);
		    readData = (ArrayList<PasswordHistory>) objectinputstream.readObject();
		}
		catch (EOFException e){
			System.out.println(" <----- Initializing Bank Application - PasswordHistory ---->");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(objectinputstream != null){
				objectinputstream .close();
			} 
			return readData;
		}
	}
	public static TreeMap<Integer,ArrayList<Integer>> readDataFromFile4(String file)  throws IOException {
		ObjectInputStream objectinputstream = null;
		TreeMap<Integer,ArrayList<Integer>> readData = null;
		try {
			FileInputStream streamInput = new FileInputStream(file);
			objectinputstream = new ObjectInputStream(streamInput);
		    readData = (TreeMap<Integer,ArrayList<Integer>>) objectinputstream.readObject();
		}
		catch (EOFException e){
			System.out.println(" <----- Initializing Bank Application - BalanceTree -------->");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(objectinputstream != null){
				objectinputstream .close();
			} 
			return readData;
		}
	}
	

}
