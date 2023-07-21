package elements;

import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;

public class MyElement implements WebElement {
    private By by;
    private WebDriver driver;
    private WebElement element;

    public MyElement(WebDriver driver, By by) {
        this.driver = driver;
        this.by = by;
        this.element = driver.findElement(this.by);
    }

    public MyElement(WebDriver driver, WebElement element) {
        this.driver = driver;
        this.element = element;
    }

    @Override
    public void click() {
        try {
            element.click();
        } catch (ElementNotInteractableException e) {
            scrollIntoView();
            element.click();
        }

    }

    @Override
    public void submit() {
        element.submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        scrollIntoView();
        element.sendKeys(keysToSend);
    }

    @Override
    public void clear() {
        element.clear();
    }

    @Override
    public String getTagName() {
        return element.getTagName();
    }

    @Override
    public String getAttribute(String name) {
        return element.getAttribute(name);
    }

    @Override
    public boolean isSelected() {
        return element.isSelected();
    }

    @Override
    public boolean isEnabled() {
        return element.isEnabled();
    }

    @Override
    public String getText() {
        scrollIntoView();
        return element.getText();
    }

    public List<MyElement> findMyElements(By by) {
        List<MyElement> result = new ArrayList<>();
        for (WebElement element : element.findElements(by)
        ) {
            result.add(new MyElement(driver, element));
        }
        return result;
    }

    @Override
    public List<WebElement> findElements(By by) {
        return element.findElements(by);
    }

    public MyElement findMyElement(By by) {
        WebElement resultElement = this.element.findElement(by);
        return new MyElement(driver, resultElement);
    }

    @Override
    public WebElement findElement(By by) {
        return element.findElement(by);
    }

    @Override
    public boolean isDisplayed() {
        return element.isDisplayed();
    }

    @Override
    public Point getLocation() {
        return element.getLocation();
    }

    @Override
    public Dimension getSize() {
        return element.getSize();
    }

    @Override
    public Rectangle getRect() {
        return element.getRect();
    }

    @Override
    public String getCssValue(String propertyName) {
        return element.getCssValue(propertyName);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return element.getScreenshotAs(target);
    }

    private void scrollIntoView() {
        ((JavascriptExecutor) this.driver).executeScript("arguments[0].scrollIntoView(true);", this.element);
    }
}