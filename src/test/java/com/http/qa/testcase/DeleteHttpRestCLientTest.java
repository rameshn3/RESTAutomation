package com.http.qa.testcase;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.json.JSONException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.http.qa.base.TestBase;
import com.http.qa.client.RestClient;

public class DeleteHttpRestCLientTest extends TestBase {
	public DeleteHttpRestCLientTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	String endpointURI;
	String apiURL;
	String baseUrl;
	RestClient restclient;
	CloseableHttpResponse closeableHttpResponse;
	
	

	@BeforeMethod
	public void setup() {

		baseUrl = prop.getProperty("URL");
		apiURL = prop.getProperty("serviceURL");

		endpointURI = baseUrl + apiURL + "/2";

	}

	@Test
	public void deleteTest() throws ClientProtocolException, IOException, JSONException {
		// create object for the RestClient class
		restclient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Accept", "application/json");
		headerMap.put("Content-Type", "application/json");

		closeableHttpResponse = restclient.deleteAPI(endpointURI, headerMap);

		// 1.a status code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 204);

	}
	
}
