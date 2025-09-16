@Login
Feature: Add Item to Cart Functionality
  As a standard user of Saucedemo
  I want to add first item to ShoppingCart

  Scenario: Add Item to ShoppingCart
    Given I am on the Item Page of source demo
    When I click addToCart Button
    Then Added successfully