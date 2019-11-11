package com.server.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

/**
 * Servlet implementation class Alert
 */
@WebServlet("/Alert")
public class Alert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    
    public Alert() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   
		   System.out.println("hello");
		   response.setContentType("text/html");
		   PrintWriter out=response.getWriter();
	   	  // s="CRON";//request.getParameter("alert").trim();
	   	   DatagramSocket ds;
	   	   String s=request.getParameter("a") ;
	   	   System.out.println(s);
	   	   String si[]=s.split("=");
	   	   String arg=si[si.length-1];
	   	   ds = new DatagramSocket(4446);
	   	   String str="<table border=1>";
	   	   
		 while (true) 
	       { 
	          
	           byte b[]=new byte[65000];
	           System.out.println("hello before");
	           DatagramPacket p=new DatagramPacket(b,b.length);
	          
				ds.receive(p);
			    p.getAddress();
	          
	           String input = new String(b, 0, p.getLength());
	           if(input.equals("end")) 
	           break; 
	           if(input.contains(arg))
	           {
	        	   str+="<tr><td>"+input+"</td></tr>";
	           }
	           
	            
	         
		}
		 str+="</table>";
		 out.println(str);
		 out.flush();
		 ds.close();
		 
	       

}
}
