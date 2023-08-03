package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SectionTest extends BaseTest {
    @BeforeMethod(alwaysRun = true)
    public void addSection() {
        loginPage.logIn(USERNAME, PASSWORD);
        dashboardPage.openProject("TestProject");
        projectPage.openCaseTab();
        testCasesTab.isPageOpened();
        testCasesTab.clickCreateSectionButton();
    }

    @Test(description = "Check if the test section can be created", groups = "regression")
    public void createSectionTest() {
        String sectionName = faker.animal().name() + faker.number().randomDigit();
        String sectionDescription = faker.country().capital() + faker.number().randomDigit();
        testCasesTab.createSection(sectionName, sectionDescription);
        testCasesTab.openCaseTab();
        Assert.assertTrue(testCasesTab.isSectionExist(sectionName), "Section was not created");
    }

    @Test(description = "Check if the test section can be updated", groups = "regression")
    public void updatedSectionTest() {
        String sectionName = faker.country().name() + faker.number().randomDigit();
        String newSectionName = faker.currency().name() + faker.number().randomDigit();
        String sectionDescription = faker.country().capital();
        String newSectionDescription = faker.currency().code();
        testCasesTab.createSection(sectionName, sectionDescription);
        testCasesTab.clickEditSection(sectionName);
        testCasesTab.updateSection(newSectionName, newSectionDescription);
        Assert.assertTrue(testCasesTab.isSectionExist(newSectionName), "Section was not updated");
    }

    @Test(description = "Check if the test section can be deleted", groups = "regression")
    public void deletedSectionTest() {
        String sectionName = faker.country().name() + faker.number().randomDigit();
        String sectionDescription = faker.country().capital();
        testCasesTab.createSection(sectionName, sectionDescription);
        testCasesTab.clickDeleteSection(sectionName);
        testCasesTab.confirmDeleteSection();
        testCasesTab.openCaseTab();
        Assert.assertFalse(testCasesTab.isSectionExist(sectionName), "Section has not been deleted");
    }
}
