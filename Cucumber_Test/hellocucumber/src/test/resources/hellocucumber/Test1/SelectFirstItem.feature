@Login
Feature: Selecting First Item functionality
  As a standard user of Saucedemo
  I want to select first item on the list

  Scenario: Go first item Page successfully
    Given I am on the Inventory Page of Sauce demo
    When I select first item on the list
    Then I am on the item Page Successfully