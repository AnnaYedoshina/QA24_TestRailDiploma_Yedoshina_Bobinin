package pages;

import com.sun.jdi.NativeMethodException;
import elements.Button;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Log4j2
public class AllProjectsPage extends BasePage {

    public AllProjectsPage(WebDriver driver) {
        super(driver);
    }

    public String addMilestoneButtonId = "navigation-overview-addmilestones";
    public String projectLinkLocator = "//a[text() = '%s']";

    public String addProjectButtonId = "sidebar-projects-add";

    @Step
    public boolean isAddProjectButtonDisplayed() {
        return driver.findElement(By.id(addProjectButtonId)).isDisplayed();
    }


    @Step
    public void openProjectByName(String projectName) {
        log.info(String.format("Opening project by name = %s", projectName));
        new Button(driver, By.xpath(String.format(projectLinkLocator, projectName))).click();
    }

    @Step
    public void clickAddProjectButton() {
        new Button(driver, addProjectButtonId).click();

    }
}


