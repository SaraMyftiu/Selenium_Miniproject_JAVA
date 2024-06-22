package stepDefinitions;

import implementations.SigninPageImpl;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.SigninPage;
import utils.BaseBrowser;


public class SigninTest {
    private WebDriver driver;
    private SigninPage signinPage;


    @Then("i click Sign In link")
    public void i_click_sign_in_link() {
        driver = BaseBrowser.driver;
        driver.get(BaseBrowser.baseurl);
        signinPage = new SigninPageImpl(driver);
        signinPage.clickSignInLink();
    }

    @Then("i enter valid credentials with email {string} and pass {string}")
    public void i_enter_valid_credentials_created_when_i_creating_an_account(String email, String password) {
         signinPage.enterEmail(email);
         signinPage.enterPassword(password);
    }

    @Then("i click Sign In button")
    public void i_click_sign_in_button() {
        signinPage.clickSignInButton();
    }

    @Then("i check if username is displayed on right corner of the page")
    public void i_check_if_username_is_displayed_right() {
        Assert.assertTrue(signinPage.checkUsername());
    }
}