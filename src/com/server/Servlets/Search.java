package com.server.Servlets;
import com.server.Collector.elasticsearch.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;


@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Search() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   SearchEs ss=new SearchEs();
		   response.setContentType("text/html");
		   System.out.println("incoming");
		   String keyword=request.getParameter("fname").trim();
		   String dess=request.getParameter("dname").trim();
		   String k=request.getParameter("kname").trim();
		   System.out.println();
		   if(k==null)
		   k="-019191"; 
		   String time=request.getParameter("time");
		   System.out.println(time);
		   if(keyword==null)
		   keyword="-12tttrtrttytrytryrt-210";
		   PrintWriter out=response.getWriter();
	       System.out.println(k);
	       String str="";
	       int count=0;
	       str=ss.searchEs(keyword,dess,k);
	 	   out.println(str);
	 	   out.flush();
	 	     
	 	     
	 	     
	 	 }
}


