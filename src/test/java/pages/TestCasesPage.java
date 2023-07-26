package pages;

import elements.Button;
import elements.Checkbox;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

@Log4j2
public class TestCasesPage extends BasePage {
    public TestCasesPage(WebDriver driver) {
        super(driver);
    }

    public By checkBoxesLocator = By.cssSelector(".selectionCheckbox");

    public String editLinkLocator = ".editLink";

    public String testCasesButtonId = "navigation-suites";

    public By deleteTestCaseButtonLocator = By.xpath("//span[text()='Delete this test case']");

    public By markAsDeletedButton = By.xpath("//*[@id='casesDeletionDialog']/div[3]/div/a[1]");

    @Step
    public void checkCheckboxChooseAll() {
        log.info("Checking checkboxChooseAll");
        new Checkbox(driver, checkBoxesLocator).check();

    }

    @Step
    public void uncheckCheckbox() {
        new Checkbox(driver, checkBoxesLocator).uncheck();
    }

    @Step
    public boolean isCheckboxChecked() {
        return new Checkbox(driver, checkBoxesLocator).isChecked();

    }

    @Step
    public void clickEditTestCaseByName(String testCaseName) {
        log.info(String.format("Deleting testCase by name = %s", testCaseName));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(editLinkLocator)));
        new Button(driver, By.cssSelector(String.format(editLinkLocator, testCaseName))).click();

    }

    @Step
    public void clickTestCasesButton() {
        new Button(driver, testCasesButtonId).click();
    }

    @Step
    public void clickDeleteButton() {
        new Button(driver, deleteTestCaseButtonLocator).click();
    }

    @Step
    public void clickMarkAsDeletedButton() {
        new Button(driver, markAsDeletedButton).click();
    }
}

