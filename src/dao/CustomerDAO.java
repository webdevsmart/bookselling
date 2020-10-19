package dao;

import java.util.List;

import model.Customer;

public interface CustomerDAO {

	// add Book
	public void addCustomer(Customer customer);

	// get all book list
	public List<Customer> getList();

	public Customer getCustomer(int id);
	
}
