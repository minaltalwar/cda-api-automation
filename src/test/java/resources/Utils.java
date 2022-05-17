package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import stepDefination.Hooks;

public class Utils {

	RequestSpecification req;
	public RequestSpecification requestSpecification() throws IOException {
		System.out.println(Hooks.cookieValue);
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt",true));
		req = new RequestSpecBuilder().setRelaxedHTTPSValidation().setBaseUri(getGlobalValue("baseUrl")).addHeader("Content-Type","application/json").addHeader("Cookie",Hooks.cookieValue)
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		return req;
	}
	
	public static String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\etlwmnl\\cda-workspace\\cda-api-automation\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return (String) prop.get(key);
		
				
	}
	
	public String getJsonPath (Response response,String key)
	{
		String resp = response.asString();
		JsonPath js =new JsonPath(resp);
		return js.get(key).toString();
	}
	
	
	
}
