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
<title>Expenses-report</title>
<script src="webjars/bootstrap/5.2.3/js/bootstrap.min.js" defer></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
</head>			
<body>
	<div class="container">
		<div class="row">
			<div class="col-2"><br><br><br>
				<jsp:include page="vertical-navigation.jsp" />
			</div>
			<div class="col-9">
			<c:forEach items="${budgets}" var="budget">
				<div class="row">
					<div class="col">
						<h2 class="text-center mt-3">Budget ${budget.name}</h2>
						<h5 class="text-center">${budget.formattedStartDate}-${budget.formattedEndDate}</h5><br>
					</div>
				</div>
				<div class="row">
					<div class="col-6">
					<c:forEach items="${budget.expenses}" var="expense">
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
							    </label>
							 </div><br>
						 </div>
					 </c:forEach>
					</div>		
					<div class="col-6">
						<canvas id="myChart_${budget.id}"></canvas><br>	
						<div style="text-align: center;">
						  <p style="font-size: 22px; text-align: left;"><b>Summary:</b><br>
						    In total, <b>${budget.customer.firstName} ${budget.customer.lastName}</b> allocated <b>$${budget.formattedAmount}</b> 
						    toward the ${budget.name} Budget, a total of <b>$${budget.getSpentSumString()}</b> was spent. 
						    The customer is <b style="color: ${budget.spentSum > budget.amount ? 'red' : 'green'};">${budget.spentSum > budget.amount ? 'over' : 'under'}</b>
						     the budget amount by <b style="color: ${budget.spentSum > budget.amount ? 'red' : 'green'};">$${budget.remainingSum}.</b><br><br>
						  </p>
						</div>	
					</div>
					</div>
			<c:set var="xValues" value=""/>
			<c:set var="yValues" value=""/>
			<c:forEach items="${budget.expenses}" var="expense">
				<c:set var="xValues" value="${xValues}${expense.amount}, "/>
				<c:set var="yValues" value="${yValues}'${expense.name}', "/>
			</c:forEach>

			<script>
			var xValues_${budget.id} = [${xValues}];
	        var yValues_${budget.id} = [${yValues}];
	        var barColors_${budget.id} = [
	        	"#aec7e8",
	            "#00aba9",
	            "#2b5797",
	            "#e8c3b9",
	            "#b91d47",
	            "#1e7145",
	            "#d7a8b8",
	            "#7f7f7f",
	            "#ff7f0e",
	            "#9467bd",
	            "#8c564b",
	            "#e377c2",
	            "#7f7f7f",
	            "#bcbd22",
	            "#17becf",
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
	            "#66c2a5",
				];
		
				new Chart("myChart_${budget.id}", {
				  type: "doughnut",
				  data: {
				    labels: yValues_${budget.id},
				    datasets: [{
				      backgroundColor: barColors_${budget.id},
		              data: xValues_${budget.id}
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
			</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>




