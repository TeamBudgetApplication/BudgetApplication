package com.budgetly.application.springMVC.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.budgetly.application.Service.BudgetService;
import com.budgetly.application.Service.CustomerService;
import com.budgetly.application.entities.Budget;
import com.budgetly.application.entities.Customer;
import com.budgetly.application.entities.SearchRequest;


@Controller
public class BudgetController {
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private BudgetService budgetService;
	
	// Route retrieves all of a users budgets passes to jsp
	@GetMapping(path = "/budgets/user-budgets/{customerId}")
	public String getUserBudgetsJsp(@PathVariable int customerId, ModelMap model) {
		
		Customer customer = customerService.getCustomer(customerId);
		// Get User Budgets
		List<Budget> budgets = budgetService.retrieveUserBudgets(customerId);
		
		// Add budgets to model for jsp interaction
		model.addAttribute("customer", customer);
		model.addAttribute("customerId", customer.getId());
		model.addAttribute("budgets", budgets);		
		model.addAttribute("numb", budgets.size());
		
		return "budgets";
	}
	
	// Sort all budgets by name ASC
	@PostMapping(path = "/budgets/user-budgets/sortBudgetsByName")
	public String sortBudgetsByName(Model model, @RequestParam("customerId") int customerId, RedirectAttributes redirectAttributes){

		Customer customer = customerService.getCustomer(customerId);
		// Get User Budgets
		List<Budget> budgets = budgetService.retrieveAllByName(customerId);
		
		// Add budgets to model for jsp interaction
		model.addAttribute("customer", customer);	
		model.addAttribute("budgets", budgets);		
		model.addAttribute("numb", budgets.size());
		
		//redirectAttributes.addAttribute("customerId", customerId);
		
		return "budgets";
	}
	
	
	// Sort all budgets by date ASC
	
	@PostMapping(path = "/budgets/user-budgets/sortBudgetsByDate")
	public String sortBudgetsByDate(Model model, @RequestParam("customerId") int customerId, RedirectAttributes redirectAttributes){
		
		Customer customer = customerService.getCustomer(customerId);
		
		List<Budget> budgets = budgetService.retrieveAllByDate(customerId);
		
		// Add budgets to model for jsp interaction
		
		model.addAttribute("customer", customer);
		//model.addAttribute("customerId", customerId);
		model.addAttribute("budgets", budgets);		
		model.addAttribute("numb", budgets.size());
		
		redirectAttributes.addAttribute("customerId", customerId);
		
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
	
	// Add Expense Page
	@PostMapping(path = "/budgets/create-budget")
	public String addBudgetExpenseJsp(@RequestBody Budget budget) {
		// Save submitted budget form
		budgetService.saveBudget(budget);
			
		// redirect to budgets jsp
		return "add-budget";
	}
	
	@RequestMapping("/budgets/user-budgets/searchByKeyword/{customerId}")
	public String searchByKeyword(Model model, @RequestParam("customerId") int customerId) {
		
		
		SearchRequest searchRequest = new SearchRequest();
		
		String keyword = searchRequest.getKeyword();
		
		List<Budget> budgets = new ArrayList<>();
		
		if (keyword == null) {
			budgets = budgetService.retrieveUserBudgets(customerId);
		} else {
			budgets = budgetService.getBudgetsByKeyword(keyword);
		}
		
		model.addAttribute("budgets", budgets);
		model.addAttribute("customerId", customerId);
		//redirectAttributes.addAttribute("customerId", customerId);
		
		return "budgets";
		
	}
	
	//delete budget
	@PostMapping(path = "/budgets/user-budgets/deleteBudget")
	public String deleteBudget(@RequestParam("budgetId") int id, RedirectAttributes redirectAttributes) {
		int customerId = budgetService.retrieveUserBudgetById(id).getCustomer().getId();
		budgetService.deleteBudget(id);
		redirectAttributes.addAttribute("customerId", customerId);
		return "redirect:/budgets/user-budgets/{customerId}";
	}
}
