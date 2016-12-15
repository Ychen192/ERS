<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
</head>
<body>


	<div class="container">
		<h1>Manager</h1>
		<c:out value="${name}" />
	</div>
	<form action="updateStatus.do" method="get">
		<div class="container table-responsive"
			style="overflow: auto; max-height: 500px;">
			<table class="table">
				<tr>
					<th>reimbID</th>
					<th>Submitted</th>
					<th>Employee</th>
					<th>Resolved</th>
					<th>Resolver</th>
					<th>description</th>
					<th>Type</th>
					<th>Status</th>
					<th>Accept/Deny</th>
					<th>Amount</th>
				</tr>
				<!-- for-each loop..  for(Employee emp : list) -->
				<c:forEach var="reimb" items="${list}">
					<tr>
						<td><c:out value="${reimb.id}" /></td>
						<td><fmt:formatDate type="date"
								value="${reimb.timeSubmitted}" /></td>
						<td><c:out value="${reimb.author}" /></td>
						<td><fmt:formatDate type="date" value="${reimb.timeResolved}" /></td>
						<td><c:out value="${reimb.resolver}" /></td>
						<td><c:out value="${reimb.description}" /></td>
						<td><c:out value="${reimb.type}" /></td>
						<td><c:out value="${reimb.status}" /></td>
						<td><center>
								<c:if test="${reimb.status == 'PENDING'}">
									<input type="checkbox" name="status" value="${reimb.id}" />
								</c:if>
							</center></td>
						<td><fmt:formatNumber type="currency" value="${reimb.amount}" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<center>
			<button type="submit" class="btn btn-success" name="accept">Accept</button>
			<button type="submit" class="btn btn-danger" name="deny">Deny</button>
		</center>
	</form>
</body>
</html>