<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Management System</title>
<style>
img {
	
}

.bdetail {
	
}

th {
	padding-right: 50px;
}
</style>
</head>
<body style="background-color: #ffe6e6;">
	<h2 style="text-align: center; color: black;">Employee Details</h2>
	<table border="2" bordercolor="black" width="80%"
		style="margin-left: 225px; margin-top: 50px;">
		<tr>

			<th style="padding-right: 10px;"><u>empId</u></th>
			<th style="padding-right: 10px;"><u>empName</u></th>
			<th><u>DateOfJoining</u></th>
			<th><u>TrainingTrack</u></th>
		</tr>

		<tr>
			<td><h4>${add.empId}</h4></td>
			<td style="padding-right: 20px;"><h4>${add.empName}</h4></td>
			<td><h4>${add.dateOfJoining}</h4></td>
			<td><h4>${add.trainingTrack}</h4></td>
			<td><h4>
					<form:form action="/employee/remove/${add.empId}" method="DELETE">
						<input type="submit" value="Remove" />
					</form:form>
					<%-- 	<a href="/employee/remove/${add.empId}">Remove</a> --%>


				</h4></td>

			<td>
				<%-- <h4>
					<a href="update/${add.empName}">Edit</a>
				</h4> --%>
				<form:form action="/employee/update/${add.empName}" method="GET">
					<input type="submit" value="Edit" />
				</form:form>
			</td>
		</tr>

	</table>

</body>

</html>