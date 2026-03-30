package com.recruitment.tests;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import static io.restassured.RestAssured.given;
public class BaseTest {
    public static String adminToken;
    public static String userToken;
    public static int jobId;
    
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://recruitment-system-backend-service.onrender.com";
        RestAssured.config = RestAssured.config()
            .httpClient(io.restassured.config.HttpClientConfig.httpClientConfig()
                .setParam("http.connection.timeout", 120000)
                .setParam("http.socket.timeout", 120000));
        String adminLoginBody = """
        {
          "email": "admin@gmail.com",
          "password": "admin123456"
        }
        """;
        adminToken =
            given()
                .contentType(ContentType.JSON)
                .body(adminLoginBody)
            .when()
                .post("/login")
            .then()
                .statusCode(200)
                .extract()
                .asString();
    }
}