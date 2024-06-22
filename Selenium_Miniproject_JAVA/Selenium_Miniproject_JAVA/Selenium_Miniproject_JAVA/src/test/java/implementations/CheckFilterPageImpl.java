package implementations;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import pages.CheckFiltersPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BaseBrowser;

import java.time.Duration;
import java.util.List;

public class CheckFilterPageImpl extends BaseBrowser implements CheckFiltersPage {

    //region
    @FindBy(how = How.XPATH,
            using = "(//nav[@class='navigation']//li)[2]")
    private WebElement womanOption;

    @FindBy(how = How.XPATH,
            using = "(//li[contains(@class, 'first parent ui-menu-item')])[1]")
    private WebElement topOption;

    @FindBy(how = How.XPATH,
            using = "(//nav[@class='navigation']//li//ul)[2]")
    private WebElement jacketOption;

    @FindBy(how = How.CSS,
            using = "div.filter-options>div:nth-child(4)")
    private WebElement colorDropdown;

    @FindBy(how = How.CSS,
            using = "div.filter-options>div:nth-child(10)")
    private WebElement priceDropdown;

    @FindBy(how = How.XPATH,
            using = "//a[@aria-label='Blue']//div")
    private WebElement blueOption;

    @FindBy(how = How.CSS,
            using = "a[href*='price=20-30']")
    private WebElement firstOptionOfPrice;
    //endregion

    public CheckFilterPageImpl(WebDriver driver) {
        BaseBrowser.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Override
    public void hoverOnPopup() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(womanOption));
        Actions actions = new Actions(driver);
        actions.moveToElement(womanOption).perform();
        wait.until(ExpectedConditions.visibilityOf(topOption));
        actions.moveToElement(topOption).perform();
    }

    @Override
    public void clickJacketMenuOption() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(jacketOption));
        jacketOption.click();
    }

    @Override
    public void clickColorDropdown() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(colorDropdown));
        colorDropdown.click();
    }

    @Override
    public void chooseOneColor() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(blueOption));
        blueOption.click();
    }

    @Override
    public boolean checkBorderRedInProducts() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> products = driver.findElements(By.cssSelector("li.item.product>div.product-item-info"));

        for (WebElement product : products) {
            List<WebElement> blueBorderedRed = product.findElements(By.cssSelector("div[aria-label='Blue'][aria-checked='true']"));
            if (blueBorderedRed.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void clickPriceDropdown() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(priceDropdown));
        priceDropdown.click();
    }

    @Override
    public void chooseFirstOption()  {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(firstOptionOfPrice));
        firstOptionOfPrice.click();

    }

    @Override
    public String getProductsListSize() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> products = driver.findElements(By.cssSelector("li.item.product>div.product-item-info"));
        return String.valueOf(products.size());
    }

    @Override
    public boolean checkThePriceMatch() throws InterruptedException {
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
         List<WebElement> products = driver.findElements(By.cssSelector("li.item.product>div.product-item-info"));
            // Iterate over each product
            for (WebElement product : products) {
                WebElement priceElement = product.findElement(By.cssSelector("span.price"));
                String priceText = priceElement.getText().replace("$", "").replace(",", "");

                    double price = Double.parseDouble(priceText);
                    // Check if the price is within the defined range tha is 20.00-29.00
                    // The option with value 50.00 to 60.00 doesn't exist foe this color
                        Thread.sleep(15000);
                    if (price >= 20.00 && price < 30.00) {
                        System.out.println("Product price $" + price + " is within the defined range.");
                        return true;
                    } else {
                        System.out.println("Product price $" + price + " is outside the defined range.");
                    }
                }
            return false;
    }
}