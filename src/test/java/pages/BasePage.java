package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait explicitWait;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        explicitWait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        PageFactory.initElements(this.driver, this);
    }

    public void waiter(String text) {
        explicitWait.until(ExpectedConditions.urlContains(text));
    }

    public String checkType(WebElement webElement) {
        return webElement.getAttribute("type");
    }

    public String getMessage(WebElement messageField) {
        explicitWait.until(ExpectedConditions.visibilityOf(messageField));
        return messageField.getText();
    }
}
