package conf;

import org.openqa.selenium.remote.DesiredCapabilities;

import static io.appium.java_client.remote.AndroidMobileCapabilityType.AVD;
import static io.appium.java_client.remote.MobileCapabilityType.AUTOMATION_NAME;
import static io.appium.java_client.remote.MobileCapabilityType.UDID;

public class CapabilitiesConfigurator {

    private CapabilitiesConfigurator(){
    }

    public static DesiredCapabilities getLocalCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(UDID, ConfigReader.get().udid());
        capabilities.setCapability(AVD, ConfigReader.get().localDeviceName());
        capabilities.setCapability(AUTOMATION_NAME, ConfigReader.get().automationName());
        return capabilities;
    }
}
