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
<div class="ml-3 mr-3 mb-2">
<a class="btn btn-outline-primary btn-sm" href="#" onclick="add_new_data('0');" role="button">Add New Rooms</a>
</div>
<div class="ml-3 mr-3">
<div class="table-responsive" >
<table class="table table-striped" id="listdata">
  <thead>
    <tr>
      <th scope="col">S.NO.</th>
<th>Hotel Name</th>
 <th>Room Type</th>
 <th>Room Capacity</th>
 <th>Total Room</th>
 <th>Status</th>
 <th>Action</th>
</tr>
 </thead>
  <tbody>

  <c:forEach var="rm" items="${rm}">
    <tr>
      <td>${rm.id}</td>
      <td>${rm.hotel_name}</td>
      <td>${rm.room_type}</td>
      <td>${rm.capacity}</td>
      <td>${rm.total_room}</td>
      <td>${rm.status}</td>
      <td><button class="btn btn-sm btn-info" onclick="add_new_data('${rm.id}')">Edit</button>   
    </tr>

    </c:forEach>
  </tbody>
</table>
</div>
</div>
</div>
<script>
function add_new_data(val)
{
	if(val!="0")
     {
		$.ajax({
    	    type: "GET",
    	   
    	    url: "${pageContext.request.contextPath}/get_room_data",
    	    data : ({
    	    	'id':val,
			}),
    	  
    	    success: function (data) {
    	   // 	alert(data.id)
    	        
    	    	$('#id').val(data.id);
    	    	$('#hotel_name').val(data.hotel_name);
    	    	$('#room_type').val(data.room_type);
    	    	if(data.status=='A'){
    	    	$('#status').prop( "checked", true );}
    	    	else {$('#status').prop( "checked", false )}
    	    	$('#capacity').val(data.capacity);
    	    	$('#total_room').val(data.total_room);

    	    	$("#room_data").modal({show:true,backdrop: 'static'});
    	       }
    	  }); 
	}
	else{$('#id').val('0');
	$("#room_data").modal({show:true,backdrop: 'static'});}
	
}
function remove_data()
{
$('#id').val('0');
$('#hotel_name').val('');
$('#room_type').val('');
$('#status').prop( "checked", true );
$('#capacity').val('');
$('#total_room').val('');
}
function save_data()
{
	var formData = $("#form_data").serialize();
	//alert(formData);
	 $.ajax({
         type: "POST",
         url: "${pageContext.request.contextPath}/connect-room-data",
         data: formData,
         success: function (data) {
       // Process AJAX request
       	if(data==1)
       		{
           alert("Data Saved Successfully.");
           window.location = "${pageContext.request.contextPath}/rooms-list";
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
        <h5 class="modal-title">Room Details</h5>
        <button type="button" class="close" data-dismiss="modal" onclick="remove_data();" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
            <div class="modal-body " id="table1">
     <div class="m-2">       
	 <form method="POST" id="form_data" action="${pageContext.request.contextPath}/connect-room-data" autocomplete="off" onsubmit="save_data();">
	 <div class="row">
	 <div class="col-md-6 form-group">
	 <label for="hotel_name">Hotel Name<font color="red">*</font></label>
	 <input type="text" class="form-control input-sm"  maxlength="70" name="hotel_name" id="hotel_name" required>
	 <input type="hidden" class="form-control input-sm" min="0" name="id" id="id" required>
	 </div>
	 <div class="col-md-6 form-group">
	 <label for="room_type">Room Type<font color="red">*</font></label>
	 <select class="form-control input-sm" name="room_type" id="room_type" required>
	 <option value="" disabled selected>Select room type</option>
	 <option value="Standard">Standard</option>
	 <option value="Deluxe">Deluxe</option>
	 <option value="Luxury">Luxury</option>
	 </select>
	 </div>
	 <div class="col-md-6 form-group">
	 <label for="capacity">Room Capacity<font color="red">*</font></label>
	 <input type="number" class="form-control input-sm" min="0" name="capacity" id="capacity" required>
	 </div>
	  <div class="col-md-6 form-group">
	 <label for="total_room">Total Room<font color="red">*</font></label>
	 <input type="number" class="form-control input-sm" min="0" name="total_room" id="total_room" required>
	 </div>
	 <div class="col-md-6 form-group">
	 <label for="status">Status<font color="red">*</font></label>
	  <div class="form-check">
	 <input type="checkbox" class="form-check-input"  name="status" id="status" checked required>
	 </div>
	 </div>
	 </div>
	 <div class="mt-3">
	 <button type="submit" class="btn btn-sm btn-info" id="save">Save</button></div>
     </form>
     </div>
            </div>
          
        </div>
      
    </div>
</div>
</body>
</html>