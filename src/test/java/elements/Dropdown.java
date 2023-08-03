package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Dropdown {
    private final MyElement element;

    public Dropdown(WebDriver driver, By by) {
        this.element = new MyElement(driver, by);
    }

    public Dropdown(WebDriver driver, WebElement element) {
        this.element = new MyElement(driver, element);
    }

    public Dropdown(WebDriver driver, String id) {
        this.element = new MyElement(driver, By.id(id));
    }

    public String getSelectedOption() {
        for (MyElement element : getAllOptions()) {
            if (element.getAttribute("class").contains("result-selected")) {
                return element.getText();
            }
        }
        return null;
    }

    public void selectOptionByText(String text, boolean shouldSearch) {
        expand();
        if (shouldSearch) {
            element.findMyElement(By.id("chzn-single")).sendKeys(text);
        }
        for (MyElement element : getAllOptions()) {
            if (element.getAttribute("innerText").equals(text)) {
                element.click();
                return;
            }
        }
    }

    public void selectOptionByIndex(int index) {
        expand();
        getAllOptions().get(index).click();
    }

    private List<MyElement> getAllOptions() {
        return element.findMyElements(By.cssSelector("ul.chzn-results li"));
    }

    private void expand() {
        element.findMyElement(By.cssSelector("a.chzn-single div b")).click();
    }

}

