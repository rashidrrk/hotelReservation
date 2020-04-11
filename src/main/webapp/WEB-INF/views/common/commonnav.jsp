<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
         <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
</head>
<body>
<style>
.body
{
margin-top:130px;
}
</style>
<div class="fixed-top">
<h1 class="text-center">Hotel Reservation System</h1>
<jsp:include page="/WEB-INF/views/common/commonlink.jsp"></jsp:include>

<nav class="navbar navbar-expand-lg navbar-light bg-light">

    <ul class="navbar-nav">
    <li class="nav-item">
      <a class="nav-link" href="${pageContext.request.contextPath}/home" aria-hidden="true">Home</a>
      </li>
      <sec:authorize access="hasAuthority('USER')">
      <li class="nav-item">
      <a class="nav-link" href="${pageContext.request.contextPath}/new-search" aria-hidden="true"> Search Hotel</a>
      </li>
      </sec:authorize>
       
       <sec:authorize access="hasAuthority('ADMIN')">
       <li class="nav-item">
      <a class="nav-link" href="${pageContext.request.contextPath}/rooms-list" aria-hidden="true">View Hotel Details</a>
      </li>
      </sec:authorize>
      <sec:authorize access="hasAuthority('ADMIN')">
      <li class="nav-item">
      <a class="nav-link" href="${pageContext.request.contextPath}/view-booking-details" aria-hidden="true">View All Hotel Bookings</a>
      </li>
      </sec:authorize>
         <li class="nav-item"> 
      <a class="nav-link" href="${pageContext.request.contextPath}/logout" aria-hidden="true">Logout</a>
      </li>
      </ul>

</nav>
</div>

