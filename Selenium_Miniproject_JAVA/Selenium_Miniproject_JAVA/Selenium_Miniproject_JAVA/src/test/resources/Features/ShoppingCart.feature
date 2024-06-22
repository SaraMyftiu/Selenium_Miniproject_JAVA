Feature: Shopping Cart

  Background: Check filters scenario prerequisite
    Given i am on the webpage for Shopping Cart
    Then i click Sign In link
    Then i enter valid credentials with email "testautomation@gmail.com" and pass "TestAutomation@123"
    Then i click Sign In button
    Then i hover over Tops dropdown on the open pop up and click on Jacket menu option
    Then i click on Color dropdown and choose one of the colors
    Then i check that all the displayed products have the selected color bordered in red
    Then i click on Price dropdown and choose the first option
    Then i check that they are "5" products displayed
    Then i check that the price matches the defined criteria for each product

  Scenario: Shopping Cart Test
    Then i add all displayed items to the Shopping Cart
    Then the successful message is shown
    Then i open the Shopping Cart Link
    Then "Shopping Cart" Page title is shown
    Then i check that the prices sum for all items is equal to Order Total price in the Summary section
    #emptyshoppingcard Test Case
    Then i click the first remove button in item on shopping cart
    Then i check that the number of elements in Shopping Cart table is decreased by one
    Then i repeat steps two first steps until the last item is deleted
    Then "You have no items in your shopping cart." message is displayed
