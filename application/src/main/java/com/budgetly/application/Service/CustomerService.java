package com.budgetly.application.Service;

import java.util.List;

import com.budgetly.application.entities.Customer;

public interface CustomerService {
	
	public List<Customer> getCustomers();
	
	public Customer getCustomer(int id);
	
	public void saveCustomer(Customer customer);

}
