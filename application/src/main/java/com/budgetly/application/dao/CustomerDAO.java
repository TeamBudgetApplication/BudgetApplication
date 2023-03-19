package com.budgetly.application.dao;

import java.util.List;

import com.budgetly.application.entities.Customer;


public interface CustomerDAO {
	
	public List<Customer> getCustomers();

	public Customer getCustomer(int id);

	public void saveCustomer(Customer customer);

}
