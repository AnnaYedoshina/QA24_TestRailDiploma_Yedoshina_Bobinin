package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ProjectTest extends BaseTest {
    static Faker faker = new Faker();

    @Test(description = "Check if the project can be created", groups = "smoke")
    public void projectShouldBeCreated() {
        String projectName = faker.app().name() + faker.number().randomDigit();
        String projectAnnouncement = faker.app().version();
        loginPage.logIn(USERNAME, PASSWORD);
        dashboardPage.clickCreateProjectButton();
        projectCreationPage.createProject(projectName, projectAnnouncement);
        dashboardPage.open();
        Assert.assertTrue(dashboardPage.isProjectExist(projectName), "Project was not created");
    }

    @Test(description = "Check if the project can be updated", groups = "regression")
    public void projectShouldBeUpdated() {
        String projectName = faker.app().name() + faker.number().randomDigit();
        String newProjectName = faker.book().title() + faker.number().randomDigit();
        String projectAnnouncement = faker.app().version();
        String newProjectAnnouncement = faker.book().genre();
        loginPage.logIn(USERNAME, PASSWORD);
        dashboardPage.clickCreateProjectButton();
        projectCreationPage.createProject(projectName, projectAnnouncement);
        administrationPage.isPageOpened();
        administrationPage.editProject(projectName);
        projectCreationPage.updateProject(newProjectName, newProjectAnnouncement);
        dashboardPage.open();
        Assert.assertTrue(dashboardPage.isProjectExist(newProjectName), "Project was not updated");
        Alert alert = driver.switchTo().alert();
        alert.accept();
        driver.switchTo().defaultContent();

    }

    @Test(description = "Check if the project can be deleted", groups = "regression")
    public void projectShouldBeDeleted() {
        String projectName = faker.app().name() + faker.number().randomDigit();
        String projectAnnouncement = faker.app().version();
        loginPage.logIn(USERNAME, PASSWORD);
        dashboardPage.clickCreateProjectButton();
        projectCreationPage.createProject(projectName, projectAnnouncement);
        administrationPage.isPageOpened();
        administrationPage.deleteProject(projectName);
        administrationPage.confirmDeleteProject();
        dashboardPage.open();
        Assert.assertFalse(dashboardPage.isProjectExist(projectName), "Project has not been deleted");
    }
}
