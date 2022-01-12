<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div align="center">
		<h2>Here are your details</h2>
		<table>
			<tr>
				<th>TrackName</th>
				<th>Technologies</th>
	
			</tr>
			<c:forEach items="${tracks}" var="track">
				<tr>
					<td>${track.trackname}</td>
					<%-- <td>${emps.empName}</td>
					<td>${emps.dateOfJoining}</td>
					<td>${emps.trainingTrack}</td>
					<td><form:form action="/employee/remove/${emps.empId}"
							method="DELETE">
							<input type="submit" value="Remove" />
						</form:form></td>
					<td><form:form action="/employee/update/${emps.empName}"
							method="GET">
							<input type="submit" value="Edit" />
						</form:form></td> --%>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>