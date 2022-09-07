package helpers;

import driver.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class ContextSwitcher {

    private final AndroidDriver driver;
    protected static final Logger LOGGER = LogManager.getLogger();
    enum Context {NATIVE_APP, WEBVIEW}

    public ContextSwitcher() {
        this.driver = DriverManager.getDriver();
    }

    public void toWebViewContext() {
        tryToSwitchToContext(Context.WEBVIEW);
    }

    public void toNativeContext() {
        tryToSwitchToContext(Context.NATIVE_APP);
    }

    private void tryToSwitchToContext(Context contextName) {
        if (!tryToSwitchWithSleep(contextName)) {
            String errorMsg = "Failed to switch to " + contextName;
            LOGGER.error(errorMsg);
            throw new RuntimeException(errorMsg);
        }
    }

    private boolean tryToSwitchWithSleep(Context contextName) {
        long sleepTime = 1000;
        for (int i = 0; i < 30; i++) {
            try {
                if (switchToContext(contextName)) return true;
                Thread.sleep(sleepTime);
                LOGGER.debug("No " + contextName + ", sleep #" + i + 1 + " for " + sleepTime + " millis");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    private boolean switchToContext(Context contextName) {
        ArrayList<String> contexts = new ArrayList<>(driver.getContextHandles());
        for (String context : contexts) {
            if (context.contains(contextName.toString())) {
                driver.context(context);
                return true;
            }
        }
        return false;
    }
}
