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

import com.budgetly.application.Service.BudgetServiceImpl;
import com.budgetly.application.entities.Budget;


@RestController
public class BudgetController {
	
	@Autowired 
	private BudgetServiceImpl service;
	
	
	// Retrieve all budgets
	@GetMapping(path = "/user/{id}/budgets/get-bugdets")
	public List<Budget> retrieveAll(ModelMap model) {
		
		List<Budget> budgets = service.getBudgets();
		
		// Throw error if budgets null
		if (budgets == null) {
			throw new Error("Error fetching budgets...");
		}
				
		// Pass data to modal -> budgets.jsp
		model.addAttribute("budgets", budgets);
		
		return budgets;
	}
	
	
	// Retrieve budget by id
	@GetMapping(path = "/user/{id}/budgets/get-budget/{budgetId}")
	public Budget getBudget(@PathVariable int budgetId, ModelMap model) {
		Budget budget = service.getBudget(budgetId);
		
		// Throw error if budgets null
		if (budget == null) {
			throw new Error("Error fetching budget...");
		}
		
		// Pass data to the model
		model.addAttribute("budget", budget);
		
		return budget; // jsp needing individual budget
	}
	
	
	// Save budget to db
	@PostMapping(path = "/user/{id}/budgets/create-budget")
	public String addBudget(@RequestBody Budget budget) {
		Budget savedBudget = service.saveBudget(budget);
		
		// Throw error if budget found
		if (budget == null) {
			 throw new Error("Error adding budget...");
		}
		
		// Create uri returning a link for the recently added budget
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{budgetId}")
				.buildAndExpand(savedBudget.getId())
				.toUri();
				
		ResponseEntity.created(location).build();
		
		return "createBudget.jsp";
	}
	
}
