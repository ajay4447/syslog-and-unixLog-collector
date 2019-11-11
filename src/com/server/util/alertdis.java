package com.server.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;
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
 


@WebServlet("/alertdis")
public class alertdis extends HttpServlet   {
	private static final long serialVersionUID = 1L;
       
   
    
        
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    
		     
		  
			doPost(request,response);
		}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	   final String DB_URL = "jdbc:mysql://localhost:3306/ajay";
 	   String user="ajay";
 	   String pwd="root";
 	   Connection conn=null;
 	   Statement st=null;
 	   String color="#FFFFFF";
 	   GenericObjectPool gPool = null;
 	   gPool = new GenericObjectPool();
       gPool.setMaxActive(5);
       RestHighLevelClient client = new RestHighLevelClient(
		        RestClient.builder(
		                new HttpHost("localhost", 9200, "http"),
		                new HttpHost("localhost", 9201, "http")));
 	   
 	   PrintWriter out=response.getWriter();
 	   response.setContentType("text/html");
    try{
     ConnectionFactory cf = new DriverManagerConnectionFactory(DB_URL, user, pwd);
     PoolableConnectionFactory pcf = new PoolableConnectionFactory(cf, gPool, null, null, false, true);
     DataSource ds = new PoolingDataSource(gPool);
     Class.forName("com.mysql.jdbc.Driver");
     conn=ds.getConnection();
     st=conn.createStatement();
     String sql2 = "select * from syslogs";
     ResultSet rs=st.executeQuery(sql2);
     SearchRequest searchRequest = new SearchRequest("alerts"); 
     SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder(); 
     searchSourceBuilder.query(QueryBuilders.matchAllQuery()); 
     searchSourceBuilder.size(1000);
     searchRequest.source(searchSourceBuilder);
     searchRequest.scroll(TimeValue.timeValueMinutes(1L));
     SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
     SearchHits hits = searchResponse.getHits();
     String g="<p>";
     SearchHit[] searchHits = hits.getHits();
     for (SearchHit hit : searchHits) {
      	String id = hit.getId();
      	System.out.println(id);
      	GetRequest getRequest = new GetRequest("alerts",id);
      	GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
      	
      	if (getResponse.isExists()) 
      	    {
      		String source=hit.getSourceAsString();
      		g+=source+"</p>";
      		}
      	}
     
     
    
     out.println(g);
     out.flush();
     
     
     
 }
 catch(Exception e)
 {
	e.printStackTrace();
 }

}
}
