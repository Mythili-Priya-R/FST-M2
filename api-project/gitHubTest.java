package Github_Rest_Assured;

import io.restassured.authentication.BasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.json.Json;

import static io.restassured.RestAssured.given;

public class gitHubTest {

    RequestSpecification resSpec;
    Response response;
    String sshKey;
    int id;

    @BeforeClass
    public void setUp() {
        resSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("https://api.github.com")
                .addHeader("Authorization", "token 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")
                .build();


    }

    @Test(priority = 0 )
    public void postMethod() {

        String reqBody ="{\n" +
                "    \"title\": \"TestAPIKey\",\n" +
                "    \"key\": \"ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQC7DTFnt9sh6PYjtMEBMe80MoO4AlqpmmP0fLTb9K2Kl61y7AniXU/9Vc/tHtFdxq0H+4LUgEwOrvB9jxTRMxzNu0TL5/qdYwio5OLY0w9IWWwc/MpEJCp4uPgPzOwbA4ltKf16UdApePafk2U/DIpIUpL+V/BHAipQ7xGFqbmjH65lPMhi9E1L6G0RN/TeZK27pYogPm+t07yYKzNGW52wx0ry8h1CWdq4PQK1FN2DcpoNEuU3cEH+s23HkHMe6vgRqXLmKpo7dcQkUjtM1RRA1A4L1L47I+KNcDOHWn9Iv7zNpyCjoluvMW/iqAM42ZLWb4iF9Mo6tGAKuYTd2tgh\"}";
        response = given().spec(resSpec).body(reqBody).when().post("/user/keys");

        System.out.println("response from post  "+response.getBody().asPrettyString());
        id=response.then().extract().path("id");
        System.out.println("id"+id);
        System.out.println(response.statusCode());
        int postcode=response.getStatusCode();
        Assert.assertEquals(postcode,201);

    }
    @Test(priority = 1)
    public void getMethod()
    {
        System.out.println("id"+id);
    response=given().spec(resSpec)
            .pathParam("keyId",id)
            .when().get("/user/keys/{keyId}");
    System.out.println("GET response "+response.getBody().asPrettyString());
    int getCode = response.getStatusCode();
    Assert.assertEquals(getCode,200);
    }

    @Test(priority = 2)
    public void deleteMethod()
    {
        response=given().spec(resSpec).pathParam("keyId",id).when().delete("/user/keys/{keyId}");
        System.out.println(response.getBody().asPrettyString());
        int deleteCode = response.getStatusCode();
        Assert.assertEquals(deleteCode,204);
    }
}

