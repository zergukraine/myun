package Steps;

import Components.BaseTest;
import PajeObject.CommonMethods;
import PajeObject.HomePage;
import PajeObject.SignInPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;


import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class Steps extends BaseTest {

    protected SignInPage signInPage;
    protected HomePage homePage;
    protected CommonMethods commonMethods;
    protected Response lastResponse;


    @Given("Navigate to the SignInPage")
    public void navigateToSignInPage() {
        signInPage = new SignInPage(driver);
        driver.get(signInPage.url);
    }

    @When("Enter credential on SignInPage")
    public void enterCredentialOnSignInPage() {
        signInPage = new SignInPage(driver);
        signInPage.enterCredentials(signInPage.utilisateur, signInPage.pass);
    }

    @When("^Enter username (.+) and password (.+) on SignInPage$")
    public void enterUserNameAndPasswordOnSignInPage(String username, String pass) {
        signInPage = new SignInPage(driver);
        signInPage.enterCredentials(username, pass);
    }

    @And("Set checkboxes on SignInPage")
    public void setCheckboxesOnSignInPage() {
        signInPage = new SignInPage(driver);
        signInPage.setCheckboxes();
    }

    @And("Click submit button on SignInPage")
    public void clickSubmitButtonOnSignInPage() throws InterruptedException {
        signInPage = new SignInPage(driver);
        signInPage.clickSubmit();
        Thread.sleep(5000);
    }

    @Given("Launch the app")
    public void launchTheApp() {
        initializeDriver();
    }

    @And("Close browser")
    public void closeBrowser() {
        driver.quit();
    }

    @Then("The user avatar is displayed on the home page")
    public void theUserAvatarIsDisplayedOnTheHomePage() {
        homePage = new HomePage(driver);
        assertTrue(homePage.avatarIsDisplayedOnHomePage());
    }

    @Then("The week dashboard is displayed on the home page")
    public void theWeekDashboardIsDisplayedOnTheHomePage() {
        homePage = new HomePage(driver);
        assertTrue(homePage.weekDashboardIsDisplayedOnHomePage());
    }

    @Then("The left menu items are displayed on the home page")
    public void theLeftMenuItemsAreDisplayedOnTheHomePage() {
        homePage = new HomePage(driver);
        assertTrue(homePage.leftMenuItemsAreDisplayedOnHomePage());
    }

    @And("Store the access token")
    public void theAccessTokenIsStoredForFurtherTests() {
        homePage = new HomePage(driver);
        homePage.getAccessToken();
    }

    @Given("The user has an access token")
    public void theUserHasAnAccessToken() {
        commonMethods = new CommonMethods(driver);
        String myuAccessToken = commonMethods.readTokenFromFile();
        assertNotNull(myuAccessToken, "No access token found!");
    }

    @When("^The user sends a request to change the password from old_password (.+) to new_password (.+)$")
    public void theUserSendsARequestToChangeThePassword(String oldPass, String newPass) {
        commonMethods = new CommonMethods(driver);

        // Update the password
        lastResponse = commonMethods.updatePassword(oldPass, newPass);
    }

    @Then("The request should be successful with a status code {int}")
    public void theRequestShouldBeSuccessfulWithAStatusCodeOf(int expectedStatusCode) {
        int actualStatusCode = lastResponse.getStatusCode();
        assertEquals(expectedStatusCode, actualStatusCode);
    }


}
