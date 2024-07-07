package Models;


public class Person extends Account{
	
	private String name;
	private Address address;
	private Long PhoneNumber;
	private String email;
	
	
	public Person(int id, String password, String name, Address address, Long phoneNumber, String email) {
		super(id, password);
		this.name = name;
		this.address = address;
		PhoneNumber = phoneNumber;
		this.email = email;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Long getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(Long phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
