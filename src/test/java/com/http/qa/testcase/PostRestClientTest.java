package com.http.qa.testcase;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.http.qa.base.TestBase;
import com.http.qa.client.RestClient;
import com.http.qa.data.Users;
import com.http.qa.util.TestUtil;

public class PostRestClientTest extends TestBase {
	String endpointURI;
	String apiURL;
	String baseUrl;
	RestClient restclient;
	String jfpath="C:\\workspace\\HttpRestClientAutomation\\src\\main\\java\\com\\http\\qa\\data\\users.json";
	CloseableHttpResponse closeableHttpResponse;
	public PostRestClientTest() throws IOException {
		super();
		
	}

		@BeforeMethod
	public void setup() {

		baseUrl = prop.getProperty("URL");
		apiURL = prop.getProperty("serviceURL");

		endpointURI = baseUrl + apiURL;

	}
		
		@Test
		public void postTest() throws Exception {
			// create object for the RestClient class
			restclient = new RestClient();
			//setting the headers
			HashMap<String, String> headerMap = new HashMap<String, String>();
			headerMap.put("Accept", "application/json");
			headerMap.put("Content-Type", "application/json");
			//Jackson API
			ObjectMapper mapper=new ObjectMapper();
			Users users=new Users("morpheus","leader"); //expected users object
			//object to json file conversion
			mapper.writeValue(new File(jfpath),users);
			
			//object to json in string 
			String userJsonString=mapper.writeValueAsString(users);
			System.out.println(userJsonString);
			closeableHttpResponse = restclient.postAPI(endpointURI,userJsonString,headerMap);
			System.out.println(closeableHttpResponse);
			// 1.a status code
			int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
			System.out.println(statusCode);
			Assert.assertEquals(statusCode, 201);
			
			//2.JsonString:
			String responseString=EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
			
			JSONObject responseJson=new JSONObject(responseString);
			System.out.println("response from API is:"+responseJson);
			//json to java Object
			Users usersResObj=mapper.readValue(responseString, Users.class); //actual users object
			Assert.assertTrue(users.getName().equals(usersResObj.getName()));
			Assert.assertTrue(users.getJob().equals(usersResObj.getJob()));
			
			
		}
}