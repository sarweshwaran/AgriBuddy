package Models;


import java.util.*;

public class Customer extends Person{
	static int id = 2001;
	private ArrayList<Integer> orderIdList;

	public Customer(String password, String name, Address address, Long phoneNumber, String email) {
		super(id++, password, name, address, phoneNumber, email);
		this.orderIdList = new ArrayList<Integer>();
	}
	
	
	public void addOrder(int orderId) {
		orderIdList.add(orderId);
	}

	public ArrayList<Integer> getOrderIdList() {
		
		return this.orderIdList;
	}

}
