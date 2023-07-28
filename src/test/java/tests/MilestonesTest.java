package tests;

import com.github.javafaker.Faker;
import models.Milestone;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MilestonesTest extends BaseTest {

    static Faker faker = new Faker();

    @Test(description = "Check if the milestone can be created", groups = "regression")
    public void createMilestoneTest() {
        String milestoneName = faker.animal().name() + faker.number().randomDigit();
        String milestoneDescription = faker.country().capital() + faker.number().randomDigit();
        loginPage.logIn(USERNAME, PASSWORD);
        dashboardPage.openProject("TestProject");
        projectPage.openMilestoneTab();
        milestonesTab.isPageOpened();
        milestonesTab.clickAddMilestoneButton();
        milestonesTab.createMilestone(milestoneName, milestoneDescription);
        milestonesTab.openMilestoneTab();
        Assert.assertTrue(milestonesTab.isMilestoneExist(milestoneName), "Milestone was not created");
    }

    @Test(description = "Check if the milestone can be updated", groups = "regression")
    public void updatedMilestoneTest() {
        String milestoneName = faker.country().name() + faker.number().randomDigit();
        String milestoneDescription = faker.currency().name() + faker.number().randomDigit();
        String newMilestoneName = faker.country().name() + faker.number().randomDigit();
        String newMilestoneDescription = faker.currency().code();
        loginPage.logIn(USERNAME, PASSWORD);
        dashboardPage.openProject("TestProject");
        projectPage.openMilestoneTab();
        milestonesTab.isPageOpened();
        milestonesTab.clickAddMilestoneButton();
        milestonesTab.createMilestone(milestoneName, milestoneDescription);
        milestonesTab.clickEditMilestone(milestoneName);
        milestonesTab.updateMilestone(newMilestoneName, newMilestoneDescription);
        Assert.assertTrue(milestonesTab.isMilestoneExist(newMilestoneName), "Milestone was not updated");
    }

    @Test(description = "Check if the test milestone can be deleted", groups = "regression")
    public void deletedMilestoneTest() {
        String milestoneName = faker.country().name() + faker.number().randomDigit();
        String milestoneDescription = faker.country().capital();
        loginPage.logIn(USERNAME, PASSWORD);
        dashboardPage.openProject("TestProject");
        projectPage.openMilestoneTab();
        milestonesTab.isPageOpened();
        milestonesTab.clickAddMilestoneButton();
        milestonesTab.createMilestone(milestoneName, milestoneDescription);
        milestonesTab.clickDeleteMilestone(milestoneName);
        milestonesTab.confirmDeleteMilestone();
        milestonesTab.openMilestoneTab();
        Assert.assertFalse(milestonesTab.isMilestoneExist(milestoneName), "Milestone has not been deleted");
    }
}

