package api_tests;

import com.github.javafaker.Faker;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.Section;
import models.Case;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CasesApiTests extends BaseApiTest {
    private static final int SUITE_ID = 1;
    protected int caseId;
    protected String title = "Test testCase";
    protected int sectionId;
    static Faker faker = new Faker();

    @BeforeMethod(alwaysRun = true)
    public void addNewTestCase() {
        Section section = Section.builder()
                .setName("Test section")
                .setDescription("Our test section")
                .setParentId("")
                .setSuiteId(SUITE_ID)
                .build();
        Response response = sectionController.addSection(section, projectId);
        sectionId = response.getBody().jsonPath().getInt("id");
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

    @Test(priority = 1, groups = "api")
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

    @Test(priority = 2, groups = "api")
    public void getTestCase() {
        Response response = casesController.getTestCase(caseId);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("title"), title);
    }

    @Test(priority = 3, groups = "api")
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

    @Test(priority = 4, groups = "api")
    public void deleteNewTestCase() {
        Response response = casesController.deleteTestCase(caseId);
        Assert.assertEquals(response.getStatusCode(), 200);

    }

}

