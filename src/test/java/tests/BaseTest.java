package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public abstract class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait explicitWait;
    protected String baseURL = "https://vue-demo.daniel-avellaneda.com";

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Lenovo\\Desktop\\ITBootamp\\Chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get(baseURL);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
