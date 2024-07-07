package Controllers;

import java.util.*;
import DAO.DAO;
import Models.*;

public class CustomerController {
	DAO dao = new DAO();		//Data Access Object
	Scanner sc = Controller.sc;	//Using Common Scanner Object
	
	//To Create New Customer object and add it to the System
	public void newCustomer() {
		
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
		
		Customer newCustomer = new Customer(password, name, address, phNum, email);
		
		dao.addCustomer(newCustomer);	//Adding Customer To the List
		
		System.out.println("\n\n---------Successfully Registered!-----------");
		System.out.println("Your User Id : "+ newCustomer.getUserId());
		System.out.println("Use your User ID and Password For Login.\n\n");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//DashBoard For Customer To perform Operations 
	public void dashBoard(int userId) {
		//Retrieving customer From List
		Customer customer = dao.getCustomer(userId);
		
		System.out.println("Welcome Back! "+customer.getName());
		int ch;
		
		//User Option Loop
		do {
			System.out.print("\nWhat Do You Want to Do?\n1.Search Product\n2.Order Product\n3."
					+ "View Orders\n4.Cancel Order\n5.Recieve Order\n6.Log out\n\nEnter Your Choice: ");
			
			ch = sc.nextInt();
			sc.nextLine();
			
			switch(ch) {
			case 1:
				searchProduct();
				break;
			case 2:
				orderProduct(customer);
				break;
			case 3:
				viewOrders(customer);
				break;
			case 4:
				cancelOrder(customer);
				break;
			case 5: 
				receiveOrder(customer);
				break;
			case 6:
				System.out.println("Thank you!");
				break;
			default:
				System.out.println("Invalid Input");
			}
			
		}while(ch != 6);
		
	}
	
	
	
	/*
	 
	 Created Separate Methods for Each Functionality 
	 to Maintain Modularity and Easy Modification
	 
	 */
	
	//Method to Search product
	void searchProduct() {
		
		System.out.println("Search By?\n1.Product ID\n2.Product Name"
							+ "\n3.Farmer ID\n\nEnter your Choice: ");
		int ch = sc.nextInt();
		
		if(ch == 1) {			//Search using Product ID
			
			System.out.println("Enter Product ID: ");
			int pId = sc.nextInt();
			sc.nextLine();				//to Avoid null Input Behavior due to nextInt()
			
			
			Product product = dao.getProduct(pId);		//Retrieving Product from List
			
			if(product!= null) {	//Condition to check Invalid Product ID
				System.out.println("\n---------------------------------------"
						+ "\nPId\tName\tQnt\tPrice\tFarmerID\n"            
						+"---------------------------------------");
				System.out.println(product+"\t"+product.getFarmerId());
			}
			else {
				System.out.println("Product Not Found!");
			}
		}
		else if(ch == 2){				//Search Using Product Name
			
			System.out.println("Enter Product Name: ");
			String pName = sc.nextLine();
			
			ArrayList<Product> products = dao.getProducts(pName);		//Retrieving Products with Same Name from the List
			
			//Printing Products Details
			Iterator<Product> it = products.iterator();
			System.out.println("\n---------------------------------------"
								+ "\nPId\tName\tQnt\tPrice\tFarmerID\n"            
								+"---------------------------------------");
			while(it.hasNext()) {
				Product product = it.next();
				System.out.println(product+"\t"+product.getFarmerId());
			}
			
		}
		else {
			System.out.println("Enter Farmer ID: ");
			int farmerId = sc.nextInt();
			
			ArrayList<Product> products = dao.getProducts(farmerId);		//Retrieving Products with Specified Farmer ID from the List
			
			//Printing Products Details
			Iterator<Product> it = products.iterator();
			System.out.println("\n---------------------------------------"
								+ "\nPId\tName\tQnt\tPrice\n"            
								+"---------------------------------------");
			while(it.hasNext()) {
				System.out.println(it.next());
			}
			
		}
		
	}
	
	//To Place Order Using Product ID
	private void orderProduct(Customer customer) {
		
		System.out.println("Enter Product ID to Place Order: ");
		int pId = sc.nextInt();
		Product product = dao.getProduct(pId);
		
		if(product == null) {		//Checking For Invalid Product ID
			System.out.println("\nProduct ID Not Found!");
			return;
		}
		else if(product.getQuantity() == 0) {		//Checking For Out of Stock Product
			System.out.println("\nProduct Out of Stock!");
			return;
		}
		
		//Getting Quantity to Order
		int qty;
		
		//Loop to Ensure Order Quantity Does Not Exits Product Available Quantity
		do {
		System.out.println("Enter Product Quantity: ");
		qty = sc.nextInt();
		
		if(qty > product.getQuantity()) {
			System.out.println("Quantity To High");
		}
		}while(qty > product.getQuantity());
		
		
		Order order = new Order(product,customer,qty);		//Creating Order
		
		order.printBill();		//Printing Order Summary
		
		
		//Getting Confirmation
		System.out.println("Do you Want to Confirm your Order(y/n): ");
		if(!(sc.next().equalsIgnoreCase("y"))){
			System.out.println("\nOrder Discarded!\n");
			Order.id--;
			return;
		}
		
		//Placing Order
		dao.addOrder(order);
		int oId = order.getoId();
		dao.getFarmer(product.getFarmerId()).addOrderId(oId);
		dao.getCustomer(customer.getUserId()).addOrder(oId);
		product.setQuantity(product.getQuantity()-qty);
		dao.updateProduct(product);
		
		order.remark = "Order Confirmed!";
		System.out.println("Order Confirmed!");
		
		sc.nextLine();		// to Avoid Null Input Due to NextInt
	}

	//To View Orders with their Status
	private void viewOrders(Customer customer) {
		
		//iterating Orders ID List of Customer 
		Iterator<Integer> it = customer.getOrderIdList().iterator();
		int count = 1;
		System.out.println("\n-------------------------------------\n\t\tOrder Details\n-------------------------------------\n");
		while(it.hasNext()) {
			System.out.println("Order "+ count++);
			System.out.println(dao.getOrder(it.next()).orderDetailsForCustomer()+"\n"); 	//Retrieving Order From Order ID and Printing
		}
	}
	
	//To Cancel Order
	private void cancelOrder(Customer customer) {
		System.out.print("Enter Order ID to be Cancelled: ");
		int oId = sc.nextInt();
		Order order = dao.getOrder(oId);
		if(order == null) {		//Checking for Invalid Order
			System.out.println("\nOrder ID not Exists!");
			return;
		}
		
		//Getting Cancellation Order Cancellation
		System.out.println(order.orderDetailsForCustomer());
		System.out.println("\nDo you Want to Confirm your Order Cancellation(y/n): ");
		if(!(sc.next().equalsIgnoreCase("y"))){
			System.out.println("\nOrder Cancellation Discarded!\n");
			return;
		}
		
		System.out.println("Enter the Reason For Cancellation :");
		sc.nextLine();
		order.remark = "Order Cancelled due to: " + sc.nextLine();
		
		if(!order.cancelOrder()){		//Canceling  Order
			System.out.println("Unable to Cancel Order!");
			return;
		}
		
		System.out.println("Order Cancelled Successfully!");
		Product product = order.getProduct();
		product.setQuantity(product.getQuantity()+order.getQuantity());		//Increasing Product Quantity 
		
		//Updating Product and Order Details
		dao.updateProduct(product);
		dao.updateOrder(order);
			
	}
	
	//To Receive Order using OTP
	private void receiveOrder(Customer customer) {
		System.out.println("Enter Order ID to Recieve: ");
		int oId = sc.nextInt();
		Order order = dao.getOrder(oId);
		
		//Checking Whether Order Is Ready to Receive Or Not
		if(order.getStatus() != OrderStatus.dispatched) {
			System.out.println("Order Not Dispatched Yet!");
			return;
		}
		
		System.out.println(order.remark);		//Displaying OTP stored in Remark to Receive Order
		
		//Getting COnfirmation 
		System.out.print("Did you Recieved Your Order?(y/n): ");
		if(!(sc.next().equalsIgnoreCase("y"))){
			System.out.println("\nDidn\'t Received the Order!!\n");
			return;
		}
		
		System.out.println("Order Received Successfully!");
		
		order.setStatus(OrderStatus.received);		//Updating Status
		
		dao.updateOrder(order);		//Updating Order In OrdersList
		
	}

	
	
 	

	
	
	
	
}
