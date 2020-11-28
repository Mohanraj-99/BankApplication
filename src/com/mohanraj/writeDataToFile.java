package com.mohanraj;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.TreeMap;

public class writeDataToFile {

	public static void writeDataToFile1(String file,ArrayList<Customer> arrlist)  throws IOException{
		ObjectOutputStream oos =null;
		FileOutputStream fout ;
		try{
		fout  =  new FileOutputStream(file);
		oos = new ObjectOutputStream(fout);
		oos.writeObject(arrlist);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			if(oos!=null)
				oos.close();
		}
	}
	public static void writeDataToFile2(String file,ArrayList<transaction> arrlist)  throws IOException {
		ObjectOutputStream oos =null;
		FileOutputStream fout ;
		try{
		fout  =  new FileOutputStream(file);
		oos = new ObjectOutputStream(fout);
		oos.writeObject(arrlist);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			if(oos!=null)
				oos.close();
		}
	}
	public static void writeDataToFile3(String file,ArrayList<PasswordHistory> arrlist)   throws IOException{
		ObjectOutputStream oos =null;
		FileOutputStream fout ;
		try{
		fout  =  new FileOutputStream(file);
		oos = new ObjectOutputStream(fout);
		oos.writeObject(arrlist);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			if(oos!=null)
				oos.close();
		}
	}
	public static void writeDataToFile4(String file,TreeMap<Integer,ArrayList<Integer>> tm)  throws IOException {
		ObjectOutputStream oos =null;
		FileOutputStream fout ;
		try{
		fout  =  new FileOutputStream(file);
		oos = new ObjectOutputStream(fout);
		oos.writeObject(tm);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			if(oos!=null)
				oos.close();
		}
	}
	
}
