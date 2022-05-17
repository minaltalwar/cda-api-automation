package stepDefination;

import static io.restassured.RestAssured.given;

import static org.junit.Assert.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.TestDataBuild;
import resources.Utils;
import resources.APIResources;

public class StepDefination extends Utils {

	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	
	@Given("Sample share request payload for {string} {string}")
	public void sample_share_request_payload_for(String kpi, String technology) throws Throwable {
		res = given().spec(requestSpecification()).body(data.sampleSharePayload(kpi, technology));
	
	}

	@When("user calls {string} API with POST http request")
	public void user_calls_api_with_post_http_request(String resource) {
		APIResources resourceAPIobj = APIResources.valueOf(resource);
		System.out.println(resourceAPIobj.getResource());
		response = res.when().post(resourceAPIobj.getResource());
	   
	}

	@Then("API call success with status code {int}")
	public void api_call_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(), 200);
	  
	}
}
