package hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utilities.drivers.WebDrivers;
import utilities.report.ReportUtil;


public class Hooks {

    private static final Logger logger = LogManager.getLogger(Hooks.class);

    @Before
    public void driverSetUp(){
        logger.info("Starting the test");
        WebDrivers.setUp();
    }

    @AfterStep
    public void afterStep(Scenario scenario){
        ReportUtil.takeScreenShoot(scenario);
    }

    @After
    public void driverKill() {
        WebDrivers.killDriver();
        logger.info("Test has completed");
    }


}
