package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(className = "btnForgotPassword")
    private WebElement forgotPasswordButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[3]/span/form/div/div[3]/button")
    private WebElement loginButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[4]/div/div/div/div/div[1]/ul/li")
    private WebElement message;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[4]/div/div/div/div/div[1]/button/span")
    private WebElement closeMessage;

    public LoginPage(WebDriver driver, WebDriverWait explicitWait) {
        super(driver, explicitWait);
    }

    public void fillLogin(String email, String password) {
        explicitWait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    public WebElement getMessage() {
        return message;
    }

    public WebElement getEmailField() {
        return emailField;
    }

    public WebElement getPasswordField() {
        return passwordField;
    }

    public void clickForgotPassword() {
        forgotPasswordButton.click();
    }
}
