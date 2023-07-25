package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AddTestRunPageTests extends BaseTest {

    @Test
    public void checkSelectSpecificRadioTest() {
        String projectName = "AnnaYedoshinaQA24";
        loginPage.logIn(USERNAME, PASSWORD);
        allProjectsPage.openProjectByName(projectName);
        projectPage.clickAddTestRunsButton();
        addTestRunPage.checkSelectSpecificRadio();
        Assert.assertTrue(addTestRunPage.isSelectSpecificRadioChecked());
        Assert.assertFalse(addTestRunPage.isIncludeAllRadioUnchecked());


    }
}
