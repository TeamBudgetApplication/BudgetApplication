package com.budgetly.application.springMVC.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.budgetly.application.Service.BudgetService;
import com.budgetly.application.Service.ExpenseService;
import com.budgetly.application.entities.Budget;


@Controller
public class BudgetController {
	
	@Autowired
	private BudgetService budgetService;
	@Autowired
	private ExpenseService expenseService;
	
	// Route retrieves all of a users budgets passes to jsp
	@GetMapping(path = "/budgets/user-budgets/{customerId}")
	public String getUserBudgetsJsp(@PathVariable int customerId, ModelMap model) {
		
		// Get User Budgets
		List<Budget> budgets = budgetService.retrieveUserBudgets(customerId);
		
		// Add budgets to model for jsp interaction
		model.addAttribute("budgets", budgets);		
		model.addAttribute("numb", budgets.size());
		
		return "budgets";
	}
	
	// Route retrieves all of a users budgets WITH EXPENSES passes to jsp
	@GetMapping(path = "/budgets/expenses/{customerId}")
	public String getAllUserExpensesJsp(@PathVariable int customerId, ModelMap model) {
		
		// Get User Budgets
		List<Budget> budgets = budgetService.retrieveUserBudgets(customerId);
		
		// Add budgets to model for jsp interaction
		model.addAttribute("budgets", budgets);		
			
		return "expenses-report";
	}
		
	//delete expense in expenses report	
	@PostMapping(path = "/budgets/expenses/deleteExpense")
	public String deleteExpense(@RequestParam("expenseId") int id, RedirectAttributes redirectAttributes) {
		int customerId = expenseService.getExpenseById(id).getBudget().getCustomer().getId();
		expenseService.deleteExpense(id);
		redirectAttributes.addAttribute("customerId", customerId);
		return "redirect:/budgets/expenses/{customerId}";
	}
	
	// Route retrieves a users budget by id passes to jsp
	@GetMapping(path = "/budgets/user-budget/{budgetId}")
	public String getUserBudgetJsp(@PathVariable int budgetId, ModelMap model) {
		// Get User Budget
		Budget budget = budgetService.retrieveUserBudgetById(budgetId);
		System.out.println(budget);
		
		// Add budgets to model for jsp interaction
		model.addAttribute("budget", budget);
		
		return "";
	}
	
	// Add Expense Page
	@PostMapping(path = "/budgets/create-budget")
	public String addBudgetExpenseJsp(@RequestBody Budget budget) {
		// Save submitted budget form
		budgetService.saveBudget(budget);
			
		// redirect to budgets jsp
		return "";
	}
}
