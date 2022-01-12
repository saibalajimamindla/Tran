<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Management System</title>
</head>
<body>
	<div align="center">
		<h1>Add Employee</h1>
		<form:form action="register" method="post"
			modelAttribute="addEmployee">
			<h2 style="color: navy; font-family: sans-serif;">Fill The Form</h2>
			<table align="center">
				<tr>
					<td>EmployeeID</td>
					<td>:</td>
					<td><form:input path="empId" placeholder="EmployeeId" /></td>

				</tr>
				<tr>
					<td>EmployeeName</td>
					<td>:</td>
					<td><form:input path="empName" placeholder="EmployeeName" /></td>
				</tr>
				<tr>
					<td>DateOfJoining</td>
					<td>:</td>
					<td><form:input type="date" path="dateOfJoining"
							placeholder="dateOfJoining" /></td>
				</tr>
				<%-- <tr>
					<td><form:input path="trainingTrack"
							placeholder="TrainingTrack" /></td>
				</tr> --%>
				<tr>
					<td>TrainingTrack</td>
					<td>:</td>
					<td><form:select path="trainingTrack">
							<form:option value="hybris" label="Hybris" />
							<form:option value="java" label="Java" />
							<form:option value="aem" label="AEM" />
							<form:option value="default" label="Default" />
						</form:select></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td><input type="submit" value="submit" /></td>
				</tr>
			</table>
		</form:form>
	</div>

	<div align="center" style="padding-top: 20px; padding-right: 30px;">

		<form:form action="/employee/allEmployeeDetail" method="get">
			<input type="submit" value="getAllEmployeeData" />

		</form:form>


	</div>

	<div align="center" style="padding-top: 20px; padding-right: 45px;">

		<form:form action="/TrainingTrack/getAllTracks" method="get">
			<input type="submit" value="getAllTheTracks" />

		</form:form>


	</div>
	<div align="center" style="padding-top: 20px; padding-right: 30px;">

		<form:form action="/technology/getAllTechnologies" method="get">
			<input type="submit" value="getAllTechnologies" />

		</form:form>


	</div>
	<div align="center" style="padding-top: 20px; padding-right: 30px;">

		<form:form action="/technology/AddTechnologies" method="GET">
			<input type="submit" value="AddTechnologies" />
		</form:form>


	</div>
	<div align="center" style="padding-top: 20px; padding-right: 30px;">

		<form:form action="/TrainingTrack/addTracks" method="GET">
			<input type="submit" value="AddNewTrack" />
		</form:form>


	</div>


</body>
</html>