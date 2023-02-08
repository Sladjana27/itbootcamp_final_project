package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div/header/div/div[3]/a[4]")
    private WebElement signupButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div/div/header/div/div[3]/a[3]/span")
    private WebElement loginButtonHomePage;

    @FindBy(className = "btnLocaleActivation")
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

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[1]/div[1]/h1")
    private WebElement header;

    @FindBy(className = "btnEN")
    private WebElement en;

    @FindBy(className = "btnES")
    private WebElement es;

    @FindBy(className = "btnFR")
    private WebElement fr;

    @FindBy(className = "btnCN")
    private WebElement cn;

    @FindBy(className = "btnUA")
    private WebElement ua;

    @FindBy(className = "btnProfile")
    private WebElement profileButton;

    private Languages languages;

    public HomePage(WebDriver driver) {
        super(driver);
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

    public void clickMyProfile() {
        explicitWait.until(ExpectedConditions.visibilityOf(profileButton));
        profileButton.click();
    }

    public String getHeaderText() {
        return header.getText();
    }

    public void setLanguage(Languages choosselanguage) {
        languageButton.click();
        explicitWait.until(ExpectedConditions.visibilityOf(es));
        switch (choosselanguage) {
            case EN:
                en.click();
                break;
            case ES:
                es.click();
                break;
            case FR:
                fr.click();
                break;
            case CN:
                cn.click();
                break;
            case UA:
                ua.click();
                break;
        }
    }

    public void navigate(String url) {
        driver.navigate().to("https://vue-demo.daniel-avellaneda.com" + url);
    }
}
