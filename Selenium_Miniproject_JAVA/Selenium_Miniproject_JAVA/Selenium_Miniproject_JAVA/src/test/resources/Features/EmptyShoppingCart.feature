Feature: Empty Shopping Card Test
  Background: Shopping Card Prerequisite
    Given i am on the webpage for empty shopping cart
    Then i click Sign In link
    Then i enter valid credentials with email "testautomation@gmail.com" and pass "TestAutomation@123"
    Then i click Sign In button
    Then i hover over Tops dropdown on the open pop up and click on Jacket menu option
    Then i click on Color dropdown and choose one of the colors
    Then i check that all the displayed products have the selected color bordered in red
    Then i click on Price dropdown and choose the first option
    Then i check that they are "5" products displayed
    Then i check that the price matches the defined criteria for each product
    #Then i add all displayed items to the Shopping Cart
    Then the successful message is shown
    Then i open the Shopping Cart Link
    Then "Shopping Cart" Page title is shown
    Then i check that the prices sum for all items is equal to Order Total price in the Summary section
