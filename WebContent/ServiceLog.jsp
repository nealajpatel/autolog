<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="java.lang.Integer"%>
<%@ page import="autolog.core.ServiceEntry"%>
<%@ page import="autolog.core.Vehicle"%>



<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>

<html>
<head>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/welcome.css">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>AutoLog Service Log</title>
</head>
<body>
<%
//allow access only if session exists
String make = null;
String model = null;
String year = null;
String mileage = null;
Vehicle v = new Vehicle(0,0, null, null, 0, 0);
if(session.getAttribute("vehicle") == null){
	response.sendRedirect("Welcome.jsp");
}else 
	
	v = (Vehicle) session.getAttribute("vehicle");
	
	make = v.getMake();
	model = v.getModel();
	year = Integer.toString(v.getYear());
	mileage = String.format("%,d", v.getMileage());
	
ArrayList<ServiceEntry> vehicleList = (ArrayList<ServiceEntry>) session.getAttribute("seList");

/*int id = (int) session.getAttribute("vID");
String make = (String) session.getAttribute("make");
String model = (String) session.getAttribute("model");
int year = (int) session.getAttribute("year");
int mileage = (int) session.getAttribute("mileage");*/

%>
<div class="bg">
<h1> <%=year %> <%=make %> <%=model %> with <%=mileage %> miles</h1>
<br>
<div class="container" id="contact"> 
<h3>Your Services</h3>
<table>
	<thead>
		<tr>
			<th>Description</th>
			<th>Date</th>
			<th>Cost</th>
			<th>Mileage</th>
			<th>Actions</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items= "${seList}" var="ServiceEntry" varStatus="loop">
			<tr>
				<td><c:out value="${ServiceEntry.description}"/></td>
				<td><c:out value="${ServiceEntry.date}"/></td>
				<td>
				<fmt:setLocale value = "en_US"/>
         		<fmt:formatNumber value = "${ServiceEntry.cost}" type = "currency"/>
    			</td>
				<td>
				<fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${ServiceEntry.mileage}" />
				</td>
				<td>
				<div id= "tableButtons">
					<form style= width:49% action="EditServiceEntry" method="post">
						<input type="hidden" name="row" value="${loop.index}">
						<button type="submit">Edit <i class="w3-margin-left glyphicon glyphicon-wrench"></i></button>
					</form>
					<form style= width:49% action="DeleteServiceEntry" method="post">
						<input type="hidden" name="row" value="${loop.index}">
						<button type="submit">Delete <i class="w3-margin-left glyphicon glyphicon-trash"></i></button>
					</form>
				</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<br>
<form action="AddServiceEntry.jsp">
<button name="submit" type="submit" id="contact-submit" data-submit="...Sending">Add Service Entry</button>
</form>
<form action="Welcome.jsp">
<button name="submit" type="submit" id="contact-submit" data-submit="...Sending">Back to Vehicles</button>
</form>
<form action="Logout" method="post">
<button name="submit" type="submit" id="contact-submit" data-submit="...Sending">Logout</button>
</form>
</div>
</div>
</body>
</html>