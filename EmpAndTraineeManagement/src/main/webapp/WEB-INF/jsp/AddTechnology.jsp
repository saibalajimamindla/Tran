<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<h1>Add Technology</h1>
		<form:form action="/technology/addTech" method="post"
			modelAttribute="addTechnology">
			<h2 style="color: navy; font-family: sans-serif;">Add Here</h2>
			<table align="center">
				<tr>
					<td><form:input path="TechnologyName"
							placeholder="TechnologyName" /></td>
				</tr>
				<tr>
					<td><form:input path="TechnologyType"
							placeholder="TechnologyType" /></td>
				</tr>
				<tr>
					<td><form:input path="Technologyduration"
							placeholder="Technologyduration" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="submit" /></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>/addTech