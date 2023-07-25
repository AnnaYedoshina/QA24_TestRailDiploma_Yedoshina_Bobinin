package controllers;

import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.Section;

import static io.restassured.RestAssured.given;

public class SectionController extends BaseController {


    public Response addSection(Section section, int projectId) {
        return given()
                .body(section, ObjectMapperType.GSON)
                .pathParam("project_id", projectId)
                .when()
                .post("/index.php?/api/v2/add_section/{project_id}")
                .then()
                .log().all()
                .extract().response();
    }

    public Response getSection(int sectionId) {
        return given()
                .pathParam("section_id", sectionId)
                .log().all()
                .when()
                .get("index.php?/api/v2/get_section/{section_id}")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();

    }

    public Response updateSection(Section section, int sectionId) {
        return given()
                .body(section, ObjectMapperType.GSON)
                .pathParam("project_id", sectionId)
                .when()
                .post("/index.php?/api/v2/update_section/{section_id}")
                .then()
                .log().all()
                .extract().response();
    }

    public Response deleteSection(int sectionId) {
        return given()
                .pathParam("project_id", sectionId)
                .log().all()
                .when()
                .post("index.php?/api/v2/delete_section/{section_id}")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();
    }
}
