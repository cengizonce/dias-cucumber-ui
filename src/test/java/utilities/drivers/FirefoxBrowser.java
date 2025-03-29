package utilities.drivers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import runners.TestRunner;

import java.net.MalformedURLException;
import java.net.URL;

public class FirefoxBrowser {

    private static final Logger logger = LogManager.getLogger(FirefoxBrowser.class);


    public static WebDriver driverCreate() {
        switch (TestRunner.getEnviroment()) {
            case "local":
                logger.info("The test will run in the local environment.");
                return new FirefoxDriver();
            case "remote":
                logger.info("The test will run in the remote environment.");
                try {
                    FirefoxOptions remotefirefoxOptions = new FirefoxOptions();
                    return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),remotefirefoxOptions);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            default:
                System.out.println("Invalid browser type");
                return null;
        }
    }

    public static void killDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }
}
