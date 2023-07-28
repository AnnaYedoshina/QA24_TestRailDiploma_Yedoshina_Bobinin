package api_tests;

import com.github.javafaker.Faker;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.Project;
import models.Section;
import models.Case;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CasesApiTests extends BaseApiTest {
    private static final int SUITE_ID = 1;
    protected int caseId;
    protected String title = "Test testCase";
    protected int sectionId;
    static Faker faker = new Faker();

    @BeforeClass(alwaysRun = true)
    public void addNewTestCase() {
        Project project = Project.builder()
                .setName("TestProject")
                .setAnnouncement("Project for testing")
                .setShowAnnouncement(false)
                .setSuiteMode(1)
                .build();
        Response responseProject = projectController.addProject(project);
        projectId = responseProject.getBody().jsonPath().getInt("id");
        Section section = Section.builder()
                .setName("Test section")
                .setDescription("Our test section")
                .setParentId("")
                .setSuiteId(SUITE_ID)
                .build();
        Response responseSection = sectionController.addSection(section, projectId);
        sectionId = responseSection.getBody().jsonPath().getInt("id");
        Case testCase = Case.builder()
                .setTitle(title)
                .setEstimate("2m")
                .setTypeId(faker.random().nextInt(1, 4))
                .setPriorityId(faker.random().nextInt(1, 4))
                .setTemplateId(faker.random().nextInt(1, 3))
                .setSectionId(sectionId)
                .build();
        Response responseCase = casesController.addTestCase(testCase, sectionId);
        caseId = responseCase.getBody().jsonPath().getInt("id");
    }

    @Test(description = "Check if the test case can be created by API", priority = 1, groups = "api")
    public void addTestCase() {
        Case testCase = Case.builder()
                .setTitle(title)
                .setEstimate("2m")
                .setTypeId(faker.random().nextInt(1, 4))
                .setPriorityId(faker.random().nextInt(1, 4))
                .setTemplateId(faker.random().nextInt(1, 3))
                .setSectionId(sectionId)
                .build();
        Response response = casesController.addTestCase(testCase, sectionId);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getBody().as(Case.class, ObjectMapperType.GSON), testCase);
    }

    @Test(description = "Check if the test case can be gotten by API", priority = 2, groups = "api")
    public void getTestCase() {
        Response response = casesController.getTestCase(caseId);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("title"), title);
    }

    @Test(description = "Check if the test case can be updated by API", priority = 3, groups = "api")
    public void updateTestCase() {
        Case testCase = Case.builder()
                .setTitle("Updated testcase")
                .setEstimate("2m")
                .setTypeId(faker.random().nextInt(1, 4))
                .setPriorityId(faker.random().nextInt(1, 4))
                .setTemplateId(faker.random().nextInt(1, 3))
                .setSectionId(sectionId)
                .build();
        Response response = casesController.updateTestCase(testCase, caseId);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getBody().as(Case.class, ObjectMapperType.GSON), testCase);
    }

    @Test(description = "Check if the test case can be deleted by API", priority = 4, groups = "api")
    public void deleteNewTestCase() {
        Response response = casesController.deleteTestCase(caseId);
        Assert.assertEquals(response.getStatusCode(), 200);

    }

}

