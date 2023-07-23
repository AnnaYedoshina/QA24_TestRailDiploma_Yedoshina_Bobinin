package pages;

import elements.Button;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class ProjectPage extends BasePage {
    public ProjectPage(WebDriver driver) {
        super(driver);
    }

    public String addTestCaseButtonId = "sidebar-cases-add";
    public String testCasesButtonId = "sidebar-cases-overview";

    public String addTestRunsButtonId = "sidebar-runs-add";
    public String addMilestoneButtonId = "sidebar-milestones-add";


    @Step
    public boolean isTestCaseButtonDisplayed() {
        return driver.findElement(By.id(addTestCaseButtonId)).isDisplayed();
    }

    @Step
    public void clickAddTestCasesLink() {
        log.info("Clicking addTestCaseLink");
        new Button(driver, addTestCaseButtonId).click();

    }

    @Step
    public void clickTestCasesButton() {
        log.info("Clicking testCasesButton");
        new Button(driver, testCasesButtonId).click();
    }

    @Step
    public void clickAddTestRunsButton() {
        log.info("Clicking addTestRunsButton");
        new Button(driver, addTestRunsButtonId).click();
    }

    @Step
    public void clickAddMilestoneButton() {
        log.info("Clicking addMilestoneButton");
        new Button(driver, addMilestoneButtonId).click();
    }


}
