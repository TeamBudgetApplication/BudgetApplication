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
<form:form action = "addExpense" modelAttribute = "newExpense">
	<div class="container">
		<div class="row">
			<div class="col">
				<h1 class="text-center mt-3">Budget ${budgetName}</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-3">
				<jsp:include page="vertical-navigation.jsp" /><form>
					
				</form>
			</div>
			<div class="col-9">ADD EXPENSE PAGE</div>
		</div>
	</div>
	</form:form>
</body>
</html>
</body>
</html>