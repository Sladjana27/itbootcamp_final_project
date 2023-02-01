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

        softAssert.assertEquals(actualEmail, expectedEmail);
        softAssert.assertEquals(actualPassword, expectedPassword);
        softAssert.assertEquals(actualConfirmPassword, expectedPassword);
        softAssert.assertAll();
    }
}
