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
		
		
	//Route retrieves all of a users budgets WITH EXPENSES passes to jsp
	@GetMapping(path = "/budgets/expenses/{customerId}")
	public String getAllUserExpensesJsp(@PathVariable int customerId, ModelMap model) {
		
		// Get User Budgets
		List<Budget> budgets = budgetService.retrieveUserBudgets(customerId);
		
		// Add budgets to model for jsp interaction
		model.addAttribute("budgets", budgets);	
			
		return "expenses-report";
	}
	
	//it's what chatGPT says
	
//	@RequestMapping("/budgets/user-budgets/updateBudget/{customerId}")
//	public String updateBudget(Model model, @PathVariable("customerId") int customerId, @RequestParam("budgetId") int budgetId) {
//	    Budget budget = budgetService.retrieveUserBudgetById(budgetId);
//	    model.addAttribute("budgetId", budgetId);
//	    model.addAttribute("budget", budget);
//	    model.addAttribute("customerId", customerId);
//	    return "update-budget-form";
//	}
//	
//	@RequestMapping("/budgets/user-budgets/updateBudget/processUpdateBudget")
//	public String processUpdateBudget(Model model, @ModelAttribute("budget") Budget budget, @RequestParam("customerId") int customerId, RedirectAttributes redirectAttributes) {
//	    budgetService.saveBudget(budget);
//	    redirectAttributes.addAttribute("customerId", customerId);
//	    return "redirect:/budgets/user-budgets/{customerId}";
//	}
	
	
	//Add New Budget from BUDGETS VIEW PAGE
		@RequestMapping(path = "/budgets/user-budgets/create-budget/{customerId}")
		public String addBudget(Model model, @PathVariable("customerId") int customerId, @RequestParam(required = false) Integer budgetId) {
			//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Budget budget = null;
//			String name;
//			String endDate;
//			String startDate;
//			double amount;
			
			if (budgetId != null) {
		        budget = budgetService.retrieveUserBudgetById(budgetId);
//		        name = budget.getName();
//	        	endDate = dateFormat.format(budget.getEndDate());
//	        	startDate = dateFormat.format(budget.getStartDate());
//	        	amount = budget.getAmount();
		    }
			model.addAttribute("budget", budget);
			model.addAttribute("budgetId", budgetId);
//			model.addAttribute("name", name);
//			model.addAttribute("endDate", endDate);
//			model.addAttribute("startDate", startDate);
//			model.addAttribute("amount", amount);
			
			// redirect to budgets jsp
			return "create-budget-from-budget";			
		}
	
	@RequestMapping("/budgets/user-budgets/create-budget/processBudget")
	public String processBudget(Model model, @ModelAttribute("budget") Budget budget, 
			String name, String endDate, String startDate, double amount, 
			@RequestParam("customerId") int customerId, @RequestParam(required = false) int budgetId, RedirectAttributes redirectAttributes) throws ParseException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//Budget budget = new Budget();
		
//		if (budget != null) {
//			//id = budget.getId();
//			name = budget.getName();
//	        endDate = dateFormat.format(budget.getEndDate());
//	        startDate = dateFormat.format(budget.getStartDate());
//	        amount = budget.getAmount();
//		} else {
//			budget = new Budget();
//		}
		
		if (budget == null) {
			budget = new Budget();
		}
		
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
	
	
	
	//Add New Budget from DASHBOARD
	@RequestMapping(path = "/budgets/create-budget/{customerId}")
	public String createBudget(Model model, @PathVariable("customerId") int customerId) {
			
		// redirect to budgets jsp
		return "create-budget-from-dashboard";
	}
	
	@RequestMapping("/budgets/create-budget/saveBudget")
	public String saveBudget(Model model,String name, String endDate, String startDate, double amount, @RequestParam("customerId") int customerId, RedirectAttributes redirectAttributes) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Budget budget = new Budget();
		Customer customer = customerService.getCustomer(customerId);
		budget.setCustomer(customer);
		budget.setName(name);
		budget.setAmount(amount);
		budget.setEndDate(dateFormat.parse(endDate));
		budget.setStartDate(dateFormat.parse(startDate));
		budgetService.saveBudget(budget);
		
		redirectAttributes.addAttribute("customerId", customerId);
		
		return "redirect:/budgets/user-budgets/{customerId}";
	}
	
	@GetMapping(path = "/budgets/user-budgets/searchByKeyword/{customerId}")
	public String searchByKeyword(@PathVariable int customerId, @RequestParam(required = false) String keyword, ModelMap model){

		Customer customer = customerService.getCustomer(customerId);
		List<Budget> budgets = new ArrayList<>();
		
		if (keyword == null || keyword.trim().isEmpty()) {
			budgets = budgetService.retrieveUserBudgets(customerId);
		} else {
			budgets = budgetService.getBudgetsByKeyword(customerId, keyword);
		}
		
		customer.setBudgets(budgets);		
		model.addAttribute("customer", customer);
		model.addAttribute("customerId", customerId);
		model.addAttribute("budgets", budgets);		
		model.addAttribute("numb", budgets.size());
		model.addAttribute("keyword", keyword);
		
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
