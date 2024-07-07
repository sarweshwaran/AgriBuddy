package Models;

public class Product {
	static int id = 5001;
	private int pId;
	private String name;
	private int quantity;
	private float price;
	private int farmerId;
	
	
	
	public int getFarmerId() {
		return farmerId;
	}

	public Product(int farmerId, String name, int quantity, float price) {
		this.pId = id++;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.farmerId = farmerId;
	}

	public Product(int userId, int pId, String name, int quantity, float price) {
		this.pId = pId;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.farmerId = userId;
	}

	public int getpId() {
		return pId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	
	public String toString() {
		return this.pId +"\t"+ this.name+"\t"+this.quantity+"\tRs."+this.price;
	}
	
	

}
