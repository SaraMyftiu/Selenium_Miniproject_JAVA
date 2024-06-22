package stepDefinitions;

import implementations.WishListPageImpl;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.WishListPage;
import utils.BaseBrowser;


public class WishListTest {

    private WebDriver driver;
    private WishListPage wishListPage;

    @Given("i am on the webpage for WishList")
    public void i_am_on_the_webpage_for_wishlist(){
        WebDriver driver = BaseBrowser.driver;
        driver.get(BaseBrowser.baseurl);
        wishListPage = new WishListPageImpl(driver);
    }

    @Then("i remove price filter")
    public void i_remove_price_filter() {
        wishListPage.clickRemovePriceFilter();
    }

    @Then("i check the items number displayed is increased")
    public void i_check_the_items_number_displayed_is_increased() {
        Assert.assertTrue(wishListPage.checkIncreasedNumberItems());
    }

    @Then("i add the two first item in the Wish List and check if success message is shown")
    public void i_add_the_two_first_item_in_the_wish_list_and_check_if_success_message_is_shown() throws InterruptedException {
        wishListPage.addTwoFirstItemsInWishListAndCheckIfSuccessMessageIsShown();
    }

    @Then("i click on User Profile")
    public void i_click_on_user_profile() {
        wishListPage.clickSwitchButton();
    }

    @Then("i check the correct number of items is displayed")
    public void i_check_the_correct_number_of_items_are_displayed() {
        Assert.assertTrue(wishListPage.areItemsDisplayedProperly());
    }
}