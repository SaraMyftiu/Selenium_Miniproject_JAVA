package implementations;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CreateAccountPage;
import utils.BaseBrowser;

import java.time.Duration;

public class CreateAccountPageImpl extends BaseBrowser implements CreateAccountPage {

    //region
    @FindBy(how = How.XPATH,
            using ="(//a[text()='Create an Account'])[1]")
    private WebElement createAccountLink;

    @FindBy(how = How.CSS,
            using ="h1.page-title>span")
    private WebElement createAccountTitle;

    @FindBy(how = How.ID,
            using ="firstname")
    private WebElement firstNameField;

    @FindBy(how = How.ID,
            using ="lastname")
    private WebElement lastNameField;

    @FindBy(how = How.ID,
            using ="email_address")
    private WebElement emailField;

    @FindBy(how = How.ID,
            using ="password")
    private WebElement passwordField;

    @FindBy(how = How.ID,
            using ="password-confirmation")
    private WebElement confirmPasswordField;

    @FindBy(how = How.CSS,
            using ="button.submit")
    private WebElement createAccountButton;

    @FindBy(how = How.XPATH,
            using ="(//button[@data-action='customer-menu-toggle'])[1]")
    private WebElement switchButton;

    @FindBy(how = How.XPATH,
            using ="//div[contains(@data-bind, 'message.text')]")
    private WebElement successMessage;

    @FindBy(how = How.XPATH,
            using ="(//a[contains(text(),'Sign Out')])[1]")
    private WebElement signoutOption;
    //endregion

    public CreateAccountPageImpl(WebDriver driver) {
        BaseBrowser.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Override
    public void clickCreateAccountLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(createAccountLink));
        createAccountLink.click();
    }

    @Override
    public String isTitleVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(createAccountTitle));
        createAccountLink.click();
        return  createAccountTitle.getText().trim();
    }

    @Override
    public void fillFirstNameField(String firstName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(firstNameField));
        firstNameField.sendKeys(firstName);
    }

    @Override
    public void fillLastNameField(String lastName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(lastNameField));
        lastNameField.sendKeys(lastName);
    }

    @Override
    public void fillEmailField(String email) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.sendKeys(email);
    }

    @Override
    public void fillPasswordField(String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.sendKeys(password);
    }

    @Override
    public void fillConfirmPasswordField(String confirmPassword) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(confirmPasswordField));
        confirmPasswordField.sendKeys(confirmPassword);
    }

    @Override
    public void clickCreateAccountButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(createAccountButton));
        createAccountButton.click();
    }

    @Override
    public boolean isSuccessMessageVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(successMessage));
        return successMessage.isDisplayed();
    }

    @Override
    public boolean isIconVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement parentElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[data-bind='html: $parent.prepareMessageForHtml(message.text)']")
        ));

        // using to get the content of the ::before pseudo-element
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String beforeContent = (String) js.executeScript(
                "return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');", parentElement);


        // The isDisplayed() method is not applicable to a String object, which is why we cannot directly use beforeContent.isDisplayed()
        return (!beforeContent.isEmpty());
    }

    @Override
    public void clickSwitchButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(switchButton));
        switchButton.click();
    }

    @Override
    public void doSignout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(signoutOption));
        signoutOption.click();
    }
}