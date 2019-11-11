package com.server.Collector.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DisplayDb {
	public String display()
	{  
		final String DB_URL = "jdbc:mysql://localhost:3306/ajay";
	 	String user="ajay";
	 	String pwd="root";
	 	String str="";
	 	try
	   	   {
	   		Connection conn=null;
		   	Statement st=null;
		   	Class.forName("com.mysql.jdbc.Driver");
		    conn=DriverManager.getConnection(DB_URL,user,pwd);
		    st=conn.createStatement();
		    
		    String sql2 = "select * from syslogs;";
		    ResultSet rs=st.executeQuery(sql2);
		     str="<table border=1><tr><th>TYPE</th><th>SERIAL</th><th>TIMESTAMP</th><th>FACILITY-SEVERITY-MESSAGEID</th><th>DESCRIPTION</th><th>MESSAGE</th><th>EXPLANATION</TH><TH>FOREIGN ADDRESS</TH><TH>GLOBAL ADDRESS</TH><TH>LOCAL ADDRESS</TH></TR>";
		     
		     while(rs.next())
		     {   
		    	
		    	 str+="<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(8)+"</td><td>"+rs.getString(9)+"</td><td>"+rs.getString(10)+"</td></tr>";
		          
		     }
		     str+="</table>";
	   	    }
	 	 catch(Exception e)
	 	{
	 		 e.printStackTrace();
	 	}
	 	return str;
		
	}

}
