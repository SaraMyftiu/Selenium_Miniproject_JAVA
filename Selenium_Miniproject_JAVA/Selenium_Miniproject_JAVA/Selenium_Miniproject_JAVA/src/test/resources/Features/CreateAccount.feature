Feature: Create an account

  Scenario: Creating an account in the webpage
    Given i am on the webpage
    Then i click Create an account link
    Then the title of the page "Create New Customer Account" should be visible
    Then i fill in the First Name Field with "Test"
    Then i fill in the Last Name Field with "Test"
    Then i fill in the Email Field with "testautomation@gmail.com"
    Then i fill in the Password field with "TestAutomation@123"
    Then i fill in the Confirm Password field with "TestAutomation@123"
    Then i click Create an account button
    Then the successful message and the icon are shown
    Then i signout