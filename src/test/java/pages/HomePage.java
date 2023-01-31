package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/header/div/div[3]/a[4]/span")
    private WebElement signupButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/header/div/div[3]/a[3]/span")
    private WebElement loginButtonHomePage;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/header/div/div[3]/button/span")
    private WebElement languageButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div/header/div/div[3]/button[2]/span")
    private WebElement logoutButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public WebElement getSignupButton() {
        return signupButton;
    }

    public WebElement getLoginButtonHomePage() {
        return loginButtonHomePage;
    }

    public void logout() {
        explicitWait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        logoutButton.click();
    }

    public boolean isLogoutPresent() {
        explicitWait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        logoutButton.isDisplayed();
        return true;
    }
}
