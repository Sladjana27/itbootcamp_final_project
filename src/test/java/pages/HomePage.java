package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/header/div/div[3]/a[4]/span")
    private WebElement signupButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/header/div/div[3]/a[3]/span")
    private WebElement loginButtonHomePage;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/header/div/div[3]/button/span")
    private WebElement languageButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public WebElement getSignupButton() {
        return signupButton;
    }

    public WebElement getLoginButtonHomePage() {
        return loginButtonHomePage;
    }
}
