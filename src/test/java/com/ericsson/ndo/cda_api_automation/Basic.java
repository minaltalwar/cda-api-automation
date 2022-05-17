package com.ericsson.ndo.cda_api_automation;

import io.restassured.path.json.JsonPath;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;


public class Basic {
	 static String cookieValue;

	public static void main(String[] args) throws Throwable{
		
		System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		driver.get("https://10.64.114.191/login.html");
		Thread.sleep(3000);
		driver.findElement(By.tagName("button")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("etlwmnl");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Ericsson@123");
		driver.findElement(By.cssSelector("#sendLogin")).click();
		Thread.sleep(4000);
		
		Set<Cookie> cookies = driver.manage().getCookies();
		
		
        Iterator<Cookie> itr = cookies.iterator();
        while (itr.hasNext()) {
            Cookie cookie = itr.next();
            cookieValue = "SESSION="+cookie.getValue();
                    }
        System.out.println(cookieValue);
		
        driver.close();
		
		RestAssured.baseURI="https://10.64.114.191";
		String response=given().relaxedHTTPSValidation().header("Content-Type","application/json").header("Cookie",cookieValue)
			.body("{\r\n" + 
					"  \"country\": \"France\",\r\n" + 
					"  \"dashboardType\": \"union\",\r\n" + 
					"  \"endTime\": 1619391600000,\r\n" + 
					"  \"map\": {\r\n" + 
					"    \"kpi\": \"DL Traffic Volume-Sum\"\r\n" + 
					"  },\r\n" + 
					"  \"operator\": [\r\n" + 
					"    \"Orange\",\r\n" + 
					"    \"SFR\",\r\n" + 
					"    \"Bouygues\",\r\n" + 
					"    \"Free Mobile\"\r\n" + 
					"  ],\r\n" + 
					"  \"pixelSize\": 4,\r\n" + 
					"  \"polygon\": \"\",\r\n" + 
					"  \"polygonId\": 0,\r\n" + 
					"  \"startTime\": 1619222400000,\r\n" + 
					"  \"technology\": \"2G\",\r\n" + 
					"  \"carriers\": [],\r\n" + 
					"  \"tileSize\": 256,\r\n" + 
					"  \"zoom\": 6,\r\n" + 
					"  \"sampleType\": [],\r\n" + 
					"  \"polygonBounds\": null\r\n" + 
					"}")
			.when().post("/cda-opbench-service/opbench/sampleshare")
			.then().assertThat().statusCode(200).extract().response().asString();
			
			System.out.println("Scenario: opbench/sampleshare");
			System.out.println("Response: "+response);
			JsonPath js=new JsonPath(response); //for parsing Json
			String name=js.getString("name");
			String share=js.getString("share");	
			System.out.println(name);
			System.out.println(share);
			Assert.assertEquals("[SFR, Bouygues, Orange]",name);
			Assert.assertEquals("[2, 16, 3]", share);
			
			
	}
}
