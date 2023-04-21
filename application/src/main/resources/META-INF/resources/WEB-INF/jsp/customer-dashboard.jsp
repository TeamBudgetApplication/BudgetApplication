<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Dashboard - ${firstName }</title>
<script src="webjars/bootstrap/5.2.3/js/bootstrap.min.js" defer></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
<script src="js/util.js"></script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<style>
tbody td {
	border: none !important;
}

tr {
	border: none !important;
}
</style>
</head>
<body>
	<header class="d-flex justify-content-between align-items-center p-2"
		style="border-bottom: 3px solid #3496f9; max-width: 1500px; margin: 0 auto;">
		<!-- Logo -->
		<div class="d-flex align-items-center">
			<img src="http://localhost:8080/static/img/logoblue.png"
				alt="budget application logo" width="55px" height="55px" /> <span
				class="d-flex flex-column"
				style="color: #3496f9; font-weight: 600; font-size: 1.1rem">Budgetly
				<span style="font-weight: 400; font-size: 0.8rem; line-height: 1.1;">Budget
					Tracker</span>
			</span>
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
				</div>
				<br>
				<div class="row">
					<div class="col-md-4">
						<div class="card h-100">
							<h5 class="card-header">This Month's Budgets</h5>
							<div class="card-body">
								<div id="carouselExampleControls1" class="carousel slide"
									data-ride="carousel">
									<div class="carousel-inner">
										<c:if test="${empty thisMonthsBudgets}">
											<div style="text-align: center;">
												<br>
												<h3>You don't have any budgets yet</h3>
												<br>
												<a href="${pageContext.request.contextPath}/budgets/create-budget/${customerId}"
												class="btn" type="submit" style="background: #3496f9; color: #ffffff">Add New Budget</a>
											</div>
										</c:if>
										<c:forEach items="${thisMonthsBudgets}" var="budget"
											varStatus="budgetStatus">
											<c:if test="${budgetStatus.index == 0}">
												<div class="carousel-item active">
												<a href="${pageContext.request.contextPath}/expenses/budget-expenses/${budget.id}">
													<div>
														<canvas id="myChart1_${budget.id}"
															style="width: 300px; height: 300px;"></canvas>
													</div></a>
												</div>
											</c:if>
											<c:if test="${budgetStatus.index != 0}">
												<div class="carousel-item">
												<a href="${pageContext.request.contextPath}/expenses/budget-expenses/${budget.id}">
													<div>
														<canvas id="myChart1_${budget.id}"
															style="width: 300px; height: 300px;"></canvas>
													</div></a>
												</div>
											</c:if>

											<c:set var="xValues" value="" />
											<c:set var="yValues" value="" />

											<c:forEach items="${budget.expenses}" var="expense">
												<c:set var="xValues" value="${xValues}${expense.amount}, " />
												<c:set var="yValues" value="${yValues}'${expense.name}', " />
											</c:forEach>

											<c:if test="${budget.remainingSum > 0}">
												<c:set var="xValues"
													value="${xValues}${budget.remainingSum}" />
												<c:set var="yValues" value="${yValues}'Remaining Sum', " />
											</c:if>

											<script>
							var xValues_${budget.id} = [${xValues}];
					        var yValues_${budget.id} = [${yValues}];
					        var barColors_${budget.id} = [ 
					        	"#4F96F2",
					        	"#1F3C61",
					            "#E8C3B9",
					            "#8C564B",
					            "#2CA02C",
					            "#B91D47",
					            "#D7A8B8",
					            "#9467BD",
					            "#7F7F7F",
					            "#FF7F0E",
					            "#FFBB78",
					            "#98DF8A",
					            "#FF9896",
					            "#C5B0D5",
					            "#DBDB8D",
					            "#8C6D31",
					            "#9EDAE5",
					            "#393B79",
					            "#637939",
					            "#A6CEE3",
					            "#FDAE6B",
					            "#66C2A5",
					            "#5076A0",
					            "#F2B705",
					            "#7CFFCB",
					            "#E3A0F2",
					            "#F2D7C6",
					            "#87C0FF",
					            "#F29F05",
								];
					        
					     	// Set the same color for budget.remainingSum
					     	if (${budget.remainingSum > 0}) {
					     		barColors_${budget.id}[xValues_${budget.id}.length - 1] = "#1e7145";
							}
						
								new Chart("myChart1_${budget.id}", {
								  type: "doughnut",
								  data: {
								    labels: yValues_${budget.id},
								    datasets: [{
								      backgroundColor: barColors_${budget.id},
						              data: xValues_${budget.id}
								    }]
								  },
								  options: {
								    title: {
								      display: true,
								      text: "${budget.name}",
								    fontSize: 18
								    },
								    legend: {
								        display: false
								      }
								  }
								});
							</script>
										</c:forEach>
									</div>
									<a class="carousel-control-prev"
										href="#carouselExampleControls1" role="button"
										data-slide="prev"> <span
										class="carousel-control-prev-icon" aria-hidden="true"></span>
										<span class="sr-only">Previous</span>
									</a> <a class="carousel-control-next"
										href="#carouselExampleControls1" role="button"
										data-slide="next"> <span
										class="carousel-control-next-icon" aria-hidden="true"></span>
										<span class="sr-only">Next</span>
									</a>
								</div>
							</div>
							<div class="card-footer">
								
									Total Expenses:
									<fmt:setLocale value="en-US" />
									<fmt:formatNumber value="${thisMonthsExpenses }"
										type="currency" />
								
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="card h-100">
							<h5 class="card-header">This Week's Budgets</h5>
							<div class="card-body">
								<div id="carouselExampleControls2" class="carousel slide"
									data-ride="carousel">
									<div class="carousel-inner">
										<c:if test="${empty thisWeeksBudgets}">
											<div style="text-align: center;">
												<br>
												<h3>You don't have any budgets yet</h3>
												<br>
												<a href="${pageContext.request.contextPath}/budgets/create-budget/${customerId}"
												class="btn" type="submit" style="background: #3496f9; color: #ffffff">Add New Budget</a>
											</div>
										</c:if>
										<c:forEach items="${thisWeeksBudgets}" var="budget"
											varStatus="budgetStatus">
											<c:if test="${budgetStatus.index == 0}">
												<div class="carousel-item active">
													<div>
														<canvas id="myChart2_${budget.id}"
															style="width: 300px; height: 300px;"></canvas>
													</div>
												</div>
											</c:if>
											<c:if test="${budgetStatus.index != 0}">
												<div class="carousel-item">
													<div>
														<canvas id="myChart2_${budget.id}"
															style="width: 300px; height: 300px;"></canvas>
													</div>
												</div>
											</c:if>

											<c:set var="xValues" value="" />
											<c:set var="yValues" value="" />

											<c:forEach items="${budget.expenses}" var="expense">
												<c:set var="xValues" value="${xValues}${expense.amount}, " />
												<c:set var="yValues" value="${yValues}'${expense.name}', " />
											</c:forEach>

											<c:if test="${budget.remainingSum > 0}">
												<c:set var="xValues"
													value="${xValues}${budget.remainingSum}" />
												<c:set var="yValues" value="${yValues}'Remaining Sum', " />
											</c:if>

											<script>
							var xValues_${budget.id} = [${xValues}];
					        var yValues_${budget.id} = [${yValues}];
					        var barColors_${budget.id} = [ 
					        	"#4F96F2",
					        	"#1F3C61",
					            "#E8C3B9",
					            "#8C564B",
					            "#2CA02C",
					            "#B91D47",
					            "#D7A8B8",
					            "#9467BD",
					            "#7F7F7F",
					            "#FF7F0E",
					            "#FFBB78",
					            "#98DF8A",
					            "#FF9896",
					            "#C5B0D5",
					            "#DBDB8D",
					            "#8C6D31",
					            "#9EDAE5",
					            "#393B79",
					            "#637939",
					            "#A6CEE3",
					            "#FDAE6B",
					            "#66C2A5",
					            "#5076A0",
					            "#F2B705",
					            "#7CFFCB",
					            "#E3A0F2",
					            "#F2D7C6",
					            "#87C0FF",
					            "#F29F05",
								];
					        
					     	// Set the same color for budget.remainingSum
					     	if (${budget.remainingSum > 0}) {
					     		barColors_${budget.id}[xValues_${budget.id}.length - 1] = "#1e7145";
							}
						
								new Chart("myChart2_${budget.id}", {
								  type: "doughnut",
								  data: {
								    labels: yValues_${budget.id},
								    datasets: [{
								      backgroundColor: barColors_${budget.id},
						              data: xValues_${budget.id}
								    }]
								  },
								  options: {
								    title: {
								      display: true,
								      text: "${budget.name}",
								    fontSize: 18
								    },
								    legend: {
								        display: false
								      }
								  }
								});
							</script>
										</c:forEach>
									</div>
									<a class="carousel-control-prev"
										href="#carouselExampleControls2" role="button"
										data-slide="prev"> <span
										class="carousel-control-prev-icon" aria-hidden="true"></span>
										<span class="sr-only">Previous</span>
									</a> <a class="carousel-control-next"
										href="#carouselExampleControls2" role="button"
										data-slide="next"> <span
										class="carousel-control-next-icon" aria-hidden="true"></span>
										<span class="sr-only">Next</span>
									</a>
								</div>
							</div>
							<div class="card-footer">
								
									Total Expenses:
									<fmt:setLocale value="en-US" />
									<fmt:formatNumber value="${thisWeeksExpenses }" type="currency" />
								
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="card h-100">
							<h5 class="card-header">Budgets Over Amount</h5>
							<div class="card-body">
								<div id="carouselExampleControls3" class="carousel slide"
									data-ride="carousel">
									<div class="carousel-inner">
										<c:if test="${empty budgetsOverAmount}">
											<div style="text-align: center;">
												<br>
												<h3>No budgets currently over amount</h3>
											</div>
										</c:if>
										<c:forEach items="${budgetsOverAmount}" var="budget"
											varStatus="budgetStatus">
											<c:if test="${budgetStatus.index == 0}">
												<div class="carousel-item active">
													<div>
														<canvas id="myChart3_${budget.id}"
															style="width: 300px; height: 300px;"></canvas>
													</div>
												</div>
											</c:if>
											<c:if test="${budgetStatus.index != 0}">
												<div class="carousel-item">
													<div>
														<canvas id="myChart3_${budget.id}"
															style="width: 300px; height: 300px;"></canvas>
													</div>
												</div>
											</c:if>

											<c:set var="xValues" value="" />
											<c:set var="yValues" value="" />

											<c:forEach items="${budget.expenses}" var="expense">
												<c:set var="xValues" value="${xValues}${expense.amount}, " />
												<c:set var="yValues" value="${yValues}'${expense.name}', " />
											</c:forEach>

											<c:if test="${budget.remainingSum > 0}">
												<c:set var="xValues"
													value="${xValues}${budget.remainingSum}" />
												<c:set var="yValues" value="${yValues}'Remaining Sum', " />
											</c:if>

											<script>
							var xValues_${budget.id} = [${xValues}];
					        var yValues_${budget.id} = [${yValues}];
					        var barColors_${budget.id} = [ 
					        	"#4F96F2",
					        	"#1F3C61",
					            "#E8C3B9",
					            "#8C564B",
					            "#2CA02C",
					            "#B91D47",
					            "#D7A8B8",
					            "#9467BD",
					            "#7F7F7F",
					            "#FF7F0E",
					            "#FFBB78",
					            "#98DF8A",
					            "#FF9896",
					            "#C5B0D5",
					            "#DBDB8D",
					            "#8C6D31",
					            "#9EDAE5",
					            "#393B79",
					            "#637939",
					            "#A6CEE3",
					            "#FDAE6B",
					            "#66C2A5",
					            "#5076A0",
					            "#F2B705",
					            "#7CFFCB",
					            "#E3A0F2",
					            "#F2D7C6",
					            "#87C0FF",
					            "#F29F05",
								];
					        
					     	// Set the same color for budget.remainingSum
					     	if (${budget.remainingSum > 0}) {
					     		barColors_${budget.id}[xValues_${budget.id}.length - 1] = "#1e7145";
							}
						
								new Chart("myChart3_${budget.id}", {
								  type: "doughnut",
								  data: {
								    labels: yValues_${budget.id},
								    datasets: [{
								      backgroundColor: barColors_${budget.id},
						              data: xValues_${budget.id}
								    }]
								  },
								  options: {
								    title: {
								      display: true,
								      text: "${budget.name}",
								    fontSize: 18
								    },
								    legend: {
								        display: false
								      }
								  }
								});
							</script>
										</c:forEach>
									</div>
									<a class="carousel-control-prev"
										href="#carouselExampleControls3" role="button"
										data-slide="prev"> <span
										class="carousel-control-prev-icon" aria-hidden="true"></span>
										<span class="sr-only">Previous</span>
									</a> <a class="carousel-control-next"
										href="#carouselExampleControls3" role="button"
										data-slide="next"> <span
										class="carousel-control-next-icon" aria-hidden="true"></span>
										<span class="sr-only">Next</span>
									</a>
								</div>

							</div>
							<c:forEach items="${budgetsOverAmount}" var="budget">
							<c:if test="${budget.getRemainingSum() < 0}">
							<div class="card-footer">
							Exceeded Amount: 
								<fmt:setLocale value="en-US" />
								<fmt:formatNumber value="${budget.getRemainingSum() }"
										type="currency" />
							</div>
							</c:if>
							</c:forEach>
						</div>
					</div>
				</div>

				<br>
				<div class="row">
					<div class="col-md-12">
						<div class="card">
							<h5 class="card-header">
								Most Recent Expenses -
								<fmt:setLocale value="en-US" />
								<fmt:formatNumber value="${calculatedExpenses }" type="currency" />
							</h5>
							<div class="card-body table-responsive table-borderless">
								<table class="table table-borderless">
									<c:if test="${empty mostRecentExpenses}">
										<div style="text-align: center;">
											<br>
											<h2>You don't have any expenses yet</h2>
										</div>
									</c:if>
									<c:forEach var="expense" items="${mostRecentExpenses}"
										varStatus="status">
										<c:if test="${status.index % 3 == 0}">
											<tr class="no-border">
										</c:if>
										<td><b>${expense.getFormattedDate() }</b>:<br>
											${expense.name} $${expense.getFormattedAmount() }</td>
										<c:if test="${status.index % 3 == 2 or status.last}">
											</tr>
										</c:if>
									</c:forEach>
								</table>
							</div>
						</div>
					</div>
				</div>
				<br> <br>
			</div>
		</div>
	</div>
</body>
</html>