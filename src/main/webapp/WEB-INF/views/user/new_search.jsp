<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search-${HRS}</title>
</head>
<body>
  <jsp:include page="/WEB-INF/views/common/commonnav.jsp"></jsp:include>
<div class="body">
<div class="m-3">

<form class="form-inline" method="POST" id="form_data" onsubmit="call_search_method()">

<div class="form-group ml-2">
<label for="check_in">Check-in date:</label>
<input type="date" name="check_in" id="check_in" onchange="change_date(this);" class="form-control input-sm" required>
</div>
<div class="form-group ml-2">
<label for="check_out">Check-out date:</label>
<input type="date" name="check_out" id="check_out" class="form-control input-sm" required>
</div>
<div class="form-group ml-2">
<label for="guest_no">No. of Guest:</label>
<input type="number" class="form-control input-sm" min="1" name="guest" id="guest" required>
</div>
<div class="form-group ml-2">
<button type="submit" class="btn btn-sm btn-info">Search</button></div>
</form>

<div id="show_info" class="mt-2"></div>
</div>
<script>
$(function(){
    var dtToday = new Date();
    
    var month = dtToday.getMonth() + 1;
    var day = dtToday.getDate();
    var year = dtToday.getFullYear();
    if(month < 10)
        month = '0' + month.toString();
    if(day < 10)
        day = '0' + day.toString();
    
    var maxDate = year + '-' + month + '-' + day;
    
    $('#check_in').attr('min', maxDate);
});
function change_date(e)
{
	document.getElementById("check_out").value ="";
     var dtToday =new Date(e.value);
     
     var month = dtToday.getMonth() + 1;
     var day = dtToday.getDate();
     var year = dtToday.getFullYear();
     if(month < 10)
         month = '0' + month.toString();
     if(day < 10)
         day = '0' + day.toString();
    
     var maxDate = year + '-' + month + '-' + day;
     
     $('#check_out').attr('min', maxDate);
  
 }
function call_search_method()
{
	var formData = $("#form_data").serialize();
	//alert(formData);
	 $.ajax({
         type: "POST",
         url: "${pageContext.request.contextPath}/connect-search_data",
         data: formData,
         success: function (data) {
       // Process AJAX request
       	$('#show_info').html(data);

         }
	  
   });
     // Prevent default form action
     event.preventDefault();
}
</script>
</div>
</body>
</html>