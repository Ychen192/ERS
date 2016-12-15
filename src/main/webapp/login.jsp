<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>Reimbursement</title>
</head>
<body>
	
	<h1>Reimbursement</h1>
	
	<c:if test="${not empty authFailed}">
	<font color="red"> <c:out value="${authFailed}" /> </font>
	</c:if>
	
	<form action="login.do" method="post" >
		Username:	<input type="text" name="userid" />		<br/>
		Password:	<input type="password" name="pass" />	<br/>
		<input type="submit" value="Login"/>
	</form>

</body>
</html>
