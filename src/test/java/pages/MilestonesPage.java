package pages;

import elements.Button;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.Milestone;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

@Log4j2
public class MilestonesPage extends BasePage {
    public MilestonesPage(WebDriver driver) {
        super(driver);
    }

    private String allMilesTonesId = ("content-inner");

    private By deleteMilestonesButtonId = By.id("delete-milestones");

    private By milestoneCheckboxLocator = By.xpath("//input[type = 'checkbox']");

    @Step
    public boolean isMilestoneAdded() {
        log.info("Checking is milestone added");
        return driver.findElement(By.id(allMilesTonesId)).isDisplayed();

    }

    @Step
    public void deleteMilestone(Milestone milestone) {
        new Select(driver.findElement(By.id(allMilesTonesId))).selectByVisibleText(milestone.getName());
        new Button(driver, deleteMilestonesButtonId).click();
    }
}
