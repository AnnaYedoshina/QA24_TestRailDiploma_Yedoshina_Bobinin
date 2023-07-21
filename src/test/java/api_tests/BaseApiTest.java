package api_tests;

import controllers.ProjectController;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Project;
import org.apache.http.protocol.HTTP;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import utils.PropertyReader;

import static io.restassured.RestAssured.given;


public class BaseApiTest {
    protected static final String BASE_URL = System.getProperty("base_url", PropertyReader.getProperty("base_url"));
    protected static final String USERNAME = System.getProperty("username", PropertyReader.getProperty("username"));
    protected static final String PASSWORD = System.getProperty("password", PropertyReader.getProperty("password"));
    protected int projectId;

    ProjectController projectController= new ProjectController();


    @BeforeSuite
    public void createTestProject() {
        Project project = Project.builder()
                .setName("TestProject")
                .setAnnouncement("Project for testing")
                .setShowAnnouncement(false)
                .setSuiteMode(1)
                .build();
        Response response = projectController.addProject(project);
        projectId = response.getBody().jsonPath().getInt("id");
    }

    @AfterSuite
    public void deleteTestProject() {
        projectController.deleteProject(projectId);
    }


    @BeforeTest
    public void setup() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.requestSpecification = given()
                .auth().preemptive().basic(USERNAME, PASSWORD)
                .header(HTTP.CONTENT_TYPE, ContentType.JSON);
    }
}
