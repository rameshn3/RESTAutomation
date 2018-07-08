package com.http.qa.testcase;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.http.qa.base.TestBase;
import com.http.qa.client.RestClient;
import com.http.qa.data.UpdateUsers;
import com.http.qa.data.Users;

public class PutRestClientTest extends TestBase {
	public PutRestClientTest() throws IOException {
		super();
		
	}

	String endpointURI;
	String apiURL;
	String baseUrl;
	RestClient restclient;
	String jfpath="C:\\workspace\\HttpRestClientAutomation\\src\\main\\java\\com\\http\\qa\\data\\update.json";
	CloseableHttpResponse closeableHttpResponse;
	

		@BeforeMethod
	public void setup() {

		baseUrl = prop.getProperty("URL");
		apiURL = prop.getProperty("serviceURL");

		endpointURI = baseUrl + apiURL+"/2";

	}
		
		@Test
		public void putTest() throws Exception {
			// create object for the RestClient class
			restclient = new RestClient();
			//setting the headers
			HashMap<String, String> headerMap = new HashMap<String, String>();
			headerMap.put("Accept", "application/json");
			headerMap.put("Content-Type", "application/json");
			//Jackson API
			ObjectMapper mapper=new ObjectMapper();
			UpdateUsers users=new UpdateUsers("morpheus","zion resident"); //expected users object
			//object to json file conversion
			mapper.writeValue(new File(jfpath),users);
			
			//object to json in string 
			String userJsonString=mapper.writeValueAsString(users);
			System.out.println(userJsonString);
			closeableHttpResponse = restclient.putAPI(endpointURI,userJsonString,headerMap);
			System.out.println(closeableHttpResponse);
			// 1.a status code
			int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
			System.out.println(statusCode);
			Assert.assertEquals(statusCode, 200);
			
			//2.JsonString:
			String responseString=EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
			
			JSONObject responseJson=new JSONObject(responseString);
			System.out.println("response from API is:"+responseJson);
			//json to java Object
			UpdateUsers usersResObj=mapper.readValue(responseString, UpdateUsers.class); //actual users object
			Assert.assertTrue(users.getName().equals(usersResObj.getName()));
			Assert.assertTrue(users.getJob().equals(usersResObj.getJob()));
			
			
		}
		
		
}
