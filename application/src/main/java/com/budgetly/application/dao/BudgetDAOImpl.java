package com.budgetly.application.dao;


import java.util.List;

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

	@Override
	public List<Budget> queryBudgetsOverAmount(int customerId) {
	    TypedQuery<Budget> query = entityManager.createQuery("SELECT b " +
	            "FROM Budget b " +
	            "JOIN b.customer c " +
	            "JOIN b.expenses e " +
	            "WHERE c.id = :customerId " +
	            "GROUP BY b.id " +
	            "HAVING b.amount < SUM(e.amount)", Budget.class);
	    List<Budget> budgets = query.setParameter("customerId", customerId).getResultList();
	    return budgets;
	}

	@Override
	public List<Budget> budgetsActiveThisMonth(int customerId) {
	    TypedQuery<Budget> query = entityManager.createQuery("SELECT b " +
	            "FROM Budget b " +
	            "JOIN b.customer c " +
	            "WHERE c.id = :customerId " +
	            "AND FUNCTION('MONTH', b.startDate) = FUNCTION('MONTH', CURRENT_DATE()) " +
	            "AND FUNCTION('YEAR', b.startDate) = FUNCTION('YEAR', CURRENT_DATE()) ", Budget.class);
	    List<Budget> budgets = query.setParameter("customerId", customerId).getResultList();
	    return budgets;
	}
	
	@Override
	public List<Budget> budgetsActiveThisWeek(int customerId) {
	    TypedQuery<Budget> query = entityManager.createQuery("SELECT b " +
	            "FROM Budget b " +
	            "JOIN b.customer c " +
	            "WHERE c.id = :customerId " +
	            "AND FUNCTION('WEEK', b.startDate) = FUNCTION('WEEK', CURRENT_DATE()) " +
	            "AND FUNCTION('YEAR', b.startDate) = FUNCTION('YEAR', CURRENT_DATE()) ", Budget.class);
	    List<Budget> budgets = query.setParameter("customerId", customerId).getResultList();
	    return budgets;
	}

}
