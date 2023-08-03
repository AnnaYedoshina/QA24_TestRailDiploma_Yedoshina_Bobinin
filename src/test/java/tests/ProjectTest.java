package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class ProjectTest extends BaseTest {
    @BeforeMethod(alwaysRun = true)
    public void addProject() {
        loginPage.logIn(USERNAME, PASSWORD);
        dashboardPage.clickCreateProjectButton();
    }

    @Test(description = "Check if the project can be created", groups = "smoke", priority = 1)
    public void createProjectTest() {
        String projectName = faker.app().name() + faker.number().randomDigit();
        String projectAnnouncement = faker.app().version();
        createProjectPage.createProject(projectName, projectAnnouncement);
        dashboardPage.open();
        Assert.assertTrue(dashboardPage.isProjectExist(projectName), "Project was not created");
    }

    @Test(description = "Check if the project can be updated", groups = "regression", priority = 2)
    public void updateProjectTest() {
        String projectName = faker.app().name() + faker.number().randomDigit();
        String newProjectName = faker.book().title() + faker.number().randomDigit();
        String projectAnnouncement = faker.app().version();
        String newProjectAnnouncement = faker.book().genre();
        createProjectPage.createProject(projectName, projectAnnouncement);
        administrationPage.isPageOpened();
        administrationPage.editProject(projectName);
        createProjectPage.updateProject(newProjectName, newProjectAnnouncement);
        dashboardPage.open();
        Assert.assertTrue(dashboardPage.isProjectExist(newProjectName), "Project was not updated");

    }

    @Test(description = "Check if the project can be deleted", groups = "regression", priority = 3)
    public void deleteProjectTest() {
        String projectName = faker.app().name() + faker.number().randomDigit();
        String projectAnnouncement = faker.app().version();
        createProjectPage.createProject(projectName, projectAnnouncement);
        administrationPage.isPageOpened();
        administrationPage.deleteProject(projectName);
        administrationPage.confirmDeleteProject();
        dashboardPage.open();
        Assert.assertFalse(dashboardPage.isProjectExist(projectName), "Project has not been deleted");
    }
}
