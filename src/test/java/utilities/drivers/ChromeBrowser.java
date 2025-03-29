package utilities.drivers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import runners.TestRunner;

import java.net.MalformedURLException;
import java.net.URL;

public class ChromeBrowser {

    private static final Logger logger = LogManager.getLogger(ChromeBrowser.class);

    public static WebDriver driverCreate() {
        switch (TestRunner.getEnviroment()) {
            case "local":
                logger.info("The test will run in the local environment.");
                ChromeOptions localchromeOptions = new ChromeOptions();
                localchromeOptions.addArguments("--disable-notifications");
                localchromeOptions.addArguments("--disable-blink-features=AutomationControlled");
                return new ChromeDriver(localchromeOptions);
            case "remote":
                logger.info("The test will run in the remote environment.");
                ChromeOptions remoteChromeOptions = new ChromeOptions();
                remoteChromeOptions.addArguments("--disable-notifications");
                remoteChromeOptions.addArguments("--disable-blink-features=AutomationControlled");
                try {
                    return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), remoteChromeOptions);
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
            driver.close();
            driver.quit();
        }
    }
}
