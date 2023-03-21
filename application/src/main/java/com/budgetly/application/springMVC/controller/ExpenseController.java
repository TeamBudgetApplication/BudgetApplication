package com.budgetly.application.springMVC.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.budgetly.application.Service.ExpenseServiceImpl;
import com.budgetly.application.entities.Expense;

@RestController
public class ExpenseController {
	
	
	@Autowired
	private ExpenseServiceImpl service;
	
	
	// Retrieve expenses
	@GetMapping("/budgets/{budgetId}/expenses")
	public String retrieveAll(ModelMap model) {
		List<Expense> expenses = service.getExpenses();
		
		// Throw error if expenses null
		if (expenses == null) {
			throw new Error("Error fetching expenses...");
		}
		
		// Pass expenses to model
		model.addAttribute("expenses", expenses);
		
		return "expenses.jsp";
	}
	
	
	// Retrieve expense by id
	@GetMapping("/budgets/{budgetId}/expenses/{expId}")
	public String retrieveAll(@PathVariable int expId, ModelMap model) {
		Expense expense = (Expense) service.getExpenses(expId);
		
		// Throw error if expenses null
		if (expense == null) {
			throw new Error("Error fetching expenses...");
		}
		
		// Pass expenses to model
		model.addAttribute("expense", expense);
		
		return ""; // jsp which needs indiviual expense
	}
	
	// Save expense to Db
	@PostMapping(path = "/budget/{budgetId}/expenses") 
	public String addExpense(@RequestBody Expense expense) {
		Expense savedExpense = service.saveExpense(expense);
		
		// Throw error if expenses null
		if (savedExpense == null) {
			throw new Error("Error adding expense...");
		}
		
		// Create uri returning a link for the recently added budget
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{expId}")
				.buildAndExpand(savedExpense.getId())
				.toUri();
						
		ResponseEntity.created(location).build();
		
		return "createExpense.jsp";
	}
	
	
}
