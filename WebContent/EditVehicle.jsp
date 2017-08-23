<%@page import="java.time.Year"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/addvehicle.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Vehicle</title>
</head>
<body>
<div class="bg">
<h1>
AutoLog
</h1>
	<!-- <form method="post" action="AddVehicle">
        <label>Make:</label> <input type="text" name=make /><br/><br/>
        <label>Model:</label> <input type="text" name="model" /><br/><br/>
        <label>Year:</label> <input type="number" name="year" min="1800" max="2018" /><br/><br/>
        <label>Mileage:</label> <input type="number" name="mileage" min="0" max="500000"/><br/><br/>
        <input type="submit" value="Add Vehicle" />
    </form> -->
    
    <div class="container">  
  <form id="contact" action="DoEditVehicle" method="post">
    <h3>Edit Vehicle Form</h3>
    <h4>Enter Vehicle Information</h4>
    <fieldset>
      <input placeholder="Make" type="text" name="make" value="${vehicleToEdit.make}" tabindex="1" required autofocus>
    </fieldset>
    <fieldset>
      <input placeholder="Model" type="text" name="model" value="${vehicleToEdit.model}" tabindex="2" required>
    </fieldset>
    <fieldset>
      <input placeholder="Year" type="number" name="year" value="${vehicleToEdit.year}" min="1800" max="2018" tabindex="3" required>
    </fieldset>
    <fieldset>
      <input placeholder="Mileage" type="number" name="mileage" value="${vehicleToEdit.mileage}" min="0" max="500000" tabindex="4" required>
    </fieldset>
    <fieldset>
      <button name="submit" type="submit" id="contact-submit" data-submit="...Sending">Save Vehicle</button>
    </fieldset>
    <!-- <p class="copyright">Designed by <a href="https://colorlib.com" target="_blank" title="Colorlib">Colorlib</a></p>-->
  </form>
</div>
</div>
</body>
</html>