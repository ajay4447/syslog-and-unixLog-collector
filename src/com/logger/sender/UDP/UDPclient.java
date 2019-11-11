package com.logger.sender.UDP;
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;
import java.util.Timer;
import java.util.Timer.*;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import io.netty.util.concurrent.ScheduledFuture;

public class UDPclient {
 public void start(String port, String msg,String batch, String ip, String file,String time) 
{   
	try
	{
		 
	DatagramSocket socket = new DatagramSocket();
	InetAddress a=InetAddress.getByName("localhost");
	Scanner s1=new Scanner(new File("E:\\"+file+".txt"));
	socket.setSendBufferSize(2000000000);
	int n=Integer.valueOf(batch);
    int q=0;
   int su=Integer.valueOf(msg);
   System.out.println(su);
   int sum=0;
   byte b1[]=batch.getBytes();
   DatagramPacket p1=new DatagramPacket(b1,b1.length,a,4446);
   socket.send(p1);
   long times=Long.valueOf(time);
   
   Timer t = new Timer();
   TimerTask tt = new TimerTask()
		   { int sum=0;
	  synchronized public void run(){
		   int q=0;
    while(s1.hasNextLine())
	    {    
		if(q<n)
		{
		   String s=s1.nextLine();
		   byte[] b=s.getBytes();
		   DatagramPacket p=new DatagramPacket(b,b.length,a,4446);
		   try {
			socket.send(p);
			
		       }
		   catch (IOException e) {
		              e.printStackTrace();
		                         }
		   q++;
		 }
		else 
			break;
	    }
    sum+=n;
    if(sum>=su)
    {
    	t.cancel();
    	String s="end";
    	//System.out.println(sum);
    	byte[] b=s.getBytes();
    	DatagramPacket p=new DatagramPacket(b,b.length,a,4446);
    	
    	try {
			socket.send(p);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
  
    
	   }
	  
		   };
	
       
		   t.scheduleAtFixedRate(tt,0,times*1000);    
	
	
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	//start(port,msg,ip,file);
}

}
