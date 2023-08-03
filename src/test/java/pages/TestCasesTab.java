package pages;

import elements.Button;
import elements.Checkbox;
import elements.Input;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

@Log4j2
public class TestCasesTab extends BasePage {
    public TestCasesTab(WebDriver driver) {
        super(driver);
    }

    private static final By DELETE_PERMANENTLY_BUTTON = By.xpath("//*[contains(@class,'ui-dialog')]/descendant::a[contains(text(),'Delete Permanently')]");
    private static final By REPEAT_DELETE_PERMANENTLY_BUTTON = By.xpath("//*[@id='casesDeletionConfirmationDialog']/descendant::a[contains(text(),'Delete Permanently')]");
    private static final By PAGE_TITLE = By.xpath("//*[@id='content-header']/descendant::div[contains(text(),'Test Cases')]");
    private static final By ADD_SECTION_BUTTON = By.id("addSection");
    private static final By ADD_SECTION_BUTTON_FOR_PROJECT_WITHOUT_SECTIONS = By.id("addSectionInline");
    private static final By SECTION_NAME = By.name("editSectionName");
    private static final By SECTION_DESCRIPTION = By.id("editSectionDescription_display");
    private static final By SUBMIT_SECTION_BUTTON = By.id("editSectionSubmit");
    private static final By ALL_SECTIONS = By.xpath("//div[contains(@class,'grid-container')]/descendant::span[contains(@id,'sectionName')]");
    private static final By CASE_TAB = By.id("navigation-suites");
    private static final By DELETE_SECTION_CHECKBOX = By.xpath("//*[@id='deleteDialog']/descendant::input[@name='deleteCheckbox']");
    private static final By CONFIRM_DELETE_SECTION_BUTTON = By.xpath("//*[@id='deleteDialog']/descendant::a[contains(@class,'button-ok')]");
    private static final By WARNING_MESSAGE_IN_CONFIRMATION_DELETE_SECTION_WINDOW = By.xpath("//*[@id='deleteDialog']/descendant::p[@class='dialog-extra text-delete']");
    private static final By BLOCK_WINDOW = By.cssSelector("[class='blockUI blockOverlay']");
    String deleteSectionIconLocator = "//span[contains(@id,'sectionName') and text()='%s']/parent::div/descendant::div[contains(@class,'icon-small-delete')]";
    String editSectionIconLocator = "//span[contains(@id,'sectionName') and text()='%s']/parent::div/descendant::div[contains(@class,'icon-small-edit')]";
    String caseLocator = "//div[contains(@class,'grid-container')]/descendant::span[text()='%s']";
    String sectionLocator = "//div[contains(@class,'grid-container')]/descendant::span[contains(@id,'sectionName') and text()='%s']";


    private void waitDisappearBlockingWindow() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(BLOCK_WINDOW));
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

    @Step("Checking the existence of the section with title '{sectionName}'")
    public boolean isSectionExist(String sectionName) {
        log.info("Checking the existence of the section with title '{}'", sectionName);
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
            driver.switchTo().defaultContent();
        } catch (NoAlertPresentException e) {
        }
        return isEntityExist(sectionName, ALL_SECTIONS);
    }

    private void clickIcon(String entityName, String entityTitleLocator, String iconActionLocator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(caseLocator, entityName))));
        scroll(entityTitleLocator, entityName);
        hover(entityTitleLocator, entityName);
        WebElement icon = driver.findElement(By.xpath(String.format(iconActionLocator, entityName)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(iconActionLocator, entityName))));
        log.info("Click edit/delete icon");
        icon.click();
    }

    @Step("Editing section with title '{sectionName}'")
    public void clickEditSection(String sectionName) {
        clickIcon(sectionName, sectionLocator, editSectionIconLocator);
    }

    @Step("Deleting section with title '{sectionName}'")
    public void clickDeleteSection(String sectionName) {
        clickIcon(sectionName, sectionLocator, deleteSectionIconLocator);
    }

    public void isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
    }

    public void clickCreateSectionButton() {
        log.info("Click 'Add section' button");
        waitDisappearBlockingWindow();
        List<WebElement> testSectionsList = driver.findElements(ALL_SECTIONS);
        if (testSectionsList.isEmpty()) {
            wait.until(ExpectedConditions.elementToBeClickable(ADD_SECTION_BUTTON_FOR_PROJECT_WITHOUT_SECTIONS));
            new Button(driver, ADD_SECTION_BUTTON_FOR_PROJECT_WITHOUT_SECTIONS).click();
        } else {
            wait.until(ExpectedConditions.elementToBeClickable(ADD_SECTION_BUTTON));
            new Button(driver, ADD_SECTION_BUTTON).click();
        }
    }

    @Step("Creating section with title '{sectionName}'")
    public void createSection(String sectionName, String sectionDescription) {
        log.info("Creating section with title '{}'", sectionName);
        waitDisappearBlockingWindow();
        new Input(driver, SECTION_NAME).setValue(sectionName);
        new Input(driver, SECTION_DESCRIPTION).setValue(sectionDescription);
        new Button(driver, SUBMIT_SECTION_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(sectionLocator, sectionName))));
    }

    public void openCaseTab() {
        log.info("Opening page containing Sections and Cases");
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
            driver.switchTo().defaultContent();
        } catch (NoAlertPresentException e) {
            waitDisappearBlockingWindow();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(SUBMIT_SECTION_BUTTON));
            wait.until(ExpectedConditions.elementToBeClickable(CASE_TAB));
            new Button(driver, CASE_TAB).click();
        }
    }

    public void confirmDeleteSection() {
        log.info("Confirmation delete section");
        wait.until(ExpectedConditions.visibilityOfElementLocated(WARNING_MESSAGE_IN_CONFIRMATION_DELETE_SECTION_WINDOW));
        new Checkbox(driver, DELETE_SECTION_CHECKBOX).check();
        new Button(driver, CONFIRM_DELETE_SECTION_BUTTON).click();
    }

    @Step("Creating new section with title '{newSectionName}'")
    public void updateSection(String newSectionName, String newSectionDescription) {
        log.info("Updating primary section to section with title '{}'", newSectionName);
        waitDisappearBlockingWindow();
        wait.until(ExpectedConditions.visibilityOfElementLocated(SUBMIT_SECTION_BUTTON));
        new Input(driver, SECTION_NAME).clearValue();
        new Input(driver, SECTION_NAME).setValue(newSectionName);
        wait.until(ExpectedConditions.elementToBeClickable(SUBMIT_SECTION_BUTTON));
        new Input(driver, SECTION_DESCRIPTION).clearValue();
        new Input(driver, SECTION_DESCRIPTION).setValue(newSectionDescription);
        new Button(driver, SUBMIT_SECTION_BUTTON).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(sectionLocator, newSectionName))));
    }
}
