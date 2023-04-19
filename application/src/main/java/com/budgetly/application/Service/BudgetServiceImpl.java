 package com.budgetly.application.Service;

import java.util.Collections;
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
	@Override
	public  List<Budget> retrieveUserBudgets(int customerId) {
		List<Budget> budgets = budgetDAO.retrieveAll(customerId);
		
		if (budgets == null) {
			return Collections.emptyList();
		}
		
		return budgets;
	}
	
	@Transactional
	public List<Budget> retrieveAllByName(int customerId) {
		List<Budget> budgets = budgetDAO.retrieveAllByName(customerId);
		
		if (budgets == null) {
			return Collections.emptyList();
		}
		
		return budgets;
	}
	
	@Transactional
	public List<Budget> retrieveAllByDate(int customerId) {
		List<Budget> budgets = budgetDAO.retrieveAllByDate(customerId);
		
		if (budgets == null) {
			return Collections.emptyList();
		}
		
		return budgets;
	}
	
	@Transactional
	@Override
	public Budget retrieveUserBudgetById(int budgetId) {
		Budget budget = budgetDAO.retrieveById(budgetId);
		
		if (budget == null) {
			throw new NotFoundException("No budget was found with id:  " + budgetId);
		}
		
		return budget;
	}
	@Transactional
	@Override
	public Budget saveBudget(Budget budget) {
		Budget savedBudget = budgetDAO.saveBudget(budget);
		
		if (budget.getAmount() <= 0 || budget.getName() == "" || budget.getEndDate() == null || budget.getStartDate() == null) {
			throw new NotFoundException("Error cannot add empty budget add required budget field");
		}
		
		return savedBudget;
	}
	@Transactional
	@Override
	public Budget deleteBudget(int budgetId) {
		Budget deletedBudget = budgetDAO.deleteById(budgetId);
		System.out.println("Deleted budget");
		return deletedBudget;
	}

	@Override
	@Transactional
	public List<Budget> queryBudgetsOverAmount(int customerId) {
		// TODO Auto-generated method stub
		return budgetDAO.queryBudgetsOverAmount(customerId);
	}
	@Transactional
	@Override
	public List<Budget> budgetsActiveThisMonth(int customerId) {
		// TODO Auto-generated method stub
		return budgetDAO.budgetsActiveThisMonth(customerId);
	}
	@Transactional
	@Override
	public List<Budget> budgetsActiveThisWeek(int customerId) {
		// TODO Auto-generated method stub
		return budgetDAO.budgetsActiveThisWeek(customerId);
	}
	
	@Override
	@Transactional
	public List<Budget> getBudgetsByKeyword(String keyword) {
		
		List<Budget> budgets = budgetDAO.getBudgetsByKeyword(keyword);
		
		if (budgets == null) {
			return Collections.emptyList();
		}
		
		return budgets;
	}
	
	
}
