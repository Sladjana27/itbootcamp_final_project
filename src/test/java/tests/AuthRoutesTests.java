package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthRoutesTests extends BaseTest {
    @Test
    public void forbidsVisitsToHomeURLIfNotAuthenticated() {
        driver.navigate().to("https://vue-demo.daniel-avellaneda.com/home");
        String actualURL = driver.getCurrentUrl();
        Assert.assertTrue(actualURL.contains("/login"));
    }

    @Test
    public void forbidsVisitsToProfileURLIfNotAuthenticated() {
        driver.navigate().to("https://vue-demo.daniel-avellaneda.com/profile");
        String actualURL = driver.getCurrentUrl();
        Assert.assertTrue(actualURL.contains("/login"));
    }

    @Test
    public void forbidsVisitsToAdminCitiesURLIfNotAuthenticated() {
        driver.navigate().to("https://vue-demo.daniel-avellaneda.com/admin/cities");
        String actualURL = driver.getCurrentUrl();
        Assert.assertTrue(actualURL.contains("/login"));
    }

    @Test
    public void forbidsVisitsToAdminUsersURLIfNotAuthenticated() {
        driver.navigate().to("https://vue-demo.daniel-avellaneda.com/admin/users");
        String actualURL = driver.getCurrentUrl();
        Assert.assertTrue(actualURL.contains("/login"));
    }
}
