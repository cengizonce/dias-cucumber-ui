package utilities.report;

import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.drivers.WebDrivers;

public class ReportUtil {

    public static void takeScreenShoot(Scenario scenario){
        if (scenario.isFailed()){
            final byte[] screenshoot = ((TakesScreenshot) WebDrivers.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshoot,"image/png","image");
        }
    }

}
