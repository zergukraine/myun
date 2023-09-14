package PajeObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class SignInPage extends CommonMethods{

    WebDriver driver;

    public SignInPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String utilisateur = "user.test.cabinet+myunisoft_candidat@myunisoft.fr";
    public String pass = "G00D_LuCk";
    public String passUpdated = "G00D_LuCk1";
    public String url = "https://app.myunisoft.fr";

    By mailLocator = By.id("mail");
    By passLocator = By.id("password");

    public void enterCredentials(String username, String pass) {
        waitForElementAppear(mailLocator);

        driver.findElement(mailLocator).click();
        driver.findElement(mailLocator).sendKeys(username);

        driver.findElement(passLocator).click();
        driver.findElement(passLocator).sendKeys(pass);
    }

    By submitLocator = By.cssSelector("button[type='submit']");

    public void clickSubmit(){
        driver.findElement(submitLocator).click();
    }

    By cguLocator = By.cssSelector("input[value='cgu']");
    By allowDataLocator = By.cssSelector("input[value='allowData']");

    public void setCheckboxes(){
        WebElement cguCheckbox = driver.findElement(cguLocator);
        if (!cguCheckbox.isSelected()) {
            cguCheckbox.click();
        }

        WebElement allowDataCheckbox = driver.findElement(allowDataLocator);
        if (!allowDataCheckbox.isSelected()) {
            allowDataCheckbox.click();
        }
    }


}
