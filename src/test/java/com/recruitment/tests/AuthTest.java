package com.recruitment.tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class AuthTest extends BaseTest {

    @Test
    public void testSignup() {

        String body = """
        {
          "name": "Test User",
          "email": "testuser@gmail.com",
          "password": "1234",
          "userType": "APPLICANT"
        }
        """;

        given()
            .header("Content-Type", "application/json")
            .body(body)
        .when()
            .post("/signup")
        .then()
            .statusCode(200);
    }

    @Test(dependsOnMethods = "testSignup")
    public void testLogin() {

        String body = """
        {
          "email": "testuser@gmail.com",
          "password": "1234"
        }
        """;

        BaseTest.userToken =
            given()
                .header("Content-Type", "application/json")
                .body(body)
            .when()
                .post("/login")
            .then()
                .statusCode(200)
                .extract()
                .asString(); 
    }
}