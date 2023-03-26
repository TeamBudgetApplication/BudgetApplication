package com.budgetly.application.dao;

import java.util.List;

import com.budgetly.application.entities.Budget;


public interface BudgetDAO {
	
	public List<Budget> retrieveAll(int customerId);
	
	public Budget retrieveById(int budgetId);
	
	public Budget deleteById(int budgetId);
	
	public Budget saveBudget(Budget budget);
}
