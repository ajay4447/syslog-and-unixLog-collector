<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LOGGER</title>
</head>

<body>


<form action="LogCollector" method="POST">
<input type="submit" name="start" value="LISTEN TCP">
</form>

<form action="LogCollectorUDP" method="POST">
<input type="submit" name="start" value="LISTEN UDP">
</form>
<form action="Display" method="post">
<input type="submit" name ="display" value="GETDATA">
<jsp:forward page="/CollectorCaller"/>

</form>
</body>
</html>