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

	// Get expenses
	public List<Expense> getExpenses(int budgetId) {
		
		List<Expense> expenses = expenseDAO.retrieveAll(budgetId);
		
		if (expenses.isEmpty()) {
			throw new NotFoundException("Error no expenses found...");
		}
		
		return expenses;
	}

	
	// Get single expense
	public Expense getExpenseById(int expenseId) {
		Expense expense = expenseDAO.retrieveById(expenseId);
		
		if (expense == null) {
			throw new NotFoundException("Error no expense found.");
		}
		
		return expense;
	}
	
	
	// Save budget expense
	public Expense saveExpense(Expense expense) {
		Expense savedExpense = expenseDAO.saveExpense(expense);
		
		if (savedExpense == null) {
			throw new Error("Empty Expense not added please add fields.");
		}
		
		return savedExpense;
	}
	
	
	// Delete budget expense
	public Expense deleteExpense(int expenseId) {
		return expenseDAO.deleteById(expenseId);
	}


}
