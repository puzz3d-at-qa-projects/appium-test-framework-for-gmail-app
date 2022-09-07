package helpers;

import driver.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class Navigator {

    private final AndroidDriver driver;

    public Navigator() {
        this.driver = DriverManager.getDriver();
    }

    public void toHomeScreen() {
        driver.pressKey(new KeyEvent(AndroidKey.HOME));
    }

    public void toRecentApps() {
        driver.pressKey(new KeyEvent(AndroidKey.APP_SWITCH));
    }
}
