package getRequest;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;


public class VerifyAPI {
 
	@Test
    public void testResponsecode() {

        // Call the API and store the response in a rest-assured response object
    	Response apiResponse = RestAssured.get("https://api.tmsandbox.co.nz/v1/Categories/6327/Details.json?catalogue=false");
        // Evaluate Json path from the response object
        JsonPath apiJson = apiResponse.jsonPath();

        //Extract and validate the name Node	
        String name = apiJson.get("Name");
        Assert.assertEquals("Name matched with response", name, "Carbon credits");

        //Extract and validate the CanRelist Node
        Boolean relist = apiJson.get("CanRelist");
        Assert.assertEquals("Relist option matched with response", relist, Boolean.TRUE);

        //Extract and Validate Description of Gallery element in Promotions node
        String apiDescription = apiJson.get("Promotions.find{it.Name == 'Gallery'}.Description");
        Assert.assertTrue("Gallery's description contains desired text", apiDescription.contains("2x larger image"));
    }
}