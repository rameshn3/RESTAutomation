package com.http.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
/**
* Author : Lavanya
* date modified : June 27 2020
*/
public class RestClient {

//1.getAPI
	
	public CloseableHttpResponse getAPI(String url,HashMap<String,String>headerMap) throws ClientProtocolException, IOException{
		
	CloseableHttpClient httpClient=	HttpClients.createDefault();
	//create object for HttpGet class
	HttpGet httpget=new HttpGet(url);
	
	for(Map.Entry<String, String> entry:headerMap.entrySet()){
		httpget.addHeader(entry.getKey(), entry.getValue());
	}
		
	CloseableHttpResponse closeablehttpRes= httpClient.execute(httpget);
	
		return closeablehttpRes; 
	}

	
	//2. postAPI
	public CloseableHttpResponse postAPI(String url,String entityString,HashMap<String,String>headerMap) throws ClientProtocolException, IOException{
		
		CloseableHttpClient httpClient=	HttpClients.createDefault();
		
		HttpPost httppost=new HttpPost(url); //http post request
		
		httppost.setEntity(new StringEntity(entityString));  //for payload
		
		
		for(Map.Entry<String, String> entry:headerMap.entrySet()){
			httppost.addHeader(entry.getKey(), entry.getValue());
		}
			
		CloseableHttpResponse closeablehttpRes= httpClient.execute(httppost);
		
			return closeablehttpRes; 
		}
	
	//3.deleteAPI
	
		public CloseableHttpResponse deleteAPI(String url,HashMap<String,String>headerMap) throws ClientProtocolException, IOException{
			
		CloseableHttpClient httpClient=	HttpClients.createDefault();
		
		HttpDelete httpdelet=new HttpDelete(url);
		
		for(Map.Entry<String, String> entry:headerMap.entrySet()){
			httpdelet.addHeader(entry.getKey(), entry.getValue());
		}
			
		CloseableHttpResponse closeablehttpRes= httpClient.execute(httpdelet);
		
			return closeablehttpRes; 
		}
	
		
		//4.putAPI
		
		public CloseableHttpResponse putAPI(String url,String entityString,HashMap<String,String>headerMap) throws ClientProtocolException, IOException{
			
			CloseableHttpClient httpClient=	HttpClients.createDefault();
			
			HttpPut httpput=new HttpPut(url); //http post request
			
			httpput.setEntity(new StringEntity(entityString));  //for payload
			
			
			for(Map.Entry<String, String> entry:headerMap.entrySet()){
				httpput.addHeader(entry.getKey(), entry.getValue());
			}
				
			CloseableHttpResponse closeablehttpRes= httpClient.execute(httpput);
			
				return closeablehttpRes; 
			}
		
}
