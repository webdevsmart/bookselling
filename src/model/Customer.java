package model;

public class Customer {
	private int id;
	private String full_name;
	private String email;
	private String address;
	
	public Customer() {
	}

	public Customer(int id, String full_name, String email, String address) {
		this.id = id;
		this.full_name = full_name;
		this.email = email;
		this.address = address;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFullName() {
		return full_name;
	}
	
	public void setFullName(String full_name) {
		this.full_name = full_name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}

}
