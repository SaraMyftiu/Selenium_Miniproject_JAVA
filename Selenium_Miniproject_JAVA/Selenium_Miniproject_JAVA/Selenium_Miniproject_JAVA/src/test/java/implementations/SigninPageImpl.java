package implementations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SigninPage;
import utils.BaseBrowser;

import java.time.Duration;

public class SigninPageImpl extends BaseBrowser implements SigninPage {

    //region
    @FindBy(how = How.XPATH,
            using ="(//a[contains(text(), 'Sign In')])[1]")
    private WebElement siginLink;

    @FindBy(how = How.ID,
            using ="email")
    private WebElement emailField;

    @FindBy(how = How.ID,
            using ="pass")
    private WebElement passwordField;

    @FindBy(how = How.CSS,
            using ="button.login")
    private WebElement singinButton;

    @FindBy(how = How.XPATH,
            using ="(//span[contains(text(), 'Welcome')])[1]")
    private WebElement username;
    //endregion

    public SigninPageImpl(WebDriver driver) {
        BaseBrowser.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @Override
    public void clickSignInLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(siginLink));
        siginLink.click();
    }

    @Override
    public void enterEmail(String email) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.sendKeys(email);
    }

    @Override
    public void enterPassword(String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.sendKeys(password);
    }

    @Override
    public void clickSignInButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(singinButton));
        singinButton.click();
    }

    @Override
    public boolean checkUsername() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(username));
        return username.isDisplayed();
    }
}
