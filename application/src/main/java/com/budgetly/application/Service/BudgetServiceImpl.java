 package com.budgetly.application.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.budgetly.application.dao.BudgetDAO;
import com.budgetly.application.entities.Budget;
import com.budgetly.application.springMVC.controller.NotFoundException;


@Service
public class BudgetServiceImpl implements BudgetService {
	
	@Autowired
	private BudgetDAO budgetDAO;
	
	@Transactional
	public List<Budget> getBudgets() {
		return budgetDAO.getBudgets();
	}
	
	@Transactional
	public List<Budget> getBudgets(int customerId) {
		List<Budget> budgets = budgetDAO.getBudgets(customerId);
		
		if (budgets.isEmpty()) {
			throw new NotFoundException("customer Id not found. Id: " + customerId);
		}
		return budgets;
	}
	
	
	@Transactional
	public Budget getBudget(int id) {
		
		Budget budget = budgetDAO.getBudget(id);
		
		if (budget == null) {
			throw new NotFoundException("budget Id not found. Id: " + id);
		}
		
		return budgetDAO.getBudget(id);
	}
	
	@Transactional
	public void saveBudget(Budget budget) {
		budgetDAO.saveBudget(budget);
	}

	@Transactional
	public void deleteBudget(int id) {
		
		Budget budget = budgetDAO.getBudget(id);
		
		if (budget == null) {
			throw new NotFoundException("budget Id not found. Id: " + id);
		}
		
		budgetDAO.deleteBudget(id);
	}
	
}
