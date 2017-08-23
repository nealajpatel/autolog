<%@page import="java.time.Year"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/addvehicle.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Service Entry</title>
</head>
<body>
<div class="bg">
<h1>
AutoLog
</h1>
    <div class="container">  
  <form id="contact" action="DoEditServiceEntry" method="post">
    <h3>Edit Service Entry Form</h3>
    <h4>Enter Service Information</h4>
    <fieldset>
      <input placeholder="Description" type="text" name="desc" value="${seToEdit.description}" tabindex="1" required autofocus>
    </fieldset>
    <fieldset>
      <input placeholder="Date" type="date" name="date" value="${seToEdit.date}" tabindex="2" required>
    </fieldset>
    <fieldset>
      <input placeholder="Cost" type="number" step="0.01" name="cost" value="${seToEdit.cost}" tabindex="3" required>
    </fieldset>
    <fieldset>
      <input placeholder="Mileage" type="number" name="mileage" value="${seToEdit.mileage}" min="0" max="500000" tabindex="4" required>
    </fieldset>
    <fieldset>
      <button name="submit" type="submit" id="contact-submit" data-submit="...Sending">Save Service Entry</button>
    </fieldset>
  </form>
</div>
</div>
</body>
</html>