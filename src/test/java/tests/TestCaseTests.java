package tests;

import models.TestCase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.TestDataGenerator;

public class TestCaseTests extends BaseTest {
    private static final String TITLE = "Позитивное тестирование формы Login";
    private static final String EXPECTED_ERROR_MESSAGE = "Field Title is a required field.";

    @BeforeMethod(alwaysRun = true)
    public void addTestCase() {
        loginPage.logIn(USERNAME, PASSWORD);
        allProjectsPage.openProjectByName(NAME);
        projectPage.clickAddTestCasesLink();

    }

    @Test(description = "Check if the test case can be created", groups = "smoke")
    public void positiveAddTestCaseTest() {
        TestCase actualTestCase = TestDataGenerator.positiveTestCaseGeneration(TITLE);
        addTestCasePage.fillingOutTestCase(actualTestCase);
        addTestCasePage.clickAddTestCaseButton();
        Assert.assertTrue(addedTestCasePage.isAddAnotherLinkDisplayed());
        TestCase expectedTestCase = testCaseInfoPage.getTestCaseInfo();
        Assert.assertEquals(expectedTestCase, actualTestCase);
    }

    @Test(description = "Check if the test case can not be created without title", groups = "smoke")
    public void negativeAddTestCaseTest() {
        TestCase testCase = TestDataGenerator.negativeTestCaseGeneration("");
        addTestCasePage.fillingOutTestCase(testCase);
        addTestCasePage.clickAddTestCaseButton();
        Assert.assertEquals(addTestCasePage.gerErrorMessageText(), EXPECTED_ERROR_MESSAGE);

    }

}
