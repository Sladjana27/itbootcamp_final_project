package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;

public class EditProfileTest extends BaseTest {
    private ProfilePage profilePage;
    private LoginPage loginPage;
    private String phoneNumber;
    private String twitter;
    private String gitHub;
    private String country;

    @BeforeClass
    @Override
    public void beforeClass() {
        super.beforeClass();
        profilePage = new ProfilePage(driver, explicitWait);
        loginPage = new LoginPage(driver, explicitWait);
        phoneNumber = faker.phoneNumber().phoneNumber();
        twitter = "https://" + faker.internet().url();
        gitHub = "https://" + faker.internet().url();
        country = faker.country().name();
    }

    @BeforeMethod
    @Override
    public void beforeMethod() {
        super.beforeMethod();
        homePage.getLoginButtonHomePage().click();
        loginPage.fillLogin("admin@admin.com", "12345");
        homePage.clickMyProfile();
    }

    @Test
    public void editsProfile() {
        profilePage.fillMyProfile(phoneNumber, country, twitter, gitHub, "Chicago");

        String actualPhoneNumber = profilePage.checkAtributte(profilePage.getPhoneField(), "value");
        String actualCountry = profilePage.checkAtributte(profilePage.getCountryField(), "value");
        String actualTwitter = profilePage.checkAtributte(profilePage.getTwitterField(), "value");
        String actualGitHub = profilePage.checkAtributte(profilePage.getGitHubField(), "value");
        String actualMessage = homePage.getMessage(profilePage.getMessage());

        softAssert.assertEquals(actualPhoneNumber, phoneNumber, "TestPhoneNumberValue");
        softAssert.assertEquals(actualCountry, country, "TestCountryValue");
        softAssert.assertEquals(actualTwitter, twitter, "TestTwitterValue");
        softAssert.assertEquals(actualGitHub, gitHub, "TestGitHubValue");
        softAssert.assertTrue(actualMessage.contains("Profile saved successfuly"), "TestMessage");
        softAssert.assertAll();
    }

    @AfterMethod
    public void afterMethod() {
        homePage.logout();
    }
}
