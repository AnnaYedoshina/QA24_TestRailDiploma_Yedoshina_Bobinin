package pages;

import elements.Button;
import elements.Input;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.Milestone;
import models.Project;
import org.openqa.selenium.WebDriver;

@Log4j2
public class AddProjectPage extends BasePage {

    public AddProjectPage(WebDriver driver) {
        super(driver);
    }

    private String nameInputId = "name";
    private String announcementInputId = "announcement_display";
    private String addProjectButtonId = "accept";

    @Step
    public void fillProject(Project project) {
        log.info("Filling out project = %s", project);
        new Input(driver, nameInputId).setValue(project.getName());
        new Input(driver, announcementInputId).setValue(project.getAnnouncement());
    }

    @Step
    public void clickAddProjectButton() {
        log.info("Clicking addProjectButton");
        new Button(driver, addProjectButtonId).click();
    }

}
