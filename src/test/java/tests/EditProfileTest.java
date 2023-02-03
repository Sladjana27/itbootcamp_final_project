package tests;

import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;

public class EditProfileTest extends BaseTest {
    private ProfilePage profilePage;
    private HomePage homePage;
    private LoginPage loginPage;
    private String phoneNumber;
    private String twitter;
    private String gitHub;
    private String country;

    @BeforeClass
    @Override
    public void beforeClass() {
        super.beforeClass();
        profilePage = new ProfilePage(driver);
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
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

        String actualPhoneNumber = profilePage.getValue(profilePage.getPhoneField());
        String actualCountry = profilePage.getValue(profilePage.getCountryField());
        String actualTwitter = profilePage.getValue(profilePage.getTwitterField());
        String actualGitHub = profilePage.getValue(profilePage.getGitHubField());
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