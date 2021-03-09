package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification request;

	public RequestSpecification requestSpecification() throws IOException {
		
		if(request==null) {
		
		PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));
		//RestAssured.baseURI="https://rahulshettyacademy.com";
		request = new RequestSpecBuilder().setBaseUri(getUrl("baseUrl"))
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		return request;
	}
		return request;
}
	
	public static String getUrl(String key) throws IOException {
		
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream("E:\\Selenium-Learn\\APIFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		String url=prop.getProperty(key);
		return url;
		
	}
	
	//to get the values from the response body
	public String getJsonPath(Response response,String key) {
		String resp=response.asString();
		JsonPath js=new JsonPath(resp);
		return js.get(key).toString();
	}
	
	
}
