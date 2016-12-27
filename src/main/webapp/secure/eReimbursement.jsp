<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Reimbursements</title>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
	function myFunction() {
		// Declare variables
		var input, filter, table, tr, td, i;
		input = document.getElementById("myInput");
		filter = input.value.toUpperCase();
		table = document.getElementById("eTable");
		tr = table.getElementsByTagName("tr");

		// Loop through all table rows, and hide those who don't match the search query
		for (i = 0; i < tr.length; i++) {
			td = tr[i].getElementsByTagName("td")[4];
			td2 = tr[i].getElementsByTagName("td")[6];
			if (td) {
				if ( td.innerHTML.toUpperCase().indexOf(filter) > -1 || td2.innerHTML.toUpperCase().indexOf(filter) > -1) {
					tr[i].style.display = "";
				} else {
					tr[i].style.display = "none";
				}
			}
		}
	}
</script>

</head>

<style>
html, body {
	background-color: #ccc;
	background-image: url("http://subtlepatterns.com/patterns/subtle_dots.png");
}
table {
	background-color: whitesmoke;
}
#menuBar {
	background-color: black;
	opacity: .85;
}

#employeeView {
	
}

#leftBar {
	color: white;
	font-size: 30px;
	opacity: .6;
}

#myInput {
	height: 2.5em;
	width: 100%;
	margin-top: .25em;
}

#logoutButton {
	background: transparent;
	border: none;
	font-size: 17px;
	float: right;
	color: #ccc;
	margin-top: .2em;
}

#reimbButton {
	float: right;
}
</style>

<body>
	<div id="employeeView" class="container">
		<div style="overflow: auto;">
		
			<div id="menuBar" class="row">
				<div id="leftBar" class="col-lg-3">ERS</div><!-- /.col-lg-3 -->
				
				<div id="centerBar" class="col-lg-6">
					<input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search for descriptions or status..">
				</div>	<!-- /.col-lg-6 -->
				
				<div id="rightBar" class="col-lg-3">
					<button id="logoutButton" type="button"
						class="btn btn-default dropdown-toggle" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false">
						Signed in as <c:out value="${name}" />
						<span class="caret"></span>
					</button>
					<ul class="dropdown-menu dropdown-menu-right">
						<li><a href="/ers/logOut.do">log out</a></li>
					</ul>
				</div>	<!-- /.col-lg-6 -->
			</div>	<!-- /.row -->
			
			<table id="eTable" class="table table-striped" style="max-height: 500px;">
				<tr>
					<th>reimbID</th>
					<th>Submitted</th>
					<th>Resolved</th>
					<th>Resolver</th>
					<th>description</th>
					<th>Type</th>
					<th>Status</th>
					<th>Amount</th>
				</tr>
				<!-- for-each loop..  for(Employee emp : list) -->
				<c:forEach var="reimb" items="${list}">
					<tr>
						<td><c:out value="${reimb.id}" /></td>
						<td><fmt:formatDate type="date"
								value="${reimb.timeSubmitted}" /></td>
						<td><fmt:formatDate type="date" value="${reimb.timeResolved}" /></td>
						<td><c:out value="${reimb.resolver}" /></td>
						<td><c:out value="${reimb.description}" /></td>
						<td><c:out value="${reimb.type}" /></td>
						<td><c:out value="${reimb.status}" /></td>
						<td><fmt:formatNumber type="currency" value="${reimb.amount}" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div>
			<!-- Trigger the modal with a button -->
			<button id="reimbButton" type="button" class="btn btn-info btn-lg"
				data-toggle="modal" data-target="#myModal">New
				Reimbursement</button>

			<!-- Modal -->
			<div id="myModal" class="modal fade" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<form action="submitReimbursement.do" method="post">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Create a new Reimbursement</h4>
							</div>

							<div class="modal-body">
								Type: <select name="rType">
									<c:forEach var="temp" items="${listOfType}">
										<option value="${temp.typeID}">${temp.typeName}</option>
									</c:forEach>
								</select> Amount: <input type="number" step=".01" name="rAmount" /><br />
								Description: <input type="text" name="rDescription" /> <br />

							</div>

							<div class="modal-footer">
								<button type="submit" class="btn btn-primary">Submit</button>
								<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>


