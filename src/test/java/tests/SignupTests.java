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

    @BeforeClass
    @Override
    public void beforeClass() {
        super.beforeClass();
        signupPage = new SignupPage(driver);
    }

    @BeforeMethod
    @Override
    public void beforeMethod() {
        super.beforeMethod();
        homePage.clickSignup();
    }

    @Test
    public void visitSignUpPage() {
        signupPage.waitURL("https://vue-demo.daniel-avellaneda.com/signup");
        String actualURL = driver.getCurrentUrl();

        Assert.assertTrue(actualURL.contains("/signup"));
    }

    @Test
    public void checkInputType() {
        explicitWait.until(ExpectedConditions.visibilityOf(signupPage.getEmail()));
        String actualEmail = signupPage.checkAttribute(signupPage.getEmail(), "type");
        String expectedEmail = "email";
        String actualPassword = signupPage.checkAttribute(signupPage.getPassword(), "type");
        String expectedPassword = "password";
        String actualConfirmPassword = signupPage.checkAttribute(signupPage.getConfirmPassword(), "type");

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
        signupPage.fillSignupFields("Sladjana Vreco", faker.internet().emailAddress(), "123456", "123456");

        String expectedMessage = "IMPORTANT: Verify your account";
        String actualMessage = homePage.getMessage(homePage.getSignupMessage());

        Assert.assertEquals(actualMessage, expectedMessage);
        homePage.closeMessage();
        homePage.logout();
    }

    @Test
    public void signupWithBlankFields() {
        signupPage.clickSignMeUp();

        String expectedMessage = "This field is required";
        String actualMessage = homePage.getMessage(signupPage.getMessageForBlankField());

        Assert.assertEquals(actualMessage, expectedMessage);
    }
}
