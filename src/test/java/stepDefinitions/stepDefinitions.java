package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import static org.junit.Assert.*;

import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class stepDefinitions extends Utils{
	
	RequestSpecification req;
	ResponseSpecification resspec;
	RequestSpecification res;
	Response response;
	static String place_id;

	TestDataBuild data=new TestDataBuild();
	
	/*@Given("^Add place playload with {String} {String} {String}$")
	public void add_place_playload_with(String name,String language,String address) throws IOException {
		res=given().spec(requestSpecification())
				.body(data.addPlacePayLoad(name,language,address));
	}*/
	
	@Given("^Add place playload with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void add_place_playload_with(String name, String language, String address) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		res=given().spec(requestSpecification())
				.body(data.addPlacePayLoad(name,language,address));
	}

	@When("^user calls \"([^\"]*)\" with \"([^\"]*)\" http request$")
	public void user_calls_with_http_request(String resource, String method) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions	
		
		APIResources resourceAPI=APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		
		resspec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("GET")) {
			response=res.when().get(resourceAPI.getResource());
		}
		else if(method.equalsIgnoreCase("POST")) {
			response=res.when().post(resourceAPI.getResource());
		}
		/*response = res.when().post(resourceAPI.getResource()).then()
				.spec(resspec).extract().response();*/
	}

	@Then("^the API call got success with status code (\\d+)$")
	public void the_API_call_got_success_with_status_code(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	   assertEquals(response.getStatusCode(),200);
	}

	@Then("^\"([^\"]*)\" in reponse body is \"([^\"]*)\"$")
	public void in_reponse_body_is(String keyValue, String expectedValue) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    String responeString=response.asString();
	    JsonPath js = new JsonPath(responeString);
	    assertEquals(js.get(keyValue).toString(),expectedValue);
	}
	
	@Then("^verify place Id created maps to \"([^\"]*)\" using \"([^\"]*)\"$")
	public void verify_place_Id_created_maps_to_using(String expectedName, String resource) throws Throwable {
	    place_id=getJsonPath(response,"place_id");
	    res=given().spec(requestSpecification()).queryParam("place_id", place_id);
	    user_calls_with_http_request(resource,"GET");
	    String actualName=getJsonPath(response,"name");
	    System.out.println(actualName);
	    assertEquals(actualName, expectedName);
	    
	}
	
	@Given("^DeletePlace PayLoad$")
	public void deleteplace_PayLoad() throws Throwable {
	 res=given().spec(requestSpecification()).body(data.deletePlacePlayLoad(place_id));
	}
}
