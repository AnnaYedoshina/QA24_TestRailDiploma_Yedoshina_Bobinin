package pages;

import elements.Checkbox;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class TestCasesPage extends BasePage {
    public TestCasesPage(WebDriver driver) {
        super(driver);
    }

    public By checkBoxesLocator = By.cssSelector(".selectionCheckbox");

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
}

