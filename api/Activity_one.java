package activities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Activity_one {

   // base URL
        String baseURL = "https://petstore.swagger.io/v2/pet/";
        Response response;

        @Test(priority = 0)
    public void postActivity() throws IOException {
            File file = new File("src/test/resources/input.json");
            FileInputStream fis = new FileInputStream(file);

            //converting byte to string
            byte[] bytes = new byte[(int)file.length()];
            fis.read(bytes);

            String reqBody = new String(bytes, StandardCharsets.UTF_8);


            response = given().contentType(ContentType.JSON).body(reqBody)
                    .when().post(baseURL);

            String resBody = response.getBody().asPrettyString();
            System.out.println("response for the post request "+resBody );



            response.then().statusCode(200);
            response.then().body("name", equalTo("niely"));

            int statusCode = response.getStatusCode();

            Assert.assertEquals(statusCode,200);

        }

        @Test(priority = 1)
    public void getActivity()
        {
            response = given().contentType(ContentType.JSON).pathParam("petId",77235)
                    .when().get(baseURL+"{petId}");

            String getRes = response.getBody().asPrettyString();
            System.out.println("get request using path parameter "+getRes);

            response = given().contentType(ContentType.JSON).queryParam("status","sold")
                    .when().get(baseURL+"findByStatus");

            String getResQuery = response.getBody().asPrettyString();
            System.out.println("get request using query parameter "+getRes);

        }
        @Test(priority = 2)
    public void deleteActivity()
        {
            response = given().header("Content-Type","application/json")
                    .when().delete(baseURL+"77235");

            int resCode = response.getStatusCode();
System.out.println("checking assertion here");
            Assert.assertEquals(resCode,200);
        }

}
