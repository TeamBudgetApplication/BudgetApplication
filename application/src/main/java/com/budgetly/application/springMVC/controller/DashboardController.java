package com.budgetly.application.springMVC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.budgetly.application.Service.BudgetService;
import com.budgetly.application.Service.CustomerService;
import com.budgetly.application.Service.ExpenseService;

@Controller
public class DashboardController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private BudgetService budgetService;
	
	@Autowired
	private ExpenseService expenseService;
	
	

}
