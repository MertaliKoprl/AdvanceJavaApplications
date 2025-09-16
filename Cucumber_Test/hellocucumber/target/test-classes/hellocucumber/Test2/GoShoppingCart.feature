Feature: Navigating to ShoppingCart Functionality
  As a standard user of Saucedemo
  I want to go to shoppingCart Page

  Scenario: Proceed to ShoppingCart Page
    Given I have successfully add Item to ShoppingCart
    When I click ShoppingCart button
    Then I should be in ShoppingCart Page