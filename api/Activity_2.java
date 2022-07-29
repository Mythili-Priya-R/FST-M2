package activities;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Activity_2 {
    String baseURL ="https://petstore.swagger.io/v2/user";
    Response response ;

    @Test(priority = 0)
    public void postUser() throws IOException {
        JSONObject data = new JSONObject();
        data.put("id",1);
        data.put("username","Anae");
        data.put("firstName","An");
        data.put("lastName","ae");
        data.put("email","email@com");
        data.put("password","pass");
        data.put("phone","123123");
        data.put("userStatus" , 2);

        String reqBody = data.toString();
        System.out.println("request body "+reqBody);
        System.out.println(reqBody);
        response = given()
                .header("Content-Type","application/JSON")
                .body(reqBody).log().all()
                .when().post(baseURL);


        String postRes = response.getBody().asPrettyString();
        System.out.println("posting response" +postRes);

        String name = response.then().extract().path("username");
        System.out.println(name);

        JsonPath json = response.jsonPath();
        String fname = json.get("firstName");
        System.out.println(fname);
        response.then().statusCode(200);

    }
    @Test(priority = 1)
    public void getMethod()
    {

        response = given().contentType(ContentType.JSON)
                .pathParams("username","Anae").when().get(baseURL+"/{username}");

        response.then().statusCode(200);
        String name = response.then().extract().path("firstName");
        System.out.println(name);
        int id = response.then().extract().path("id");
        System.out.println(id);
        String lastname= response.then().extract().path("lastName");
        System.out.println(lastname);
    }
    @Test(priority = 2)
    public void deleteMethod()
    {
        response = given().header("Content-Type","application/json")
                .when().delete(baseURL+"/Anae");

        int resCode = response.getStatusCode();
        System.out.println("checking assertion here");
        Assert.assertEquals(resCode,200);
    }


}
