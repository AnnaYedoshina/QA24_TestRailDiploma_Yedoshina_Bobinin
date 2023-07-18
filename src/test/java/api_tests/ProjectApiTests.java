package api_tests;

import io.restassured.mapper.ObjectMapperType;
import models.Project;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class ProjectApiTests extends BaseApiTest {

    @Test
    public void getProject() {
        Project expectedProject = Project.builder()
                .setId(7)
                .setName("New project24")
                .setAnnouncement("Welcome to new project24")
                .setShowAnnouncement(true)
                .setIsCompleted(false)
                .setCompletedOn(null)
                .setSuiteMode(1)
                .setUrl("https://ayqa241.testrail.io/index.php?/projects/overview/7")
                .build();
        Project actualProject = given()
                .pathParam("project_id", 7)
                .log().all()
                .when()
                .get("index.php?/api/v2/get_project/{project_id}")
                .then()
                .log().all()
                .statusCode(SC_OK)
                .extract().as(Project.class, ObjectMapperType.GSON);
        Assert.assertEquals(actualProject, expectedProject);
    }

    @Test
    public void deleteProject() {
        given()
                .pathParam("project_id", 3)
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
