package com.budgetly.application.springMVC.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		
		List<Expense> expenses = expenseService.getExpenses(budgetId);
		
		model.addAttribute("expenses", expenses);
		model.addAttribute("budgetId", budgetId);
		
		Budget budget = budgetService.retrieveUserBudgetById(budgetId);
		int customerId = budget.getCustomer().getId();
		model.addAttribute("budget", budget);
		model.addAttribute("customerId", customerId);
		
		return "one-budget-expenses";
	}
	
	@GetMapping(path = "/expenses/budget-expenses/returnBackButton")
	public String returnBackButton(@RequestParam("customerId") int customerId, RedirectAttributes redirectAttributes) {
		
		redirectAttributes.addAttribute("customerId", customerId);
		return "redirect:/budgets/user-budgets/{customerId}";
	}
	
	
	@GetMapping(path = "/expenses/budget-expenses/addExpense")
	public String addExpense(Model model, @RequestParam("budgetId") int budgetId) {
		Budget budget = budgetService.retrieveUserBudgetById(budgetId);
		String budgetName = budget.getName();
		int customerId = budget.getCustomer().getId();
		Expense expense = new Expense();
		model.addAttribute("expense", expense);
		model.addAttribute("budgetId", budgetId);
		model.addAttribute("budgetName", budgetName);
		model.addAttribute("customerId", customerId);
		
		return "add-expense";
		
	}
	
	@RequestMapping("/expenses/budget-expenses/processExpense")
	public String processExpense(Model model, @ModelAttribute("expense") Expense expense, @RequestParam("budgetId") int budgetId, RedirectAttributes redirectAttributes) {
		
		Budget budget = budgetService.retrieveUserBudgetById(budgetId);
		expense.setBudget(budget);
		expenseService.saveExpense(expense);
		
		redirectAttributes.addAttribute("budgetId", budgetId);
		
		return "redirect:/expenses/budget-expenses/{budgetId}";
	}
	
	
	@GetMapping(path = "/expenses/budget-expenses/returnToBudgetButton")
	public String returnToBudgetButton(@RequestParam("budgetId") int budgetId, RedirectAttributes redirectAttributes) {
		
		redirectAttributes.addAttribute("budgetId", budgetId);
		return "redirect:/expenses/budget-expenses/{budgetId}";
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
