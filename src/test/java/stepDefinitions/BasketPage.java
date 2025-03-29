package stepDefinitions;

import io.cucumber.java.en.Given;
import org.testng.Assert;
import stepMethods.BaseMethods;
import stepMethods.SaveValueMethods;

public class BasketPage {

    BaseMethods baseMethods = new BaseMethods();
    SaveValueMethods saveValueMethods = new SaveValueMethods();

    @Given("Cart product price is saved")
    public void saveBasketProductPrice() {
        saveValueMethods.saveStringValue("savedBasketPagePrice", baseMethods.returnTextWithRemoveAndTrim("cartSelectedItemsPrice", "TL"));
    }

    @Given("Product price on detail page and cart price are verified to match")
    public void priceControl() {
        Assert.assertEquals(saveValueMethods.getStringValue("savedProductDetailPagePrice"), saveValueMethods.getStringValue("savedBasketPagePrice"));
    }


}
