package pom;

import helpers.Navigator;
import helpers.Swiper;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.HowToUseLocators;
import org.openqa.selenium.WebElement;

import static io.appium.java_client.pagefactory.LocatorGroupStrategy.ALL_POSSIBLE;

public class GmailNativeApp extends NativeBaseApp {

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE, iOSXCUITAutomation = ALL_POSSIBLE)
    @AndroidFindBy(id = "com.google.android.gm:id/welcome_tour_got_it")
    // id, resource-id com.google.android.gm:id/welcome_tour_got_it
    @AndroidFindBy(id = "com.google.android.gm:id/illustration")
//    @AndroidFindBy(id = "com.google.android.gm:id/welcome_tour_skip")

    WebElement gotItButtonOrEmptyClick;

    @HowToUseLocators(androidAutomation = ALL_POSSIBLE, iOSXCUITAutomation = ALL_POSSIBLE)
    @AndroidFindBy(id = "com.google.android.gm:id/welcome_tour_skip")
    @AndroidFindBy(id = "com.google.android.gm:id/illustration")
    WebElement skipButtonOrEmptyClick;

    @AndroidFindBy(id = "com.google.android.gm:id/setup_addresses_add_another")
    WebElement addAnEmail;

    @AndroidFindBy(id = "com.google.android.gm:id/account_setup_item")
    WebElement googleEmail;

    @AndroidFindBy(id = "com.google.android.gms:id/sud_items_switch")
    WebElement backUpSwitch;

    @AndroidFindBy(className = "android.widget.Button")
    WebElement acceptButton;

    @AndroidFindBy(className = "android.widget.ScrollView")
    WebElement scrollView;

    public void skipSuggestionsIfPresent() {
        skipButtonOrEmptyClick.click();
        LOGGER.debug("Skipped suggestions");
    }

    public void selectGoogleEmail() {
        addAnEmail.click();
        googleEmail.click();
    }

    public void acceptSettings() {
        backUpSwitch.click();
        acceptButton.click();
        scrollView.click(); // just for waiting screen with added address
    }

    public void removeRecentAppFromMemory() {
        new Navigator().toRecentApps();
        new Swiper().swipeToCloseApp();
    }
}
