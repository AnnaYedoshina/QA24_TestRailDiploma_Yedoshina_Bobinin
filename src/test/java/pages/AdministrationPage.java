package pages;

import elements.Button;
import elements.Checkbox;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class AdministrationPage extends BasePage {
    private static final By DELETE_CHECKBOX = By.xpath("//*[@id='deleteDialog']/descendant::input[@name='deleteCheckbox']");
    private static final By CONFIRM_BUTTON = By.xpath("//*[@id='deleteDialog']/descendant::a[contains(@class,'button-ok')]");
    private static final By PROJECTS_ON_NAVIGATION_BAR = By.id("navigation-sub-projects");
    String deleteProjectIconLocator = "//*[contains(text(),'%s')]/ancestor::tr/descendant::div[contains(@class,'icon-small-delete')]";
    String editProjectIconLocator = "//*[contains(text(),'%s')]/ancestor::tr/descendant::div[contains(@class,'icon-small-edit')]";

    public AdministrationPage(WebDriver driver) {
        super(driver);
    }

    @Step("Deleting project with title '{projectName}'")
    public void deleteProject(String projectName) {
        log.info("Deleting project with title '{}'", projectName);
        new Button(driver, By.xpath(String.format(deleteProjectIconLocator, projectName))).click();
    }

    @Step("Editing project with title '{projectName}'")
    public void editProject(String projectName) {
        log.info("Editing project with title '{}'", projectName);
        new Button(driver, By.xpath(String.format(editProjectIconLocator, projectName))).click();
    }

    public void confirmDeleteProject() {
        log.info("Confirmation deleting project");
        new Checkbox(driver, DELETE_CHECKBOX).check();
        new Button(driver, CONFIRM_BUTTON).click();
    }

    public void isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PROJECTS_ON_NAVIGATION_BAR));
    }
}
