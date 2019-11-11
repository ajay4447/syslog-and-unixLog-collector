package com.logger.sender.TCP;

 import java.io.DataInputStream; 
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException; 
import java.net.InetAddress; 
import java.net.Socket; 
import java.net.UnknownHostException; 
import java.util.Scanner; 
  
public class clients 
{ 
    public void clie(String port2, String msg, String ip2, String file) 
    {  
    	try
    	{
        InetAddress ip = InetAddress.getByName(ip2); 
        int port = Integer.valueOf(port2);  
        Scanner sc = new Scanner(System.in); 
        Socket s = new Socket(ip, port); 
        s.setReuseAddress(true);
        DataInputStream dis = new DataInputStream(s.getInputStream()); 
        DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
        Scanner s1=new Scanner(new File("E:\\"+file+".txt"));
        int n=Integer.valueOf(msg);
        int q=0;
        long t=System.currentTimeMillis();
        while (s1.hasNextLine()) 
        {   
        	long t1=System.currentTimeMillis();
            if(q<n && t-t1<=100)
            {
            System.out.println("sent");
            String inp = s1.nextLine(); 
            dos.writeUTF(inp); 
            String ans = dis.readUTF(); 
            System.out.println(ans);
            q++;
            }
            else
            	break;
            
        }
        //long t1=System.currentTimeMillis();
        System.out.println(t-q);
        String inp="end";
        dos.writeUTF(inp);
    }
    	catch(Exception e)
    	{
    	 System.out.println(e);
    	}

	
		
		
	}
}

