<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Management System</title>
</head>
<body>
	<div align="center">
		<form:form action="${pageContext.request.contextPath}/employee/update" method="POST" modelAttribute="updateEmployee"> 
			<table>
				<tr>
					<td>Employee Id</td>
					<td>:</td>
					<td><h4>
							<form:input path="empName" value="${add.empId}" />
						</h4></td>

				</tr>
				<tr>
					<td>EmployeeName</td>
					<td>:</td>
					<td><h4>
							<form:input path="empName" value="${add.empName}" />
						</h4></td>
				</tr>
				<tr>
					<tr><td>DateOfjoining</td>
					<td>:</td>
					<td><h4>
							<form:input path="dateOfJoining" value="${add.dateOfJoining}" />
						</h4></td>
				</tr>
				<tr>
					<td>TrainingTrack</td>
					<td>:</td>
					<td><h4>
							<form:input path="trainingTrack" value="${add.trainingTrack}" />
						</h4></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td><h4>
							<input type="submit" value="update" />
						</h4></td>
				</tr>
			</table>
			</form:form>
	</div>

</body>
</html>