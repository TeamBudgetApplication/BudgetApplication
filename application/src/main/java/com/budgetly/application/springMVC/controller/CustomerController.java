package com.budgetly.application.springMVC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.budgetly.application.Service.CustomerService;
import com.budgetly.application.entities.Customer;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/customer/register")
	public String signup(Model model) {
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		return "add-customer";
	}
	
	@PostMapping("/customer/processNewCustomer")
	public String processNewCustomer(@ModelAttribute("customer") Customer customer) {
		customerService.saveCustomer(customer);
		return "customer-dashboard";
	}
	
	@GetMapping("customer/updateCustomer")
	public String updateCustomer(Model model, @RequestParam("customerId") int customerId) {
		Customer customer = customerService.getCustomer(customerId);
		model.addAttribute("customer", customer);
		return "add-customer";
	}

}
