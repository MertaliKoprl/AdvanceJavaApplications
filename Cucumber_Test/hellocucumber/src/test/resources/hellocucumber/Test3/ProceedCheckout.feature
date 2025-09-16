@Login
Feature: Proceed to checkout functionality
  As a standard user of Saucedemo
  I want to checkout items on the list

  Scenario: Proceed to checkout successfully
    Given I am on the Shopping Cart Page
    When I click Checkout Button
    Then I should be taken to Checkout Page of Sauce demo