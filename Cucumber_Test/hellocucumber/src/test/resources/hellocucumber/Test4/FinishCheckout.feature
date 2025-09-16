@Login
Feature: Finish Checkout functionality
  As a standard user of Saucedemo
  I want to finish checkout process

  Scenario: Finish checkout successfully
    Given I am on the Checkout Overview Page of Sauce demo
    When I click finish button
    Then I should be taken to Checkout Complete Page of Sauce demo