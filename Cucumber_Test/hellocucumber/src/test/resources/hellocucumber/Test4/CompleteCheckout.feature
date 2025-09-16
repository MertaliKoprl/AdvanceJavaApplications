@Login
Feature: Checkout functionality
  As a standard user of Saucedemo
  I want to complete checkout

  Scenario: Complete checkout successfully
    Given I am on the Checkout Page of Sauce demo
    When I enter valid firstname, lastname and zip code and click continue button
    Then I should be taken to Checkout Overview Page