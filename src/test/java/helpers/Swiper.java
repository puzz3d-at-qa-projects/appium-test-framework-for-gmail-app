package helpers;

import driver.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.List;

public class Swiper {

    private final AndroidDriver driver;

    enum To {APP_MENU, CLOSE_APP;}
    public Swiper() {
        this.driver = DriverManager.getDriver();
    }

    public void swipeToAppMenu() {
        swipe(To.APP_MENU);
    }

    public void swipeToCloseApp() {
        swipe(To.CLOSE_APP);
    }

    private void swipe(To action) {
        final int MOVE_TIME = 400;
        final int SLEEP_TIME = 200;
        int edgeBorder = 10; // better avoid edges

        Dimension dims = driver.manage().window().getSize(); // init screen variables
        Point start = null, end = null;
        switch (action) {
            case APP_MENU -> {
                start = new Point(dims.width / 2, dims.height - edgeBorder); // center of footer
                end = new Point(dims.width / 2, dims.height / 2); // center of screen
            }
            case CLOSE_APP -> {
                start = new Point(dims.width / 2, dims.height / 2); // center of screen
                end = new Point(dims.width / 2, edgeBorder); // top of screen
            }
        }
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), start.x, start.y));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(MOVE_TIME),
                PointerInput.Origin.viewport(), end.x, end.y));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(swipe));

        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
