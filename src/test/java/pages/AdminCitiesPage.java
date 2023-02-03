package pages;

import org.checkerframework.common.value.qual.MinLenFieldInvariant;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

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

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[3]/div/div/div/div/div[1]")
    private WebElement message;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[1]/div[1]/div[2]/div/div/div/div[3]/div")
    private WebElement searchMagnify;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div[1]/div[2]/table/tbody/tr/td[2]")
    private WebElement cityNameInTable;

    @FindBy(xpath = "//*[@id=\"delete\"]/span/i")
    private WebElement deleteIcon;

    @FindBy(xpath = "//*[@id=\"app\"]/div[5]/div/div/div[2]/button[2]/span")
    private WebElement deleteButton;
    private LoginPage loginPage;
    private HomePage homePage;

    public AdminCitiesPage(WebDriver driver) {
        super(driver);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

    public WebElement getMessage() {
        return message;
    }

    public void createNewCity(String nameCity) {
        explicitWait.until(ExpectedConditions.visibilityOf(newItemButton));
        newItemButton.click();
        nameField.sendKeys(nameCity);
        saveButton.click();
    }

    public void searchCities(String searchCity) {
        explicitWait.until(ExpectedConditions.visibilityOf(searchField));
        searchField.clear();
        searchField.sendKeys(searchCity);
        searchMagnify.click();
    }

    public void editCityName(String newName) {
        editButton.click();
        nameField.click();
        nameField.sendKeys(newName);
        saveButton.click();
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
    }
}
