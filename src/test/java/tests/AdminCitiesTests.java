package tests;

import org.openqa.selenium.bidi.log.Log;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.AdminCitiesPage;
import pages.HomePage;
import pages.LoginPage;

public class AdminCitiesTests extends BaseTest{
    private HomePage homePage;
    private LoginPage loginPage;
    private AdminCitiesPage adminCitiesPage;
    private String cityFakerName;

    private String editedCityName;

    @BeforeClass
    @Override
    public void beforeClass() {
        super.beforeClass();
        homePage = new HomePage(driver);
        loginPage =  new LoginPage(driver);
        adminCitiesPage = new AdminCitiesPage(driver);
        cityFakerName = faker.address().cityName();
        editedCityName = cityFakerName + " " + cityFakerName + " - edited";
    }

    @BeforeMethod
    @Override
    public void beforeMethod() {
        super.beforeMethod();
        homePage.getLoginButtonHomePage().click();
        loginPage.login("admin@admin.com", "12345");
        homePage.clickAdminCities();
    }

    @AfterMethod
    public void afterMethod() {
        homePage.logout();
    }

    @Test (priority = 1)
    public void visitAdminCitiesPage() {
        String actualURL = driver.getCurrentUrl();
        softAssert.assertTrue(actualURL.contains("/admin/cities"), "TestURL");
        softAssert.assertTrue(homePage.isLogoutPresent(), "TestVisibilityofLogoutButton");
        softAssert.assertAll();
    }

    @Test (priority = 2)
    public void createNewCity() {
        adminCitiesPage.createNewCity(cityFakerName);
        String actualMessage = homePage.getMessage(adminCitiesPage.getMessage());
        Assert.assertTrue(actualMessage.contains("Saved successfully"));
    }

    @Test (priority = 3)
    public void editCityName() {
        adminCitiesPage.searchCities(cityFakerName);
        adminCitiesPage.editCityName(" " + cityFakerName + " - edited");
        String actualMessage = homePage.getMessage(adminCitiesPage.getMessage());
        Assert.assertTrue(actualMessage.contains("Saved successfully"));
    }
    @Test (priority = 4)
    public void searchCityName() {
        adminCitiesPage.searchCities(editedCityName);

        String expectedCityName = editedCityName;
        String actualCityName = adminCitiesPage.getCityNameInTable();

        Assert.assertEquals(actualCityName, expectedCityName);
    }

    @Test (priority = 5)
    public void deleteCity() {
        adminCitiesPage.searchCities(editedCityName);
        softAssert.assertEquals(adminCitiesPage.getCityNameInTable(), editedCityName, "TestSearch");

        adminCitiesPage.deleteCity();
        String actualMessage = homePage.getMessage(adminCitiesPage.getMessage());

        softAssert.assertTrue(actualMessage.contains("Deleted successfully"), "TestMessage");
        softAssert.assertAll();
    }
}
