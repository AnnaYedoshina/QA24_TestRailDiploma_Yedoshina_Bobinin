package api_tests;

import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.Project;
import models.TestCase;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class ProjectApiTests extends BaseApiTest {
    private int projectId;

    @BeforeTest
    public void addNewProject() {
        Project project = Project.builder()
                .setName("New project")
                .setAnnouncement("Project to check")
                .setShowAnnouncement(false)
                .setSuiteMode(1)
                .build();
        Response response = projectController.addProject(project);
        projectId = response.getBody().jsonPath().getInt("id");
    }

    @Test(priority = 1)
    public void getProject() {
        Response response = projectController.getProject(projectId);
        Assert.assertEquals(response.jsonPath().getString("name"), "New project");
    }


    @Test(priority = 2)
    public void createProjectFromFile() {
        File file = new File(System.getProperty("user.dir") + "/src/test/resources/requestProjectBody.json");
        Response response = projectController.addProject(file);
        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority = 3)
    public void createProject() {
        Project project = Project.builder().setName("New project24")
                .setAnnouncement("Welcome to new project24")
                .setShowAnnouncement(true)
                .setSuiteMode(1).build();
        Project actualProject = given()
                .body(project, ObjectMapperType.GSON)
                .when()
                .post("index.php?/api/v2/add_project")
                .then()
                .log().all()
                .statusCode(SC_OK)
                .extract().as(Project.class, ObjectMapperType.GSON);
        Assert.assertEquals(project, actualProject);
    }

    @Test(priority = 4)
    public void updateProject() {
        Project projectToUpdate = Project.builder()
                .setName("Updated Project")
                .setAnnouncement("Updated Announcement")
                .setShowAnnouncement(true)
                .setSuiteMode(1)
                .build();
        Project actualProject = given()
                .pathParam("project_id", projectId)
                .body(projectToUpdate, ObjectMapperType.GSON)
                .when()
                .post("index.php?/api/v2/update_project/{project_id}")
                .then()
                .log().all()
                .statusCode(SC_OK)
                .extract().as(Project.class, ObjectMapperType.GSON);
        Assert.assertEquals(projectToUpdate, actualProject);
    }

    @Test(priority = 5)
    public void deleteProject() {
        given()
                .pathParam("project_id", projectId)
                .log().all()
                .when()
                .post("index.php?/api/v2/delete_project/{project_id}")
                .then()
                .log().all()
                .statusCode(SC_OK);

    }

}
