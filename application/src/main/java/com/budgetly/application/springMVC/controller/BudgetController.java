package com.budgetly.application.springMVC.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.budgetly.application.Service.BudgetService;
import com.budgetly.application.Service.CustomerService;
import com.budgetly.application.Service.ExpenseService;
import com.budgetly.application.entities.Budget;
import com.budgetly.application.entities.Customer;
import com.budgetly.application.entities.Expense;
import com.budgetly.application.entities.SearchRequest;




@Controller
public class BudgetController {
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private BudgetService budgetService;
	@Autowired
	private ExpenseService expenseService;
	
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
	
	// Sort all budgets by name
	@GetMapping(path = "/budgets/user-budgets/sortBudgetsByName/{customerId}")
	public String sortBudgetsByName(@PathVariable int customerId, ModelMap model){

		Customer customer = customerService.getCustomer(customerId);
		// Get User Budgets
		List<Budget> budgets = budgetService.retrieveAllByName(customerId);
		
		// Add budgets to model for jsp interaction
		model.addAttribute("customer", customer);
		model.addAttribute("customerId", customer.getId());
		model.addAttribute("budgets", budgets);		
		model.addAttribute("numb", budgets.size());
		
		return "budgets";
	}
	
	
	// Sort all budgets by date ASC
	
	@GetMapping(path = "/budgets/user-budgets/sortBudgetsByDate/{customerId}")
	public String sortBudgetsByDate(@PathVariable int customerId, ModelMap model){

		Customer customer = customerService.getCustomer(customerId);
		// Get User Budgets
		List<Budget> budgets = budgetService.retrieveAllByDate(customerId);
		
		// Add budgets to model for jsp interaction
		model.addAttribute("customer", customer);
		model.addAttribute("customerId", customer.getId());
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
	
	// Add Expense Page
	@PostMapping(path = "/budgets/create-budget")
	public String addBudgetExpenseJsp(@RequestBody Budget budget) {
		// Save submitted budget form
		budgetService.saveBudget(budget);
		
		// redirect to budgets jsp
		return "add-budget";
	}
	
	// Add New Budget
	@RequestMapping(path = "/budgets/create-budget/{customerId}")
	public String addBudget(Model model, @PathVariable("customerId") int customerId) {
			
		// redirect to budgets jsp
		return "add-budget";
	}
	
	@RequestMapping("/budgets/create-budget/processBudget")
	public String processBudget(Model model,String name, String endDate, String startDate, double amount, @RequestParam("customerId") int customerId, RedirectAttributes redirectAttributes) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Budget budget = new Budget();
		Customer customer = customerService.getCustomer(customerId);
		budget.setCustomer(customer);
		System.out.println(customer.getId());
		budget.setName(name);
		System.out.println(name);
		budget.setAmount(amount);
		System.out.println(amount);
		budget.setEndDate(dateFormat.parse(endDate));
		System.out.println(budget.getEndDate());
		budget.setStartDate(dateFormat.parse(startDate));
		System.out.println(budget.getStartDate());
		budgetService.saveBudget(budget);
		
		redirectAttributes.addAttribute("customerId", customerId);
		
		return "redirect:/budgets/user-budgets/{customerId}";
	}
	
//	@RequestMapping("/budgets/user-budgets/{customerId}/searchByKeyword/{keyword}")
//	public String searchByKeyword(Model model, @RequestParam("customerId") int customerId) {
//	
//		SearchRequest searchRequest = new SearchRequest();
//		
//		String keyword = searchRequest.getKeyword();
//		
//		List<Budget> budgets = new ArrayList<>();
//		
//		if (keyword == null) {
//			budgets = budgetService.retrieveUserBudgets(customerId);
//		} else {
//			budgets = budgetService.getBudgetsByKeyword(keyword);
//		}
//		
//		model.addAttribute("budgets", budgets);
//		model.addAttribute("customerId", customerId);
//		//redirectAttributes.addAttribute("keyword", keyword);
//		
//		return "budgets";
//		
//	}

	
	@RequestMapping("/budgets/user-budgets/{customerId}/searchForm")
	public String searchForm(Model model, @RequestParam("customerId") int customerId) {

		model.addAttribute("customerId", customerId);
		
		return "redirect:/searchOutput";
		
	}

	@GetMapping("/budgets/user-budgets/searchByKeyword/{customerId}/{keyword}")
	public String searchByKeyword(Model model, @ModelAttribute("searchRequest") SearchRequest searchRequest, @RequestParam("customerId") int customerId) {
		
		String keyword = searchRequest.getKeyword();
		
		List<Budget> budgets = new ArrayList<>();
		
		if (keyword == null) {
			budgets = budgetService.retrieveUserBudgets(customerId);
		} else {
			budgets = budgetService.getBudgetsByKeyword(keyword);
		}
		
		model.addAttribute("budgets", budgets);
		model.addAttribute("customerId", customerId);
		
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
	
	//delete expense from the page "expenses-report"
		@PostMapping(path = "/budgets/expenses/deleteExpense")
		public String deleteExpense(@RequestParam("expenseId") int id, RedirectAttributes redirectAttributes) {
			int customerId = expenseService.getExpenseById(id).getBudget().getCustomer().getId();
			expenseService.deleteExpense(id);
			redirectAttributes.addAttribute("customerId", customerId);
			return "redirect:/budgets/expenses/{customerId}";
		}
}
