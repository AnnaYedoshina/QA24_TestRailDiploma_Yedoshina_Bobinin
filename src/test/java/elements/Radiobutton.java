package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Radiobutton {
    private final MyElement element;

    public Radiobutton(WebDriver driver, By by) {
        this.element = new MyElement(driver, by);
    }

    public Radiobutton(WebDriver driver, WebElement element) {
        this.element = new MyElement(driver, element);
    }

    public Radiobutton(WebDriver driver, String id) {
        this.element = new MyElement(driver, By.id(id));
    }

    public void check() {
        if (!isChecked()) {
            element.click();
        }

    }

    public boolean isChecked() {
        return element.isSelected();
    }
}
