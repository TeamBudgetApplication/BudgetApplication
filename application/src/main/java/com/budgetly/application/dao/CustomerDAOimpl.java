package com.budgetly.application.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.budgetly.application.entities.Customer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class CustomerDAOimpl implements CustomerDAO {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public List<Customer> getCustomers() {
		TypedQuery<Customer> query = entityManager.createQuery("SELECT * FROM Customer ", Customer.class);		
		return query.getResultList();
	}

	@Override
	public void saveCustomer(Customer customer) {
		entityManager.merge(customer);
	}
	
	@Override
    public Customer getCustomer(int id) {
        return entityManager.find(Customer.class, id);
    }
	
}
