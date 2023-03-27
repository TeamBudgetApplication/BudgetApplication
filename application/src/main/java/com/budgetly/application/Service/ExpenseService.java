package com.budgetly.application.Service;

import java.util.List;

import com.budgetly.application.entities.Expense;

public interface ExpenseService {
	
	public List<Expense> getExpenses(int budgetId);
	
	public Expense getExpenseById(int expenseId);
	
	public Expense deleteExpense(int expenseId);
	
	public Expense saveExpense(Expense expense);

}
