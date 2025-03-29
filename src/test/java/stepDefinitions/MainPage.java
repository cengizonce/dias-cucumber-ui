package stepDefinitions;

import io.cucumber.java.en.Given;
import stepMethods.BaseMethods;

public class MainPage {

    BaseMethods baseMethods = new BaseMethods();

    @Given("PROD URL is opened")
    public void getUrl() {
        baseMethods.screenMaximize();
        baseMethods.getUrl("https://www.hepsiburada.com");
    }

    @Given("Cookies are accepted")
    public void acceptCookies() {
        baseMethods.clickableElement("acceptCookies", 20).click();
        baseMethods.visibilityElement("postCookieAd", 10);
        baseMethods.waitForPageToLoad(10);
    }

    @Given("{string}, {string}, {string} subcategory is navigated")
    public void navigateSubCategory(String mainCategory, String subCategory, String subSubCategory) {
        baseMethods.hoverElement(mainCategory);
        baseMethods.hoverElement(subCategory);
        baseMethods.hoverElement(subSubCategory);
        baseMethods.clickableElement(subSubCategory, 10).click();
        baseMethods.waitForPageToLoad(30);
    }

}
