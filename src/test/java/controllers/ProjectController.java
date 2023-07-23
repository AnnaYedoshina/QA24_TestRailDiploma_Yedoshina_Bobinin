package controllers;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.Project;


import static io.restassured.RestAssured.given;

public class ProjectController extends BaseController {
    private final static String BASE_URL = "https://ibay.testrail.io/";
    protected final static String USERNAME = "ilyab.sumo@gmail.com";
    protected final static String PASSWORD = "Aa12345@";

    public Response addProject(Project project) {
        return given()
                .body(project, ObjectMapperType.GSON)
                .when()
                .post("/index.php?/api/v2/add_project")
                .then()
                .log().all()
                .extract().response();
    }

    public Response getProject(int projectId) {
        return given()
                .pathParam("project_id", projectId)
                .log().all()
                .when()
                .get("/index.php?/api/v2/get_project/{project_id}")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();
    }

    public Response updateProject(Project project, int projectId) {
        return given()
                .body(project, ObjectMapperType.GSON)
                .pathParam("project_id", projectId)
                .when()
                .post("/index.php?/api/v2/update_project/{project_id}")
                .then()
                .log().all()
                .extract().response();
    }

    public Response deleteProject(int projectId) {
        return given()
                .pathParam("project_id", projectId)
                .log().all()
                .when()
                .post("/index.php?/api/v2/delete_project/{project_id}")
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();
    }


}
