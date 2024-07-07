package DAO;

import java.util.*;

import Models.Customer;
import Models.Farmer;
import Models.Order;
import Models.Product;

public class DAO {
	
	//Using ArrayList As DB 
	private static ArrayList<Farmer> farmersList = new ArrayList<Farmer>();
	private static ArrayList<Customer> customersList = new ArrayList<Customer>();
	private static ArrayList<Product> productsList = new ArrayList<Product>();
	private static ArrayList<Order> ordersList = new ArrayList<Order>();
	

	//to Add new Farmer in the List
	public void addFarmer(Farmer newFarmer) {
		farmersList.add(newFarmer);
	}

	//To Get The Farmer Object With Specified UserId
	public Farmer getFarmer(int userId) {
		Iterator<Farmer> it = farmersList.iterator();
		while(it.hasNext()) {
			Farmer farmer = it.next();
			if(farmer.getUserId() == userId) {
				return farmer;
			}
		}
		
		return null;
	}
	
	
	//to Adding new Customer To the List
	public void addCustomer(Customer newCustomer) {

		customersList.add(newCustomer);
	}

	//To Get The Customer Object With Specified UserId
	public Customer getCustomer(int userId) {
		Iterator<Customer> it = customersList.iterator();
		while(it.hasNext()) {
			Customer customer = it.next();
			if(customer.getUserId() == userId) {
				return customer;
			}
		}
		
		return null;
	}
	
	//To Add new Product 
	public void addProduct(Product product) {
		productsList.add(product);
		
	}
	
	//to Remove Existing Product with Specified Product ID Belongs to Farmer with Given USerID
	public boolean removeProduct(int userId, int pId) {
		Iterator<Product> it = productsList.iterator();
		while(it.hasNext()) {
			Product product = it.next();
			if(product.getFarmerId() == userId && product.getpId() == pId) {	//Checking Product ID and REspective Farmer ID
				productsList.remove(product);
				//Printing Deleted Product
				System.out.println("Deleted Product..");
				System.out.println(product);
				return true;
			}
		}
		return false;
	}
	
	//TO Update Product Details 
	public boolean updateProduct(Product newProduct) {
		Iterator<Product> it = productsList.iterator();
		while(it.hasNext()) {
			Product oldProduct = it.next();
			if(oldProduct.getpId() == newProduct.getpId()) {
				productsList.set(productsList.indexOf(oldProduct),newProduct);
				return true;
			}
		}
		
		return false;
		
	}
	
	//To Get Farmer's Product List
	public ArrayList<Product> getProducts(int userId) {
		ArrayList<Product> resultSet = new ArrayList<Product>();
		Iterator<Product> it = productsList.iterator();
		while(it.hasNext()) {
			Product Product = it.next();
			if(Product.getFarmerId() == userId) {
				resultSet.add(Product);
				
			}
		}
		
		return resultSet;
	}

	
	//To get a Product With Specified user ID
	public Product getProduct(int pId){
		Iterator<Product> it = productsList.iterator();
		while(it.hasNext()) {
			Product product = it.next();
			if(product.getpId() == pId) {
				
				return product;
			}
		}
		return null;
	}

	//To Get Product List with given name
	public ArrayList<Product> getProducts(String pName) {
		ArrayList<Product> resultSet = new ArrayList<Product>();
		Iterator<Product> it = productsList.iterator();
		while(it.hasNext()) {
			Product Product = it.next();
			if(Product.getName().equalsIgnoreCase(pName)) {
				resultSet.add(Product);
				
			}
		}
		
		return resultSet;
	}

	//to Add New Order
	public void addOrder(Order order) {
		ordersList.add(order);
		
	}

	//To get Order Details
	public Order getOrder(int oId) {
		Iterator<Order> it = ordersList.iterator();
		while(it.hasNext()) {
			Order order = it.next();
			if(order.getoId() == oId) {
				return order;
			}
		}
		return null;
	}

	//To update a order Details
	public void updateOrder(Order newOrder) {
		Iterator<Order> it = ordersList.iterator();
		while(it.hasNext()) {
			Order oldOrder = it.next();
			if(oldOrder.getoId() == newOrder.getoId()) {
				ordersList.set(ordersList.indexOf(oldOrder),newOrder);
			}
		}
		
	}
	
	

}
