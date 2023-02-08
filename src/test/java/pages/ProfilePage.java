package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ProfilePage extends BasePage {

    @FindBy(id = "phone")
    private WebElement phoneField;

    @FindBy(xpath = "//*[@id=\"country\"]")
    private WebElement countryField;

    @FindBy(xpath = "//*[@id=\"urlTwitter\"]")
    private WebElement twitterField;

    @FindBy(id = "urlGitHub")
    private WebElement gitHubField;

    @FindBy(id = "city")
    private WebElement cityField;

    @FindBy(xpath = "/html/body/div/div[1]/main/div/div[2]/div/div/div[2]/span/form/div/div/div[8]/button")
    private WebElement saveButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[4]/div/div/div/div/div[1]")
    private WebElement message;

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public WebElement getMessage() {
        return message;
    }

    public WebElement getPhoneField() {
        explicitWait.until(ExpectedConditions.visibilityOf(phoneField));
        return phoneField;
    }

    public WebElement getCountryField() {
        return countryField;
    }

    public WebElement getTwitterField() {
        return twitterField;
    }

    public WebElement getGitHubField() {
        return gitHubField;
    }

    public void fillPhone(String phone) {
        explicitWait.until(ExpectedConditions.visibilityOf(phoneField));
        phoneField.sendKeys(Keys.CONTROL + "a");
        phoneField.sendKeys(phone);
    }

    public void fillCountry(String country) {
        countryField.sendKeys(Keys.CONTROL + "a");
        countryField.sendKeys(country);
    }

    public void fillTwitter(String twitter) {
        twitterField.sendKeys(Keys.CONTROL + "a");
        twitterField.sendKeys(twitter);
    }

    public void fillGitHub(String gitHub) {
        gitHubField.sendKeys(Keys.CONTROL + "a");
        gitHubField.sendKeys(gitHub);
    }

    public void fillCity(String city) {
        cityField.sendKeys(Keys.SPACE, Keys.CONTROL + "a", city, Keys.ARROW_DOWN, Keys.ENTER);
        explicitWait.until(ExpectedConditions.visibilityOf(saveButton));
        saveButton.click();
        explicitWait.until(ExpectedConditions.visibilityOf(message));
    }

    public void fillMyProfile(String phone, String country, String twitter, String gitHub, String city) {
        fillPhone(phone);
        fillTwitter(twitter);
        fillCountry(country);
        fillGitHub(gitHub);
        fillCity(city);
    }
}
