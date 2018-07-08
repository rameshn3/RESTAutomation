package com.http.qa.testcase;

import java.io.IOException;
import java.util.HashMap;

import com.http.qa.base.TestBase;
import com.http.qa.client.RestClient;
import com.http.qa.util.TestUtil;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GetRestClientTest extends TestBase {

	String endpointURI;
	String apiURL;
	String baseUrl;
	RestClient restclient;
	CloseableHttpResponse closeableHttpResponse;

	public GetRestClientTest() throws IOException {
		super();

	}

	@BeforeMethod
	public void setup() {

		baseUrl = prop.getProperty("URL");
		apiURL = prop.getProperty("serviceURL");

		endpointURI = baseUrl + apiURL + "?page=2";

	}

	@Test
	public void getTest() throws ClientProtocolException, IOException, JSONException {
		// create object for the RestClient class
		restclient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Accept", "application/json");
		headerMap.put("Content-Type", "application/json");

		closeableHttpResponse = restclient.getAPI(endpointURI, headerMap);

		// 1.a status code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		Assert.assertEquals(statusCode, 200);

		// 2validate the json response body content
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

		JSONObject responseJson = new JSONObject(responseString);

		System.out.println("Response JSON from API-->" + responseJson);

		// 2.a -->per_page field validation
		String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println("value of per page is-->" + perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 3);

		// 2.b -->total field validation
		String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
		System.out.println("value of Total is-->" + totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue), 12);

		
		//3.Array of Elements 
		String idVal=TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		String firstnameVal=TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		String lastnameVal=TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		String avatarVal=TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		System.out.println("array data element id value is-->"+idVal);
		System.out.println("array data element firstname value is-->"+firstnameVal);
		System.out.println("array data element lastnameVal is-->"+lastnameVal);
		System.out.println("array data element avatarVal is-->"+avatarVal);
	}

}
