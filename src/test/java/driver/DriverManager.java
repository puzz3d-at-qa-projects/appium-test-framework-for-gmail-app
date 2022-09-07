package driver;

import conf.AddressConfigurator;
import conf.CapabilitiesConfigurator;
import conf.ConfigReader;
import conf.EnvType;
import io.appium.java_client.android.AndroidDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Optional;

import static java.lang.String.format;

public class DriverManager {

    private static final Logger LOGGER = LogManager.getLogger();
    public static final EnvType ENV_TYPE = EnvType.valueOf(ConfigReader.get().env().toUpperCase());
    private static AndroidDriver driver;

    private DriverManager() {
    }


    public static AndroidDriver getDriver() {
        if (driver == null) {
            driver = createDriver();
        }
        return driver;
    }

    private static AndroidDriver createDriver() {
        switch (ENV_TYPE) {
            case LOCAL ->
                    driver = new AndroidDriver(AddressConfigurator.getAppiumDriverLocalService(ConfigReader.get().appiumPort()),
                            CapabilitiesConfigurator.getLocalCapabilities());
            default -> throw new IllegalArgumentException(format("Unexpected environment value: %s", ENV_TYPE));
        }
        LOGGER.info("Driver created");
        LOGGER.info("Environment type: {}", ENV_TYPE);
        return driver;
    }

    public static void closeDriver() {
        Optional.ofNullable(getDriver()).ifPresent(driverInstance -> {
            driverInstance.quit();
            driver = null;
            LOGGER.info("Driver closed");
        });
    }

    public static void closeAppium() {
        AddressConfigurator.stopService();
    }

    public static void closeEmulator() {
        try {
            Runtime.getRuntime().exec(format("C:\\Users\\puzz\\AppData\\Local\\Android\\sdk\\platform-tools\\adb.exe -s %s emu kill", ConfigReader.get().udid()));
            LOGGER.info("AVD closed");
        } catch (IOException e) {
            LOGGER.info("AVD was not closed, message: {}", e.getMessage());
        }
    }
}
