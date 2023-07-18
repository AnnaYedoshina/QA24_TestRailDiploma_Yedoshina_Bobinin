package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class AddedTestCasePage extends BasePage {
    public AddedTestCasePage(WebDriver driver) {
        super(driver);
    }

    private By addAnotherLink = By.xpath("//a[text() = 'Add another']");

    @Step
    public boolean isAddAnotherLinkDisplayed() {
        return driver.findElement(addAnotherLink).isDisplayed();

    }


}
