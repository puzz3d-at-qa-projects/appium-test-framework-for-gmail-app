package pom;

import driver.DriverManager;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public abstract class NativeBaseApp {

    protected static final Logger LOGGER = LogManager.getLogger();

    public NativeBaseApp() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getDriver(), Duration.ofSeconds(50)), this);
    }
}
