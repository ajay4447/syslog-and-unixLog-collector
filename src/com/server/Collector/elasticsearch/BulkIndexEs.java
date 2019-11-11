package com.server.Collector.elasticsearch;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;

public class BulkIndexEs {
	public void indexBulk(String input[])
	{   
		System.out.println("entered");
		int ql=0;
		Map<String, Object> jsonMap = new HashMap<>();
		String fdr[]=new String[3];
		RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
		BulkRequest request = new BulkRequest("user", "mess");
		try
		{
			for(int o=1;o<input.length;o++)
			{
		if(input[o].matches("\\w{3}\\s\\d{0,3}\\s\\d{1,4}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}:\\s*%\\w*-\\w-\\w*:\\s?(\\s*\\w*)*.*"))
			{   
     	   DateFormat dateFormat = new SimpleDateFormat("kk:mm");
     	   Date d=new Date();
     	   String date=dateFormat.format(d);
     	   String s13=null;
     	   String s14=null;
     	   String s19=null;
     	   
		   Pattern p1=Pattern.compile("\\s\\d{0,3}\\.+\\d{0,3}\\.\\d{0,3}\\.\\d{0,9}\\/\\d{0,5}");
		   Matcher m=p1.matcher(input[o]);
		   int i=0;
		   String spi[]=input[o].split(" ",6);
		   while(m.find())
			 {
			  fdr[i]=m.group();
				     i++;
			 }
			    
				m.reset();
				p1=Pattern.compile("\\w{3}\\s\\d{0,3}\\s\\d{1,4}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}: ");
				m=p1.matcher(input[o]);
				while(m.find())
				{
					//date=m.group();
				}
				
				m.reset();
				p1=Pattern.compile(":\\s\\w(\\s*+\\w*)*.*");
				m=p1.matcher(input[o]);
				while(m.find())
				{
					s13=m.group();
				}
				
				m.reset();
				p1=Pattern.compile("%\\w{0,3}-\\d-\\d{0,6}");
				m=p1.matcher(input[o]);
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
             jsonMap.put("type", "syslogsUDP");
		     jsonMap.put("date", new Date());
		     jsonMap.put("FACILITY-SEVERITY-MESSAGEID",s19);
		     jsonMap.put("Description",s14);
		     jsonMap.put("message",s13);
		     jsonMap.put("explanation",s133);
		     jsonMap.put("gadr",fdr[0]);
		     jsonMap.put("fadr",fdr[1]);
		     jsonMap.put("ladr",fdr[2]);
		     //System.out.println(jsonMap);
		      
		      

			request.add(new IndexRequest("user").source(jsonMap,XContentType.JSON));
			     
			}
		if(input[o].matches("\\<\\d{0,3}>\\w*(\\[\\d*])*:?(\\s*\\d*\\w*\\W*)*:?(\\s*\\d*\\w*\\W*)"))
        {  
			
     	   DateFormat dateFormat = new SimpleDateFormat("kk:mm");
     	   Date d=new Date();
     	   String date=dateFormat.format(d);
		     Pattern p1=Pattern.compile("\\<\\d{0,3}>\\w*(\\[\\d*\\]*)*:?");
		     Matcher m=p1.matcher(input[o]);
		     String fsd="0";
		     while(m.find())
		     {
		    	 fsd=m.group();
		     }
		      m.reset();
		      p1=Pattern.compile(":\\s(\\[\\w*\\])*\\s?\\w*?(\\s*\\w\\W*)*:");
		      m=p1.matcher(input[o]);
		     String Des=null;
		     while(m.find())
		     {
		    	 Des=m.group();
		     }
		     m.reset();
		     p1=Pattern.compile(":\\s*(\\s*\\w*\\W*)*");
		     m=p1.matcher(input[o]);
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
		     m=p1.matcher(input[o]);
		     
		     while(m.find())
		     {
		    	 ladr=m.group();
		     }
		     jsonMap.put("type", "UNIXlogsUDP");
		     jsonMap.put("date", new Date());
		     jsonMap.put("FACILITY-SEVERITY-MESSAGEID",fsd);
		     jsonMap.put("Description",Des);
		     jsonMap.put("message",msg);
		     jsonMap.put("explanation",exp);
		     jsonMap.put("gadr","-");
		     jsonMap.put("fadr","-");
		     jsonMap.put("ladr","-");
		     request.add(new IndexRequest("user").source(jsonMap,XContentType.JSON));
		     
          }
		
			}
			
		long t1=System.currentTimeMillis();
		//request.setRefreshPolicy("none"); 
		BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);
		//System.out.println("inserted");
		long t2=System.currentTimeMillis();
		System.out.println(t2-t1);
		
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		


	}
}
