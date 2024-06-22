package stepDefinitions;

import implementations.CheckFilterPageImpl;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.CheckFiltersPage;
import utils.BaseBrowser;

import static org.junit.Assert.assertEquals;

public class CheckFiltersTest {
    private CheckFiltersPage checkFiltersPage;

    @Then("i hover over Tops dropdown on the open pop up and click on Jacket menu option")
    public void i_hover_over_tops_dropdown_on_the_open_pop_up_and_click_on_jacket_menu_option() {
        WebDriver driver = BaseBrowser.driver;
        driver.get(BaseBrowser.baseurl);
        checkFiltersPage = new CheckFilterPageImpl(driver);
        checkFiltersPage.hoverOnPopup();
        checkFiltersPage.clickJacketMenuOption();
    }

    @Then("i click on Color dropdown and choose one of the colors")
    public void i_click_on_color_dropdown_and_choose_one_of_the_colors() {
        checkFiltersPage.clickColorDropdown();
        checkFiltersPage.chooseOneColor();
    }

    @Then("i check that all the displayed products have the selected color bordered in red")
    public void i_check_that_all_the_displayed_products_have_the_selected_color_bordered_in_red() {
       Assert.assertTrue(checkFiltersPage.checkBorderRedInProducts());
    }

    @Then("i click on Price dropdown and choose the first option")
    public void i_click_on_price_dropdown_and_choose_the_first_option() {
        checkFiltersPage.clickPriceDropdown();
        checkFiltersPage.chooseFirstOption();
    }

    @Then("i check that they are {string} products displayed")
    public void i_check_that_they_are_products_displayed(String listSize)  {
        assertEquals(checkFiltersPage.getProductsListSize(), listSize);
    }

    @Then("i check that the price matches the defined criteria for each product")
    public void i_check_that_the_price_matches_the_defined_criteria_for_each_product() throws InterruptedException {
       Assert.assertTrue(checkFiltersPage.checkThePriceMatch());
    }
}