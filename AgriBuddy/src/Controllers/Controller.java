package Controllers;

import java.util.*;

import Models.*;

public class Controller {
	public static final Scanner sc= new Scanner(System.in);
	
	public static void main(String[] args) {
		int ch = 0;
		
		FarmerController fc = new FarmerController();
		CustomerController cc = new CustomerController();
		
		do {
			System.out.println("Welcome To AgriBuddy!\n1.Register\n2.Login\n3.Exit");
			System.out.println("Enter Your Choice: ");
			ch = sc.nextInt();
			switch(ch) {
				case 1:
					int user;
					do {
						System.out.println("May I Know Who Are You?\n1.Farmer Here to Sell Products\n2.Customer Here to Buy Products");
						user = sc.nextInt();
						sc.nextLine();
						if(user == 1) {
							
							fc.newFarmer();
						}
						else if(user == 2) {
							
							cc.newCustomer();
						}
						else {
							System.out.println("Invalid Option!");
						}
					}while((user != 1) && (user != 2));
					
					break;
					
				case 2:
					int userId;
					String password;
					System.out.println("Enter Your User Id:");
					userId = sc.nextInt();
					sc.nextLine();
					System.out.print("Enter Your Password: ");
					password = sc.nextLine();
					if(Account.validate(userId, password)) {
						if(userId>1000 && userId<2001) {
							fc.dashBoard(userId);
						}
						else {
							cc.dashBoard(userId);
						}
					}
					else {
						System.out.println("Invalid User Id or Password...\nPlease Try Again!");
					}
					
					break;
					
				case 3:
					System.out.println("Thank you!");
					break;
					
				default:
					System.out.println("Invalid Option.. Please Try Again!");
					break;
			}
			
		}while(ch!=3);
		
		sc.close();
		
		
	}

}
