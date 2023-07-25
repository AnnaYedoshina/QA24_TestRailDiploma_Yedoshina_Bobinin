package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCasesTests extends BaseTest {

    @Test
    public void checkCheckBoxChooseAllTest() {
        String projectName = "AnnaYedoshinaQA24";
        loginPage.logIn(USERNAME, PASSWORD);
        allProjectsPage.openProjectByName(projectName);
        projectPage.clickTestCasesButton();
        testCasesPage.checkCheckboxChooseAll();
        Assert.assertTrue(testCasesPage.isCheckboxChecked());

    }

    @Test
    public void uncheckCheckboxChooseAllTest() {
        String projectName = "AnnaYedoshinaQA24";
        loginPage.logIn(USERNAME, PASSWORD);
        allProjectsPage.openProjectByName(projectName);
        projectPage.clickTestCasesButton();
        testCasesPage.checkCheckboxChooseAll();
        testCasesPage.uncheckCheckbox();
        Assert.assertFalse(testCasesPage.isCheckboxChecked());

    }
}
