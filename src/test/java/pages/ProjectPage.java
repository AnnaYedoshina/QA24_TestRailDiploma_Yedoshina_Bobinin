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
    private static final By CASE_TAB = By.id("navigation-suites");
    private static final By MILESTONE_TAB = By.id("navigation-milestones");

    @Step
    public void clickAddTestCasesLink() {
        log.info("Clicking addTestCaseLink");
        new Button(driver, addTestCaseButtonId).click();

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
