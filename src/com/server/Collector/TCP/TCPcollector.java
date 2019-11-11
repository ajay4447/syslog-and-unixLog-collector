package com.server.Collector.TCP;
import com.server.Collector.Database.InsertionDb;
import com.server.Collector.elasticsearch.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
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

public class TCPcollector implements Runnable {

	@Override
	public void run() {
		
		           //InsertionDb i=new InsertionDb();
		           IndexEs e=new IndexEs();
			   	   int q=0;
			   try{
				   ServerSocket ss = new ServerSocket(4445); 
			       Socket s = ss.accept(); 
			       int l=0;
				   DataInputStream dis = new DataInputStream(s.getInputStream()); 
			       DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
			       while (true) 
			       { 
			           System.out.println("got"+l);
			           l++;
			    	   String input = dis.readUTF();
			            if(input.equals("end")) 
			            break; 
			            //i.insert(input);
			            e.index(input);
			            dos.writeUTF("recieved");
			        }
			       ss.close();
			       run();
			      }
			   //run();
			      catch(Exception e1)
			      {
			    	  e1.printStackTrace();
			      }
			   //run();
			   }
			
	}

	


