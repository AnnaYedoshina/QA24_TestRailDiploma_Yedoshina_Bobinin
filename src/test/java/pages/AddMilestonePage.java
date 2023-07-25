package pages;

import elements.Button;
import elements.Checkbox;
import elements.Input;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.Milestone;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class AddMilestonePage extends BasePage {
    public AddMilestonePage(WebDriver driver) {
        super(driver);

    }

    private String nameInputId = "name";
    private String referencesInputId = "reference";
    private String descriptionInputId = "description_display";
    private By isCompletedCheckboxLocator = By.className("dirty-trackable");
    private By addMilestoneButtonLocator = By.id("accept");

    @Step
    public void fillMilestone(Milestone milestone) {
        log.info("Filling out milestone = %s", milestone);
        new Input(driver, nameInputId).setValue(milestone.getName());
        new Input(driver, referencesInputId).setValue(milestone.getReferences());
        new Input(driver, descriptionInputId).setValue(milestone.getDescription());
    }

    @Step
    public void checkCompletedMilestoneCheckbox() {
        log.info("Checking completedMilestoneCheckbox");
        new Checkbox(driver, isCompletedCheckboxLocator).check();

    }

    @Step
    public boolean isCompletedMilestoneCheckboxChecked() {
        log.info("Checking up is completedMilestoneCheckbox checked");
        return new Checkbox(driver, isCompletedCheckboxLocator).isChecked();
    }

    @Step
    public void clickAddMilestoneButton() {
        log.info("Clicking addMilestoneButton");
        new Button(driver, addMilestoneButtonLocator).click();
    }


}
