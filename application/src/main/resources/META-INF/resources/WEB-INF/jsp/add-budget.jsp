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
<title>Add Budget</title>
</head>
	<!-- Header -->
    <header class="d-flex justify-content-between align-items-center p-4" style="border-bottom: 2px solid #222222;max-width: 1500px; margin: 0 auto;">
    	<!-- Logo -->
    	<div class="d-flex align-items-center">
    		<span class="d-flex flex-column" style="font-weight: 600;font-size: 1.1rem; color: #222222">Budget 
    		<span style="line-height: 1;">Application</span></span>
    	</div>
    	<!-- Navigation Btn -->
    	<div>
    		<a href="${pageContext.request.contextPath}/customer/${customerId}">
    			<button class="btn" type="button" style="border: 2px solid #222222">Back to Dash</button>
    		</a>
    	</div>
    </header>
<body>
	    <main style="height: 100vh;">
    	<!-- Main Page Content -->
    	<div class="col-md-6 m-auto d-flex justify-content-end">
    		<!-- Hero Section -->
				<section class="w-100 grid gap-2 my-5 px-4">
    			<!-- Row -->
    			<div class="p-2 row align-items-center">
    				<form method="post" action="http://localhost:8080/budgets/user-budgets/${customerId}" class="justify-self-end" style="background-color: #153c64;padding: 1.5em 2em 3em;border-radius: 20px;">
    				<p class="text-center" style="color: #ffffff; font-weight: 600;">Create a new budget</p>
    				<!-- Form Error -->
    				<p class="text-danger text-center m-0 p-0">${error}</p>
			        <div class="mb-2">
			          <label for="name" class="form-label" style="color: #ffffff"
			            >Budget name:</label
			          >
			          <input
			            class="form-control"
			            id="name"
			            aria-describedby="budgetName"
			            name="name"
			            value="${name}"
			            required
			          />
			        </div>
			        <div class="mb-2">
			          <label for="amount" class="form-label" style="color: #ffffff">Budget amount:</label>
			          <input
			            class="form-control"
			            id="amount"
			            aria-describedby="budgetAmount"
			            name="amount"
			            value="${amount}"
			            required
			          />
			        </div>
			        <div class="mb-2">
			          <label for="startDate" class="form-label" style="color: #ffffff">Budget start date:</label>
			          <input
			            class="form-control"
			            id="startDate"
			            aria-describedby="budgetStartDate"
			            name="startDate"
			            value="${startDate}"
			            required
			          />
			        </div>
			        <div class="mb-2">
			          <label for="endDate" class="form-label" style="color: #ffffff">Budget end date:</label>
			          <input
			            class="form-control"
			            id="endDate"
			            aria-describedby="budgetEndDate"
			            name="endDate"
			            value="${endDate}"
			            required
			          />
			        </div>
					<button class="btn py-2 mt-4" type="submit" style="width: 100%; background: #3496f9; color: #ffffff">Submit</button>			      </form>
    			</div>
    			</section>
    		</div>
    	</main>	
</body>
</html>