 package com.budgetly.application.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.budgetly.application.dao.BudgetDAO;
import com.budgetly.application.entities.Budget;


@Service
public class BudgetServiceImpl implements BudgetService {
	
	@Autowired
	private BudgetDAO budgetDAO;

	@Override
	public  List<Budget> retrieveUserBudgets(int customerId) {
		List<Budget> budgets = budgetDAO.retrieveAll(customerId);
		
		if (budgets.isEmpty()) {
			throw new NotFoundException("No budgets found for user with id: " + customerId);
		}
		
		return budgets;
	}

	@Override
	public Budget retrieveUserBudgetById(int budgetId) {
		Budget budget = budgetDAO.retrieveById(budgetId);
		
		if (budget == null) {
			throw new NotFoundException("No budget was found with id:  " + budgetId);
		}
		
		return budget;
	}
	
	@Override
	public Budget saveBudget(Budget budget) {
		Budget savedBudget = budgetDAO.saveBudget(budget);
		
		if (budget.getAmount() <= 0 || budget.getName() == "" || budget.getEndDate() == null || budget.getStartDate() == null) {
			throw new NotFoundException("Error cannot add empty budget add required budget field");
		}
		
		return savedBudget;
	}

	@Override
	public Budget deleteBudget(int budgetId) {
		Budget deletedBudget = budgetDAO.deleteById(budgetId);
		System.out.println("Deleted budget");
		return deletedBudget;
	}
	
	
}
