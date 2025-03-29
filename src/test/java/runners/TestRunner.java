package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

@CucumberOptions(
        features = "features",
        glue = {"stepDefinitions", "hooks"},
        plugin = {"pretty",
                "json:target/cucumber-reports/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        }
)
public class TestRunner extends AbstractTestNGCucumberTests {

    private static final ThreadLocal<String> testBrowser = new ThreadLocal<>();
    private static final ThreadLocal<String> testEnviroment = new ThreadLocal<>();

    @BeforeClass
    @Parameters({"browser", "environment"})
    public void setBrowser(String browser,String enviroment) {
        TestRunner.testBrowser.set(browser);
        TestRunner.testEnviroment.set(enviroment);
    }

    public static String getBrowser() {
        if (TestRunner.testBrowser.get() == null) {
            TestRunner.testBrowser.set("chrome");
        }
        return testBrowser.get();
    }

    public static String getEnviroment() {
        if (TestRunner.testEnviroment.get() == null) {
            TestRunner.testEnviroment.set("local");
        }
        return testEnviroment.get();
    }
}