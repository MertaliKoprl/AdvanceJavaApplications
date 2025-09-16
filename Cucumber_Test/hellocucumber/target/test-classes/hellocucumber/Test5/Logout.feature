@Login
Feature: Login functionality
  As a standard user of Saucedemo
  I want to logout

  Scenario: Logout Successful
    Given I am on the Checkout Complete Page of Sauce demo
    When I click Open Menu button and select logout option
    Then I should be taken to Login Page of Sauce demo