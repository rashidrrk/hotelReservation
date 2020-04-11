<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login - ${HRS}</title>
	    <jsp:include page="/WEB-INF/views/common/commonlink.jsp"></jsp:include>
</head>
<body>

<section class="container-fluid mt-5">
<section class="row justify-content-center">
<section class="col-lg-4 col-md-6 col-sm-8">
<c:choose>
			    <c:when test="${(not empty SPRING_SECURITY_LAST_EXCEPTION)}">
			        <div class="alert alert-warning " role="alert" data-auto-dismiss>
				        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
					    	<span aria-hidden="true">&times;</span>
						</button>
						 Your login attempt was not successful due to: <strong> <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/></strong>
					</div>
				</c:when>
				<c:when test="${not empty success}">
	              <div class="alert alert-warning " role="alert" data-auto-dismiss>
		              <button type="button" class="close" data-dismiss="alert" aria-label="Close">
						    <span aria-hidden="true">&times;</span>
					   </button>
					   <strong>Successfully logout</strong>
				  </div>
				</c:when>
			</c:choose>
	<div style="display: flex; justify-content: center;">

</div>


 <h4 class="text-center">Hotel Reservation System</h4>
 <br class="blank" />
<form class="form-container" method="POST" action="authenticateUser">
<div class="form-group">
 <div class="input-group">
 <div class="input-group-prepend">
      <div class="input-group-text" style="width:40px;"><i class="fa fa-user"></i></div>
    </div>
	 <input type="text"  class="form-control" name="user" id="user" onkeyup="this.value = this.value.toUpperCase();"  placeholder="User Name" required>
 </div></div>
 <div class="form-group">
  <div class="input-group">
 <div class="input-group-prepend">
      <div class="input-group-text" style="width:40px;"><i class="fa fa-key"></i></div>
    </div>
 <input type="password" class="form-control"  id="password" name="pass"  placeholder="Enter Your Password" required>
 </div>
</div>
			
 <button class="btn btn-primary btn-block" style="margin-top:5px;" type="submit" name="submit" value="Login">LOGIN</button>
<div class="text-center"><a href="#" onclick="show_registration_page()" >Register Here.</a></div>

 </form> 
  </section>
  </section>
  </section>  
	<c:remove var = "SPRING_SECURITY_LAST_EXCEPTION" />	
	<script>
	function show_registration_page()
	{
		  $("#registration_page").modal({show:true,backdrop: 'static'});	
	}
	var check = function() {
		  if (document.getElementById('new_password').value == document.getElementById('cpassword').value) {
		    document.getElementById('message').style.color = 'green';
		    document.getElementById('message').innerHTML = 'matching';
		    document.getElementById("submitbtn").disabled =false;
		  } 
		else  {
		    document.getElementById('message').style.color = 'red';
		    document.getElementById('message').innerHTML = 'not matching';
		    document.getElementById("submitbtn").disabled = true;
		  }
		}
	function remove_data()
	{
		$('#userId').val('');
		$('#email_id').val('');
		$('#cpassword').val('');
		$('#name').val('');
		$('#new_password').val('');
		$('#mobile_number').val('');
		$('#message').html('');
	}
	function check_userId_exists(e)
	{
		var ObjVal=e.value;
		$.ajax({
    	    type: "GET",
    	   
    	    url: "${pageContext.request.contextPath}/checkuserId",
    	    data : ({
    	    	'userid':ObjVal,
			}),
    	  
    	    success: function (data) {
    	    	if(data== 1)
    	        {
    	    		alert("User Id already exits.");
       	         $('#userId').val('');   
    	        }
    	        
    	
    	       }
    	  }); 
	}
	function check_mobile_no(e)
	{
		var ObjVal=e.value;
		$.ajax({
    	    type: "GET",
    	   
    	    url: "${pageContext.request.contextPath}/checkMobileNo",
    	    data : ({
    	    	'mno':ObjVal,
			}),
    	  
    	    success: function (data) {
    	    	if(data== 1)
    	        {
    	    		alert("Mobile Number Already Exists.");
       	         $('#mobile_number').val('');   
    	        }
    	        
    	
    	       }
    	  }); 
	}
	function save_registration()
	{
		var cpass=$('#cpassword').val();
		var pass=$('#new_password').val();
		if(cpass!=pass)
			{
			alert("Confirm Password is not matching.");
			}
		else{
		var formData = $("#register_data").serialize();
	//	alert(formData);
		 $.ajax({
             type: "POST",
             url: "${pageContext.request.contextPath}/register",
             data: formData,
             success: function (data) {
           // Process AJAX request
           	if(data==1)
           		{
               alert("Register Successfully.");
               $("#registration_page").modal('hide');
           		}
           	else if(data==0)
           		{
           		alert("Unable to register.");
           		}

             }
   	  
       });
	}
         // Prevent default form action
         event.preventDefault();
       }
	function empty_pass()
	{
		$('#cpassword').val('');
	}
	</script>
	<div class="modal fade" id="registration_page" role="dialog">
    <div class="modal-dialog  modal-lg">
    
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
        <h5 class="modal-title">Registration</h5>
        <button type="button" class="close" data-dismiss="modal" onclick="remove_data();" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
            <div class="modal-body " id="table1">
     <div class="m-2">       
	 <form method="POST" id="register_data" action="${pageContext.request.contextPath}/register" autocomplete="off" onsubmit="save_registration();">
	 <div class="row">
	 <div class="col-md-6 form-group">
	 <label for="userId">User Id<font color="red">*</font></label>
	 <input type="text" class="form-control input-sm"  maxlength="70" name="userId" onblur="check_userId_exists(this);" id="userId" required>
	 </div>
	 <div class="col-md-6 form-group">
	 <label for="name">Name<font color="red">*</font></label>
	 <input type="text" class="form-control input-sm" maxlength="70" name="name" id="name" required>
	 </div>
	 <div class="col-md-6 form-group">
	 <label for="mobile_number">Mobile Number<font color="red">*</font></label>
	 <input type="text" class="form-control input-sm" maxlength="10" pattern="[1-9]{1}[0-9]{9}" onblur="check_mobile_no(this);" name="mobile_number" id="mobile_number" required>
	 </div>
	 <div class="col-md-6 form-group">
	 <label for="email_id">Email Id<font color="red">*</font></label>
	 <input type="email" class="form-control input-sm" maxlength="30" name="email_id" id="email_id" required>
	 </div>
	 <div class="col-md-6 form-group">
<label for="new_passowrd">Password<font color="red">*</font></label>
<input type="password" class="form-control input-sm" autocomplete="new-password" onblur="empty_pass();" name="new_password" id="new_password" pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$" title="Minimum eight characters, at least one letter, one number and one special character"  required/>
</div>

<div class="col-md-6 form-group">
<label>Confirm Password<font color="red">*</font></label>
<input type="password" class="form-control input-sm" name="cpassword" id="cpassword" onkeyup='check();' required/><span id='message'></span>
</div>
	 </div>
	 <button type="submit" class="btn btn-sm btn-info" id="save">Register</button>
     </form>
     </div>
            </div>
          
        </div>
      
    </div>
</div>
</body>
</html>