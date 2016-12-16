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

</head>
<body>
	<div class="container">

		<c:out value="${name}" />
		<h1>Employee</h1>

		<div style="overflow: auto; max-height: 500px;">
			<table class="table">
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
			<button type="button" class="btn btn-info btn-lg" data-toggle="modal"
				data-target="#myModal">New Reimbursement</button>

			<!-- Modal -->
			<div id="myModal" class="modal fade" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<form action="submitReimbursement.do">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Create a new Reimbursement</h4>
							</div>

							<div class="modal-body">
								IDNumber:   <input type="number"   name="rID" /> <br />
								Type: <select name="rType">
										<c:forEach var="temp" items="${listOfType}">
											<option value="${temp.typeID}" >${temp.typeName}</option>
										</c:forEach>
									  </select> <br /> 
								Description: <input type="text"    name="rDescription" /> <br /> 
								Amount:      <input type="number" step="any" name="rAmount" /> <br />
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