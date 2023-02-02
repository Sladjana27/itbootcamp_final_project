package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class HomePage extends BasePage {

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div/header/div/div[3]/a[4]")
    private WebElement signupButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/header/div/div[3]/a[3]/span")
    private WebElement loginButtonHomePage;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/header/div/div[3]/button/span")
    private WebElement languageButton;

    @FindBy(className = "btnLogout")
    private WebElement logoutButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[4]/div/div/div[1]")
    private WebElement signupMessage;

    @FindBy(xpath = "//*[@id=\"app\"]/div[4]/div/div/div[3]/button/span")
    private WebElement closeMessage;

    @FindBy(className = "btnAdmin")
    private WebElement adminButton;

    @FindBy(className = "btnAdminCities")
    private WebElement adminCitiesButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public WebElement getSignupButton() {
        return signupButton;
    }

    public WebElement getLoginButtonHomePage() {
        return loginButtonHomePage;
    }

    public WebElement getSignupMessage() {
        return signupMessage;
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

    public void clickSignup() {
        explicitWait.until(ExpectedConditions.visibilityOf(signupButton));
        signupButton.click();
    }

    public void closeMessage() {
        closeMessage.click();
    }

    public void clickAdminCities() {
        explicitWait.until(ExpectedConditions.visibilityOf(adminButton));
        adminButton.click();
        adminCitiesButton.click();
    }
}
