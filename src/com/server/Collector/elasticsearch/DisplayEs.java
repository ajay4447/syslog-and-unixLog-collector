package com.server.Collector.elasticsearch;

import java.util.Iterator;
import java.util.Map;

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

public class DisplayEs {
	public String displayEs()
	{
		RestHighLevelClient client = new RestHighLevelClient(
		        RestClient.builder(
		                new HttpHost("localhost", 9200, "http")));
		String str="";
          try
          {      
        	     SearchRequest searchRequest = new SearchRequest("user"); 
                 SearchSourceBuilder searchSourceBuilder = new     SearchSourceBuilder(); 
        	     searchSourceBuilder.query(QueryBuilders.matchAllQuery()); 
        	     searchSourceBuilder.size(1000);
        	     searchRequest.source(searchSourceBuilder);
        	     searchRequest.scroll(TimeValue.timeValueMinutes(1L));
        	     SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        	     SearchHits hits = searchResponse.getHits();
                 SearchHit[] searchHits = hits.getHits();
        	     str ="<table border=1><tr><th>Timestamp</th><th>LocalAddress</th><th>DESCRIPTION</th><th>FACILITY-SEVERITY-MESSAGEID</th><th>GLOBAL ADDRESS</th><th>FOREIGN ADDRESS</TH><TH>TYPE</TH><TH>MESSAGE</TH><TH>EXPLANATION</TH></TR><tr>";
        	     for (SearchHit hit : searchHits) {
        	     	String id = hit.getId();
        	     	System.out.println(id);
        	     	GetRequest getRequest = new GetRequest("user",id);
        	     	GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        	     	if (getResponse.isExists()) {
        	     		Map<String, Object> sourceAsMap = hit.getSourceAsMap();
        	     		Iterator hmIterator = sourceAsMap.entrySet().iterator(); 
        	     		   while (hmIterator.hasNext()) 
        	     		   { 
        	                 Map.Entry mapElement = (Map.Entry)hmIterator.next(); 
        	                 str+="<td>"+mapElement.getValue()+"</td>";
        	                 String value= (String) mapElement.getValue();
        	                
        	     		   }
        	     		  str+="</tr>";
        	     	    
        	     	}
        	     	           
        	     }
        	     str+="</table>";
        	    
        	     
        	  
          }
          catch(Exception e)
          {
        	  e.printStackTrace();
          }
		return str;
		
	}

}
