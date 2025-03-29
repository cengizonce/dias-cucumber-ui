package stepMethods;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.drivers.WebDrivers;
import utilities.elementsReader.ElementResources;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BaseMethods {

    private static final Logger logger = LogManager.getLogger(BaseMethods.class);

    public final ElementResources elementResources;
    public WebDriver driver;

    public BaseMethods() {
        elementResources = ElementResources.getInstance();
        driver = WebDrivers.getDriver();
    }

    public By getLocator(String locator) {
        return elementResources.getElementBy(locator);
    }

    public List<WebElement> findElements(String locator) {
        return driver.findElements(getLocator(locator));
    }

    public WebElement clickableElement(String locator, int second) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(second));
        return wait.until(ExpectedConditions.elementToBeClickable(getLocator(locator)));
    }

    public void visibilityElement(String locator, int second) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(second));
        wait.until(ExpectedConditions.elementToBeClickable(getLocator(locator)));
    }

    public WebElement visibilityElementWithBy(By locator, int second) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(second));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement presenceOfElement(String locator, int second) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(second));
        return wait.until(ExpectedConditions.presenceOfElementLocated(getLocator(locator)));
    }

    public void screenMaximize() {
        driver.manage().window().maximize();
    }

    public void getUrl(String URL) {
        driver.get(URL);
        logger.info("{} address has been accessed.", URL);
        waitForPageToLoad(30);
    }

    public void waitForPageToLoad(int sec) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState == 'complete'"));
        logger.info("Waited until the page was loaded.");
    }

    public void hoverElement(String element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(clickableElement(element, 15)).perform();
        logger.info("{} was hovered over.", element);
    }

    public void switchLastWindow() {
        Set<String> windowHandles = driver.getWindowHandles();
        String lastWindowHandle = windowHandles.toArray()[windowHandles.size() - 1].toString();
        driver.switchTo().window(lastWindowHandle);
        logger.info("Switched to the new tab.");
    }

    public String getElementText(String locator) {
        return presenceOfElement(locator, 10).getText();
    }

    public String returnTextWithRemoveAndTrim(String locator, String removeText) {
        return getElementText(locator).replaceAll(removeText, "").trim();
    }

    public void scroolWithBy(By locator) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", visibilityElementWithBy(locator, 3));
    }

    public void hoverWithBy(By locator) {
        Actions actions = new Actions(driver);
        actions.moveToElement(visibilityElementWithBy(locator, 3)).perform();
    }


}
