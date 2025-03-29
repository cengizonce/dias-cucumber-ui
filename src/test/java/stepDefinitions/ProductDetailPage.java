package stepDefinitions;

import io.cucumber.java.en.Given;
import stepMethods.BaseMethods;
import stepMethods.SaveValueMethods;

public class ProductDetailPage {

    SaveValueMethods saveValueMethods = new SaveValueMethods();
    BaseMethods baseMethods = new BaseMethods();

    @Given("New tab with product details is switched")
    public void switchWindow() {
        baseMethods.switchLastWindow();
    }

    @Given("Product price is saved from the detail page")
    public void saveProductPriceOnProductDetailPage() {
        saveValueMethods.saveStringValue("savedProductDetailPagePrice", baseMethods.returnTextWithRemoveAndTrim("productDetailsPrice", "TL"));
    }

    @Given("Product is added to cart from the detail page")
    public void addBasketOnProductDetailPage() {
        baseMethods.clickableElement("productDetailsPageAddToCart", 10).click();
    }

    @Given("Cart is accessed via the popup")
    public void navigateBasketWithPopup() {
        baseMethods.clickableElement("goToCartFromProductDetails", 10).click();
    }

}
