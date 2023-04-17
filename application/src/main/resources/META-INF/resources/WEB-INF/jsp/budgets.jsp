<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">

<title>Budgets Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<c:url value='/style.css'/>">
</head>
<body>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/material-design-iconic-font/2.2.0/css/material-design-iconic-font.min.css" integrity="sha256-3sPp8BkKUE7QyPSl6VfBByBroQbKxKG7tsusY2mhbVY=" crossorigin="anonymous" />
<div class="container">
	<div class="row">
		<div class="col-2"><br><br>
			<jsp:include page="vertical-navigation.jsp" />
		</div>
		<div class="col-9">
			<div class="row">
				<div class="col">
					<h1 class="text-center mt-3">Your Budgets</h1>
					<h6 class="text-center">Total Amount Spent : $ ${customer.getTotalSpentSum()}</h6>
					<h6 class="text-center">Total Amount Remaining : $ ${customer.getTotalRemainingSum()}</h6>
					
				</div>
			</div>
			<div class="row">
				<div class="col-lg-11 mx-auto">
					<div class="career-search mb-60">
  						<form action = "searchByKeyword" method="get" class="career-form mb-60">
							<form class="career-form mb-60">
							<div class="row">
							<div class="col-md-6 col-lg-3 my-3">
								<div class="input-group position-relative">
									<input type="text" class="form-control" placeholder="Enter Your Keyword" id="keyword" value="${keyword}">
								</div>
							</div>
							<div class="col-md-6 col-lg-3 my-3">
								<input type="hidden" name="customerId" value="${customerId}" />
								<button type = "submit" class="btn btn-lg btn-block btn-light btn-custom">Search</button>
						<!-- <button type="button" class="btn btn-lg btn-block btn-light btn-custom" id="contact-submit">
								Search
								</button> -->
							</div>
							<div class="col-md-6 col-lg-3 my-3">
								<form action = "sortBudgetsByName" method="post">
									<input type="hidden" name="customerId" value="${customerId}" />
									<button type = "submit" class="btn btn-lg btn-block btn-light btn-custom">Sort by Names</button>
								</form>
							</div>
							<div class="col-md-6 col-lg-3 my-3">
								<form action = "sortBudgetsByDate" method="post">
									<input type="hidden" name="customerId" value="${customerId}" />
									<button type = "submit" class="btn btn-lg btn-block btn-light btn-custom">Sort by Dates</button>
								</form>
							</div>				
						</div>
						
						<div class="filter-result">
						<p class="ff-montserrat">Total Budgets : ${numb}</p>
						<div class="row">
							<form action = "addBudget" method="get">
								<input type="hidden" name="customerId" value="${customerId}" />
								<button type = "submit" class="form-control btn btn-lg btn-block btn-light special-button">Add New Budget</button>
							</form>	
						<style>
						  button.special-button {
						    display: block;
							width: 100%;
							margin: 0 auto;
						  }
						</style>
						</div>
							<p class="mb-20"></p>
							
							<c:forEach items="${budgets}" var="budget">
							<style>
								   a {
								    text-decoration: none;
								    color: black;
								  }
								  a:hover {
								  color: black; 
								  text-decoration: none;
								</style>
								<a href="${pageContext.request.contextPath}/expenses/budget-expenses/${budget.id}">
								<div class="job-box d-md-flex align-items-center justify-content-between mb-3">
									<div class="job-left my-4 d-md-flex align-items-center flex-wrap">
										<div class="img-holder mr-md-4 mb-md-0 mb-4 mx-auto mx-md-0 d-md-none d-lg-flex">
										${budget.letter}
										</div>
										<div class="job-content">
											<h5 class="text text-md-left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${budget.name}</h5>
											<ul class="d-md-flex flex-wrap text-capitalize ff-open-sans">
											<li class="mr-md-4">&nbsp;
											<i class="zmdi zmdi-money mr-1"></i>${budget.getSpentSumString()} spent
											</li>
											<li class="mr-md-4"><!-- &nbsp; -->
											<i class="zmdi zmdi-money mr-1"></i>${budget.getRemainingSumString()} remaining
											</li>
											<li class="mr-md-4">
											<i class="zmdi zmdi-time mr-1"></i>
											${budget.formattedStartDate}-${budget.formattedEndDate} (${budget.getNumberOfDays()} days)											
											</li>
											</ul>
										</div>
									</div>
									<div class="button-container">
									    <div class="job-right my-3 flex-shrink-0">
									        <a href="${pageContext.request.contextPath}/expenses/budget-expenses/addExpense?budgetId=${budget.id}"
									        class="btn d-block w-100 d-sm-inline-block btn-light">Add Expense</a>
									    </div>
									    <div class="job-right my-3 flex-shrink-0">
									        <!-- <a href="#" class="btn d-block w-100 d-sm-inline-block btn-light">Delete</a> -->
									        <form action = "deleteBudget" method = "post">
											<input type="hidden" name="budgetId" value="${budget.id}" />
											<input type="submit" class="btn d-block w-100 d-sm-inline-block btn-light" value="Delete" />
											</form>
									    </div>
									</div>
								</div></a>					
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
			</form>
			<%-- <div class="row">
			<div class="col-lg-11 mx-auto">
				<form action = "addBudget" method="get">
					<input type="hidden" name="customerId" value="${customerId}" />
					<button type = "submit" class="special-button">Add New Budget</button>
				</form>	
			<style>
			  button.special-button {
			    display: block;
				width: 100%;
				margin: 0 auto;
			  }
			</style>
			</div>
			</div> --%>
		</div>
	</div>
</div><br><br>
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript"></script>
</body>
</html>