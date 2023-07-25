package controllers;

import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.Case;

import static io.restassured.RestAssured.given;

public class CasesController extends BaseController {

    public Response addTestCase(Case testCase, int sectionId) {
        return given()
                .body(testCase, ObjectMapperType.GSON)
                .pathParam("section_id", sectionId)
                .when()
                .post("/index.php?/api/v2/add_case/{section_id}")
                .then()
                .log().all()
                .extract().response();
    }

    public Response getTestCase(int caseId) {
        return given()
                .pathParam("case_id", caseId)
                .log().all()
                .when()
                .get("/index.php?/api/v2/get_case/{case_id}")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();
    }

    public Response updateTestCase(Case testCase, int caseId) {
        return given()
                .body(testCase, ObjectMapperType.GSON)
                .pathParam("case_id", caseId)
                .when()
                .post("/index.php?/api/v2/update_case/{case_id}")
                .then()
                .log().all()
                .extract().response();
    }

    public Response deleteTestCase(int caseId) {
        return given()
                .pathParam("case_id", caseId)
                .log().all()
                .when()
                .post("/index.php?/api/v2/delete_case/{case_id}")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();
    }

}

