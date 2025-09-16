@Login
Feature: Login functionality
  As a standard user of Saucedemo
  I want to login

  Scenario: Login Successful
    Given I am on the Login Page of Sauce demo
    When I enter valid username and password
    Then I should be taken to Inventory Page