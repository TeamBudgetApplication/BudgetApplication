package com.budgetly.application.Service;

import java.util.List;

import com.budgetly.application.entities.Expense;

public interface ExpenseService {
	
	public List<Expense> getExpenses();
	
	public List<Expense> getExpenses(int budgetId);

	public Expense getExpense(int id);

	public void onlySaveExpense(Expense expense);
	
	public Expense saveExpense(Expense expense);
	
	public void deleteExpense(int id);

}
