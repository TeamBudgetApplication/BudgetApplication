package com.budgetly.application.dao;

import java.util.List;

import com.budgetly.application.entities.Expense;


public interface ExpenseDAO {
	
	public List<Expense> retrieveAll(int budgetId);
	
	public Expense retrieveById(int expenseId);
	
	public Expense deleteById(int expenseId);
	
	public Expense saveExpense(Expense expense);
	
}
