Feature: UI Test Automation

  Scenario: Product Detail and Cart Price Check
    Given PROD URL is opened
    When Cookies are accepted
    And "navbarElektronik", "navbarContainerBilgisayarTablet", "navbarContainerTablet" subcategory is navigated
    And "Apple" option is selected from the "markalar" filter
    And "13,2 inç" option is selected from the "ekranboyutu" filter
    And Highest priced product in the listing is clicked
    And New tab with product details is switched
    Then Product price is saved from the detail page
    When Product is added to cart from the detail page
    And Cart is accessed via the popup
    Then Cart product price is saved
    And Product price on detail page and cart price are verified to match