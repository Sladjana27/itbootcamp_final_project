package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import sun.security.ssl.HandshakeOutStream;

public class LoginTests extends BaseTest {

    private HomePage homePage;
    private LoginPage loginPage;
    private SoftAssert softAssert;
    protected Faker faker;


    @BeforeClass
    @Override
    public void beforeClass() {
        super.beforeClass();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        softAssert = new SoftAssert();
        faker = new Faker();
    }

    @BeforeMethod
    @Override
    public void beforeMethod() {
        super.beforeMethod();
        homePage.getLoginButtonHomePage().click();
    }

    @Test
    public void visitLoginPage() {
        String actualURL = driver.getCurrentUrl();
        Assert.assertTrue(actualURL.contains("/login"));
    }

    @Test
    public void checkInputTypes() {
        explicitWait.until(ExpectedConditions.visibilityOf(loginPage.getEmailField()));
        String actualEmail = loginPage.getEmailField().getAttribute("type");
        String expectedEmail = "email";

        String actualPassword = loginPage.getPasswordField().getAttribute("type");
        String expectedPassword = "password";
        softAssert.assertEquals(actualEmail, expectedEmail, "TestEmail");
        softAssert.assertEquals(actualPassword, expectedPassword, "TestPassword");
        softAssert.assertAll();
    }

    @Test
    public void loginWhenUserNotExists() {
        loginPage.login(faker.internet().emailAddress(), faker.internet().password());

        explicitWait.until(ExpectedConditions.textToBePresentInElement(loginPage.getMessage(), "User does not exists"));
        String actualMessage = loginPage.getMessage().getText();
        String expectedMessage = "User does not exists";
        String actualURL = driver.getCurrentUrl();

        softAssert.assertEquals(actualMessage, expectedMessage, "TestMessage");
        softAssert.assertTrue(actualURL.contains("/login"), "TestURL");
        softAssert.assertAll();
    }

    @Test
    public void loginWithInvalidPassword() {
        explicitWait.until(ExpectedConditions.visibilityOf(loginPage.getEmailField()));
        loginPage.login("admin@admin.com", faker.internet().password());

        explicitWait.until(ExpectedConditions.textToBePresentInElement(loginPage.getMessage(), "Wrong password"));
        String actualMessage = loginPage.getMessage().getText();
        String expectedMessage = "Wrong password";
        String actualURL = driver.getCurrentUrl();

        softAssert.assertEquals(actualMessage, expectedMessage, "TestMessage");
        softAssert.assertTrue(actualURL.contains("/login"));
        softAssert.assertAll();
    }
}
