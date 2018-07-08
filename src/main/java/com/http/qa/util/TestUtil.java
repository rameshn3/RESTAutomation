package com.http.qa.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TestUtil {

public static String getValueByJPath(JSONObject responsejson,String jpath) throws JSONException{
	Object obj=responsejson;
	for(String s:jpath.split("/")){
		if(!s.isEmpty()){
			if(!(s.contains("[")||s.contains("]"))){
				obj=((JSONObject)obj).get(s);
			}else if(s.contains("[")||s.contains("]")){
				obj=((JSONArray)((JSONObject) obj).get(s.split("\\[")[0])).get(Integer.parseInt(s.split("\\[")[1].replace("]", "")));
			}
		}
	}
	
	
	
	return obj.toString();
	
	}
	
public static String readJsonFile(String path) throws FileNotFoundException, Exception {
   System.out.println("Executing readJsonFile() ");

    String line;
    String json = "";
    File f = new File(path);
    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));

    while ((line = br.readLine()) != null) {
        json = json + line.trim();
    }

    br.close();
    System.out.println(json);

    return json;
}
	
}
