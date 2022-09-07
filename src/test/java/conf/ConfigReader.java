package conf;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    public static final Properties properties = new Properties();
    private static ConfigReader instance;
    private static final Logger LOGGER = LogManager.getLogger();

    private ConfigReader() {
    }

    public static ConfigReader get() {
        if (instance == null) {
            instance = new ConfigReader();
            try {
                properties.load(new FileInputStream("src/test/resources/test.properties"));
                LOGGER.info("Properties were loaded");
            } catch (IOException e) {
                LOGGER.error("Properties were not loaded");
            }
        }
        return instance;
    }

    public String env() {

        return properties.getProperty("env.type");
    }

    public String platformName() {
        return properties.getProperty("platform.name");
    }

    public String platformVersion() {
        return properties.getProperty("platform.version");
    }

    public String automationName() {
        return properties.getProperty("automation.name");
    }

    public String localDeviceName() {
        return properties.getProperty("local.device.name");
    }

    public String udid() {
        return properties.getProperty("udid");
    }

    public String appiumAddress() {
        return properties.getProperty("appium.address");
    }

    public int appiumPort() {
        return Integer.parseInt(properties.getProperty("appium.port"));
    }

    public String gmailAccEmail() {
        return properties.getProperty("gmail.acc.email");
    }

    public String gmailAccPassword() {
        return properties.getProperty("gmail.acc.password");
    }
}
