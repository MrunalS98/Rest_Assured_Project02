package com.MrunalSolanki.GET_Request;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class API_Test_GET_02 {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    @Description("Verify response created by post request")
    public void test_GET_TC1(){
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://jsonplaceholder.typicode.com/");
        requestSpecification.basePath("posts/1");

        response = requestSpecification.when().log().all().get();

        validatableResponse = response.then().log().all().statusCode(200);

    }
}
