<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="autolog.core.Vehicle"%>


<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>

<html>
<head>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/welcome.css">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>AutoLog Home</title>
</head>
<body>
<%
//allow access only if session exists
String user = null;
if(session.getAttribute("name") == null){
	response.sendRedirect("index.html");
}else user = (String) session.getAttribute("name");
ArrayList<Vehicle> vehicleList = (ArrayList<Vehicle>) session.getAttribute("vehicleList");
%>
<div class= "bg">
<h1>Welcome <%=user %>!</h1>
<br>
<div class="container" id="contact"> 
<h3>Your Vehicles</h3>
<table>
	<thead>
		<tr>
			<th>Make</th>
			<th>Model</th>
			<th>Year</th>
			<th>Mileage</th>
			<th>Actions</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items= "${vehicleList}" var="Vehicle" varStatus="loop">
			<tr>
				<td><c:out value="${Vehicle.make}"/></td>
				<td><c:out value="${Vehicle.model}"/></td>
				<td><c:out value="${Vehicle.year}"/></td>
				<td>
				<fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${Vehicle.mileage}" />
				</td>
				<td>
					<form action="ServiceEntries" method="post">
						<input type="hidden" name="row" value="${loop.index}">
						<button type="submit">View <i class="w3-margin-left glyphicon glyphicon-book"></i></button>
					</form>
					<form action="EditVehicle" method="post">
						<input type="hidden" name="row" value="${loop.index}">
						<button type="submit">Edit <i class="w3-margin-left glyphicon glyphicon-wrench"></i></button>
					</form>
					<form action="DeleteVehicle" method="post">
						<input type="hidden" name="row" value="${loop.index}">
						<button type="submit">Delete <i class="w3-margin-left glyphicon glyphicon-trash"></i></button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<br>
<form style= width:49% action="AddVehicle.jsp">
<button name="submit" type="submit" id="contact-submit" data-submit="...Sending">Add Vehicle</button>
</form>
<form style= width:49% action="Logout" method="post">
<button name="submit" type="submit" id="contact-submit" data-submit="...Sending">Logout</button>
</form>
</div>
</div>
</body>
</html>