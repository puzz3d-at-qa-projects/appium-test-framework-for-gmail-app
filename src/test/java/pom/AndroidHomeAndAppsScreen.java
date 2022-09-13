package pom;

import helpers.Navigator;
import helpers.Swiper;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class AndroidHomeAndAppsScreen extends NativeBaseApp {

    @AndroidFindBy(accessibility = "Gmail")
    WebElement gmailIcon;
    @AndroidFindBy(accessibility = "Settings")
    WebElement settingsIcon;

    public void openGmail() {
        new Navigator().toHomeScreen();
        new Swiper().swipeToAppMenu();
        gmailIcon.click();
        LOGGER.debug("Opening Gmail");
    }

    public void openSettings() {
        new Navigator().toHomeScreen();
        new Swiper().swipeToAppMenu();
        settingsIcon.click();
        LOGGER.debug("Opening settings");
    }
}
