package com.server.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.logger.sender.TCP.clients;
import com.logger.sender.UDP.unixlogs;

/**
 * Servlet implementation class Sender
 */
@WebServlet("/Sender")
public class Sender extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sender() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		unixlogs u=new unixlogs();
		clients c=new clients();
		String ud=request.getParameter("udp");
		String tc=request.getParameter("tcp");
		
		if(ud==null)
			ud="";
		if(tc==null)
			tc="";
		if(ud.equals("UDP"))
	    {
		  String port=request.getParameter("port");
		  String msg=request.getParameter("msg");
		  String bt=request.getParameter("batch");
		  String ip=request.getParameter("ip");
		  String file=request.getParameter("file");
		  String time=request.getParameter("TimeSlot");
		  
		
			u.udp(port,msg,bt,ip,file,time);
		
	    }
		  
		else if(request.getParameter("tcp").equals("TCP"))
		 {    
			  String port=request.getParameter("port");
			  String msg=request.getParameter("msg");
			  String bt=request.getParameter("batch");
			  String ip=request.getParameter("ip");
			  String file=request.getParameter("file");
			  //c.clie(port,msg,ip,file,bt);
		  }
	}

}
