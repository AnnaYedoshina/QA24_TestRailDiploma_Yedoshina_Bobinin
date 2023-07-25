package controllers;

import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.Milestone;

import static io.restassured.RestAssured.given;

public class MilestoneController extends BaseController {

    public Response addMilestone(Milestone milestone, int projectId) {
        return given()
                .body(milestone, ObjectMapperType.GSON)
                .pathParam("project_id", projectId)
                .when()
                .post("/index.php?/api/v2/add_milestone/{project_id}")
                .then()
                .log().all()
                .extract().response();
    }

    public Response getMilestone(int milestoneId) {
        return given()
                .pathParam("milestone_id", milestoneId)
                .log().all()
                .when()
                .get("/index.php?/api/v2/get_milestone/{milestone_id}")
                .then()
                .log().all()
                .extract().response();
    }

    public Response updateMilestone(Milestone milestone, int milestoneId) {
        return given()
                .body(milestone, ObjectMapperType.GSON)
                .pathParam("milestone_id", milestoneId)
                .when()
                .post("/index.php?/api/v2/update_milestone/{milestone_id}")
                .then()
                .log().all()
                .extract().response();
    }

    public Response deleteMilestone(int milestoneId) {
        return given()
                .pathParam("milestone_id", milestoneId)
                .log().all()
                .when()
                .post("/index.php?/api/v2/delete_milestone/{milestone_id}")
                .then()
                .log().all()
                .extract().response();
    }
}
