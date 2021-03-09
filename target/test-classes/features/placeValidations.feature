Feature: Validating Place API's

@AddPlace
Scenario Outline: Verify if the place is being Sucessfully added using AddPlaceAPI
Given Add place playload with "<name>" "<language>" "<address>"
When user calls "AddPlaceAPI" with "POST" http request
Then the API call got success with status code 200
And "status" in reponse body is "OK"
And "scope" in reponse body is "APP"
And verify place Id created maps to "<name>" using "getPlaceAPI"

Examples:
    |name|language|address|
    |ram|english|main road|
#    |ravi|telugu|main junction|

@deletePlace
Scenario: Verify if delete place functionality is working

Given DeletePlace PayLoad
When user calls "deletePlaceAPI" with "POST" http request
Then the API call got success with status code 200
And "status" in reponse body is "OK"