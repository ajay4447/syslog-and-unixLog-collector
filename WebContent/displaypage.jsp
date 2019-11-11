<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
body {
  font-family: Arial;
}

* {
  box-sizing: border-box;
}

form.example input[type=text] {
  padding: 10px;
  font-size: 17px;
  border: 1px solid grey;
  float: left;
  width: 80%;
  background: #f1f1f1;
}

form.example button {
  float: left;
  width: 20%;
  padding: 10px;
  background: #2196F3;
  color: white;
  font-size: 17px;
  border: 1px solid grey;
  border-left: none;
  cursor: pointer;
}

form.example button:hover {
  background: #0b7dda;
}

form.example::after {
  content: "";
  clear: both;
  display: table;
}
</style>
</head>
<body>
<form action="Search" method="post" target="_blank">
<input type="text" placeholder="Search by Log Type." name="fname">
<input type="text" placeholder="Search by Keyword." name="kname">
<input type="text" placeholder="Search by Description." name="dname">
<input type="submit" name="logs" value="search">  
</form>
<form action="Alerter.jsp" target=_blank method="get">
<input type="text" name="alert" placeholder="Enter the alerts required"></input>
<input type="submit" value="alerter"></input>
</form>

<form action="example" method="get">
<input type="submit" value="Display Logs">
</form>
</form>
</body>
</html> 
