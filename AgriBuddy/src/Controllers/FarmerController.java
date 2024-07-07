package Controllers;

import java.util.*;

import DAO.DAO;
import Models.*;

public class FarmerController {
	DAO dao = new DAO();
	Scanner sc = Controller.sc;
	
	//Adding New Farmer To the System
	public void newFarmer() {
		
		//Getting Details
		System.out.print("Please Enter your Name: ");
		String name = sc.next();
		System.out.print("Please Enter your Phone Number: ");
		long phNum = sc.nextLong();
		sc.nextLine();
		System.out.print("Please Enter your Email: ");
		String email = sc.next();
		System.out.print("Please Enter your Address Line 1: ");
		String adln1 = sc.next();
		System.out.print("Please Enter your Address Line 2: ");
		String adln2 = sc.next();
		System.out.print("Please Enter your City: ");
		String city = sc.next();
		System.out.print("Please Enter your State: ");
		String state = sc.next();
		System.out.print("Please Enter your PIN Code: ");
		int pin = sc.nextInt();
		sc.nextLine();
		
		Address address = new Address(adln1,adln2,city,state,pin);
		
		System.out.println("Please Set a Password: ");
		String password = sc.next();
		
		Farmer newFarmer = new Farmer(password, name, address, phNum, email);
		
		
		dao.addFarmer(newFarmer);			//Adding new Farmer with Given details to the system List
		
		System.out.println("\n\n---------Successfully Registered!-----------");
		System.out.println("Your User Id : "+ newFarmer.getUserId());
		System.out.println("Use your User ID and Password For Login.\n\n");
		
		//Pausing Execution to let the user to read the Confirmation message and His\Her User ID
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.out.println("Unable to Pause the Execution");
			e.printStackTrace();
		}
		
	}

	
	//dash board Of Farmer
	public void dashBoard(int userId) {
		Farmer farmer = dao.getFarmer(userId);			//Retrieving farmer Object using UserID
		
		System.out.println("Welcome Back! "+farmer.getName());
		
		//Farmer Main Menu Loop
		int ch;
		do {
			System.out.print("\nWhat Do You Want to Do?\n1.Add Product\n2.View Products\n3.Update Product\n4.Remove Product\n"
					+ "5.View Orders\n6.Dispatch Order\n7.Log out\n\nEnter Your Choice: ");
			
			ch = sc.nextInt();
			sc.nextLine();
			switch(ch) {
			case 1:
				addProduct(farmer);
				break;
			case 2:
				viewProducts(farmer);
				break;
			case 3:
				updateProduct(farmer);
				break;
			case 4:
				removeProduct(farmer);
				break;
			case 5: 
				viewOrders(farmer);
				break;
			case 6:
				dipatchOrder(farmer);
				break;
			case 7:
				System.out.println("Thank you!");
				break;
			default:
				System.out.println("Invalid Input");
			}
			
		}while(ch != 7);
		
	}

	
	

	/*
	 * 
	 * Created Separate Methods For Each Functionalities
	 * to Maintain Modularity and Easy Maintenance
	 * 
	 */
	
	//Adding Product to the System
	private void addProduct(Farmer farmer) {
		
		//Getting Details
		System.out.print("Enter Product Name:");
		String productName = sc.nextLine();
		System.out.print("Enter Product Quatity in Stock: ");
		int quantity = sc.nextInt();
		System.out.print("Enter Product Price Per Unit: ");
		float price = sc.nextFloat();
		
		Product product = new Product(farmer.getUserId(), productName, quantity, price);
		
		int pId = product.getpId();
		
		//Updating Products LIst
		dao.addProduct(product);
		farmer.addProductId(pId);	//Adding to Farmer's Products ID List
		
		//Printing Confirmation Message
		System.out.println("\n----------Product Successfully Added------------");
		System.out.println("With Product ID : "+ pId);
		
		
	}
		
	
	//To View Farmer's Own Product LIst
	private void viewProducts(Farmer farmer) {
		//Iterating through Farmer's Product List
		ArrayList<Product> products = dao.getProducts(farmer.getUserId());
		Iterator<Product> it = products.iterator();
		System.out.println("\n---------------------------------------"
				+ "\nPId\tName\tQnt\tPrice\n"            
				+"---------------------------------------");
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}

	
	//To Update Existing Product Information
	private void updateProduct(Farmer farmer) {
		//Getting Details To Update
		System.out.println("Enter Product ID to Update: ");
		int pId = sc.nextInt();
		sc.nextLine();
		System.out.print("Enter Product Name:");
		String productName = sc.nextLine();
		System.out.print("Enter Product Quatity in Stock: ");
		int quantity = sc.nextInt();
		System.out.print("Enter Product Price Per Unit: ");
		float price = sc.nextFloat();
		
		Product product = new Product(farmer.getUserId(), pId, productName, quantity, price);
		
		
		if(farmer.getProductIdList().contains(pId) && dao.updateProduct(product)) {		//Validating Product ID and Update
			
			System.out.println("\n------Product Details updated Successfully------\n");
		}
		else {
			System.out.println("Product ID Not Found!");
		}
	}

	
	//To delete the Product From the List
	private void removeProduct(Farmer farmer) {
		//getting Product Id
		System.out.println("Enter Product ID to Remove: ");
		int pId = sc.nextInt();
		sc.nextLine();
		
		if(farmer.getProductIdList().contains(pId) && dao.removeProduct(farmer.getUserId(), pId)) {			//Validating Product and Removal
			System.out.println("\n------Product Details Deleted Successfully------\n");
			farmer.getProductIdList().remove((Integer)pId);
		}
		else {
			System.out.println("Product ID Not Found!");
		}
	
	}

	
	//To View Order List
	private void viewOrders(Farmer farmer) {
		//Iterating through Order ID List
		Iterator<Integer> it = farmer.getOrderIdList().iterator();
		int count = 1;
		System.out.println("\n-------------------------------------\n\t\tOrder Details\n-------------------------------------\n");
		while(it.hasNext()) {
			System.out.println("Order "+ count++);
			System.out.println(dao.getOrder(it.next()).orderDetailsForFarmer()+"\n"); 		//Retrieving Order Details From Order ID
		}
		
	}

	//To Dispatch Received Orders
 	private void dipatchOrder(Farmer farmer) {
 		//Getting Details
		System.out.println("Enter Order ID to Dispatch: ");
		int oId = sc.nextInt();
		Order order = dao.getOrder(oId);
		if(order == null || order.getStatus()==OrderStatus.cancelled) {		//Checking Order
			System.out.print("\nOrder ID Either not Exists or Cancelled!\n");
			return;
		}
		
		//Printing Dispatch Address
		Product product = order.getProduct();
		System.out.println("Product Name :\t"+product.getName());
		System.out.println("Dispatched to Address:\n");
		System.out.println(order.getCustomer().getName()+"\n");
		System.out.println(order.getCustomer().getAddress());
		System.out.println("Ph :- "+order.getCustomer().getPhoneNumber());
		
		
		//Getting Confirmation
		System.out.println("\n\nDid you Dispatched the Order?(y/n) : ");
		if(!(sc.next().equalsIgnoreCase("y"))){
			System.out.println("\nDiapatch Discarder!\n");
			return;
		}
		
		//Updating Status
		order.setStatus(OrderStatus.dispatched);
		
		//Updating Remark with OTP
		order.setRemark("Your Order Dispatched!\nUse "+((new Random()).nextInt(9000)+1000) +" as your OTP to recieve the Order");
		
		dao.updateOrder(order);		//Updating Order Details
		
		System.out.println("Order Dispatched!");
		
	}
	

}
