package com.budgetly.application.dao;

import java.util.List;

import com.budgetly.application.entities.Budget;


public interface BudgetDAO {
	
	public List<Budget> retrieveAll(int customerId);
	
	public Budget retrieveById(int budgetId);
	
	public Budget deleteById(int budgetId);
	
	public Budget saveBudget(Budget budget);
	
	public List<Budget> queryBudgetsOverAmount(int customerId);

	public List<Budget> budgetsActiveThisMonth(int customerId);

	public List<Budget> budgetsActiveThisWeek(int customerId);
}
