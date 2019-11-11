package com.logger.sender.UDP;
import java.util.*;
import java.util.regex.Pattern;

import com.logger.sender.UDP.Udpclie;

import java.util.regex.*;
import java.io.*;
public class unixlogs {
	public void udp(String port, String msg,String batch, String ip, String file,String time) 
	{   
	  
	  UDPclient c=new UDPclient();
	  c.start(port,msg,batch,ip,file,time);
	 
	  
	}
}


