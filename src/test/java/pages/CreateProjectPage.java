package pages;

import elements.Button;
import elements.Input;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class CreateProjectPage extends BasePage {
    public CreateProjectPage(WebDriver driver) {
        super(driver);
    }

    private static final By PROJECT_NAME = By.name("name");
    private static final By PROJECT_ANNOUNCEMENT = By.name("announcement");
    private static final By ADD_PROJECT_BUTTON = By.id("accept");


    @Step("Creating project with title '{projectName}'")
    public void createProject(String projectName, String projectAnnouncement) {
        log.info("Creating project with title '{}'", projectName);
        new Input(driver, PROJECT_NAME).setValue(projectName);
        new Input(driver, PROJECT_ANNOUNCEMENT).setValue(projectAnnouncement);
        new Button(driver, ADD_PROJECT_BUTTON).click();
    }

    @Step("Changing a primary project on project with title '{projectName}'")
    public void updateProject(String projectName, String projectAnnouncement) {
        log.info("Updating project to project with title '{}'", projectName);
        new Input(driver, PROJECT_NAME).clearValue();
        new Input(driver, PROJECT_NAME).setValue(projectName);
        new Input(driver, PROJECT_ANNOUNCEMENT).clearValue();
        new Input(driver, PROJECT_ANNOUNCEMENT).setValue(projectAnnouncement);
        new Button(driver, ADD_PROJECT_BUTTON).click();
    }
}
