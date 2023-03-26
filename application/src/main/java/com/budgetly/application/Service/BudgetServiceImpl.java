 package com.budgetly.application.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.budgetly.application.dao.BudgetDAO;
import com.budgetly.application.dao.BudgetDAOImpl;
import com.budgetly.application.entities.Budget;
import com.budgetly.application.springMVC.controller.NotFoundException;


@Service
@Transactional
public class BudgetServiceImpl implements BudgetService {
	
	@Autowired
	private BudgetDAO budgetDAO;

	@Override
	public  List<Budget> retrieveUserBudgets(int customerId) {
		return budgetDAO.retrieveAll(customerId);
	}

	@Override
	public Budget retrieveUserBudgetById(int budgetId) {
		return budgetDAO.retrieveById(budgetId);
	}

	@Override
	public Budget deleteBudget(int budgetId) {
		return budgetDAO.deleteById(budgetId);
	}

	@Override
	public Budget saveBudget(Budget budget) {
		return budgetDAO.saveBudget(budget);
	}
	
	
}
