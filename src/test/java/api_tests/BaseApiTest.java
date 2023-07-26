package api_tests;

import com.github.javafaker.Faker;
import controllers.MilestoneController;
import controllers.ProjectController;
import controllers.SectionController;
import controllers.CasesController;
import io.restassured.response.Response;
import models.Case;
import models.Project;
import models.Section;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;


public class BaseApiTest {

    protected int projectId;

    ProjectController projectController = new ProjectController();
    SectionController sectionController = new SectionController();
    CasesController casesController = new CasesController();
    MilestoneController milestoneController = new MilestoneController();


    @BeforeSuite(alwaysRun = true)
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

    @BeforeClass(alwaysRun = true)
    public void createProject() {
        Project project = Project.builder()
                .setName("TestProject")
                .setAnnouncement("Project for testing")
                .setShowAnnouncement(false)
                .setSuiteMode(1)
                .build();
        Response response = projectController.addProject(project);
        projectId = response.getBody().jsonPath().getInt("id");

    }


    @AfterSuite(alwaysRun = true)
    public void deleteTestProject() {
        projectController.deleteProject(projectId);
    }

}


