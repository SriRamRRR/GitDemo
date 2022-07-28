Feature: Validating Place API's

@AddPlace @Regression
Scenario Outline: Verify if place is being successfully added using AddPlace API
Given Add Place Payload with "<name>" "<language>" "<address>"
When user calls "AddPlaceAPI" with "POST" http request
Then the API call got success with status code 200
And the "status" in response body is "OK"
And the "scope" in response body is "APP"
And verify place_id created maps to "<name>" using "getPlaceAPI"

Examples:

		|name | language | address 			  |
		|AAho | English  | World Cross Center |
#		|BBho | Spanish	 | Sea Cross Center	  |

@DeletePlace @Regression
Scenario: Verify if Delete Place Functionality is working
Given DeletePlace Payload
When user calls "deletePlaceAPI" with "POST" http request
Then the API call got success with status code 200
And the "status" in response body is "OK"

