package activities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Activity_3 {

    RequestSpecification reqSpec;
    ResponseSpecification resSpec;

    Response response;
    @BeforeClass
    public void setUp()
    {
        reqSpec = new RequestSpecBuilder()
                .setBaseUri("https://petstore.swagger.io/v2/pet")
                .setContentType(ContentType.JSON)
                .build();
        resSpec = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .expectBody("status",equalTo("alive"))
                .build();

    }
    @DataProvider
            public Object [] [] testProvider() {
        Object[][] testData = new Object[][]
                {
                        {77235, "Ziely", "alive"},
                        {77236, "Riely", "alive"}
                };
        return  testData;
    }
    @Test(priority = 1)
    public void postRequest() {
        /*Map<String, String> data = new HashMap<String, String>();
        data.put("id", "77323");
        data.put("name", "Riley");
        data.put("status", "alive");

        String reqBody = data.toString();
*/
        String postReq = "{\"id\" :77235 , \"name\" :\"Ziely\",\"status\":\"alive\" }";
        response = given().spec(reqSpec).body(postReq)
                .when().post();
        String postReq1 = "{\"id\" :77236 , \"name\" :\"Riely\",\"status\":\"alive\" }";
        response = given().spec(reqSpec).body(postReq1)
                .when().post();

        response.then().spec(resSpec);
    }
    @Test( dataProvider = "testProvider",priority = 2)

    public void getRequest(int id , String name ,String status )
    {

        response = given().spec(reqSpec).pathParam("petId",id)
                .when().get("/{petId}");

        System.out.println(response.getBody().asPrettyString());

        response.then().spec(resSpec);

    }
    @Test(dataProvider = "testProvider", priority = 3)
    public void deleteRequest(int id, String name, String status)
    {
        response = given().spec(reqSpec).pathParam("petId",id)
                .when().delete("/{petId}");

        response.then().statusCode(200);
    }
}
