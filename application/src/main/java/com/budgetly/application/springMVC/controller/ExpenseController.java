package com.budgetly.application.springMVC.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.budgetly.application.Service.ExpenseService;
import com.budgetly.application.entities.Expense;



@Controller
public class ExpenseController {
	
	@Autowired
	private ExpenseService service;
	
	// Route retrieves all of a users budgets passes to jsp
	@GetMapping(path = "/expenses/budget-expenses/{budgetId}")
	public String getBudgetExpensesJsp(@PathVariable int budgetId, ModelMap model) {
		
		// Get User Budgets
		List<Expense> expenses = service.getExpenses(budgetId);
		
		// Add budgets to model for jsp interaction
		model.addAttribute("expenses", expenses);
		
		return "expenses";
	}
	
	// Route retrieves a budget expenses by id passes to jsp
	@GetMapping(path = "/expenses/budget-expense/{budgetId}")
	public String getBudgetExpenseJsp(@PathVariable int budgetId, ModelMap model) {
		// Get User Budget
		Expense expense = service.getExpenseById(budgetId);
		
		// Add budgets to model for jsp interaction
		model.addAttribute("expense", expense);
		
		return "single-expense";
	}
	
	
	// Add Expense Page
	@PostMapping(path = "/expenses/add-expense")
	public String addBudgetExpenseJsp(@RequestBody Expense expense) {
		// Save submitted expense form
		service.saveExpense(expense);
		
		// redirect to expenses or budgets jsp
		return "expenses";
	}
}
