package com.budgetly.application.springMVC.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.budgetly.application.Service.BudgetService;
import com.budgetly.application.Service.ExpenseService;
import com.budgetly.application.entities.Budget;
import com.budgetly.application.entities.Expense;

@Controller
public class ExpenseController {
	
	@Autowired
	private ExpenseService expenseService;
	@Autowired
	private BudgetService budgetService;
	
	// Route retrieves all of a users budgets passes to jsp
	@GetMapping(path = "/expenses/budget-expenses/{budgetId}")
	public String getBudgetExpensesJsp(@PathVariable int budgetId, ModelMap model) {
		
		// Get User Budgets + Expenses
		List<Expense> expenses = expenseService.getExpenses(budgetId);
		
		// Add budgets to model for jsp interaction
		model.addAttribute("expenses", expenses);
		Budget budget = budgetService.retrieveUserBudgetById(budgetId);
		model.addAttribute("budget", budget);
		
		return "one-budget-expenses";
	}
	
	// Route retrieves a budget expense by id passes to jsp
	@GetMapping(path = "/expenses/budget-expense/{expenseId}")
	public String getBudgetExpenseJsp(@PathVariable int expenseId, ModelMap model) {
		// Get User Budget
		Expense expense = expenseService.getExpenseById(expenseId);
		
		// Add budgets to model for jsp interaction
		model.addAttribute("expense", expense);
		
		return "";
	}
	
	
//	// Add Expense Page
//	@PostMapping(path = "/expenses/add-expense")
//	public String addBudgetExpenseJsp(@RequestBody Expense expense) {
//		// Save submitted expense form
//		expenseService.saveExpense(expense);
//		
//		// redirect to expenses or budgets jsp
//		return "";
//	}
	
	
	@GetMapping(path = "/expenses/budget-expenses/addExpense")
	public ModelAndView addExpense(Model model, @RequestParam("budgetId") int id) {
		Budget budget = budgetService.retrieveUserBudgetById(id);
		model.addAttribute("budgetName", budget.getName());
		
		return new ModelAndView("add-expense", "newExpense", new Expense());
		
	}
	
	
	//delete expense from the page "one-budget-expenses"
	@PostMapping(path = "/expenses/budget-expenses/deleteExpense")
	public String deleteExpense(@RequestParam("expenseId") int id, RedirectAttributes redirectAttributes) {
		int budgetId = expenseService.getExpenseById(id).getBudget().getId();
		expenseService.deleteExpense(id);
		redirectAttributes.addAttribute("budgetId", budgetId);
		return "redirect:/expenses/budget-expenses/{budgetId}";
	}
	
}
