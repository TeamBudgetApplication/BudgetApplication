package com.budgetly.application.springMVC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
		return "redirect:/customer-dashboard";
	}

	@GetMapping("customer/updateCustomer/{customerId}")
	public String updateCustomer(Model model, @PathVariable("customerId") int customerId) {
		Customer customer = customerService.getCustomer(customerId);
		model.addAttribute("customer", customer);
		return "add-customer";
	}

	@GetMapping("/customer/{customerId}")
	public String getCustomerById(Model model, @PathVariable("customerId") int customerId) {
	    Customer customer = customerService.getCustomer(customerId);
	    model.addAttribute("customer", customer);
	    return "customer-dashboard";
	}

}
