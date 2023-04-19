<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header class="d-flex justify-content-between align-items-center p-2" style="border-bottom: 3px solid #3496f9;max-width: 1500px; margin: 0 auto;">
   	<!-- Logo -->
   	<div class="d-flex align-items-center">
   		<img src="http://localhost:8080/static/img/logoblue.png" 
   		alt="budget application logo" width="55px" height="55px" />
   		<span class="d-flex flex-column" style="color: #3496f9;font-weight: 600;font-size: 1.1rem">Budgetly 
   		<span style="font-weight: 400;font-size: 0.8rem;line-height: 1.1;">Budget Tracker</span></span>
   	</div>
   	</header>
<div class="container">
	<div class="row">
		<div class="col-md-2">
			<jsp:include page="vertical-navigation.jsp" />
		</div>
		<div class="col-md-9">
			<div class="row">
				<h1 class="text-center mt-3">Welcome Home, ${firstName}</h1>
			</div><br>
			<div class="row">
			<div class="col-md-4">
				<div class="card h-100">
					<h5 class="card-header">This Month's Budgets</h5>
					<div class="card-body">
						<table class="table">
							<thead>
								<tr>
									<th>Name</th>
									<th>Start Date</th>
									<th>End Date</th>
									<th>Amount</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="budget" items="${thisMonthsBudgets}">
									<tr>
										<td>${budget.name}</td>
										<td><fmt:formatDate value="${budget.startDate}"
												pattern="M/d" /></td>
										<td><fmt:formatDate value="${budget.endDate}"
												pattern="M/d" /></td>
										<td>${budget.getFormattedAmount()}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

					</div>
					<div class="card-footer">
						<p>Total Expenses: $ ${thisMonthsExpenses }</p>
					</div>
				</div>
			</div>
			
			<div class="col-md-4">
				<div class="card h-100">
					<h5 class="card-header">This Week's Budgets</h5>
					<div class="card-body">
						<table class="table">
							<thead>
								<tr>
									<th>Name</th>
									<th>Start Date</th>
									<th>End Date</th>
									<th>Amount</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="budget" items="${thisWeeksBudgets}">
									<tr>
										<td>${budget.name}</td>
										<td><fmt:formatDate value="${budget.startDate}"
												pattern="M/d" /></td>
										<td><fmt:formatDate value="${budget.endDate}"
												pattern="M/d" /></td>
										<td>${budget.getFormattedAmount()}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="card-footer">
						<p>Total Expenses: $ ${thisWeeksExpenses }</p>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="card h-100">
					<h5 class="card-header">Budgets Over Amount</h5>
					<div class="card-body">
						<table class="table">
							<thead>
								<tr>
									<th>Name</th>
									<th>Start Date</th>
									<th>End Date</th>
									<th>Amount</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="budget" items="${budgetsOverAmount}">
									<tr>
										<td>${budget.name}</td>
										<td><fmt:formatDate value="${budget.startDate}"
												pattern="M/d" /></td>
										<td><fmt:formatDate value="${budget.endDate}"
												pattern="M/d" /></td>
										<td>${budget.getFormattedAmount()}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<h5 class="card-header">Most Recent Expenses -
						$${calculatedExpenses }</h5>
					<div class="card-body">
						<table class="table table-responsive">
							<c:forEach var="expense" items="${mostRecentExpenses}"
								varStatus="status">
								<c:if test="${status.index % 3 == 0}">
									<tr>
								</c:if>
								<td>${expense.name} $${expense.getFormattedAmount() }</td>
								<c:if test="${status.index % 3 == 2 or status.last}">
									</tr>
								</c:if>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div></div></div>
</body>
</html>