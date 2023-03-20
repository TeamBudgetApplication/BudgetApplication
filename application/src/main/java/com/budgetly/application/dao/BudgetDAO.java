package com.budgetly.application.dao;

import java.util.List;

import com.budgetly.application.entities.Budget;


public interface BudgetDAO {
	
	public List<Budget> getBudgets();
	
	public List<Budget> getBudgets(int customerId);

	public Budget getBudget(int id);
	
	public void onlySaveBudget(Budget budget);

	public Budget saveBudget(Budget budget);
	
	public void deleteBudget(int id);

}
