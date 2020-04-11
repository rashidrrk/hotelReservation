<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<head>
<meta charset="ISO-8859-1">
<title>View -${HRS}</title>
</head>
<body>
  <jsp:include page="/WEB-INF/views/common/commonnav.jsp"></jsp:include>
<div class="body">
        <script>
$(document).ready(function() {
    $('#listdata').DataTable();
} );</script>

<div class="ml-3 mr-3">
<div class="table-responsive" >
<table class="table table-striped" id="listdata">
  <thead>
    <tr>
      <th scope="col">S.no.</th>
<th>Customer Name</th>
 <th>No. of Guest </th>
 <th>Room Type</th>
 <th>Hotel Name</th>
 <th>Check In Date</th>
  <th>Check Out Date</th>
   <th>Number Of Room Booked</th>
</tr>
 </thead>
  <tbody>
<% int i=1;%>
  <c:forEach var="rm" items="${rm}">
    <tr>
      <td><%=i %></td>
      <td>${rm.customer_name}</td>
      <td>${rm.guest}</td>
      <td>${rm.room_type}</td>
      <td>${rm.hotel_name}</td>
      <td>${rm.check_in_date}</td>
      <td>${rm.check_out_date}</td> 
      <td>${rm.no_room}</td> 
    </tr>
<%i++; %>
    </c:forEach>
  </tbody>
</table>
</div>
</div>
</div>

</body>
</html>