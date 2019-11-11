package com.server.Collector.UDP;
import com.server.Collector.Database.InsertionDb;
import com.server.Collector.elasticsearch.*;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
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

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

public class UDPcollector implements Runnable{

	@Override
	public void run() {
		

		IndexEs i=new IndexEs();
		BulkIndexEs bi=new BulkIndexEs();
		InsertionDb e=new InsertionDb();
		 //System.out.println("got");
		 int l=0;
		 
	   try{
		   DatagramSocket ds=new DatagramSocket(4446);
	       ds.setReuseAddress(true);
	       byte b1[]=new byte[65000];
           DatagramPacket p=new DatagramPacket(b1,b1.length);
           ds.receive(p);
           p.getAddress();
           int a = Integer.valueOf(new String(b1, 0, p.getLength()));
	       String input[]=new String[a+1];
	       ds.setReceiveBufferSize(640000000);
	       int lq=ds.getReceiveBufferSize();
	       System.out.println(a);
	       int k=0;
	       while (true) 
	       {   
	    	   k++;
	    	   byte b[]=new byte[65000];
	            p=new DatagramPacket(b,b.length);
	           ds.receive(p);
	           p.getAddress();
	           input[k] = new String(b, 0, p.getLength());
	          
	         if(input.equals("end"))
	        	 break;
	       
	         if(k>(a-1))
	         {
	        	
	        	bi.indexBulk(input);
	        	k=0;
	        	lq++;
	        	System.out.println(lq);
	         }
	            
	        
	        }
	     //bi.indexBulk(input);
	       System.out.println("ended");
            
      
	  
	}
	   catch(Exception e1)
	   {
		   e1.printStackTrace();
	   }
	}

	}


