package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthRoutesTests extends BaseTest {
    @Test
    public void forbidsVisitsToHomeURLIfNotAuthenticated() {
        homePage.navigate("https://vue-demo.daniel-avellaneda.com/home");

        String actualURL = driver.getCurrentUrl();

        Assert.assertTrue(actualURL.contains("/login"));
    }

    @Test
    public void forbidsVisitsToProfileURLIfNotAuthenticated() {
        homePage.navigate("https://vue-demo.daniel-avellaneda.com/profile");

        String actualURL = driver.getCurrentUrl();

        Assert.assertTrue(actualURL.contains("/login"));
    }

    @Test
    public void forbidsVisitsToAdminCitiesURLIfNotAuthenticated() {
        homePage.navigate("https://vue-demo.daniel-avellaneda.com/admin/cities");

        String actualURL = driver.getCurrentUrl();

        Assert.assertTrue(actualURL.contains("/login"));
    }

    @Test
    public void forbidsVisitsToAdminUsersURLIfNotAuthenticated() {
        homePage.navigate("https://vue-demo.daniel-avellaneda.com/admin/users");

        String actualURL = driver.getCurrentUrl();

        Assert.assertTrue(actualURL.contains("/login"));
    }
}
