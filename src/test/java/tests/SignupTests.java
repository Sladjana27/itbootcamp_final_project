package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignupPage;

public class SignupTests extends BaseTest {
    private SignupPage signupPage;
    private HomePage homePage;

    @BeforeClass
    @Override
    public void beforeClass() {
        super.beforeClass();
        signupPage = new SignupPage(driver);
        homePage = new HomePage(driver);
    }

    @BeforeMethod
    @Override
    public void beforeMethod() {
        super.beforeMethod();
        homePage.clickSignup();
    }

    @Test
    public void visitSignUpPage() {
        String actualURL = driver.getCurrentUrl();
        Assert.assertTrue(actualURL.contains("/signup"));
    }

    @Test
    public void checkInputType() {
        explicitWait.until(ExpectedConditions.visibilityOf(signupPage.getEmail()));
        String actualEmail = signupPage.checkType(signupPage.getEmail());
        String expectedEmail = "email";

        String actualPassword = signupPage.checkType(signupPage.getPassword());
        String expectedPassword = "password";

        String actualConfirmPassword = signupPage.checkType(signupPage.getConfirmPassword());

        softAssert.assertEquals(actualEmail, expectedEmail, "TestTypeEmail");
        softAssert.assertEquals(actualPassword, expectedPassword, "TestTypePassword");
        softAssert.assertEquals(actualConfirmPassword, expectedPassword, "TestTypeConfirmPassword");
        softAssert.assertAll();
    }

    @Test
    public void signupWithExistingUser() {
        signupPage.fillSignupFields("Test Test", "admin@admin.com", "123654", "123654");
        String expectedMessage = "E-mail already exists";
        String actualMessage = signupPage.getMessage(signupPage.getMessage());

        softAssert.assertEquals(actualMessage, expectedMessage, "TestMessage");
        softAssert.assertTrue(driver.getCurrentUrl().contains("/signup"), "TestURL");
        softAssert.assertAll();
    }

    @Test
    public void signupWithValidInformation() {
        signupPage.fillSignupFields("Sladjana Vreco", "sladjana11@gmail.com", "123456", "123456");
        String expectedMessage = "IMPORTANT: Verify your account";
        String actualMessage = homePage.getMessage(homePage.getSignupMessage());

        Assert.assertEquals(actualMessage, expectedMessage);
        homePage.closeMessage();
        homePage.logout();
    }
}
