package pages;

import elements.Button;
import elements.Checkbox;
import elements.Input;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

@Log4j2
public class MilestonesTab extends BasePage {

    public MilestonesTab(WebDriver driver) {
        super(driver);
    }

    private static final By PAGE_TITLE = By.xpath("//*[@id='content-header']/descendant::div[contains(text(),'Milestones')]");
    private static final By ADD_MILESTONE_BUTTON = By.id("navigation-milestones-add");
    private static final By MILESTONE_NAME = By.id("name");
    private static final By MILESTONE_DESCRIPTION = By.id("description_display");
    private static final By SUBMIT_MILESTONE_BUTTON = By.id("accept");
    private static final By MILESTONE_TAB = By.id("navigation-milestones");
    private static final By ALL_MILESTONES = By.cssSelector(".summary-title");
    private static final By CONFIRM_DELETE_MILESTONE_BUTTON = By.xpath("//*[@id='deleteDialog']/descendant::a[contains(@class,'button-ok')]");
    private static final By WARNING_MESSAGE_IN_CONFIRMATION_DELETE_MILESTONE_WINDOW = By.xpath("//*[@id='deleteDialog']/descendant::p[@class='top bottom dialog-message']");
    String milestonesLocator = "//div[contains(@class,'summary-title')]/descendant::a[text()='%s']";
    String editMilestoneLocator = "//div[@class = 'summary-links text-secondary']/descendant::a";
    String deleteMilestoneLocator = "//div[@class = 'icon-small-delete ']";


    public void isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
    }

    public void clickAddMilestoneButton() {
        log.info("Click 'Add milestone' button");
        new Button(driver, ADD_MILESTONE_BUTTON).click();
    }

    @Step("Creating milestone with title '{milestoneName}'")
    public void createMilestone(String milestoneName, String milestoneDescription) {
        log.info("Creating milestone with title '{}'", milestoneName);
        new Input(driver, MILESTONE_NAME).setValue(milestoneName);
        new Input(driver, MILESTONE_DESCRIPTION).setValue(milestoneDescription);
        new Button(driver, SUBMIT_MILESTONE_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(milestonesLocator, milestoneName))));
    }

    public void openMilestoneTab() {
        log.info("Opening page containing Milestones");
        new Button(driver, MILESTONE_TAB).click();
    }

    private boolean isEntityExist(String entityName, By entityLocator) {
        List<WebElement> entitiesList = driver.findElements(entityLocator);
        boolean isEntityExist = false;
        for (WebElement entity : entitiesList) {
            if (entity.getText().equals(entityName)) {
                isEntityExist = true;
            }
        }
        return isEntityExist;
    }

    private void scroll(String targetLocator, String targetName) {
        log.info("Scrolling page to case or section, or milestone with title '{}'", targetName);
        WebElement targetTitle = driver.findElement(By.xpath(String.format(targetLocator, targetName)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", targetTitle);
        ((JavascriptExecutor) driver).executeScript("scrollBy(0, -200)");
    }

    private void hover(String targetLocator, String targetName) {
        log.info("Hovering over the case or section, or milestone with title '{}'", targetName);
        Actions actions = new Actions(driver);
        WebElement targetTitle = driver.findElement(By.xpath(String.format(targetLocator, targetName)));
        actions.moveToElement(targetTitle).build().perform();
    }

    private void clickIcon(String entityName, String entityTitleLocator, String iconActionLocator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(milestonesLocator, entityName))));
        scroll(entityTitleLocator, entityName);
        hover(entityTitleLocator, entityName);
        WebElement icon = driver.findElement(By.xpath(String.format(iconActionLocator, entityName)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(iconActionLocator, entityName))));
        log.info("Click edit/delete icon");
        icon.click();
    }

    @Step("Checking the existence of the milestone with title '{milestoneName}'")
    public boolean isMilestoneExist(String milestoneName) {
        log.info("Checking the existence of the milestone with title '{}'", milestoneName);
        return isEntityExist(milestoneName, ALL_MILESTONES);
    }

    @Step("Creating new milestone with title '{newMilestoneName}'")
    public void updateMilestone(String newMilestoneName, String newMilestoneDescription) {
        log.info("Updating primary milestone to milestone with title '{}'", newMilestoneName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(SUBMIT_MILESTONE_BUTTON));
        new Input(driver, MILESTONE_DESCRIPTION).clearValue();
        new Input(driver, MILESTONE_DESCRIPTION).setValue(newMilestoneDescription);
        new Button(driver, SUBMIT_MILESTONE_BUTTON).click();
    }

    @Step("Editing milestone with title '{milestoneName}'")
    public void clickEditMilestone(String milestoneName) {
        clickIcon(milestoneName, milestonesLocator, editMilestoneLocator);
    }

    @Step("Deleting milestone with title '{milestoneName}'")
    public void clickDeleteMilestone(String sectionName) {
        clickIcon(sectionName, milestonesLocator, deleteMilestoneLocator);

    }

    public void confirmDeleteMilestone() {
        log.info("Confirmation delete milestone");
        wait.until(ExpectedConditions.visibilityOfElementLocated(WARNING_MESSAGE_IN_CONFIRMATION_DELETE_MILESTONE_WINDOW));
        new Button(driver, CONFIRM_DELETE_MILESTONE_BUTTON).click();
    }

}

