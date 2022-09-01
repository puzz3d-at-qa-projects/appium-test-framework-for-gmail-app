import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GmailTest {

    private static final String APP = "https://github.com/cloudgrey-io/the-app/releases/download/v1.9.0/TheApp-v1.9.0.apk";
    private static final String APPIUM = "http://localhost:4723";
    AndroidDriver driver;
    //    RemoteWebDriver driver;
    static final Logger LOGGER = LogManager.getLogger();

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "12");
        caps.setCapability("deviceName", "Android Emulator");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("androidDeviceSerial", "0351811S471028E6");
//        caps.setCapability("browserName", "Chrome");
//        caps.setCapability("app", APP);
//        driver = new RemoteWebDriver(new URL(APPIUM), caps);
        driver = new AndroidDriver(new URL(APPIUM), caps);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    void test() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.pressKey(new KeyEvent(AndroidKey.HOME));
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Search")));
//        driver.pressKey(new KeyEvent(AndroidKey.HOME));
        swipeAction("appMenu");
        LOGGER.debug("Apps screen opened");
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Gmail")))
                .click();
        LOGGER.debug("Opening Gmail");
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.View")))
                .click(); // tap on "Got it"
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.google.android.gm:id/welcome_tour_skip")))
                    .click(); // tap on "Skip"
        } catch (TimeoutException ign) {
            System.out.println("No 'Skip' element this time :)");
        }

        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView")))
                .click(); // tap on "Add an email address"
        System.out.println("Before tap on Gmail");
//        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout")))
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.google.android.gm:id/account_setup_item")))
                .click(); // tap on "Google"
        System.out.println("After tap on Gmail");

        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.webkit.WebView/android.webkit.WebView")));

        driver.context(getWebContext(driver));

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input#identifierId")))
                .sendKeys("private.static.final.long@gmail.com"); // enter email
        System.out.println("Address typed in");
        driver.findElement(By.xpath("//span[text()='Next']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.name("password")))
                .sendKeys("_8pZgRS2wA"); // enter password
        driver.findElement(By.cssSelector("div#passwordNext")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#signinconsentNext")))
                .click();

        driver.context("NATIVE_APP");

        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.google.android.gms:id/sud_items_switch")))
                .click(); // tap on "Backup to Google Drive" switch
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.className("android.widget.Button")))
                .click(); // tap on "Backup to Google Drive" switch
        try {
            Thread.sleep(4000);
        } catch (Exception ign) {}

        driver.pressKey(new KeyEvent(AndroidKey.APP_SWITCH));
//        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.google.android.gm:id/illustration")))
//                .click();
        try {
            Thread.sleep(1000);
        } catch (Exception ign) {}

        swipeAction("swipeUpAway");
        driver.pressKey(new KeyEvent(AndroidKey.HOME));
        swipeAction("appMenu");
        LOGGER.debug("Apps screen opened");
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Gmail")))
                .click();
        WebElement searchInput = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.google.android.gm:id/open_search_bar_text_view")));
        searchInput.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Back")));
//        searchInput.sendKeys("Can't believe it! Finally I'm in!" + Keys.ENTER);
//        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.google.android.gm:id/empty_icon")));
        System.out.println("Passed!");

        driver.pressKey(new KeyEvent(AndroidKey.HOME));
        swipeAction("appMenu");
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Settings")))
                .click();
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[5]/android.widget.RelativeLayout/android.widget.TextView[1]")))
                .click();
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[6]/android.widget.LinearLayout[2]/android.widget.TextView[1]")))
                .click();
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.android.settings:id/button")))
                .click();
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("android:id/button1")))
                .click();
        driver.pressKey(new KeyEvent(AndroidKey.HOME));


        try {
            Thread.sleep(2000);
        } catch (Exception ign) {
        }


    }

    private void swipeAction(String target) {
        final int ANIMATION_TIME = 300; // Animation default time: Android: 300ms iOS: 200ms
        final int PRESS_TIME = 200;
        int edgeBorder = 20; // better avoid edges

        Dimension dims = driver.manage().window().getSize(); // init screen variables
        Point start = null, end = null;
        switch (target) {
            case "appMenu" -> {
                start = new Point(dims.width / 2, dims.height - edgeBorder); // center of footer
                end = new Point(dims.width / 2, dims.height / 2); // center of screen
            }
            case "swipeUpAway" -> {
                System.out.println("SwipeUpAway switch entered");
                start = new Point(dims.width / 2, dims.height / 2); // center of screen
                end = new Point(dims.width / 2, edgeBorder); // top of screen
            }
        }
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), start.x, start.y));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(700),
                PointerInput.Origin.viewport(), end.x, end.y));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(swipe));
    }


    private String getWebContext(AndroidDriver driver) {
        Set<String> contextNames = driver.getContextHandles();
        ArrayList<String> contexts = new ArrayList<>(driver.getContextHandles());
        for (String context : contexts) {
            if (!context.equals("NATIVE_APP")) {
                return context;
            }
        }
        return null;
    }
}
