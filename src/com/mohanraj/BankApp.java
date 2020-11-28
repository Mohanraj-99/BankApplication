package com.mohanraj;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.NavigableMap;

/*
// Descending key store or Use NavigationalMap
class CC implements Serializable,Comparator<Integer>{
	@Override
	public int compare(Integer t1, Integer t2) {
		return t2.compareTo(t1);
	}
}
*/
public class BankApp implements Serializable {
	
	private  static Customer loginUser(ArrayList<Customer> arrlist) {
		int UserID;
		String Password;
		Scanner sc =  new Scanner(System.in);
		System.out.println("\n ----- Login ------");
		System.out.println( "\nEnter the UserID\n");
		UserID = (Integer.parseInt(sc.nextLine().trim())) ;
		System.out.println("\nEnter the User password \n");
		Password = sc.nextLine().trim();
		while(!validation(UserID,Password,arrlist)){
			System.out.println("\nEnter the User password again\n");
			Password = sc.nextLine().trim();
		}
		return retriveCurrentUser(UserID, arrlist);
	}

	private  static Customer retriveCurrentUser(int UserID,ArrayList<Customer> arrlist) {
		for(Customer c: arrlist){
			if(c.getCusID() == UserID){
				System.out.println("Welcome "+ c.getName());
				return c;
			}
		}
		return null;
	}
	
	private  static boolean validation(int UserID,String password,ArrayList<Customer> arrlist) {
		String pass = encrypt(password);
		for(Customer c: arrlist){
			if(c.getPassword().equals(pass) && c.getCusID() == UserID){
				System.out.println("\nLogin Success\n");
				return true;
			}
		}
		System.out.println("\n Incorrect Password ");
		return false;
	}

	private static ArrayList<Object> registerUser(TreeMap<Integer,ArrayList<Integer>> tm ) {
		Scanner sc =  new Scanner(System.in);
		Random r = new Random();
		// Declarations
		String Name,Password,repass,encryptpass="";
		int balance = 10000,cusID ,AccountNo;
		boolean status=false;
		// Getting User Details
		System.out.println( "\n --- Enter the User Name ---- ");
		Name = sc.nextLine().trim();
		System.out.println("\n --- Enter the User Password ----");
		Password = sc.nextLine().trim();
		// Validating User Password
		while(!validate(Password)){
			System.out.println("\nUser password must contain at least 6 characters:\n2 UpperCase - 2 lowerCase - 2 numbers minimum \n --- Enter the user password ----");
			Password = sc.nextLine().trim();
		}
		// Validating User Password
		System.out.println("\n ---- Retype the User password ----");
		repass = sc.nextLine().trim();
		while(!validate(Password)){
			System.out.println("\nRetype User password must contain at least 6 characters:\n2 UpperCase - 2 lowerCase - 2 numbers minimum \n --- Enter the user password ----");
			Password = sc.nextLine().trim();
		}
		status = verification(Password,repass);
		if(status){
			// Password Encryption
			encryptpass = encrypt(Password);
		}
		else{
			System.out.println("\nUser-password miss matches with the re-typed password ");
			registerUser(tm);
		}
		System.out.println("\nRegistered Successfully\n");
		// User --> ID and Account Number generation
		cusID = r.nextInt(1000);
		AccountNo = r.nextInt(1000000);
		// Getting back data:
		// 1.Password History
		ArrayList<String> al = new ArrayList<>();
		al.add(encryptpass);
		PasswordHistory cusPassHis = new PasswordHistory(cusID,al);
		// 2.Customer Details
		Customer cusDetails =  new Customer(AccountNo,cusID, Name, encryptpass, balance);
		System.out.println(cusDetails);
		// 3.Top N Details
		ArrayList<Integer> temp1 = new ArrayList<>();
		temp1.add(cusDetails.getCusID());
		if(tm.containsKey(cusDetails.getBalance())){
			temp1.clear();
			temp1 = tm.get(cusDetails.getBalance());
			temp1.add(cusDetails.getCusID());
		}
		tm.put(cusDetails.getBalance(), temp1);
		// 4.Transaction part
		ArrayList<Userindivtrans> temp2 = new ArrayList<>();
		Userindivtrans userTran = new Userindivtrans(1,"Opening",10000, Integer.toString(cusDetails.getCusID()),cusDetails.getBalance(),"None");
		temp2.add(userTran);
		transaction tran = new transaction(cusDetails.getCusID(),temp2);
		ArrayList<Object> ret = new ArrayList<>();
		ret.add(cusDetails);
		ret.add(cusPassHis);
		ret.add(tran);
		return ret;
	}
	
	private static boolean validate(String pass) {
		// Min - 6 Character ( Includes Min - 2 (UpperCase,LowerCase,Numbers))
		int upperCount=0,lowerCount=0,numCount=0;
		for(int i = 0;i<pass.length();i++){
			if(pass.charAt(i)>='A' && pass.charAt(i)<='Z')
				upperCount++;
			else if(pass.charAt(i)>='a' && pass.charAt(i)<='z')
				lowerCount++;
			else if(pass.charAt(i)>='0' && pass.charAt(i)<='9')
				numCount++;
		}
		return numCount > 1 && upperCount > 1 && lowerCount > 1;
	}

	private static boolean verification(String p1 , String p2) {
		return p1.equals(p2);
	}

	private static String encrypt(String pass) {
		StringBuilder str= new StringBuilder();
		for(int i = 0;i<pass.length();i++){
			if(pass.charAt(i)=='Z')
				str.append('A');
			else if(pass.charAt(i)=='z')
				str.append('a');
			else if(pass.charAt(i)=='9')
				str.append('0');
			else
				str.append((char) (pass.charAt(i) + 1));
		}
		return str.toString();
	}

	public static void main(String[] args) throws IOException,EOFException{
		// PART - 1 :
		// For Top N users
		TreeMap<Integer,ArrayList<Integer>> tm = new TreeMap<>();
		// For Customers
		ArrayList<Customer> arrlist = new ArrayList<>();
		// For Customers individual transactions
		ArrayList<transaction> trans = new ArrayList<>();
		// For Customers individual password history
		ArrayList<PasswordHistory> phis = new ArrayList<>();

		// Data source
		if(readDataFromFile.readDataFromFile1("D:\\Projects\\src\\com\\mohanraj\\customdata.dat")!=null) {
			arrlist = readDataFromFile.readDataFromFile1("D:\\Projects\\src\\com\\mohanraj\\customdata.dat");
		}
		if(readDataFromFile.readDataFromFile2("D:\\Projects\\src\\com\\mohanraj\\transdata.dat")!=null) {
			trans = readDataFromFile.readDataFromFile2("D:\\Projects\\src\\com\\mohanraj\\transdata.dat");
		}
		if(readDataFromFile.readDataFromFile3("D:\\Projects\\src\\com\\mohanraj\\passdata.dat")!=null) {
			phis = readDataFromFile.readDataFromFile3("D:\\Projects\\src\\com\\mohanraj\\passdata.dat");
		}
		if(readDataFromFile.readDataFromFile4("D:\\Projects\\src\\com\\mohanraj\\baldata.dat")!=null) {
			tm = readDataFromFile.readDataFromFile4("D:\\Projects\\src\\com\\mohanraj\\baldata.dat");
		}

		// Declarations
		boolean data = false;
		// For CurrentUsers transactions
		transaction t = new  transaction();
		// For AccountTransfer purpose
		transaction t2 = new transaction();
		Customer c =  new Customer();

		//PART - 2 :
		while(!data) {
			Scanner sc = new Scanner(System.in);
			System.out.println("\n <----- Welcome to our Bank Service ----->\n");
			// Declarations
			int amount, benID, num;
			boolean set = false, end = false;
			//PART - 2.1 :
			while (!set) {
				System.out.println("\nEnter the number:\n1 - Register \n2 - Login\n");
				num = sc.nextInt();
				if (num == 1) {
					// Outcome is needed for all 4
					ArrayList<Object> obj = registerUser(tm);
					arrlist.add((Customer) obj.get(0));
					phis.add((PasswordHistory) obj.get(1));
					trans.add((transaction) obj.get(2));
					// Store
					writeDataToFile.writeDataToFile1("D:\\Projects\\src\\com\\mohanraj\\customdata.dat", arrlist);
					writeDataToFile.writeDataToFile2("D:\\Projects\\src\\com\\mohanraj\\transdata.dat", trans);
					writeDataToFile.writeDataToFile3("D:\\Projects\\src\\com\\mohanraj\\passdata.dat", phis);
					writeDataToFile.writeDataToFile4("D:\\Projects\\src\\com\\mohanraj\\baldata.dat", tm);
					// Current User
					c = loginUser(arrlist);
					set = true;
				} else if (num == 2) {
					// Current User
					c = loginUser(arrlist);
					set = true;
				} else {
					System.out.println("\nYou have entered invalid number \nYou can enter either 1 or 2");
				}
			}
			// Getting Current transaction class
			for(transaction k :trans){
				if(k.getUserId()==c.getCusID()){
					t = k;
				}
			}
			//PART - 2.2 :
			while (!end) {
				System.out.println("Enter the number:\n1 - WithDraw \n2 - CashDeposit \n3 - AccountTransfer \n4 - Change Password \n5 - To see the password History  \n6 - Transaction History \n7 - Top N Users \n8 - Max to Min Balance User(Just for checking) \n");
				num = sc.nextInt();
				if (num == 1) {
					System.out.println("\nEnter the amount to withdraw :");
					amount = sc.nextInt();
					ArrayList<Integer> al = getTop3(tm);
					withDraw(c, amount, trans, t , tm, al ,0);
				}
				else if (num == 2) {
					System.out.println("\nEnter the amount :");
					amount = sc.nextInt();
					ArrayList<Integer> al = getTop3(tm);
					cashDeposit(c, amount, trans, t, tm,al,0);
				}
				else if (num == 3) {
					int flag = 0;
					System.out.println("\nEnter the amount to transfer :");
					amount = sc.nextInt();
					System.out.println("\nEnter the Beneficiary ID :");
					benID = sc.nextInt();
					ArrayList<Integer> al = getTop3(tm);
					Customer cus;

					// Finding Valid Customer
					for (Customer m : arrlist) {
						if (m.getCusID() == benID) {
							cus = m;
							flag = 1;
							for(transaction k :trans){
								if(k.getUserId()==m.getCusID()){
									t2 = k;
								}
							}
							System.out.println("\nValid BeneficiaryID");
							AccountTransfer(c, amount, cus, trans, t,t2 , tm,al);
							break;
						}
					}
					if(flag==0)
						System.out.println("\nInValid BeneficiaryID");
				}
				else if (num == 4) {
					System.out.println("\nEnter the New-password :");
					ArrayList<String> a;
					// Validate its not invalid password as well as old password and encrypted
					String temp = encrypt(passwordChange(c));
					// Setting New password
					c.setPassword(temp);
					for (PasswordHistory l : phis) {
						if (l.getUserID() == c.getCusID()) {
							a = l.getArrList();
							a.add(0,temp);
							l.setArrList(a);
							break;
						}
					}
				}
				else if (num == 5) {
					System.out.println("\nCurrent User : " + c.getName());
					System.out.println("\n<----- Password History ----->");
					for (PasswordHistory p : phis) {
						if (p.getUserID() == c.getCusID()) {
							System.out.println("Total "+p.getArrList().size() +" passwords\n");
							for (String s : p.getArrList()) {
								System.out.println(s);
							}
							break;
						}
					}
				}
				else if (num == 6) {
					System.out.println("\nCurrent User : " + c.getName());
					System.out.println("\nCurrent UserId : " + c.getCusID());
					System.out.println("\n<----- All Transactions ----->");
					for (transaction o : trans) {
						if (o.getUserId() == c.getCusID()) {
							for (Userindivtrans q : o.getArrList()) {
								System.out.println(q);
							}
							break;
						}
					}
				}
				else if (num == 7) {
					System.out.println("\nEnter the number of Users:\n");
					int tmp = sc.nextInt();
					int count = 0;
					int flag = 0;
					System.out.println("\n<----- Top N Users ----->\n");
					System.out.println("\nUserID\n");
					NavigableMap<Integer,ArrayList<Integer>> nmap = tm.descendingMap();
					for (Map.Entry<Integer, ArrayList<Integer>> entry : nmap.entrySet()) {
						ArrayList<Integer> value1 = entry.getValue();
						for(Integer i:value1) {
							if (count == tmp) {
								flag = 1;
								break;
							} else {
								System.out.println(i);
								count++;
							}
						}
						if(flag==1)
							break;
					}
				}
				else if(num==8){
					System.out.println("\n<----- MaxToMin Users - Just for reference ----->\n");
					NavigableMap<Integer,ArrayList<Integer>> nmap = tm.descendingMap();
					for (Map.Entry<Integer, ArrayList<Integer>> entry : nmap.entrySet()) {
						System.out.println("Balance : " + entry.getKey());
						System.out.print("Customer Id : " );
						for(Integer i: entry.getValue()) {
								System.out.print(i+" ");
						}
						System.out.println();
					}
				}
				else {
					System.out.println("\nYou have entered invalid number \n You can enter between 1 - 7");
				}

				//Forced Password Change
				if (num >= 1 && num <= 3 && ((t.getArrList().size() % 5) == 0) && (t.getArrList().size() != 0)) {
					System.out.println("\nPlease Change your Password before proceeding further transactions");
					System.out.println("\nEnter the New-password \n");
					ArrayList<String> a;
					// Validate its not invalid password as well as old password and encrypted
					String temp = encrypt(passwordChange(c));
					// Setting New password
					c.setPassword(temp);
					for (PasswordHistory l : phis) {
						if (l.getUserID() == c.getCusID()) {
							a = l.getArrList();
							a.add(0,temp);
							l.setArrList(a);
							break;
						}
					}
				}
				writeDataToFile.writeDataToFile1("D:\\Projects\\src\\com\\mohanraj\\customdata.dat", arrlist);
				writeDataToFile.writeDataToFile2("D:\\Projects\\src\\com\\mohanraj\\transdata.dat", trans);
				writeDataToFile.writeDataToFile3("D:\\Projects\\src\\com\\mohanraj\\passdata.dat", phis);
				writeDataToFile.writeDataToFile4("D:\\Projects\\src\\com\\mohanraj\\baldata.dat", tm);
				System.out.println("Do you need to continue \n1 - YES \n2 - NO");
				num = sc.nextInt();
				if (num == 2) {
					end = true;
				}
			}
			System.out.println("\nNew Login \nEnter \n1 - To continue \n2 - Stop");
			data = sc.nextInt() != 1;
		}
		System.out.println("End");
	}

	private static ArrayList<Integer> getTop3(TreeMap<Integer,ArrayList<Integer>> tm) {
		//Declarations
		int count = 0, flag = 0;
		ArrayList<Integer> al = new ArrayList<>();
		for (Map.Entry<Integer, ArrayList<Integer>> entry : tm.entrySet()) {
			ArrayList<Integer> value1 = entry.getValue();
			for (Integer i : value1) {
				if (count == 3) {
					flag = 1;
					break;
				} else {
					al.add(i);
					count++;
				}
			}
			if (flag == 1)
				break;
		}
		return al;
	}

	private static String passwordChange(Customer c) {
		String Password;
		Scanner sc =  new Scanner(System.in);
		Password = sc.nextLine().trim();
		while(!validate(Password) || !checkOld(Password, c)){
			if(checkOld(Password, c))
			System.out.println("\n userpassword must contataion atleast 6 characters:\n 2 UpperCase - 2 lowerCase - 2 numbers minimum \n --- Enter the newpassword ----");
			else
				System.out.println("\n --- Enter the new-password ----");
			Password = sc.nextLine().trim();
		}
		System.out.println("\nNew Password Setted Successfully ");
		return Password;
	}

	private static boolean checkOld(String password,Customer c) {
		if((c.getPassword()).equals(encrypt(password))){
			System.out.println("\nIts Old Password pls try new one");
			return false;
		}
		else
			return true;
	}

	private static void AccountTransfer(Customer c, int amount, Customer toCus, ArrayList<transaction> trans, transaction CurrUsertrans , transaction toCusTrans,TreeMap<Integer, ArrayList<Integer>> tm , ArrayList<Integer> al ) {
		ArrayList<Integer> temp1;
		ArrayList<Integer> temp3 = new ArrayList<>();
		// Withdraw amount from Current Account
		int amount_1 = withDraw(c, amount, trans, CurrUsertrans, tm, al, toCus.getCusID());
		if (amount_1 != 0) {
			System.out.println("\nSuccessful Account Transfer\n");
			cashDeposit(toCus, amount_1, trans, toCusTrans, tm, al, c.getCusID());
			}
		}

	private static void cashDeposit(Customer c,int amount,ArrayList<transaction> trans,transaction t,TreeMap<Integer,ArrayList<Integer>> tm,ArrayList<Integer> al ,int acctran) {
		ArrayList<Integer> temp1;
		ArrayList<Integer> temp3 = new ArrayList<>();
		ArrayList<Userindivtrans> temp2 = t.getArrList();
		Userindivtrans m1;
		int k = 0,u = 10;
		if((temp2.size()+1)%10 ==0) {
			k = 100;
			// Top 3 omit - Maintenance fee
			boolean f = false;
			for(Integer a : al) {
				if (a == c.getCusID()) {
					f = true;
					break;
				}
			}
			if(f) {
				k = 0;
			}
		}
		// remove old balance record to treemap
		int oldBalance = c.getBalance();
		if(tm.get(oldBalance)!=null) {
			temp1 = tm.get(oldBalance);
			for (Integer d : temp1) {
				if (d.equals(c.getCusID())) {
					temp1.remove(d);
					if (temp1.size() > 0) {
						tm.put(oldBalance, temp1);
					} else {
						tm.remove(c.getBalance());
					}
					break;
				}
			}
		}
		// Transaction log
		if(acctran==0) {
			// Balance setting
			c.setBalance(c.getBalance()+amount-u-k);  // u -  for transaction fee && k - for maintenance fee
			m1 = new Userindivtrans(temp2.size()+1,"CashDeposit",amount, Integer.toString(c.getCusID()), c.getBalance(), Integer.toString(c.getCusID()));
		}
		else {
			// Balance setting
			c.setBalance(c.getBalance()+amount-k);  // u -  for transaction fee && k - for maintenance fee
			m1 = new Userindivtrans(temp2.size() + 1, "AccountTransfer", amount, Integer.toString(c.getCusID()), c.getBalance(), Integer.toString(acctran));
		}
		temp2.add(m1);
		transaction m2 = new transaction(c.getCusID(),temp2);
		trans.add(m2);
		System.out.println("\nCash Deposited to your account Successfully");
		// set new balance record to treemap
		temp3.add(c.getCusID());
		if (tm.containsKey(c.getBalance())) {
			temp3.clear();
			temp3 = tm.get(c.getBalance());
			temp3.add(c.getCusID());
		}
		tm.put(c.getBalance(), temp3);
	}

	private static int withDraw(Customer c,int amount,ArrayList<transaction> trans, transaction t,TreeMap<Integer,ArrayList<Integer>> tm,ArrayList<Integer> al,int acctran) {
		ArrayList<Integer> temp1 ;
		ArrayList<Integer> temp3 = new ArrayList<>();
		ArrayList<Userindivtrans> temp2 = t.getArrList();
		int k = 0;
		if(c.getBalance()-amount <= 1000){
			System.out.println("\nYour Balance becomes below the minimum balance(1000) after this transaction");
			System.out.println("You can withdraw only "+ (c.getBalance()-1001));
			return 0; 
		}
		if((temp2.size()+1)%10 ==0) {
			k = 100;
			// Top 3 omit - Maintenance fee
			boolean f = false;
			for(Integer a : al) {
				if (a == c.getCusID()) {
					f = true;
					break;
				}
			}
			if(f) {
			k = 0;
			}
		}
		// remove old balance record to treemap
		int oldBalance = c.getBalance();
		if(tm.get(oldBalance)!=null) {
			temp1 = tm.get(oldBalance);
			for (Integer d : temp1) {
				if (d.equals(c.getCusID())) {
					temp1.remove(d);
					if (temp1.size() > 0) {
						tm.put(oldBalance, temp1);
					} else {
						tm.remove(c.getBalance());
					}
					break;
				}
			}
		}
		// Balance setting
		c.setBalance(c.getBalance()-amount-10-k);  // 10 - for transaction fee && k - for maintenance fee
		// Transaction log
		Userindivtrans m1;
		if(acctran==0) {
			 m1 = new Userindivtrans(temp2.size() + 1, "withDrawal", amount, "None", c.getBalance(), Integer.toString(c.getCusID()));
		}
		else {
			m1 = new Userindivtrans(temp2.size() + 1, "AccountTransfer", amount, Integer.toString(acctran), c.getBalance(), Integer.toString(c.getCusID()));
		}
		temp2.add(m1);
		transaction m2 = new transaction(c.getCusID(),temp2);
		trans.add(m2);
		System.out.println("\nSuccess withdraw\n");

		/// set new balance record to treemap
		temp3.add(c.getCusID());
		if (tm.containsKey(c.getBalance())) {
			temp3.clear();
			temp3 = tm.get(c.getBalance());
			temp3.add(c.getCusID());
		}
		tm.put(c.getBalance(), temp3);
		return amount;
	}

}