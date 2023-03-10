package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
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
    private LoginPage loginPage;

    @BeforeClass
    @Override
    public void beforeClass() {
        super.beforeClass();
        loginPage = new LoginPage(driver);
    }

    @BeforeMethod
    @Override
    public void beforeMethod() {
        super.beforeMethod();
        homePage.getLoginButtonHomePage().click();
        explicitWait.until(ExpectedConditions.visibilityOf(loginPage.getEmailField()));
    }

    @Test
    public void visitLoginPage() {
        String actualURL = driver.getCurrentUrl();
        loginPage.waitURL("/login");

        Assert.assertTrue(actualURL.contains("/login"));
    }

    @Test
    public void checkInputTypes() {
        String actualEmail = loginPage.checkAttribute(loginPage.getEmailField(), "type");
        String expectedEmail = "email";
        String actualPassword = loginPage.checkAttribute(loginPage.getPasswordField(), "type");
        String expectedPassword = "password";

        softAssert.assertEquals(actualEmail, expectedEmail, "TestEmail");
        softAssert.assertEquals(actualPassword, expectedPassword, "TestPassword");
        softAssert.assertAll();
    }

    @Test
    public void loginWhenUserNotExists() {
        loginPage.fillLogin(faker.internet().emailAddress(), faker.internet().password());

        explicitWait.until(ExpectedConditions.textToBePresentInElement(loginPage.getMessage(),"User does not exists"));
        String actualMessage = loginPage.getMessage(loginPage.getMessage());
        String expectedMessage = "User does not exists";
        String actualURL = driver.getCurrentUrl();

        softAssert.assertEquals(actualMessage, expectedMessage, "TestMessage");
        softAssert.assertTrue(actualURL.contains("/login"), "TestURL");
        softAssert.assertAll();
    }

    @Test
    public void loginWithInvalidPassword() {
        loginPage.fillLogin("admin@admin.com", faker.internet().password());

        explicitWait.until(ExpectedConditions.textToBePresentInElement(loginPage.getMessage(),"Wrong password"));
        String actualMessage = loginPage.getMessage(loginPage.getMessage());
        String expectedMessage = "Wrong password";
        String actualURL = driver.getCurrentUrl();

        softAssert.assertEquals(actualMessage, expectedMessage, "TestMessage");
        softAssert.assertTrue(actualURL.contains("/login"), "TestURL");
        softAssert.assertAll();
    }

    @Test
    public void loginWithValidInf() {
        loginPage.fillLogin("admin@admin.com", "12345");
        loginPage.waitURL("/home");

        String actualURL = driver.getCurrentUrl();

        Assert.assertTrue(actualURL.contains("/home"));
        homePage.logout();
    }

    @Test
    public void logout() {
        loginPage.fillLogin("admin@admin.com", "12345");
        softAssert.assertTrue(homePage.isLogoutPresent(), "TestLogoutPresent");

        homePage.logout();
        softAssert.assertTrue(driver.getCurrentUrl().contains("/login"), "TestURL");

        driver.get("https://vue-demo.daniel-avellaneda.com/home");
        String actualURL = driver.getCurrentUrl();

        softAssert.assertTrue(actualURL.contains("/login"), "TestHomeRoute");
        softAssert.assertAll();
    }

    @Test
    public void forgotPassword() {
        loginPage.clickForgotPassword();

        String actualURL = driver.getCurrentUrl();

        Assert.assertTrue(actualURL.contains("/forgot"));
    }
}
