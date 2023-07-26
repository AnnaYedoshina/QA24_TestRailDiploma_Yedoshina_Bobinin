package api_tests;

import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.Milestone;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class MilestoneApiTests extends BaseApiTest {
    private final static String NAME = "Release 1.0";
    private int milestoneId;

    @BeforeTest(alwaysRun = true)
    public void addNewMilestone() {
        Milestone milestone = Milestone.builder()
                .setName("Release 1.0")
                .setDescription("New features added")
                .setReferences("RF-1")
                .build();
        Response response = milestoneController.addMilestone(milestone, projectId);
        milestoneId = response.getBody().jsonPath().getInt("id");

    }

    @Test(priority = 1, groups = "api")
    public void getMilestone() {
        Response response = milestoneController.getMilestone(milestoneId);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("name"), NAME);
    }

    @Test(priority = 2, groups = "api")
    public void addMilestone() {
        Milestone milestone = Milestone.builder()
                .setName("Release 1.0")
                .setDescription("New features added")
                .setReferences("RF-1")
                .build();
        Response response = milestoneController.addMilestone(milestone, projectId);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getBody().as(Milestone.class, ObjectMapperType.GSON), milestone);
    }

    @Test(priority = 3, groups = "api")
    public void updateMilestone() {
        Milestone milestone = Milestone.builder()
                .setName("Release 1.1")
                .setDescription("New features added")
                .setReferences("RF-2")
                .build();
        Response response = milestoneController.updateMilestone(milestone, milestoneId);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getBody().as(Milestone.class, ObjectMapperType.GSON), milestone);
    }


    @Test(priority = 4, groups = "api")
    public void deleteMilestone() {
        Response response = milestoneController.deleteMilestone(milestoneId);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}