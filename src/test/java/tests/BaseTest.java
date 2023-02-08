package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import pages.HomePage;

import java.time.Duration;

public abstract class BaseTest {

    protected WebDriver driver;

    protected WebDriverWait explicitWait;
    protected final String BASEURL = "https://vue-demo.daniel-avellaneda.com";
    protected SoftAssert softAssert;
    protected Faker faker;
    protected HomePage homePage;


    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        softAssert = new SoftAssert();
        faker = new Faker();
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get(BASEURL);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
