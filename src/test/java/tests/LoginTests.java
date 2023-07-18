package tests;

import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    private String expectedErrorText = "Email/Login or Password is incorrect. Please try again.";

    @Test(groups = {"smoke"}, description = "Тестирование формы Login")
    @Description("Позитивный тест")
    @Link(name = "Login page", type = "href", url = "https://ibay.testrail.io/")
    @Severity(SeverityLevel.CRITICAL)
    public void positiveLoginTest() {
        loginPage.logIn(USERNAME, PASSWORD);
        Assert.assertTrue(allProjectsPage.isTestCasesLinkDisplayed());
    }

    @DataProvider
    public Object[][] negativeLoginTestData() {
        return new Object[][]{
                {"", PASSWORD, "Email/Login is required."},
                {USERNAME, "", "Password is required."}
        };
    }

    @Test(groups = {"smoke"}, description = "Негативное тестирование формы Login", dataProvider = "negativeLoginTestData")
    @Description("2 негативных теста формы Login")
    @Severity(SeverityLevel.CRITICAL)
    public void negativeLoginTestData(String username, String password, String expectedErrorMessage) {
        loginPage.logIn(username, password);
        Assert.assertEquals(loginPage.getErrorMessageText(), expectedErrorMessage);

    }

    @Test(groups = {"smoke"}, description = "Негативное тестирование формы Login")
    @Description("Ввод неверного Email в форму Login")
    @Severity(SeverityLevel.CRITICAL)
    public void negativeLoginTestWrongEmail() {
        loginPage.logIn(WRONG_USERNAME, PASSWORD);
        Assert.assertEquals(loginPage.getErrorText(), expectedErrorText);

    }

    @Test(groups = {"smoke"}, description = "Негативное тестирование формы Login")
    @Description("Ввод неверного Password в форму Login")
    @Severity(SeverityLevel.CRITICAL)
    public void negativeLoginTestWrongPassword() {
        loginPage.logIn(USERNAME, WRONG_PASSWORD);
        Assert.assertEquals(loginPage.getErrorText(), expectedErrorText);


    }
}
