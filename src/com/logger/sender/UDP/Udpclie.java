package com.logger.sender.UDP;
import sun.net.*;

import java.io.File;
import java.net.*;
import java.util.Scanner;
public class Udpclie  {
public void Start(String port, String msg, String ip, String file)
{
	 
	 try
	 {  int n=0;
        DatagramSocket s2=new DatagramSocket();
	    DatagramSocket s=new DatagramSocket();
	    Scanner s1=new Scanner(new File("E:\\"+file+".txt"));
		while(s1.hasNextLine())
		{
			String s11=s1.nextLine();
			byte[] b=s11.getBytes();
			DatagramPacket p=new DatagramPacket(b,b.length,InetAddress.getByName(ip),Integer.valueOf(port));
			s.send(p);
			System.out.println("2client"+n++);
			
			
		}
		String m="end";
		System.out.println(m);
		byte[] b=m.getBytes();
		DatagramPacket p=new DatagramPacket(b,b.length,InetAddress.getByName(ip),Integer.valueOf(port));
		s.send(p);
		
		s.close();
		s2.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	 
 }



}
