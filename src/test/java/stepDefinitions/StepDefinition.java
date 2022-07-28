package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.ApiResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils {
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data=new TestDataBuild();
	JsonPath js;
	static String place_id;
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		
		
		res = given().spec(requestSpecification())
				.body(data.addPlacePayload(name,language,address));
	}
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
	    // Write code here that turns the phrase above into concrete actions
		
		ApiResources resourceAPI=ApiResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		
		if(method.equalsIgnoreCase("POST"))
		response=res.when().post(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("GET"))
			response=res.when().get(resourceAPI.getResource());
			//	.then().extract().response();
	}
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    assertEquals(response.getStatusCode(),200);
	}
	@Then("the {string} in response body is {string}")
	public void the_in_response_body_is(String key, String value) {
	    // Write code here that turns the phrase above into concrete actions
	 
	 assertEquals(getJsonPath(response, key),value);
	}
	
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
	    
		//requestSpec
		place_id=getJsonPath(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource,"GET");
		String actualName=getJsonPath(response, "name");
		assertEquals(actualName, expectedName);
	}
	
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		res=given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}
	
	public void testMethod() {
		System.out.println("This method is created for the Git tutorial");
		System.out.println("Just Ignore this method");
		System.out.println("Thank you");
		System.out.println("This is the new edit");
		System.out.println("Thank you");
		
	}


}
