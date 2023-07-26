package pages;

import elements.Button;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.Milestone;
import models.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

@Log4j2
public class MilestonesPage extends BasePage {
    public MilestonesPage(WebDriver driver) {
        super(driver);
    }

    private By allMilesLocator =By.cssSelector(".summary-title");

    private String deleteMilestonesButton = ".icon-small-delete";

    private By milestoneCheckboxLocator = By.xpath("//input[type = 'checkbox']");

    @Step
    public boolean isMilestoneAdded() {
        log.info("Checking is milestone added");
        return driver.findElement(allMilesLocator).isDisplayed();

    }

}
