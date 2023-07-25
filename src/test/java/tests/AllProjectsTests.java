package tests;

import models.Project;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AllProjectsTests extends BaseTest {

    @Test
    public void createNewProjectTest() {
        Project actualProject = Project.builder()
                .setName("AnnaYedoshinaQA24")
                .setAnnouncement("Our first project")
                .build();
        loginPage.logIn(USERNAME, PASSWORD);
        allProjectsPage.clickAddProjectButton();
        addProjectPage.fillProject(actualProject);
        addProjectPage.clickAddProjectButton();
        Assert.assertTrue(projectPage.isTestCaseButtonDisplayed());


    }

}



