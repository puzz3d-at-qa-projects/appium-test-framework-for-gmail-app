package pom;

import conf.ConfigReader;
import driver.DriverManager;
import helpers.Navigator;
import helpers.Swiper;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class AndroidSettings extends NativeBaseApp {

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'accounts')]")
    WebElement accountsMenu;

    @AndroidFindBy(id = "com.android.settings:id/button")
    WebElement removeAccountButton;

    @AndroidFindBy(id = "android:id/button1")
    WebElement removeAccountConfirmButton;

    public void deleteRecentlyAddedAccount() {
        var swipe = new Swiper();
        swipe.swipeToCloseApp();
        swipe.swipeToCloseApp();
        accountsMenu.click();
        getGmailAccount().click();
        removeAccountButton.click();
        removeAccountConfirmButton.click();
        new Navigator().toHomeScreen();
        LOGGER.debug("Account removed");
    }

    private WebElement getGmailAccount() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.widget.TextView[@text='" + ConfigReader.get().gmailAccEmail() + "']")));
    }
}
