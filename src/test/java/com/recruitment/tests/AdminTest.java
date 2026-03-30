package com.recruitment.tests;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AdminTest extends BaseTest {

    @Test
    public void testCreateJob() {
        String body = """
        {
          "title": "Java Developer",
          "description": "Backend role",
          "companyName": "ABC Pvt Ltd",
          "location": "Delhi",
          "salary": 1200000
        }
        """;
        BaseTest.jobId =
            given()
                .header("Authorization", "Bearer " + adminToken)
                .header("Content-Type", "application/json")
                .body(body)
            .when()
                .post("/admin/job")
            .then()
                .statusCode(200)
                .body("title", equalTo("Java Developer"))
                .extract()
                .path("id");
    }

    @Test
    public void testGetApplicants() {
        given()
            .header("Authorization", "Bearer " + adminToken)
        .when()
            .get("/admin/applicants")
        .then()
            .statusCode(200)
            .body("size()", greaterThan(0));
    }

    @Test(dependsOnMethods = "testGetApplicants")
    public void testGetApplicantById() {
        int applicantId =
            given()
                .header("Authorization", "Bearer " + adminToken)
            .when()
                .get("/admin/applicants")
            .then()
                .statusCode(200)
                .extract()
                .path("[0].userId");
        given()
            .header("Authorization", "Bearer " + adminToken)
        .when()
            .get("/admin/applicants/" + applicantId)
        .then()
            .statusCode(200)
            .body("userId", equalTo(applicantId));
    }

    @Test(dependsOnMethods = "testCreateJob")
    public void testGetApplicationsByJobId() {
        given()
            .header("Authorization", "Bearer " + adminToken)
        .when()
            .get("/admin/job/" + jobId + "/applications")
        .then()
            .statusCode(200);
    }
}