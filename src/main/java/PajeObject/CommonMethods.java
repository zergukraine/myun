package PajeObject;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.nio.file.*;
import java.io.IOException;
import java.time.Duration;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class CommonMethods {
    WebDriver driver;

    public CommonMethods(WebDriver driver)
    {
        this.driver = driver;
    }

    private static final String TOKEN_FILE = "token.txt";

    public void writeTokenToFile(String token) {
        try {
            Files.write(Paths.get(TOKEN_FILE), token.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readTokenFromFile() {
        try {
            return new String(Files.readAllBytes(Paths.get(TOKEN_FILE)));
        } catch (IOException e) {
            return null;
        }
    }

    public void getAccessToken() {
        String myuAccessToken = driver.manage().getCookieNamed("myu-access-token").getValue();
        writeTokenToFile(myuAccessToken);
    }


    public void waitForElementAppear(By findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public Response updatePassword(String oldPassword, String newPassword){
        SignInPage signInPage = new SignInPage(driver);
        RestAssured.baseURI = signInPage.url;
        String myuAccessToken = readTokenFromFile();

        // Change password
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + myuAccessToken)
                .body("{ \"old_password\": \"" + oldPassword +
                        "\", \"new_password\": \"" + newPassword + "\" }")
                .when()
                .put("/api/user/password");

        return response;
    }

}
