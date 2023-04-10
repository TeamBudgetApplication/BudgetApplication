package com.budgetly.application.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.budgetly.application.entities.Budget;
import com.budgetly.application.entities.Expense;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class ExpenseDAOimpl implements ExpenseDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	// Get All budget expenses
	public List<Expense> retrieveAll(int budgetId) {
		Budget budget = entityManager.find(Budget.class, budgetId);
		TypedQuery<Expense> query = entityManager.createQuery("SELECT e FROM Expense e WHERE e.budget = :budget", Expense.class);
		return query.setParameter("budget", budget).getResultList();
	}
	
	
	// Get single expense by id
	public Expense retrieveById(int expenseId) {
		return entityManager.find(Expense.class, expenseId);
	}
	
	// Save expense
	public Expense saveExpense(Expense expense) {
		return entityManager.merge(expense);
	}


	// Delete expense
	public Expense deleteById(int expenseId) {
		Expense expense = entityManager.find(Expense.class, expenseId);
		entityManager.remove(expense);
		return expense;
	}
}
