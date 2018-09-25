package getRequest;

import org.testng.annotations.Test;
import org.testng.Assert;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;



public class VerifyAPI {
 
	@Test
    public void testResponsecode() {

        // Call the API and store the response in a rest-assured response object
    	Response apiResponse = RestAssured.get("https://api.tmsandbox.co.nz/v1/Categories/6327/Details.json?catalogue=false");
        // Evaluate JSON path from the response object
        JsonPath apiJson = apiResponse.jsonPath();

        //Extract and validate the name Node	
        String name = apiJson.get("Name");
        Assert.assertEquals(name, "Carbon credits", "Name Verified");

        //Extract and validate the CanRelist Node
        Boolean relist = apiJson.get("CanRelist");
        Assert.assertEquals(Boolean.TRUE, relist, "Relist Verified");

        //Extract and Validate Description of Gallery element in Promotions node
        String apiDescription = apiJson.get("Promotions.find{it.Name == 'Gallery'}.Description");
        Assert.assertTrue(apiDescription.contains("2x larger image"),"Gallery's description contains desired text");
    }
}