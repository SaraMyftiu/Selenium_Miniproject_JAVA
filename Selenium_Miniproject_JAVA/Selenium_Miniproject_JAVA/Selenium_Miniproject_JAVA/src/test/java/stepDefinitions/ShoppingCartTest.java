package stepDefinitions;

import implementations.ShoppingCartPageImpl;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.ShoppingCartPage;
import utils.BaseBrowser;

public class ShoppingCartTest {

    private WebDriver driver;
    private ShoppingCartPage shoppingCartPage;

    @Given("i am on the webpage for Shopping Cart")
    public void i_am_on_the_webpage_for_shopping_cart() {
        WebDriver driver = BaseBrowser.driver;
        driver.get(BaseBrowser.baseurl);
        shoppingCartPage = new ShoppingCartPageImpl(driver);
    }

    @Then("i add all displayed items to the Shopping Cart")
    public void i_add_all_displayed_items_to_the_shopping_cart() {
        shoppingCartPage.addItemsToShoppingCart();
    }

    @Then("the successful message is shown")
    public void successful_message_is_shown() throws InterruptedException {
        Assert.assertTrue(shoppingCartPage.isSuccessfulMessageShown());
    }

    @Then("i open the Shopping Cart Link")
    public void i_open_the_shopping_cart_link() {
        shoppingCartPage.openShoppingCartLink();
    }

    @Then("{string} Page title is shown")
    public void page_is_shown(String title) {
        Assert.assertEquals(title, shoppingCartPage.getShoppingPageText());
    }

    @Then("i check that the prices sum for all items is equal to Order Total price in the Summary section")
    public void i_check_that_the_prices_sum_for_all_items_is_equal_to_order_total_price_in_the_summary_section() throws InterruptedException {
        Assert.assertTrue(shoppingCartPage.checkSumOfPrices());
    }
    @Then("i click the first remove button in item on shopping cart")
    public void i_click_the_first_remove_button_in_item_on_shopping_card() throws InterruptedException {
        shoppingCartPage.deleteFirstITem();
    }

    @Then("i check that the number of elements in Shopping Cart table is decreased by one")
    public void i_check_that_the_number_of_elements_in_shopping_cart_table_is_decreased_by() {
        Assert.assertTrue(shoppingCartPage.isNoOfElementsDecreaseByOne());
    }

    @Then("i repeat steps two first steps until the last item is deleted")
    public void i_repeat_steps_two_first_steps_until_the_last_item_is_deleted() {
        Assert.assertTrue(shoppingCartPage.repeatSteps());
    }

    @Then("{string} message is displayed")
    public void message_is_displayed(String message) {
           Assert.assertEquals(message, shoppingCartPage.getEmptyPageText());
    }
}
