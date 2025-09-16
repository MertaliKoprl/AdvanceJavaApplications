@Login
Feature: Checking item on the list functionality
  As a standard user of Saucedemo
  I want to check item on the list if it is correct item

  Scenario: Checking Item on the list successfully
    Given I am on the Shopping Cart Page of Sauce demo
    When I check the name of the item on the list
    Then I check the item on the list successfully