<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description"
	content="Budgetly is a robust budgeting application allowing you create, update, and 
      delete budgets and expenses." />
<title>Expenses</title>
<%-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/material-design-iconic-font/2.2.0/css/material-design-iconic-font.min.css" integrity="sha256-3sPp8BkKUE7QyPSl6VfBByBroQbKxKG7tsusY2mhbVY=" crossorigin="anonymous" />
 <link rel="stylesheet" type="text/css" href="<c:url value='/style.css'/>"> --%>
<script src="webjars/bootstrap/5.2.3/js/bootstrap.min.js" defer></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
</head>			
<body>
	<div class="container">
		<div class="row">
			<div class="col">
				<h1 class="text-center mt-3">Budget ${budget.name}</h1>
				<h5 class="text-center">${budget.formattedStartDate}-${budget.formattedEndDate}</h5><br>		
			</div>
		</div>
		
		<div class="row">
			<div class="col-2">
				<jsp:include page="vertical-navigation.jsp" />
			</div>
			<div class="col-4">
			<c:forEach items="${expenses}" var="expense">		
 				<div class="align-items-center justify-content-center">
	 				<div class="list-group list-group-checkable">
					    <label class="list-group-item rounded-3 py-3" for="listGroup">
					    <table>
							<tr>
								<td width=150><strong class="fw-semibold">${expense.name}</strong></td>
								<td width=150><span class="d-block small opacity-75">$${expense.formattedAmount}</span></td>
								<td>
								<div class="job-right">
									<form action="deleteExpense" method="post">
									    <input type="hidden" name="expenseId" value="${expense.id}" />
									    <button type="submit" class="btn d-block w-20 d-sm-inline-block btn-light">Delete</button>
									</form>
								</div>
								</td>
							</tr>
						</table>
						
						<%-- <div class="job-right">
						  <form action="deleteExpense" method="post">
						    <input type="hidden" name="expenseId" value="${expense.id}" />
						    <button type="submit" class="btn d-block w-20 d-sm-inline-block btn-light">Delete</button>
						  </form>
						</div> --%>
						  
					    </label>
					 </div><br>
				 </div>
			 </c:forEach>
			<form action = "addExpense" method="get">
			<input type="hidden" name="budgetId" value="${budgetId}" />
			<button type = "submit" class="btn btn-dark rounded-pill px-3">Add Expense </button>
			</form>
			</div>
			
				<%-- <a href="${pageContext.request.contextPath}/customerHistory?customerId=${eachCustomer.id}"></a> --%>
			
			<div class="col-5">
				<canvas id="myChart"></canvas><br>	
				<div style="text-align: center;">
				  <p style="font-size: 22px; text-align: left;"><b>Summary:</b><br>
				    In total, <b>${budget.customer.firstName} ${budget.customer.lastName}</b> allocated <b>$${budget.formattedAmount}</b> 
				    toward the ${budget.name} Budget, a total of <b>$${budget.getSpentSumString()}</b> was spent. 
				    The customer is <b style="color: ${budget.spentSum > budget.amount ? 'red' : 'green'};">${budget.spentSum > budget.amount ? 'over' : 'under'}</b>
				     the budget amount by <b style="color: ${budget.spentSum > budget.amount ? 'red' : 'green'};">$${budget.remainingSum}.</b>
				  </p>
				</div>	
			</div>
	<c:set var="xValues" value=""/>
	<c:set var="yValues" value=""/>
	<c:forEach items="${expenses}" var="expense">
		<c:set var="xValues" value="${xValues}${expense.amount}, "/>
		<c:set var="yValues" value="${yValues}'${expense.name}', "/>
	</c:forEach>

	<script>
		var xValues = [${xValues}];
		var yValues = [${yValues}];
		var barColors = [
		  "#b91d47",
		  "#00aba9",
		  "#2b5797",
		  "#e8c3b9",
		  "#1e7145",
		  "#2ca02c",
          "#ffbb78",
          "#98df8a",
          "#ff9896",
          "#c5b0d5",
          "#dbdb8d",
          "#8c6d31",
          "#9edae5",
          "#393b79",
          "#637939",
          "#a6cee3",
          "#fdae6b",
          "#ff9896",
          "#66c2a5"
		];

		new Chart("myChart", {
		  type: "doughnut",
		  data: {
		    labels: yValues,
		    datasets: [{
		      backgroundColor: barColors,
		      data: xValues
		    }]
		  },
		  /* options: {
		    title: {
		      display: true,
		      text: "Budget"
		    }
		  } */
		});
	</script>
		</div>
	</div>
</body>
</html>

<!-- <a href="add-todo">
	<button class="btn btn-success">Add Expense</button>
</a> -->



