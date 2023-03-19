package com.budgetly.application.Service;

import java.util.List;

import com.budgetly.application.entities.Budget;

public interface BudgetService {
	
	public List<Budget> getBudgets();
	
	public List<Budget> getBudgets(int customerId);

	public Budget getBudget(int id);

	public void saveBudget(Budget budget);
	
	public void deleteBudget(int id);

}
