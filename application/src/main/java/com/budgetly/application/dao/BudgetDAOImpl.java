package com.budgetly.application.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.budgetly.application.entities.Budget;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;


@Repository
@Transactional
public class BudgetDAOImpl implements BudgetDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	// Retrieve User budgets by using customer id
	public List<Budget> retrieveAll(int customerId) {
		
		TypedQuery<Budget> query = entityManager
				.createQuery("SELECT b FROM Budget b WHERE b.customer.id = :id ", Budget.class);
		
				List<Budget> budgets = query.setParameter("id", customerId).getResultList();
				
				return budgets;
		
	}
	
	// Retrieve User single budget by using budget id
	public Budget retrieveById(int budgetId) {
		// Find budget
		Budget budget = entityManager.find(Budget.class, budgetId);
		return budget;
	}
	
	
	// Save Budget
	public Budget saveBudget(Budget budget) {
		entityManager.merge(budget);
		return budget;
	}
	

	// Delete Budget
	public Budget deleteById(int budgetId) {
		// Find budget
		Budget budget = entityManager.find(Budget.class, budgetId);
		entityManager.remove(budget);
		return budget;
		
	}
	
	

}
