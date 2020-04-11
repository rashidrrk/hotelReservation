<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<head>
<meta charset="ISO-8859-1">
<title>Home -${HRS}</title>
</head>
<body>
  <jsp:include page="/WEB-INF/views/common/commonnav.jsp"></jsp:include>
<div class="body">
<div class="m-4">
<h3 class="text-center">${username}</h3>
</div>
</div>
</body>
</html>