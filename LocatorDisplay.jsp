<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Airport and Zipcode Loacator</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	</head>
	
	<body>
	<div class="container">
  <div class="jumbotron">
    <h1>Airport and Zipcode Locator</h1>
    <p><b>Homework3</b></p> 
  </div>
			<label>Enter a Zipcode and Radius to list AIRPORTS:</label><br>
			<form action="../Homework3/AirController" method="post">
				<input type="text" name="zipcode" value="${ param.zipcode }" placeholder="Zipcode" />
				<input type="text" name="radius" value="${ param.radius }" placeholder="Radius (miles)" />
				<input type="submit" value="Submit" name="Submit" />
			</form>
			
			<label>Enter a City to list ZIPCODES:</label><br>
			<form action="../Homework3/AirController" method="post">
				<input type="text" name="city" value="${ param.city }" placeholder="City" align="right" />
				<input type="submit" value="Submit" name="Submit" align="right" />
			</form>
		
		<c:if test="${ airports != null }">
			<label>List of AIRPORTS:</label><br><br>
			<c:forEach items="${ airports }" var="airport">
			<h1>${airport.airport}</h1> 
				<img src="https://maps.googleapis.com/maps/api/staticmap?center=${ airport.getLatitude()},${ airport.getLongitude() }&zoom=13&size=300x200&sensor=false&markers=color:red%7C+${ airport.getLatitude()},${airport.getLongitude()})" />
     			</c:forEach>
		</c:if>
		
	
		<c:if test="${ zipcodes != null }">
			<label>List of ZIPCODES:</label><br><br>
			<c:forEach items="${ zipcodes }" var="zipcode">
				
				<h1>${zipcode.zip}</h1> 
				<img src="https://maps.googleapis.com/maps/api/staticmap?center=${ zipcode.getLatitude()},${ zipcode.getLongitude() }&zoom=13&size=300x200&sensor=false&markers=color:red%7C+${ zipcode.getLatitude()},${zipcode.getLongitude()})" />
     			
				
			</c:forEach>
		</c:if>
		</div>
	</body>	
</html>