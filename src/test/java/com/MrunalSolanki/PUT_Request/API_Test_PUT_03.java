package com.MrunalSolanki.PUT_Request;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class API_Test_PUT_03 {
    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;

    @Test
    public void test_PuT_TC1(){

        GSON_PUT_03 PTG = new GSON_PUT_03();
        PTG.setId(24);
        PTG.setTitle("Updated API Test For PUT Request");
        PTG.setBody("The Request has been updated");
        PTG.setUserId(222);

        System.out.println(PTG);

        Gson gson = new Gson();
        String jsonStringPutRequest = gson.toJson(PTG);
        System.out.println(jsonStringPutRequest);

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://jsonplaceholder.typicode.com/");
        requestSpecification.basePath("posts/100"); //we are change full request so doesn't matter what id we give
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(jsonStringPutRequest).log().all();

        Response response = requestSpecification.when().put();
        String jsonResponseString  = response.asString();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        //  Response
        String jsonResponsegetStringget  = response.asString();
        GSON_PUT_03_Response PTGR = gson.fromJson(jsonResponsegetStringget,GSON_PUT_03_Response.class);

        System.out.println(PTGR.getTitle());
        System.out.println(PTGR.getBody());
        System.out.println(PTGR.getUserId());
        System.out.println(PTGR.getId());
    }
}
