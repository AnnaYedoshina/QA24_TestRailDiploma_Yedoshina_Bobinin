package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Button {
    private final MyElement element;

    public Button(WebDriver driver, By by) {
        this.element = new MyElement(driver, by);
    }

    public Button(WebDriver driver, WebElement element) {
        this.element = new MyElement(driver, element);
    }

    public Button(WebDriver driver, String id) {
        this.element = new MyElement(driver, By.id(id));
    }


    public void click() {
        element.click();
    }

}
