package com.http.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
public static Properties prop;	

public TestBase() throws IOException{
		
		try {
			//create object for Properties class
			prop=new Properties();
			
			//read the properties file
			FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\http\\qa\\config\\config.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}

}
