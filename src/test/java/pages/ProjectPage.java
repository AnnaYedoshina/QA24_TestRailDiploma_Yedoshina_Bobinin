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

    private static final By ADD_TEST_CASE = By.id("sidebar-cases-add");
    public String addTestCaseButtonId = "sidebar-cases-add";
    public String testCasesButtonId = "sidebar-cases-overview";

    public String addTestRunsButtonId = "sidebar-runs-add";
    private static final By CASE_TAB = By.id("navigation-suites");
    private static final By MILESTONE_TAB = By.id("navigation-milestones");


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
    public void openCaseTab() {
        log.info("Opening page containing Sections and Cases");
        new Button(driver, CASE_TAB).click();
    }

    public void openMilestoneTab() {
        log.info("Opening page containing Sections and Cases");
        new Button(driver, MILESTONE_TAB).click();
    }


}
