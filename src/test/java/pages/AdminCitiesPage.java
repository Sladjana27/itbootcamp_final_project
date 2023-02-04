package pages;

import org.checkerframework.common.value.qual.MinLenFieldInvariant;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.Optional;

public class AdminCitiesPage extends BasePage {

    @FindBy(className = "btnNewItem")
    private WebElement newItemButton;

    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(className = "btnSave")
    private WebElement saveButton;

    @FindBy(id = "search")
    private WebElement searchField;

    @FindBy(xpath = "//*[@id=\"edit\"]/span")
    private WebElement editButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[1]/div[2]/table/tbody/tr/td[2]")
    private WebElement cityNameInTable;

    @FindBy(id = "delete")
    private WebElement deleteIcon;

    @FindBy(css = "#app > div.v-dialog__content.v-dialog__content--active > div > div > div.v-card__actions > button.v-btn.v-btn--text.theme--light.v-size--default.red--text.text--lighten3")
    private WebElement deleteButton;

    @FindBy(className = "success")
    private WebElement deleteMessage;

    private LoginPage loginPage;
    private HomePage homePage;

    public AdminCitiesPage(WebDriver driver) {
        super(driver);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

    public WebElement getMessage() {
        explicitWait.until(ExpectedConditions.visibilityOf(deleteMessage));
        return deleteMessage;
    }

    public void createNewCity(String nameCity) {
        explicitWait.until(ExpectedConditions.visibilityOf(newItemButton));
        newItemButton.click();
        nameField.sendKeys(nameCity);
        saveButton.click();
    }

    public void searchCities(String searchCity) {
        explicitWait.until(ExpectedConditions.visibilityOf(searchField));
        searchField.sendKeys(Keys.CONTROL + "a");
        searchField.sendKeys(searchCity);
    }

    public void editCityName(String newName) {
        editButton.click();
        explicitWait.until(ExpectedConditions.visibilityOf(nameField));
        nameField.sendKeys(" " + newName + " - edited");
        saveButton.click();
    }

    public String editedCityName(String cityName) {
        return cityName + " " + cityName + " - edited";
    }

    public String getCityNameInTable() {
        explicitWait.until(ExpectedConditions.visibilityOf(cityNameInTable));
        return cityNameInTable.getText();
    }

    public void deleteCity() {
        explicitWait.until(ExpectedConditions.visibilityOf(deleteIcon));
        deleteIcon.click();
        explicitWait.until(ExpectedConditions.visibilityOf(deleteButton));
        deleteButton.click();
        explicitWait.until(ExpectedConditions.textToBePresentInElement(deleteMessage, "Deleted successfully"));
    }

    public void flowMethod(String cityName) {
        createNewCity(cityName);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        searchCities(cityName);
        editCityName(cityName);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        searchCities(editedCityName(cityName));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
