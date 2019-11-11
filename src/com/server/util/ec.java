package com.server.util;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpHost;
//import static org.elasticsearch.node.NodeB;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.common.xcontent.ToXContentObject;
import org.elasticsearch.action.IndicesRequest;
//import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.node.Node;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.json.*;

public class ec {


public static void main(String args[]) throws IOException
{
	RestHighLevelClient client = new RestHighLevelClient(
	        RestClient.builder(
	                new HttpHost("localhost", 9200, "http"),
	                new HttpHost("localhost", 9201, "http")));
	DeleteIndexRequest request1 = new DeleteIndexRequest("user"); 
	AcknowledgedResponse deleteIndexResponse = client.indices().delete(request1, RequestOptions.DEFAULT);

	String json = "{" +
	        "\"user\":\"kimchy\"," +
	        "\"postDate\":\"2013-01-30\"," +
	        "\"message\":\"trying out Elasticsearch\"" +
	    "}";
	Map<String, Object> json1 = new HashMap<String, Object>();
	
	ObjectMapper ma=new ObjectMapper();
	byte[] json2 = ma.writeValueAsBytes(json1);
	//Node node = nodeBuilder().node();
	//Client client = node.client();
	
     json = "{" +
	        "\"user\":\"kimchy\"," +
	        "\"postDate\":\"2013-01-30\"," +
	        "\"message\":\"trying out Elasticsearch\"" +
	    "}";
     IndexRequest indexRequest = new IndexRequest("posts")
    		    .id("1").source(json1); 
     @SuppressWarnings("deprecation")
	IndexRequest request = new IndexRequest("user", "mess","23");
    		 request.source(json, XContentType.JSON);
    @SuppressWarnings("deprecation")
	IndexRequest r=new IndexRequest("alerts","mess","22");
     r.source(json, XContentType.JSON);
    		 //client.index(request);
    		 IndexResponse response = client.index(request, RequestOptions.DEFAULT);
    		 IndexResponse res = client.index(r, RequestOptions.DEFAULT);
     
     
	String index = res.getIndex();
	//String p=response.parent();
	String type = res.getType();
	// Document ID (generated or not)
	String id = res.getId();
	// Version (if it's the first time you index this document, you will get: 1)
	long version = res.getVersion();
	// status has stored current instance statement.
	RestStatus status = res.status();
	System.out.println(index+" "+type+" "+id+" "+version+" "+status);
     Map<String, Object> jsonMap = new HashMap<>();
     jsonMap.put("user", "ajay2");
     jsonMap.put("date", new Date());
     jsonMap.put("message","good");
     System.out.println(jsonMap);
     @SuppressWarnings("deprecation")
	UpdateRequest urequest = new UpdateRequest("user","mess", "23")
    	        .doc(jsonMap); 
	GetRequest getRequest = new GetRequest(
	        "user", 
	        "23"); 
	
	boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);
	System.out.println(exists);
	DeleteRequest request2 = new DeleteRequest(
	        "user",    
	        "23");     
	DeleteResponse deleteResponse = client.delete(
	        request2, RequestOptions.DEFAULT);
	DeleteRequest request3 = new DeleteRequest(
	        "alerts",    
	        "22");     
	DeleteResponse deleteResponse1 = client.delete(
	        request2, RequestOptions.DEFAULT);
	//getRequest.storedFields("message"); 
	//GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
	
	}
	
	 
	
}


