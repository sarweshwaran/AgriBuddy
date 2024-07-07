package Models;

public class Address {
	private String addLine1;
	private String addLine2;
	private String city;
	private String state;
	private int pin;
	
	public Address(String addLine1, String addLine2, String city, String state, int pin) {
		this.addLine1 = addLine1;
		this.addLine2 = addLine2;
		this.city = city;
		this.state = state;
		this.pin = pin;
	}
	
	public String getAddLine1() {
		return addLine1;
	}
	public void setAddLine1(String addLine1) {
		this.addLine1 = addLine1;
	}
	public String getAddLine2() {
		return addLine2;
	}
	public void setAddLine2(String addLine2) {
		this.addLine2 = addLine2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	
	public String toString() {
		return addLine1+",\n"+addLine2+",\n"+city+",\n"+state+" - "+pin;
	}
	
}
