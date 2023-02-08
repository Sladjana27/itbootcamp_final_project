package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthRoutesTests extends BaseTest {

    @Test
    public void forbidsVisitsToHomeURLIfNotAuthenticated() {
        homePage.navigate("/home");

        String actualURL = driver.getCurrentUrl();

        Assert.assertTrue(actualURL.contains("/login"));
    }

    @Test
    public void forbidsVisitsToProfileURLIfNotAuthenticated() {
        homePage.navigate("/profile");

        String actualURL = driver.getCurrentUrl();

        Assert.assertTrue(actualURL.contains("/login"));
    }

    @Test
    public void forbidsVisitsToAdminCitiesURLIfNotAuthenticated() {
        homePage.navigate("/admin/cities");

        String actualURL = driver.getCurrentUrl();

        Assert.assertTrue(actualURL.contains("/login"));
    }

    @Test
    public void forbidsVisitsToAdminUsersURLIfNotAuthenticated() {
        homePage.navigate("/admin/users");

        String actualURL = driver.getCurrentUrl();

        Assert.assertTrue(actualURL.contains("/login"));
    }
}
