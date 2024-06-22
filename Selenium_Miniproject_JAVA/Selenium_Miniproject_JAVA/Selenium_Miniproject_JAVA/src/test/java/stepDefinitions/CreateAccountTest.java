package stepDefinitions;

import implementations.CreateAccountPageImpl;
import org.junit.Assert;
import pages.CreateAccountPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import utils.BaseBrowser;

public class CreateAccountTest {
    private WebDriver driver;
    private CreateAccountPage createAccountPage;


    @Given("i am on the webpage")
    public void i_am_on_the_webpage() {
        driver = BaseBrowser.driver;
        driver.get(BaseBrowser.baseurl);
        createAccountPage = new CreateAccountPageImpl(driver);
    }

    @Then("i click Create an account link")
    public void i_click_create_an_account_link() {
        createAccountPage.clickCreateAccountLink();
    }

    @Then("the title of the page {string} should be visible")
    public void the_title_of_the_page_should_be_visible(String title) {
        Assert.assertEquals(title, createAccountPage.isTitleVisible());
    }

    @Then("i fill in the First Name Field with {string}")
    public void i_fill_in_the_first_name_field_with(String firstName) {
        //haredContext.setFirstName(firstName);
        createAccountPage.fillFirstNameField(firstName);
    }


    @Then("i fill in the Last Name Field with {string}")
    public void i_fill_in_the_last_name_field_with(String lastName) {
      //  sharedContext.setLastName(lastName);
        createAccountPage.fillLastNameField(lastName);
    }

    @Then("i fill in the Email Field with {string}")
    public void i_fill_in_the_email_field_with(String email) {
        createAccountPage.fillEmailField(email);

    }

    @Then("i fill in the Password field with {string}")
    public void i_fill_in_the_password_field_with(String password) {
        createAccountPage.fillPasswordField(password);
    }

    @Then("i fill in the Confirm Password field with {string}")
    public void i_fill_in_the_confirm_password_field_with(String confirmPassword) {
        createAccountPage.fillConfirmPasswordField(confirmPassword);
    }

    @Then("i click Create an account button")
    public void i_click_create_an_account_button() {
        createAccountPage.clickCreateAccountButton();
    }

    @Then("the successful message and the icon are shown")
    public void the_successful_message_and_the_icon_are_shown() {
      Assert.assertTrue(createAccountPage.isSuccessMessageVisible());
      Assert.assertTrue(createAccountPage.isIconVisible());
    }

    @Then("i signout")
    public void i_signout () {
        createAccountPage.clickSwitchButton();
        createAccountPage.doSignout();
    }
}