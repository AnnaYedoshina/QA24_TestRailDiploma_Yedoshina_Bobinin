package tests;

import models.TestCase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCaseTests extends BaseTest {
    private static final String TITLE = "Позитивное тестирование формы Login";
    private String expectedErrorMessage = "Field Title is a required field.";

    @Test
    public void positiveAddTestCaseTest() {
        TestCase actualTestCase = TestCase.builder()
                .setTitle(TITLE)
                .setSection("Test Cases")
                .setType("Other")
                .setPriority("Medium")
                .setEstimate("30 minutes")
                .setReferences("qwe")
                .setAutomationType("None")
                .setPreconditions("Открыта форма Login на сайте TestRail")
                .setSteps("Заполнить поле email. Заполнить поле password. Нажать кнопку login")
                .setExpectedResult("Пользователь авторизован")
                .build();
        loginPage.logIn(USERNAME, PASSWORD);
        allProjectsPage.openProjectByName(NAME);
        projectPage.clickAddTestCasesLink();
        addTestCasePage.fillingOutTestCase(actualTestCase);
        addTestCasePage.clickAddTestCaseButton();
        Assert.assertTrue(addedTestCasePage.isAddAnotherLinkDisplayed());
        TestCase expectedTestCase = testCaseInfoPage.getTestCaseInfo();
        Assert.assertEquals(expectedTestCase, actualTestCase);
    }

    @Test
    public void negativeAddTestCaseTest() {
        TestCase testCase = TestCase.builder()
                .setTitle("")
                .setSection("Test Cases")
                .setType("Other")
                .setPriority("Medium")
                .setEstimate("30 minutes")
                .setReferences("qwe")
                .setAutomationType("None")
                .setPreconditions("Открыта форма Login на сайте TestRail")
                .setSteps("Заполнить поле email. Заполнить поле password. Нажать кнопку login")
                .setExpectedResult("Пользователь авторизован")
                .build();
        loginPage.logIn(USERNAME, PASSWORD);
        allProjectsPage.openProjectByName(NAME);
        projectPage.clickAddTestCasesLink();
        addTestCasePage.fillingOutTestCase(testCase);
        addTestCasePage.clickAddTestCaseButton();
        Assert.assertEquals(addTestCasePage.gerErrorMessageText(), expectedErrorMessage);

    }

    @Test
    public void deleteTestCaseTest() {
        TestCase actualTestCase = TestCase.builder()
                .setTitle("Позитивное тестирование формы Login")
                .setSection("Test Cases")
                .setType("Other")
                .setPriority("Medium")
                .setEstimate("30 minutes")
                .setReferences("qwe")
                .setAutomationType("None")
                .setPreconditions("Открыта форма Login на сайте TestRail")
                .setSteps("Заполнить поле email. Заполнить поле password. Нажать кнопку login")
                .setExpectedResult("Пользователь авторизован")
                .build();
        loginPage.logIn(USERNAME, PASSWORD);
        allProjectsPage.openProjectByName(NAME);
        projectPage.clickAddTestCasesLink();
        addTestCasePage.fillingOutTestCase(actualTestCase);
        addTestCasePage.clickAddTestCaseButton();
        testCasesPage.clickTestCasesButton();
        testCasesPage.clickEditTestCaseByName(TITLE);
        testCasesPage.clickDeleteButton();
        testCasesPage.clickMarkAsDeletedButton();
        ;


    }


}
