package tests;

import models.TestCase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddTestCaseTests extends BaseTest {
    @Test
    public void positiveAddTestCaseTest() {
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
        String projectName = "AnnaYedoshinaQA24";
        loginPage.logIn(USERNAME, PASSWORD);
        allProjectsPage.openProjectByName(projectName);
        projectPage.clickAddTestCasesLink();
        addTestCasePage.fillingOutTestCase(actualTestCase);
        addTestCasePage.clickAddTestCaseButton();
        Assert.assertTrue(addedTestCasePage.isAddAnotherLinkDisplayed());
        TestCase expectedTestCase = testCaseInfoPage.getTestCaseInfo();
        Assert.assertEquals(expectedTestCase, actualTestCase);


    }
}
