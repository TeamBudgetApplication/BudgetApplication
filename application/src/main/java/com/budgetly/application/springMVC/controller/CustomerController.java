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
import org.springframework.web.bind.annotation.RequestParam;

import com.budgetly.application.Service.CustomerService;
import com.budgetly.application.entities.Customer;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
		return "login";
	}

	@GetMapping("customer/updateCustomer/{customerId}")
	public String updateCustomer(Model model, @PathVariable("customerId") int customerId) {
		Customer customer = customerService.getCustomer(customerId);
		model.addAttribute("customer", customer);
		return "add-customer";
	}
	
//	@GetMapping("customer/{customerId}")
//	public String displayHomePage(Model model, @PathVariable("customerId") int customerId) {
//		Customer customer = customerService.getCustomer(customerId);
//		model.addAttribute("customer", customer);
//		return "customer-dashboard";
//	}
	
	@PostMapping("/customer/authenticate")
	public String authenticate(Model model, HttpServletRequest request, HttpServletResponse response,
	                           @PathVariable("customerId") int customerId, @ModelAttribute("customer") Customer customer) {
	    Customer existingCustomer = customerService.getCustomer(customerId);
	    if (existingCustomer != null && passwordEncoder.matches(customer.getPassword(), existingCustomer.getPassword())) {
	        HttpSession session = request.getSession();
	        session.setAttribute("customerId", existingCustomer.getId());
	        Cookie cookie = new Cookie("customerId", Integer.toString(existingCustomer.getId()));
	        cookie.setMaxAge(60 * 60 * 24); // cookie expires after 24 hours
	        response.addCookie(cookie);
	        model.addAttribute("customer", existingCustomer);
	        return "customer-dashboard";
	    } else {
	        model.addAttribute("error", "Invalid email or password");
	        return "login";
	    }
	}

	
	@PostMapping("/customer/authenticate-login") // Use this method when building login page and when posting, route to this method to verify login credentials 
	public String authenticateByEmail(Model model, HttpServletRequest request, HttpServletResponse response,
	                                   @RequestParam(value = "email", required = false) String email,
	                                   @RequestParam(value = "password", required = false) String password) {
	    Customer existingCustomer = customerService.getByEmail(email, password);
	    if (existingCustomer != null && passwordEncoder.matches(password, existingCustomer.getPassword())) {
	        HttpSession session = request.getSession(true);
	        session.setAttribute("customerId", existingCustomer.getId());
	        
	        // Create and add customerId cookie to response
//	        Cookie customerIdCookie = new Cookie("customerId", String.valueOf(existingCustomer.getId()));
//	        customerIdCookie.setPath("/");
//	        customerIdCookie.setMaxAge(24 * 60 * 60); // set the cookie to expire in 1 day
//	        response.addCookie(customerIdCookie);

	        model.addAttribute("firstName", existingCustomer.getFirstName());
	        return "customer-dashboard";
	    } 
	    else {
	        model.addAttribute("email", existingCustomer.getEmail());
	        model.addAttribute("error", "Invalid email or password");
	        return "login";
	    }
	}

	@GetMapping("/logout")
	public String logout(HttpSession session, HttpServletResponse response) {
	    session.invalidate();
	    Cookie customerIdCookie = new Cookie("customerId", "");
	    customerIdCookie.setMaxAge(0);
	    response.addCookie(customerIdCookie);
	    return "redirect:/login";
	}




}
