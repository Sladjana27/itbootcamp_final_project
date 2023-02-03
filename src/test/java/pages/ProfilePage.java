package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePage extends BasePage {

    private HomePage homePage;
    private City city;

    @FindBy(id = "phone")
    private WebElement phoneField;

    @FindBy(id = "country")
    private WebElement countryField;

    @FindBy(id = "urlTwitter")
    private WebElement twitterField;

    @FindBy(id = "urlGitHub")
    private WebElement gitHubField;

    @FindBy(id = "city")
    private WebElement cityField;

    @FindBy(css = "#list-item-207-0 > div > div")
    private WebElement cityBarranquilla;

    @FindBy(css = "#list-item-207-1 > div > div")
    private WebElement cityBogota;

    @FindBy(css = "#list-item-207-2 > div > div")
    private WebElement cityBucaramanaga;

    @FindBy(css = "#list-item-207-3 > div > div")
    private WebElement cityCali;

    @FindBy(css = "#list-item-207-4 > div > div")
    private WebElement cityChicago;

    @FindBy(css = "#list-item-207-5 > div > div")
    private WebElement cityMedellin;

    @FindBy(css = "#list-item-207-6 > div > div")
    private WebElement cityNewYork;

    @FindBy(css = "#list-item-207-7 > div > div")
    private WebElement cityOakland;

    @FindBy(css = "#list-item-207-8 > div > div")
    private WebElement citySanFrancisco;

    @FindBy(css = "#list-item-207-9 > div > div")
    private WebElement citySanLeandro;

    @FindBy(className = "btnSave")
    private WebElement saveButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[4]/div/div/div/div/div[1]")
    private WebElement message;

    public ProfilePage(WebDriver driver) {
        super(driver);
        homePage = new HomePage(driver);
    }

    public WebElement getMessage() {
        return message;
    }

    public void chooseCity(City city) {
        cityField.click();
        switch (city) {
            case Barranquilla:
                cityBarranquilla.click();
                break;
            case Bogota:
                cityBogota.click();
                break;
            case Bucaramanga:
                cityBucaramanaga.click();
                break;
            case Cali:
                cityCali.click();
                break;
            case Chicago:
                cityChicago.click();
                break;
            case Medellin:
                cityMedellin.click();
                break;
            case NewYork:
                cityNewYork.click();
                break;
            case Oakland:
                cityOakland.click();
                break;
            case SanFrancisco:
                citySanFrancisco.click();
                break;
            case SanLeandro:
                citySanLeandro.click();
                break;
        }
    }

    private void fillMyProfile(String phone, City city, String country, String twitter, String gitHub) {
        explicitWait.until(ExpectedConditions.visibilityOf(phoneField));
        this.phoneField.clear();
        this.countryField.clear();
        this.twitterField.clear();
        this.gitHubField.clear();
        this.phoneField.sendKeys(phone);
        chooseCity(city);
        this.countryField.sendKeys(country);
        this.twitterField.sendKeys(twitter);
        this.gitHubField.sendKeys(gitHub);
        saveButton.click();
    }

}
