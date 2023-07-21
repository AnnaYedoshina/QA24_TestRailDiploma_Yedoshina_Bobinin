package api_tests;

import com.google.gson.JsonObject;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.Project;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class ProjectApiTests extends BaseApiTest {
    private final static String NAME = "New project";
    private int projectId;
    @BeforeTest
    public void addNewProject() {
        Project project = Project.builder()
                .setName("New project")
                .setAnnouncement("Project to check")
                .setShowAnnouncement(false)
                .setSuiteMode(1)
                .build();
        Response response  = projectController.addProject(project);
        projectId = response.getBody().jsonPath().getInt("id");
    }
    @Test(priority = 1)
    public void getProject() {
        Response response = projectController.getProject(projectId);
        Assert.assertEquals(response.jsonPath().getString("name"),"New project");
    }

    @Test(priority = 2)
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

    @Test
    public void createProject() {
        given()
                .body("{" +
                        "    \"name\": \"First Project_1\"," +
                        "    \"announcement\": \"Welcome to first project_1\",\n" +
                        "    \"show_announcement\": true,\n" +
                        "    \"suite_mode\": 2\n" +
                        "}")
                .when()
                .post("index.php?/api/v2/add_project")
                .then()
                .log().all()
                .statusCode(SC_OK);

    }

    @Test
    public void createProjectFromFile() {
        File file = new File(System.getProperty("user.dir") + "/src/test/resources/requestProjectBody.json");
        given()
                .body(file)
                .when()
                .post("index.php?/api/v2/add_project")
                .then()
                .log().all()
                .statusCode(SC_OK);

    }

    @Test
    public void createProjectFromProject() {
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

}
