package com.budgetly.application.springMVC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@RequestMapping("/customer/register")
	public String signup(Model model) {
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		return "add-customer";
	}

	@PostMapping("/customer/processNewCustomer")
	public String processNewCustomer(@ModelAttribute("customer") Customer customer) {
		String password = customer.getPassword();
		String hashedPassword = passwordEncoder.encode(password);
		customer.setPassword(hashedPassword);
		customerService.saveCustomer(customer);
		return "redirect:/customer-dashboard";
	}

	@GetMapping("customer/updateCustomer/{customerId}")
	public String updateCustomer(Model model, @PathVariable("customerId") int customerId) {
		Customer customer = customerService.getCustomer(customerId);
		model.addAttribute("customer", customer);
		return "add-customer";
	}
	
	@PostMapping("/customer/authenticate") // Use this method when building login page and when posting, route to this method to verify login credentials 
	public String authenticate(Model model, @PathVariable("customerId") int customerId, @ModelAttribute("customer") Customer customer) {
	    Customer existingCustomer = customerService.getCustomer(customerId);
	    if (existingCustomer != null && passwordEncoder.matches(customer.getPassword(), existingCustomer.getPassword())) {
	        model.addAttribute("customer", existingCustomer);
	        return "customer-dashboard";
	    } 
	    else {
	        model.addAttribute("error", "Invalid email or password"); // Might need to replace this with our custom error toast pop-up
	        return "login";
	    }
	}


}
