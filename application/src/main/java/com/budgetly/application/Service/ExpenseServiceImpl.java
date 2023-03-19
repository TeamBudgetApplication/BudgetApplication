 package com.budgetly.application.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.budgetly.application.dao.ExpenseDAO;
import com.budgetly.application.entities.Expense;
import com.budgetly.application.springMVC.controller.NotFoundException;


@Service
public class ExpenseServiceImpl implements ExpenseService {
	
	@Autowired
	private ExpenseDAO expenseDAO;
	
	@Transactional
	public List<Expense> getExpenses() {
		return expenseDAO.getExpenses();
	}
	
	@Transactional
	public List<Expense> getExpenses(int budgetId) {
		List<Expense> expenses = expenseDAO.getExpenses(budgetId);
		
		if (expenses.isEmpty()) {
			throw new NotFoundException("budget Id not found. Id: " + budgetId);
		}
		return expenses;
	}
	
	
	@Transactional
	public Expense getExpense(int id) {
		
		Expense expense = expenseDAO.getExpense(id);
		
		if (expense == null) {
			throw new NotFoundException("expense Id not found. Id: " + id);
		}
		
		return expenseDAO.getExpense(id);
	}
	
	@Transactional
	public void saveExpense(Expense expense) {
		expenseDAO.saveExpense(expense);
	}
	
	
	@Transactional
	public void deleteExpense(int id) {
		
		Expense expense = expenseDAO.getExpense(id);
		
		if (expense == null) {
			throw new NotFoundException("expense Id not found. Id: " + id);
		}
		
		expenseDAO.deleteExpense(id);
	}

}
