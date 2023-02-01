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

    private HomePage homePage;
    private LoginPage loginPage;
    protected Faker faker;


    @BeforeClass
    @Override
    public void beforeClass() {
        super.beforeClass();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        faker = new Faker();
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
        loginPage.waiter("/login");
        Assert.assertTrue(actualURL.contains("/login"));
    }

    @Test
    public void checkInputTypes() {
        String actualEmail = loginPage.checkType(loginPage.getEmailField());
        String expectedEmail = "email";

        String actualPassword = loginPage.checkType(loginPage.getPasswordField());
        String expectedPassword = "password";
        softAssert.assertEquals(actualEmail, expectedEmail, "TestEmail");
        softAssert.assertEquals(actualPassword, expectedPassword, "TestPassword");
        softAssert.assertAll();
    }

    @Test
    public void loginWhenUserNotExists() {
        loginPage.login(faker.internet().emailAddress(), faker.internet().password());

        explicitWait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[4]/div/div/div/div/div[1]/ul/li"), "User does not exists"));
        String actualMessage = loginPage.getMessage();
        String expectedMessage = "User does not exists";
        String actualURL = driver.getCurrentUrl();

        softAssert.assertEquals(actualMessage, expectedMessage, "TestMessage");
        softAssert.assertTrue(actualURL.contains("/login"), "TestURL");
        softAssert.assertAll();
    }

    @Test
    public void loginWithInvalidPassword() {
        loginPage.login("admin@admin.com", faker.internet().password());

        explicitWait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@id=\"app\"]/div[1]/main/div/div[2]/div/div/div[4]/div/div/div/div/div[1]/ul/li"), "Wrong password"));
        String actualMessage = loginPage.getMessage();
        String expectedMessage = "Wrong password";
        String actualURL = driver.getCurrentUrl();

        softAssert.assertEquals(actualMessage, expectedMessage, "TestMessage");
        softAssert.assertTrue(actualURL.contains("/login"));
        softAssert.assertAll();
    }

    @Test
    public void loginWithValidInf() {
        loginPage.login("admin@admin.com", "12345");
        loginPage.waiter("/home");
        String actualURL = driver.getCurrentUrl();

        Assert.assertTrue(actualURL.contains("/home"));

        homePage.logout();
    }

    @Test
    public void logout() {
        loginPage.login("admin@admin.com", "12345");
        softAssert.assertTrue(homePage.isLogoutPresent(), "TestLogoutPresent");

        homePage.logout();
        softAssert.assertTrue(driver.getCurrentUrl().contains("/login"), "TestURL");

        driver.get("https://vue-demo.daniel-avellaneda.com/home");
        String actualURL = driver.getCurrentUrl();

        softAssert.assertTrue(actualURL.contains("/login"), "TestHomeRoute");
        softAssert.assertAll();
    }
}
