package com.server.Collector.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.xcontent.XContentType;

public class InsertionDb {
	public void insert(String input)
	{
		   final String DB_URL = "jdbc:mysql://localhost:3306/ajay";
	   	   String user="ajay";
	   	   String pwd="root";
	   	   String sql="";
	   	   try
	   	   {
	   		Connection conn=null;
		   	Statement st=null;
		   	Class.forName("com.mysql.jdbc.Driver");
		    conn=DriverManager.getConnection(DB_URL,user,pwd);
		    st=conn.createStatement();
		    if(input.matches("\\w{3}\\s\\d{0,3}\\s\\d{1,4}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}:\\s*%\\w*-\\w-\\w*:\\s?(\\s*\\w*)*.*"))
			{   
		    	String fdr[]=new String[3];
        	    DateFormat dateFormat = new SimpleDateFormat("kk:mm");
        	    Date d=new Date();
        	    String date=dateFormat.format(d);
        	    String s13=null;
        	    String s14=null;
        	    String s19=null;
				
				Pattern p1=Pattern.compile("\\s\\d{0,3}\\.+\\d{0,3}\\.\\d{0,3}\\.\\d{0,9}\\/\\d{0,5}");
				Matcher m=p1.matcher(input);
				int i=0;
				String spi[]=input.split(" ",6);
				while(m.find())
				{
				     fdr[i]=m.group();
				     i++;
				}
			    
				m.reset();
				p1=Pattern.compile("\\w{3}\\s\\d{0,3}\\s\\d{1,4}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}: ");
				m=p1.matcher(input);
				while(m.find())
				{
					//date=m.group();
				}
				
				m.reset();
				p1=Pattern.compile(":\\s\\w(\\s*+\\w*)*.*");
				m=p1.matcher(input);
				while(m.find())
				{
					s13=m.group();
				}
				
				m.reset();
				p1=Pattern.compile("%\\w{0,3}-\\d-\\d{0,6}");
				m=p1.matcher(input);
				while(m.find())
				{
					s19=m.group();
				}
			
				
           String s133=null;
           if(s13.contains("Built UDP connection"))
           {
           	s133="UDP director or backup or forwarder flow is created.";
           }
           else if(s13.contains("Teardown UDP connection"))
           {
           	      s133="UDP connection slot between two hosts is deleted";
           }
          else if(s13.contains("Deny icmp src outside"))
   		  {
   	      s133="send a packet attempting to connect to a host that does not exist";
   		  }
           else if(s13.contains("Deny TCP (no connection)"))
   		  {
   	      s133=" ensure that the routing is correct";
   		  }
           else if(s13.contains("Teardown TCP connection"))
   		  {
   	      s133="TCP connection slot between two hosts is deleted";
   		   }
           else if(s13.contains("Built outbound TCP connection") ||spi[5].contains("Built inbound TCP connection"))
   		   {
   	      s133="TCP connection slot is created between two hosts";
   		   }
            else if(s13.contains("Accessed"))
   		   {
   	      s133="Accessing";
   		   }
            else if(s13.contains("Authen Session End"))
   		   {
   	      s133="Session Ended";
   		    }
            char a[]=s19.toCharArray();
            if(a[5]=='6')
            {
           	 s14="Informational message";
            }
            else if(a[5]=='4')
            {
           	 s14="Warning condition";
            }
            else if(a[5]=='5')
            {
           	 s14="Normal but significant condition";
            }
            if(a[5]=='6')
            {
           	 s14="Informational message";
            }
            else if(a[5]=='4')
            {
           	 s14="Warning condition";
            }
            else if(a[5]=='5')
            {
           	 s14="Normal but significant condition";
            }
           
          sql="INSERT INTO SYSLOGS (TYPE,TIMESTAMP,`FACILITY-SEVERITY-MESSAGEID`,Description,MESSAGE,Explanation,`foreign address`,`Global address`,`local address`) VALUE('SYSLOG','"+date+"','"+s19+"','"+s14+"','"+s13+"','"+s133+"','"+fdr[0]+"','"+fdr[1]+"','"+fdr[2]+"');";
          st.executeUpdate(sql);
            
           }
       
           if(input.matches("\\<\\d{0,3}>\\w*(\\[\\d*])*:?(\\s*\\d*\\w*\\W*)*:?(\\s*\\d*\\w*\\W*)"))
           {
        	 DateFormat dateFormat = new SimpleDateFormat("kk:mm");
        	 Date d=new Date();
        	 String date=dateFormat.format(d);
		     Pattern p1=Pattern.compile("\\<\\d{0,3}>\\w*(\\[\\d*\\]*)*:?");
		     Matcher m=p1.matcher(input);
		     String fsd="0";
		     while(m.find())
		     {
		    	 fsd=m.group();
		     }
		      m.reset();
		      p1=Pattern.compile(":\\s(\\[\\w*\\])*\\s?\\w*?(\\s*\\w\\W*)*:");
		      m=p1.matcher(input);
		      String Des=null;
		     while(m.find())
		     {
		    	 Des=m.group();
		     }
		     m.reset();
		     p1=Pattern.compile(":\\s*(\\s*\\w*\\W*)*");
		     m=p1.matcher(input);
		     String msg=null;
		     while(m.find())
		     {
		    	 msg=m.group();
		     }
		     String exp=null;
		     if(fsd.contains("vsftpd"))
		     {
		    	
		    	 exp="File Transfer Protocol established";
		     }
		     if(fsd.contains("useradd"))
		    	 {
		    	 
		    	 exp="User is added to the group";
		    	 }
		     if(fsd.contains("passwd"))
		     {
		    	
		    	 exp="Password has been changed";
		     }
		     if(fsd.contains("groupadd"))
		    	 {
		    	
		    	 exp="A new Group is created";
		    	 }
		     if(fsd.contains("CRON"))
		    	 exp="Time based job scheduler";
		     if(fsd.contains("userdel"))
		    	 exp="User deleted";
		     if(fsd.contains("usermod"))
		    	 exp="User modified";
		     if(fsd.contains("userdel"))
		    	 exp="User deleted";
		     if(fsd.contains("sshd"))
		    	 exp="OpenSSH Daemon";
		     if(fsd.contains("su"))
		    	 exp="Super User";
		     if(fsd.contains("sudo"))
		    	 exp="Super User operation";
		     if(fsd.contains("sendmail"))
		    	 exp="Sending mail";
		     
		     String ladr=null;
		     m.reset();
		     p1=Pattern.compile("(\\d+\\.)+\\d*");
		     m=p1.matcher(input);
		     
		     while(m.find())
		     {
		    	 ladr=m.group();
		     }
		     
		     
		     sql="INSERT INTO SYSLOGS (TYPE,TIMESTAMP,`FACILITY-SEVERITY-MESSAGEID`,Description,`MESSAGE`,Explanation,`foreign address`,`Global address`,`local address`) VALUE('UNIXLOGS','"+date+"','"+fsd+"','"+Des+"','"+msg+"','"+exp+"','-','-','"+ladr+"');";
	          st.executeUpdate(sql);
	           
	          
       }
          
   }
   catch(Exception e)
   {
   	e.printStackTrace();
   }
       
   }
	   	   
	}


