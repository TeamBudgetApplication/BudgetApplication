package com.budgetly.application.Service;

import java.util.List;

import com.budgetly.application.entities.Budget;
import com.budgetly.application.entities.Expense;

public interface ExpenseService {
	
	public List<Expense> getExpenses(int budgetId);
	
	public Expense getExpenseById(int expenseId);
	
	public Expense deleteExpense(int expenseId);
	
	public Expense saveExpense(Expense expense);
	
	public List<Budget> totalExpensesForTheMonth(int customerId);
	
	public List<Budget> totalExpensesForTheWeek(int customerId);
	
	public List<Expense> mostRecentTransactions(int customerId);
	
	public double calculateMostRecentTransactions(int customerId);

}
