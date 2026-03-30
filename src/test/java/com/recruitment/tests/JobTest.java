package com.recruitment.tests;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class JobTest extends BaseTest {

    @Test
    public void testGetAllJobs() {
        given()
            .header("Authorization", "Bearer " + adminToken)
        .when()
            .get("/jobs")
        .then()
            .statusCode(200);
    }

    @Test(dependsOnMethods = "testGetAllJobs")
    public void testApplyJob() {
        given()
            .header("Authorization", "Bearer " + userToken)
        .when()
            .post("/jobs/apply/" + BaseTest.jobId)
        .then()
            .statusCode(200);
    }

    @Test
    public void testUploadResume() {
        given()
            .header("Authorization", "Bearer " + userToken)
            .multiPart("file", new java.io.File("src/test/resources/test-resume.pdf"), "application/pdf")
        .when()
            .post("/jobs/uploadResume")
        .then()
            .statusCode(200);
    }
}