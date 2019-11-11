<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-1.10.2.js"
	type="text/javascript"></script>
	<script src="js/app-ajax.js" type="text/javascript"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body onload="myFunction()" >
<script>

function myFunction() {
	//alert("hello");
	  myVar = setInterval(alertFunc, 3000);
	}

function alertFunc() 
{   
	var a = window.location.search;
	
	$.ajax({
		url:"Alert",
		method:"POST",
		data:{a:a},
		dataType:"text",
		success: function(logs) {
		//alert("hello");	 
		$('#logs').show();
		$('#logs').append(logs);
		},
		
		});
	 

}
</script>
<div id="logs"><br></div>
</body>



</html>