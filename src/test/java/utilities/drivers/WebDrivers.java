package utilities.drivers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import runners.TestRunner;

public class WebDrivers {
    private static final Logger logger = LogManager.getLogger(WebDrivers.class);
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static WebDriver setUp(){
        WebDriver driverInstance = null;
        switch (TestRunner.getBrowser()) {
            case "chrome":
                logger.info("Setting up Chrome Driver...");
                driverInstance = ChromeBrowser.driverCreate();
                break;
            case "firefox":
                logger.info("Setting up Firefox Driver...");
                driverInstance = FirefoxBrowser.driverCreate();
                break;
            default:
                logger.error("Invalid browser type");

        }
        if (driverInstance != null) {
            driver.set(driverInstance);
        }
        return driverInstance;
    }

    public static void killDriver() {
        WebDriver driverInstance = driver.get();
        if (driverInstance != null) {
            switch (TestRunner.getBrowser()) {
                case "chrome":
                    ChromeBrowser.killDriver(driverInstance);
                    break;
                case "firefox":
                    FirefoxBrowser.killDriver(driverInstance);
                    break;
                default:
                    break;
            }
        }
    }
}