package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Input {
    private final MyElement element;

    public Input(WebDriver driver, By by) {
        this.element = new MyElement(driver, by);
    }

    public Input(WebDriver driver, WebElement element) {
        this.element = new MyElement(driver, element);
    }

    public Input(WebDriver driver, String id) {
        this.element = new MyElement(driver, By.id(id));
    }

    public String getValue() {
        return element.getAttribute("value");
    }

    public void setValue(CharSequence... value) {
        element.sendKeys(value);
    }

    public void clearValue() {
        element.clear();
    }

}
