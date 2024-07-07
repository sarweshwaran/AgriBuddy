package Models;


import java.util.*;

public class Farmer extends Person{
	static int id = 1001;
	private ArrayList<Integer> productIdList;
	private ArrayList<Integer> orderIdList;

	public Farmer(String password, String name, Address address, Long phoneNumber, String email) {
		super(id++, password, name, address, phoneNumber, email);
		this.productIdList = new ArrayList<Integer>();
		this.orderIdList = new ArrayList<Integer>();
	}
	
	public void addProductId(int productId) {
		productIdList.add(productId);
	}
	
	public void addOrderId(int orderId) {
		orderIdList.add(orderId);
	}
	
	public ArrayList<Integer> getProductIdList() {
		return productIdList;
	}
	
	public ArrayList<Integer> getOrderIdList(){
		return this.orderIdList;
	}
	
	

	

}
