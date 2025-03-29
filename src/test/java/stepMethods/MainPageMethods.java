package stepMethods;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;


import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

public class MainPageMethods extends BaseMethods {

    private static final Logger logger = LogManager.getLogger(MainPageMethods.class);

    public void selectFilter(String filter, String filterOption, int maxAttempts) {
        By filterLastElementLocator = By.xpath("(//*[@name='" + filter + "']/following-sibling::div/a)[last()]");
        By filterOptionLocator = By.xpath("//*[@id='" + filter + "']//div/a[text()='" + filterOption + "']");

        logger.info("Attempting to find the {} option from the {} filter.", filterOption, filter);

        int attemptCount = 0;

        while (attemptCount < maxAttempts) {
            try {
                scroolWithBy(filterLastElementLocator);
                hoverWithBy(filterLastElementLocator);
                scroolWithBy(filterOptionLocator);
                visibilityElementWithBy(filterOptionLocator, 5).click();
                logger.info("Filter option selected.");
                break;
            } catch (Exception e) {
                attemptCount++;
                logger.error("Filter option not found, it will be tried again.");
                if (attemptCount >= maxAttempts) {
                    logger.error("Max attempts reached. Filter option could not be selected.");
                    break;
                }
            }
        }
    }

    public void selectedFilterControl(String filterOption) {
        By selectedFiltreLocator = By.xpath("//*[@data-test-id='filterbox-item-display-name'][text()='" + filterOption + "']");
        waitForPageToLoad(10);
        visibilityElementWithBy(selectedFiltreLocator, 3);
        hoverWithBy(selectedFiltreLocator);
    }

    public void clickProductWithMaxPricee() throws ParseException {
        double maxValue = 0;
        WebElement productToClick = null;
        List<WebElement> productLinks = findElements("productDetailsPageProductLinks");
        List<WebElement> productPrices = findElements("productDetailsPageProductPrice");
        for (int i = 0; i < productPrices.size(); i++) {
            String cleanedPrice = productPrices.get(i).getText().replaceAll("[^\\d,.]+", "").trim();
            NumberFormat format = NumberFormat.getInstance(new Locale("tr", "TR"));
            double currentPrice = format.parse(cleanedPrice).doubleValue();
            if (currentPrice > maxValue) {
                maxValue = currentPrice;
                productToClick = productLinks.get(i);
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productToClick);
            }
        }
        logger.info("Last max price: {}", maxValue);
        assert productToClick != null;
        productToClick.click();
    }


}
