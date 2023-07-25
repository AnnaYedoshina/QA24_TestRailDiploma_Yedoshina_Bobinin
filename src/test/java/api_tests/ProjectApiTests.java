package api_tests;

import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.Project;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

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
        Project project = Project.builder()
                .setName("New project")
                .setAnnouncement("Project to check")
                .setShowAnnouncement(false)
                .setSuiteMode(1)
                .build();
        Response response = projectController.addProject(project);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getBody().as(Project.class, ObjectMapperType.GSON), project);
    }

    @Test(priority = 4)
    public void updateProject() {
        Project project = Project.builder()
                .setName("Updated Project")
                .setAnnouncement("Updated Announcement")
                .setShowAnnouncement(true)
                .setSuiteMode(1)
                .build();
        Response response = projectController.updateProject(project, projectId);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getBody().as(Project.class, ObjectMapperType.GSON), project);
    }

    @Test(priority = 5)
    public void deleteProject() {
        Response response = projectController.deleteProject(projectId);
        Assert.assertEquals(response.getStatusCode(), 200);


    }

}
