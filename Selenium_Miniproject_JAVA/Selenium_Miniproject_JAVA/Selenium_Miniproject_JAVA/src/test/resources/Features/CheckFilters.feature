Feature: Check page filters

  Scenario: Check page filters in webpage
    Given i am on the webpage
    Then i click Sign In link
    Then i enter valid credentials with email "testautomation@gmail.com" and pass "TestAutomation@123"
    Then i click Sign In button
    Then i hover over Tops dropdown on the open pop up and click on Jacket menu option
    Then i click on Color dropdown and choose one of the colors
    Then i check that all the displayed products have the selected color bordered in red
    Then i click on Price dropdown and choose the first option
    Then i check that they are "5" products displayed
    Then i check that the price matches the defined criteria for each product