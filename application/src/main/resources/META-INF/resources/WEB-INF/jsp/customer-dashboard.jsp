<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 class="text-center mt-3">Welcome Home, ${firstName}</h1>
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<jsp:include page="vertical-navigation.jsp" />
			</div>
			<div class="col-md-3">
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
										<td><fmt:formatDate value="${budget.startDate}" pattern="M/d"/></td>
        								<td><fmt:formatDate value="${budget.endDate}" pattern="M/d"/></td>
										<td>${budget.amount}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="card h-100">
					<h5 class="card-header">This Week's Budgets</h5>
					<div class="card-body">
						<p class="card-text">${thisWeeksBudgets }</p>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="card h-100">
					<h5 class="card-header">Budgets Over Allowed Amount</h5>
					<div class="card-body">
						<p class="card-text">${budgetsOverAmount }</p>
					</div>
				</div>
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<h5 class="card-header">Most Recent Expenses (across all
						budgets)</h5>
					<div class="card-body">
						<p class="card-text">c:forEach loop to display all expenses,
							regardless of which budget can research how to color code them or
							something so they correspond with the user's budgets</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>