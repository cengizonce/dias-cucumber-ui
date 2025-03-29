package stepDefinitions;

import io.cucumber.java.en.Given;
import stepMethods.MainPageMethods;

import java.text.ParseException;

public class ProductListingPage {

    MainPageMethods mainPageMethods = new MainPageMethods();


    @Given("{string} option is selected from the {string} filter")
    public void selectFilterAndFilterOpsion(String filterOption, String filter) {
        mainPageMethods.selectFilter(filter, filterOption, 10);
        mainPageMethods.selectedFilterControl(filterOption);
    }

    @Given("Highest priced product in the listing is clicked")
    public void clickMaxPriceProduct() throws ParseException {
        mainPageMethods.clickProductWithMaxPricee();
    }

}
