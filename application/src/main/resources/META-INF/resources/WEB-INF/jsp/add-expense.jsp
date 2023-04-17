<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description"
	content="Budgetly is a robust budgeting application allowing you create, update, and 
      delete budgets and expenses." />
<title>Add a new expense</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col">
				<h1 class="text-center mt-3">Budget ${budgetName}</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-3">
				<jsp:include page="vertical-navigation.jsp" />
			</div>
			
			<div class="col-9">
			<form:form action ="processExpense" modelAttribute = "expense">
			<b>Please enter expense info:</b>
				<table>
					<tr>
						<td>Name:</td>
						<td><form:input path="name"/></td>
					</tr>
					<tr>
						<td>Amount:</td>
						<td><form:input path="amount"/></td>
					</tr>
					<tr>
						<td>Date:</td>
						<td><form:input path="expenseDate" type="date" /></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="hidden" name="budgetId" value="${budgetId}" />
						<input type = "submit" value = "Save Expense"/></td>
					</tr>	
				</table>
				</form:form>	
			</div>
			
		</div>
		<div class="row">
			<div class="col-3"></div>
			<div class="col-9">
			<form action = "returnToBudgetButton" method="get">
				<input type="hidden" name="budgetId" value="${budgetId}" />
				<button type = "submit" class="btn btn-dark rounded-pill px-3">Go to ${budgetName} Budget</button>
			</form>
			</div>
		</div>
	</div>
</body>
</html>