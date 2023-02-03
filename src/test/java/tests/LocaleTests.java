package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.Languages;

public class LocaleTests extends BaseTest {
    private HomePage homePage;

    @BeforeClass
    @Override
    public void beforeClass() {
        super.beforeClass();
        homePage = new HomePage(driver);
    }

    @Test
    public void setLocaleToES() {
        homePage.setLanguage(Languages.ES);
        String actualMessage = homePage.getHeaderText();

        Assert.assertTrue(actualMessage.contains("Página de aterrizaje"));
    }

    @Test
    public void setLocaleToEN() {
        homePage.setLanguage(Languages.EN);
        String actualMessage = homePage.getHeaderText();

        Assert.assertTrue(actualMessage.contains("Landing"));
    }

    @Test
    public void setLocaleToFR() {
        homePage.setLanguage(Languages.FR);
        String actualMessage = homePage.getHeaderText();

        Assert.assertTrue(actualMessage.contains("Page d'atterrissage"));
    }
}
