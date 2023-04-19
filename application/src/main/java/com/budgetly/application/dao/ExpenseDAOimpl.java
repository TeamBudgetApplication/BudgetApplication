package com.budgetly.application.dao;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.budgetly.application.entities.Budget;
import com.budgetly.application.entities.Expense;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ExpenseDAOimpl implements ExpenseDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	// Get All budget expenses
	public List<Expense> retrieveAll(int budgetId) {
		Budget budget = entityManager.find(Budget.class, budgetId);
		TypedQuery<Expense> query = entityManager.createQuery("SELECT e FROM Expense e WHERE e.budget = :budget ORDER BY id", Expense.class);
		return query.setParameter("budget", budget).getResultList();
	}
	
	// Get single budget expense by id
	public Expense retrieveById(int expenseId) {
		return entityManager.find(Expense.class, expenseId);
	}
	
	// Save Budget Expense
	public Expense saveExpense(Expense expense) {
		return entityManager.merge(expense);
	}


	// Delete budget expense
	public Expense deleteById(int expenseId) {
		Expense expense = entityManager.find(Expense.class, expenseId);
		entityManager.remove(expense);
		return expense;
	}

	@Override
	public List<Budget> totalExpensesForTheMonth(int customerId) {
		// TODO Auto-generated method stub
		TypedQuery<Budget> query = entityManager.createQuery(
		        "SELECT SUM(e.amount) " +
		        "FROM Expense e " +
		        "JOIN e.budget b " +
		        "JOIN b.customer c " +
		        "WHERE c.id = :customerId " +
		        "AND FUNCTION('MONTH', e.expenseDate) = FUNCTION('MONTH', CURRENT_DATE()) " +
		        "AND FUNCTION('YEAR', e.expenseDate) = FUNCTION('YEAR', CURRENT_DATE()) ", Budget.class);
		List<Budget> expenses = query.setParameter("customerId", customerId).getResultList();
		if (expenses == null) {
			return Collections.emptyList();
		}
		return expenses;
	}

	@Override
	public List<Budget> totalExpensesForTheWeek(int customerId) {
		// TODO Auto-generated method stub
		TypedQuery<Budget> query = entityManager.createQuery(
		        "SELECT SUM(e.amount) " +
		        "FROM Expense e " +
		        "JOIN e.budget b " +
		        "JOIN b.customer c " +
		        "WHERE c.id = :customerId " +
		        "AND FUNCTION('WEEK', e.expenseDate) = FUNCTION('WEEK', CURRENT_DATE()) " +
		        "AND FUNCTION('YEAR', e.expenseDate) = FUNCTION('YEAR', CURRENT_DATE()) ", Budget.class);
		List<Budget> expenses = query.setParameter("customerId", customerId).getResultList();
		if (expenses == null) {
			return Collections.emptyList();
		}
		return expenses;
	}

	@Override
	public List<Expense> mostRecentTransactions(int customerId) {
	    TypedQuery<Expense> query = entityManager.createQuery(
	        "SELECT e " +
	        "FROM Expense e " +
	        "JOIN e.budget b " +
	        "JOIN b.customer c " +
	        "WHERE c.id = :customerId " +
	        "AND FUNCTION('MONTH', e.expenseDate) = FUNCTION('MONTH', CURRENT_DATE()) " +
	        "AND FUNCTION('YEAR', e.expenseDate) = FUNCTION('YEAR', CURRENT_DATE()) " +
	        "ORDER BY e.expenseDate DESC",
	        Expense.class
	    );
	    List<Expense> expenses = query.setParameter("customerId", customerId).setMaxResults(15).getResultList();
	    if (expenses == null) {
	    	return Collections.emptyList();
	    }
	    return expenses;
	}

	@Override
	public double calculateMostRecentTransactions(int customerId) {
	    TypedQuery<Double> query = entityManager.createQuery(
	        "SELECT SUM(e.amount) " +
	        "FROM Expense e " +
	        "JOIN e.budget b " +
	        "JOIN b.customer c " +
	        "WHERE c.id = :customerId " +
	        "AND FUNCTION('MONTH', e.expenseDate) = FUNCTION('MONTH', CURRENT_DATE()) " +
	        "AND FUNCTION('YEAR', e.expenseDate) = FUNCTION('YEAR', CURRENT_DATE())",
	        Double.class
	    );
	    Double expenses = query.setParameter("customerId", customerId).getSingleResult();
	    if (expenses == null) {
	    	return 0.00;
	    }
	    return expenses;
	}

}
