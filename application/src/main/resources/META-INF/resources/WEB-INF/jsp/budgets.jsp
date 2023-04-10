<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

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
<%-- <link rel="stylesheet" type="text/css" href="<c:url value='/style.css'/>"> --%>
<div class="container">
	<div class="row">
		<div class="col">
			<h1 class="text-center mt-3">Your Budgets</h1>
			<h5 class="text-center mt-2">Total Budgets : ${numb}</h5><br>
		</div>
	</div>
	<div class="row">
		<div class="col-2">
			<jsp:include page="vertical-navigation.jsp" />
		</div>
		<div class="col-9">
			<div class="row">
				<div class="col-lg-10 mx-auto">
					<div class="career-search mb-60">
						<form action="#" class="career-form mb-60">
						<div class="row">
							<div class="col-md-6 col-lg-3 my-3">
								<div class="input-group position-relative">
									<input type="text" class="form-control" placeholder="Enter Your Keywords" id="keywords">
								</div>
							</div>
							<div class="col-md-6 col-lg-3 my-3">
								<div class="select-container">
									<select class="custom-select">
									<option selected="">Name</option>
									<c:forEach items="${budgets}" var="budget">
									<option value>${budget.name}</option>
									</c:forEach>
									</select>
								</div>
							</div>
							<div class="col-md-6 col-lg-3 my-3">
								<div class="select-container">
									<select class="custom-select">
									<option selected="">Select Start Date</option>
									<c:forEach items="${budgets}" var="budget">
									<option value>${budget.startDate}</option>
									</c:forEach>
									</select>
								</div>
							</div>
							<div class="col-md-6 col-lg-3 my-3">
								<button type="button" class="btn btn-lg btn-block btn-light btn-custom" id="contact-submit">
								Search
								</button>
							</div>
						</div>
						</form>
						<div class="filter-result">
							<p class="mb-30"></p>
							<c:forEach items="${budgets}" var="budget">
							<div class="job-box d-md-flex align-items-center justify-content-between mb-30">
								<div class="job-left my-4 d-md-flex align-items-center flex-wrap">
									<div class="img-holder mr-md-4 mb-md-0 mb-4 mx-auto mx-md-0 d-md-none d-lg-flex">
									${budget.letter}
									</div>
									<style>
									   a {
									    text-decoration: none;
									    color: black;
									  }
									  a:hover {
									  color: black; 
									  text-decoration: none;
									</style>
									<a href="${pageContext.request.contextPath}/expenses/budget-expenses/${budget.id}"><div class="job-content">
										<h5 class="text text-md-left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${budget.name}</h5>
										<ul class="d-md-flex flex-wrap text-capitalize ff-open-sans">
										<li class="mr-md-4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<i class="zmdi zmdi-money mr-2"></i>${budget.spentSum} spent
										</li>
										<li class="mr-md-4">
										<i class="zmdi zmdi-money mr-2"></i>${budget.remainingSum} remaining
										</li>
										</ul>
									</div></a>
								</div>
								<div class="button-container">
								    <div class="job-right my-4 flex-shrink-0">
								        <a href="${pageContext.request.contextPath}/expenses/budget-expenses/addExpense/${budget.id}"
								        class="btn d-block w-100 d-sm-inline-block btn-light">Add Expense</a>
								    </div>
								    <div class="job-right my-4 flex-shrink-0">
								        <a href="#" class="btn d-block w-100 d-sm-inline-block btn-light">Delete</a>
								        <%-- <form action = "deleteBudget" method = "post">
										<input type="hidden" name="budgetId" value="${budget.id}" />
										<input type="submit" value="Delete" />
										</form> --%>
								    </div>
								</div>
							</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>	
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript"></script>
</body>
</html>