package tests;

import PajeObject.CommonMethods;
import PajeObject.HomePage;
import PajeObject.SignInPage;
import Components.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;


public class verifyTests extends BaseTest {

    @Test(description = "Success sign in")
    public void checkSignIn() throws InterruptedException {
        SignInPage signInPage = new SignInPage(driver);

        driver.get(signInPage.url);
        signInPage.enterCredentials(signInPage.utilisateur,signInPage.pass);
        signInPage.setCheckboxes();
        signInPage.clickSubmit();
        Thread.sleep(5000);

        // Verify elements
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.avatarIsDisplayedOnHomePage());
        Assert.assertTrue(homePage.weekDashboardIsDisplayedOnHomePage());
        Assert.assertTrue(homePage.leftMenuItemsAreDisplayedOnHomePage());

        // Extract the token from cookies
        CommonMethods commonMethods = new CommonMethods(driver);
        commonMethods.getAccessToken();
    }

    @Test(description = "Change password")
    public void changeAndResetPassword() throws InterruptedException {
        CommonMethods commonMethods = new CommonMethods(driver);
        String myuAccessToken = commonMethods.readTokenFromFile();
        Response response;

        // Check if the token is null or empty
        if (myuAccessToken == null || myuAccessToken.isEmpty()) {
            checkSignIn();
        }
        SignInPage signInPage = new SignInPage(driver);

        // Update the password
        response = commonMethods.updatePassword(signInPage.pass, signInPage.passUpdated);
        // Verify response for success
        Assert.assertEquals(response.getStatusCode(),
                204, "Password change failed");

        // Reset the password back
        response = commonMethods.updatePassword(signInPage.passUpdated, signInPage.pass);
        // Verify response for success
        Assert.assertEquals(response.getStatusCode(),
                204, "Password reset failed");
    }



}
