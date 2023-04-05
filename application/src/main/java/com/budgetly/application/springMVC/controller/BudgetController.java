package com.budgetly.application.springMVC.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.budgetly.application.Service.BudgetService;
import com.budgetly.application.entities.Budget;


@Controller
public class BudgetController {
	
	@Autowired
	private BudgetService service;
	
	// Route retrieves all of a users budgets passes to jsp
	@GetMapping(path = "/budgets/user-budgets/{customerId}")
	public String getUserBudgetsJsp(@PathVariable int customerId, ModelMap model) {
		
		// Get User Budgets
		List<Budget> budgets = service.retrieveUserBudgets(customerId);
		
		// Add budgets to model for jsp interaction
		model.addAttribute("budgets", budgets);
		
		return ""; // budgets.jsp
	}
	
	// Route retrieves a users budget by id passes to jsp
	@GetMapping(path = "/budgets/user-budget/{budgetId}")
	public String getUserBudgetJsp(@PathVariable int budgetId, ModelMap model) {
		// Get User Budget
		Budget budget = service.retrieveUserBudgetById(budgetId);
		System.out.println(budget);
		
		// Add budgets to model for jsp interaction
		model.addAttribute("budget", budget);
		
		return ""; // single-budget if applicable
	}
	
	// Add Expense Page
	@PostMapping(path = "/budgets/create-budget")
	public String addBudgetExpenseJsp(@RequestBody Budget budget) {
		// Save submitted budget form
		service.saveBudget(budget);
			
		// redirect to budgets jsp
		return "";
	}
}
