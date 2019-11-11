<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="Sender" method="POST">
<input type="text"   name="file" placeholder="Enter the File Name"></input>
<input type="text"   name="ip"   placeholder="Enter the IP address"></input>
<input type="text"   name="msg"  placeholder="Enter the amount of logs to be sent" ></input>
<input type="text"   name="batch" placeholder="Enter the Batch Amount"></input>
<input type="text"   name="TimeSlot" placeholder="Enter the Time interval"></input>
<input type="text"   name="port" placeholder="PORT"></input>
<input type="submit" name="udp"  value="UDP">UDP MESSAGE</input>
<input type="submit" name="tcp"  value="TCP">TCP MESSAGE</input>
</form> 
</body>
</html>