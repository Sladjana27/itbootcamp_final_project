package tests;

import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.AdminCitiesPage;
import pages.HomePage;
import pages.LoginPage;

public class AdminCitiesTests extends BaseTest {
    private HomePage homePage;
    private LoginPage loginPage;
    private AdminCitiesPage adminCitiesPage;
    private String cityFakerName;

    @BeforeClass
    @Override
    public void beforeClass() {
        super.beforeClass();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        adminCitiesPage = new AdminCitiesPage(driver);
        cityFakerName = faker.address().cityName();
    }

    @BeforeMethod
    @Override
    public void beforeMethod() {
        super.beforeMethod();
        homePage.getLoginButtonHomePage().click();
        loginPage.fillLogin("admin@admin.com", "12345");
        homePage.clickAdminCities();
    }

    @AfterMethod
    public void afterMethod() {
        homePage.logout();
    }

    @Test
    public void visitAdminCitiesPage() {
        explicitWait.until(ExpectedConditions.urlContains("/admin/cities"));
        String actualURL = driver.getCurrentUrl();

        softAssert.assertTrue(actualURL.contains("/admin/cities"), "TestURL");
        softAssert.assertTrue(homePage.isLogoutPresent(), "TestVisibilityofLogoutButton");
        softAssert.assertAll();
    }

    @Test
    public void createNewCity() {
        adminCitiesPage.createNewCity(cityFakerName);
        String actualMessage = homePage.getMessage(adminCitiesPage.getMessage());
        Assert.assertTrue(actualMessage.contains("Saved successfully"));
    }

    @Test
    public void editCityName() {
        adminCitiesPage.createNewCity(cityFakerName);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        adminCitiesPage.searchCities(cityFakerName);
        adminCitiesPage.editCityName(cityFakerName);
        String actualMessage = homePage.getMessage(adminCitiesPage.getMessage());

        Assert.assertTrue(actualMessage.contains("Saved successfully"));
    }

    @Test
    public void searchCityName() {
        adminCitiesPage.flowMethod(cityFakerName);

        String expectedCityName = adminCitiesPage.editedCityName(cityFakerName);
        String actualCityName = adminCitiesPage.getCityNameInTable();

        Assert.assertEquals(actualCityName, expectedCityName);
    }

    @Test
    public void deleteCity() {
        adminCitiesPage.flowMethod(cityFakerName);

        String expectedResult = adminCitiesPage.editedCityName(cityFakerName);
        String actualResult = adminCitiesPage.getCityNameInTable();

        adminCitiesPage.deleteCity();
        String actualMessage = homePage.getMessage(adminCitiesPage.getMessage());

        softAssert.assertEquals(actualResult, expectedResult, "TestSearch");
        softAssert.assertTrue(actualMessage.contains("Deleted successfully"), "TestMessage");
        softAssert.assertAll();
    }
}
