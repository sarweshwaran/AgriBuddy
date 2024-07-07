package Models;

public class Order {
	public static int id = 10001;
	private int oId;
	private Product product;
	private Customer customer;
	private int quantity;
	private float totalPrice;
	private OrderStatus status;
	public String remark;
	
	public Order(Product product, Customer customer, int quantity) {
		this.oId = id++;
		this.product = product;
		this.customer = customer;
		this.quantity = quantity;
		this.totalPrice = product.getPrice()*quantity;
		this.status = OrderStatus.confirmed;
	}
	
	public int getoId() {
		return oId;
	}
	
	public Product getProduct() {
		return this.product;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public Customer getCustomer() {
		return this.customer;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
		
	}

	public OrderStatus getStatus() {
		return status;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public boolean cancelOrder() {
		if(status == OrderStatus.confirmed) {
			status = OrderStatus.cancelled;
			return true;
		}
		return false;
	}

	public void printBill() {

		System.out.println(   "\n---------------------------------\n"
							+ "            Order Summary" 
							+ "\n---------------------------------\n");
		System.out.println("Order Number : \t"+ this.getoId());
		System.out.println("Product Name : \t"+ this.product.getName());
		System.out.println("Product Quantity : \t"+ this.quantity);
		System.out.println("Price Per Unit : \t"+ this.product.getPrice());
		System.out.println(   "\n---------------------------------");
		System.out.println("Total Price : \t"+ this.totalPrice);
		System.out.println(   "\n---------------------------------");
		
	}
	
	public String orderDetailsForFarmer() {
		return "Order ID: \t"+this.oId+
				"\nProduct Name :\t"+this.product.getName()+
				"\nProduct ID:\t"+this.product.getpId()+
				"\nCustomer ID:\t"+this.customer.getUserId()+
				"\nQuantity :\t"+this.quantity+
				"\nStatus :\t"+this.status;
		
	}

	public String orderDetailsForCustomer() {
		return "Order ID: \t"+this.oId+
				"\nProduct Name :\t"+this.product.getName()+
				"\nProduct ID:\t"+this.product.getpId()+
				"\nFarmer ID:\t"+this.product.getFarmerId()+
				"\nQuantity :\t"+this.quantity+
				"\nTotal Price: \t"+this.totalPrice+
				"\nStatus :\t"+this.status;
	}

	public String toString() {
		return "Order ID: \t"+this.oId+"\nProduct Name :\t"+this.product.getName()
		+"\nFarmer ID:\t"+this.product.getFarmerId()+
		"\nCustomer ID:\t"+this.customer.getUserId()+
		"\nStatus :\t"+this.status;
				
	}
	
	
	
}
