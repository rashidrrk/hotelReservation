<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
          <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
   <script>
$(document).ready(function() {
    $('#listdata').DataTable();
} );</script>
<div class="table-responsive" >
<table class="table table-striped" id="listdata">
  <thead>
    <tr>
      <th scope="col">S.NO.</th>
<th>Hotel Name</th>
 <th>Room Type</th>
 <th>Room Capacity</th>
 <th>Total Available Rooms</th>
 <th>Action</th>
</tr>
 </thead>
  <tbody>
<% int i=1;%>
  <c:forEach var="rm" items="${rm}">
    <tr>
      <td><%=i%></td>
      <td>${rm.hotel_name}</td>
      <td>${rm.room_type}</td>
      <td>${rm.capacity}</td>
      <td>${rm.total_room}</td>
      <td><button class="btn btn-sm btn-info" onclick="book_room('${rm.id}','${rm.room_type}','${rm.hotel_name}','${rm.capacity}')">Book Now</button>   
    </tr>
<%i++; %>
    </c:forEach>
  </tbody>
</table>
</div>
<script>
function book_room(val1,val2,val3,val4)
{
$('#id').val(val1);
$('#room_type').val(val2);
$('#hotel_name').val(val3);
var no_guest=<c:out value='${guest}'/>;
var total_room= Math.ceil(no_guest/val4);
$('#no_room').val(total_room);
$("#room_data").modal({show:true,backdrop: 'static'});
}
function save_booking()
{
	var formData = $("#form_data1").serialize();
//	alert(formData);
	 $.ajax({
         type: "POST",
         url: "${pageContext.request.contextPath}/save-booking",
         data: formData,
         success: function (data) {
       // Process AJAX request
       	if(data==1)
       		{
           alert("Data Saved Successfully.");
           window.location = "${pageContext.request.contextPath}/new-search";
       		}
       	else if(data==0)
       		{
       		alert("Unable to save data at this time.");
       		}

         }
	  
   });
     // Prevent default form action
     event.preventDefault();
}

</script>
<div class="modal fade" id="room_data" role="dialog">
    <div class="modal-dialog  modal-lg">
    
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
        <h5 class="modal-title">Booking Details</h5>
        <button type="button" class="close" data-dismiss="modal" onclick="remove_data();" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
            <div class="modal-body " id="table1">
     <div class="m-2">       
	 <form method="POST" id="form_data1" action="${pageContext.request.contextPath}/save-booking" autocomplete="off" onsubmit="save_booking();">
	 <div class="row">
	 <div class="col-md-6 form-group">
	 <label for="hotel_name">Hotel Name<font color="red">*</font></label>
	 <input type="text" class="form-control input-sm"  maxlength="70" name="hotel_name" id="hotel_name" readonly>
	 <input type="hidden" class="form-control input-sm" min="0" name="id" id="id" required>
	 </div>
	 <div class="col-md-6 form-group">
	 <label for="room_type">Room Type<font color="red">*</font></label>
	 <input type="text" class="form-control input-sm"  maxlength="70" name="room_type" id="room_type" readonly>
	 </div>
	 <div class="col-md-6 form-group">
	 <label for="capacity">No. Of Guest<font color="red">*</font></label>
	 <input type="number" class="form-control input-sm" min="1" name="guest" id="guest" value="${guest}" readonly>
	 </div>
	 <div class="col-md-6 form-group">
	 <label for="capacity">Total Room Required<font color="red">*</font></label>
	 <input type="number" class="form-control input-sm" min="1" name="no_room" id="no_room" readonly>
	 </div>
	  <div class="col-md-6 form-group">
	 <label for="total_room">Check-In Date<font color="red">*</font></label>
	 <input type="date" class="form-control input-sm" value="${check_in}" name="check_in_date" id="check_in_date" readonly>
	 </div>
	  <div class="col-md-6 form-group">
	 <label for="total_room">Check-Out Date<font color="red">*</font></label>
	 <input type="date" class="form-control input-sm" name="check_out_date" value="${check_out}" id="check_out_date" readonly>
	 </div>
	 </div>
	 <div class="mt-3">
	 <button type="submit" class="btn btn-sm btn-info" id="save">Book now</button></div>
     </form>
     </div>
            </div>
          
        </div>
      
    </div>
    </div>
</body>
</html>