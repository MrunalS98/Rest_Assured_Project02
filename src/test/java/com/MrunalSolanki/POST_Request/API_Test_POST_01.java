package com.MrunalSolanki.POST_Request;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class API_Test_POST_01 {
    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;

    @Test
    public void test_POST_TC1(){

        GSON_POST_01 POG = new GSON_POST_01();
        POG.setTitle("Mrunal");
        POG.setBody("Testing POST API using GSON RA!");
        POG.setUserId(105);

        System.out.println(POG);

        Gson gson = new Gson();
        String jsonStringPostRequest = gson.toJson(POG);
        System.out.println(jsonStringPostRequest);

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://jsonplaceholder.typicode.com/");
        requestSpecification.basePath("posts");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(jsonStringPostRequest).log().all();

        Response response = requestSpecification.when().post();
        String jsonResponseString  = response.asString();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);

        // Case 3 - DeSer - Extraction
        //  Response - De Ser another Response Class
        String jsonResponseString1  = response.asString();
        GSON_POST_T01_Response POGR = gson.fromJson(jsonResponseString1, GSON_POST_T01_Response.class);

        System.out.println(POGR.getTitle());
        System.out.println(POGR.getBody());
        System.out.println(POGR.getUserId());
        System.out.println(POGR.getId());

    }
}
