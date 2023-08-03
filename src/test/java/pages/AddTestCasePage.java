package pages;

import elements.Button;
import elements.Dropdown;
import elements.Input;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class AddTestCasePage extends BasePage {
    public AddTestCasePage(WebDriver driver) {
        super(driver);

    }

    private static final By TITLE = By.id("title");
    private static final By ESTIMATE = By.id("estimate");
    private static final By REFERENCES = By.id("refs");
    private static final By PRECONDITIONS = By.id("custom_preconds_display");
    private static final By STEPS = By.id("custom_steps_display");
    private static final By EXPECTED_RESULT = By.id("custom_expected_display");
    private static final By ADD_CASE_BUTTON = By.id("accept");
    private static final By SECTION = By.id("section_id_chzn");
    private static final By TEMPLATE = By.id("template_id_chzn");
    private static final By TYPE = By.id("type_id_chzn");
    private static final By PRIORITY = By.id("priority_id_chzn");
    private static final By AUTOMATION_TYPE = By.id("custom_automation_type_chzn");
    private static final By ERROR_MESSAGE_LOCATOR = By.xpath("//div[@class = 'message message-error']");
    private static final By PENDO_IMAGE = By.xpath("//img[contains(@id,'pendo-image-badge')]");

    @Step("Filling out test case '{testCase.title}'")
    public void fillingOutTestCase(TestCase testCase) {
        log.info(String.format("Filling out testCase = %s", testCase));
        wait.until(ExpectedConditions.elementToBeClickable(PENDO_IMAGE));
        new Input(driver, TITLE).setValue(testCase.getTitle());
        new Dropdown(driver, SECTION).selectOptionByText(testCase.getSection().toString(), false);
        new Dropdown(driver, TEMPLATE).selectOptionByText(testCase.getTemplate(), false);
        new Dropdown(driver, TYPE).selectOptionByText(testCase.getType(), false);
        new Dropdown(driver, PRIORITY).selectOptionByText(testCase.getPriority().toString(), false);
        new Input(driver, ESTIMATE).setValue(testCase.getEstimate());
        new Input(driver, REFERENCES).setValue(testCase.getReferences());
        new Dropdown(driver, AUTOMATION_TYPE).selectOptionByText(testCase.getAutomationType(), false);
        new Input(driver, PRECONDITIONS).setValue(testCase.getPreconditions());
        new Input(driver, STEPS).setValue(testCase.getSteps());
        new Input(driver, EXPECTED_RESULT).setValue(testCase.getExpectedResult());
    }

    @Step
    public void clickAddTestCaseButton() {
        log.info("Clicking addTestCaseButton");
        new Button(driver, ADD_CASE_BUTTON).click();
    }

    @Step
    public String gerErrorMessageText() {
        log.info("Searching for error message");
        return driver.findElement(ERROR_MESSAGE_LOCATOR).getText();

    }

}
