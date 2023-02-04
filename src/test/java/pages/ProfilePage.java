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

    public ProfilePage(WebDriver driver, WebDriverWait explicitWait) {
        super(driver, explicitWait);
    }

    public WebElement getMessage() {
        return message;
    }

    public WebElement getPhoneField() {
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

    public void fillMyProfile(String phone, String country, String twitter, String gitHub, String city) {
        explicitWait.until(ExpectedConditions.visibilityOf(phoneField));
        this.phoneField.sendKeys(Keys.CONTROL + "a");
        this.phoneField.sendKeys(phone);
        this.twitterField.sendKeys(Keys.CONTROL + "a");
        this.twitterField.sendKeys(twitter);
        this.countryField.sendKeys(Keys.CONTROL + "a");
        this.countryField.sendKeys(country);
        this.gitHubField.sendKeys(Keys.CONTROL + "a");
        this.gitHubField.sendKeys(gitHub);
        this.cityField.sendKeys(Keys.SPACE);
        this.cityField.sendKeys(Keys.CONTROL + "a");
        this.cityField.sendKeys(city);
        this.cityField.sendKeys(Keys.ARROW_DOWN);
        this.cityField.sendKeys(Keys.ENTER);
        this.saveButton.click();
    }
}
