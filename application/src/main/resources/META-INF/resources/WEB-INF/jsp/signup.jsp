<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html xmlns:th="https://www.thymeleaf.org" lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="description"
	content="Sign up. Budgetly is a robust budgeting application allowing you create, update, and 
      delete budgets and expenses." />
      <link rel="preconnect" href="https://fonts.googleapis.com" />
	  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
      <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap" rel="stylesheet" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
      crossorigin="anonymous"
    />
    <title>Sign up - Budget Application</title>
  </head>
  <body >
  
  <!-- Header -->
    <header class="d-flex justify-content-between align-items-center p-4" style="border-bottom: 2px solid #222222;max-width: 1500px; margin: 0 auto;">
    	<!-- Logo -->
    	<div class="d-flex align-items-center">
    		<span class="d-flex flex-column" style="font-weight: 600;font-size: 1.1rem; color: #222222">Budget 
    		<span style="line-height: 1;">Application</span></span>
    	</div>
    	<!-- Navigation Btn -->
    	<div>
    		<a href="http://localhost:8080/login">
    			<button class="btn" type="button" style="border: 2px solid #222222">Login</button>
    		</a>
    	</div>
    </header>
    
    
    <main style="height: 80vh;">
    	<!-- Main Page Content -->
    	<div style="max-width: 1200px; margin: 0 auto;">
    		<!-- Hero Section -->
    		<section class="w-100 grid gap-2 my-5 px-4" style="padding-top: 4rem;">
    			<!-- Row -->
    			<div class="p-2 row align-items-center">
    				<!-- col-1 -->
    			<div class="py-3 col-md-6">
    				<h1 class="mb-3" style="font-weight: 500; font-size: 1.8rem; max-width: 500px;">Budget Tracking Application 
    				<span style="font-weight: 600;">SIMPLE & ENGAGING</span></h1>
    				
    				<p class="mb-5" style="font-weight: 400; font-size: 0.85rem; max-width: 480px;line-height: 1.5">Get started tracking and visualizing you budgets.</p>
    				
    				<div>
    					<ul style="margin: 0; padding-left: 12px;font-size: 0.9rem">
    						<li class="mb-2">East to use</li>
    						<li class="mb-2">Track budgets and expenses</li>
    						<li class="mb-2">Budget visualization and expense reports</li>
    					</ul>
    				</div>
    			</div>
    			
    			<!-- col-2 -->
    			<div class="col-md-6 m-auto d-flex justify-content-end">
    				<form method="post" action="http://localhost:8080/customer/processNewCustomer" class="justify-self-end" style="background-color: #222222;padding: 1.5em 2em 3em;border-radius: 20px;width: 80%;" >
    				<p class="text-center" style="color: #ffffff; font-weight: 600;">Sign up</p>
    				<!-- Form Error -->
    				<p class="text-danger text-center m-0 p-0">${error}</p>
			        <div class="mb-3 grid">
			          <div class="row">
			          	<div class="col">
			          	<label for="firstname" class="form-label" style="color: #ffffff"
			            >First name</label>
				          <input
				            type="text"
				            class="form-control"
				            id="firstName"
				            aria-describedby="firstName"
				            name="firstName"
				            value="${firstName}"
				            required
				          />
			          	</div>
			          
				          <div class="col">
				          	<label for="lastname" class="form-label" style="color: #ffffff"
				            >Last name</label>
					          <input
					            type="text"
					            class="form-control"
					            id="lastName"
					            aria-describedby="lastName"
					            name="lastName"
					            value="${lastName}"
					            required
					          />
				          </div>
			          </div>
			          
			        </div>
			        
			         <div class="mb-2">
			          <label for="userEmail" class="form-label" style="color: #ffffff">Email</label>
			          <input
			            type="email"
			            class="form-control"
			            id="userEmail"
			            name="email"
			            value="${email}"
			            required
			          />
			        </div>
			              
			        <div class="mb-2">
			          <label for="InputPassword1" class="form-label" style="color: #ffffff">Password</label>
			          <input
			            type="password"
			            class="form-control"
			            id="InputPassword1"
			            name="password"
			            required
			          />
			        </div>
			        <button class="btn py-2 mt-4" type="submit" style="width: 100%; background: #ffffff; color: #222222">Submit</button>
			      </form>
    			</div>
    			</div>
    		</section>
    	   </div>
    	</main>
  </body>