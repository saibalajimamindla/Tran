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
		<h1>Add New Tracks</h1>
		<form:form action="/TrainingTrack/addTrainingTrack" method="post"
			modelAttribute="addTrack">
			<h2 style="color: navy; font-family: sans-serif;">Here</h2>
			<table align="center">
				<tr>
					<td>TrackName</td>
					<td>:</td>
					<td><form:input path="trackname" placeholder="TrackName" /></td>
				</tr>

				<%-- <c:forEach items="${technologies.TechnologyName}" var="tech">
					<tr>
						<td>Technologies<form:checkbox path="technologies"
								value="${tech.TechnologyName}" /></td>
						<form:checkbox path="technologies" value="default" />
						<form:checkbox path="technologies" value="AEM" />
						<form:checkbox path="technologies" value="Java" />
					</tr>
				</c:forEach> --%>
				<!-- <tr>
					<td><input type="submit" value="submit" /></td>
				</tr> -->

				<tr>
					<td>Technologies</td>
					<td>:</td>
					<td><form:checkbox path="technologies" value="corejava" />CoreJava
						<form:checkbox path="technologies" value="j2ee" /> J2EE<form:checkbox
							path="technologies" value="spring" /> Spring<form:checkbox
							path="technologies" value="html" />HTMl<form:checkbox
							path="technologies" value="css" />css</td>
				</tr>
				<tr>
					<td><input type="submit" value="submit" /></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>