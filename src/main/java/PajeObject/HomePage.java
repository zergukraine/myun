package PajeObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends CommonMethods {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    By itemsLocator = By.cssSelector("div[class*='MuiButtonBase-root MuiListItem-root']");
    By weekDashboardLocator = By.id("WeekDashboard");
    By avatarLocator = By.cssSelector(".MuiAvatar-root");

    public boolean avatarIsDisplayedOnHomePage() {
        if (driver.findElement(avatarLocator).isDisplayed())
            return true;
        else {
            System.out.println(" Element is not displayed");
            return false;
        }
    }

    public boolean weekDashboardIsDisplayedOnHomePage() {
        if (driver.findElement(weekDashboardLocator).isDisplayed())
            return true;
        else {
            System.out.println(" Element is not displayed");
            return false;
        }
    }

    public boolean leftMenuItemsAreDisplayedOnHomePage() {
        List<WebElement> webElementList = driver.findElements(itemsLocator);
        for (WebElement element : webElementList) {
            if (!element.isDisplayed()) {
                System.out.println(" Element is not displayed");
                return false;
            }
            break;
        }
        return true;
    }


}
