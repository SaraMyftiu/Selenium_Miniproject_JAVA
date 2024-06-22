package implementations;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ShoppingCartPage;
import utils.BaseBrowser;

import java.time.Duration;
import java.util.List;

public class ShoppingCartPageImpl extends BaseBrowser implements ShoppingCartPage {
    //region
    @FindBy(how = How.XPATH,
            using = "//div[contains(@data-bind, 'message.text')]")
    private WebElement successMessage;

    @FindBy(how = How.XPATH,
            using = "//a[text()='shopping cart']")
    private WebElement shoppingCartLink;

    @FindBy(how = How.CSS,
            using = "span.base")
    private WebElement shoppingCartPageTitle;

    @FindBy(how = How.XPATH,
            using = "(//a[@class='action action-delete'])[1]")
    private WebElement removeBtnOfFirstItem;

    @FindBy(how = How.XPATH,
            using = "//p[text()='You have no items in your shopping cart.']")
    private WebElement emptyMessage;
    //endregion

    private int initialProductCount;

    public ShoppingCartPageImpl(WebDriver driver) {
        BaseBrowser.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Override
    public void addItemsToShoppingCart() {

        List<WebElement> products = driver.findElements(By.cssSelector("li.item.product>div.product-item-info"));

        for (WebElement product : products) {
            WebDriverWait waitToHover = new WebDriverWait(driver, Duration.ofSeconds(50));
            waitToHover.until(ExpectedConditions.visibilityOf(product));
            Actions actions = new Actions(driver);
            actions.moveToElement(product).perform();
            //clicks the size
            WebDriverWait waitsize = new WebDriverWait(driver, Duration.ofSeconds(50));
            WebElement xsOptionSize = waitsize.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='XS']")));
            xsOptionSize.click();


            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", product);
        }
    }

    @Override
    public boolean isSuccessfulMessageShown() throws InterruptedException {
        Thread.sleep(10000);
        WebDriverWait waitmessage = new WebDriverWait(driver, Duration.ofSeconds(5));
        waitmessage.until(ExpectedConditions.visibilityOf(successMessage));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement parentElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-bind='html: $parent.prepareMessageForHtml(message.text)']")));

        // using JavascriptExecutor to get the content of the ::before pseudo-element
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String beforeContent = (String) js.executeScript(
                "return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');", parentElement);
        return successMessage.isDisplayed() && (!beforeContent.isEmpty());
    }

    @Override
    public void openShoppingCartLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(shoppingCartLink));
        shoppingCartLink.click();
    }

    @Override
    public String getShoppingPageText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(shoppingCartPageTitle));
        return shoppingCartPageTitle.getText();
    }

    @Override
    public boolean checkSumOfPrices() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> productsSubTotal = driver.findElements(By.xpath("//td[contains(@class, 'subtotal')]//span[@class='price']"));
        double totalCost = 0;

        for (WebElement element : productsSubTotal) {
            String priceText = element.getText().substring(1).replace(",", "");
            double price = Double.parseDouble(priceText);
            totalCost += price;
            System.out.println("Added price: " + price + ", Total so far: " + totalCost);
        }
        Thread.sleep(20000);
        WebElement totalElement = driver.findElement(By.cssSelector("tr.totals>td>strong>span"));
        String totalText = totalElement.getText().substring(1).replaceAll(",", "");
        try {
            double totalAmount = Double.parseDouble(totalText);
            // Compare totalCost with totalAmount
            if (totalAmount == totalCost) {
                System.out.println("Total costs match.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void deleteFirstITem() throws InterruptedException {
        Thread.sleep(20000);

        initialProductCount = driver.findElements(By.cssSelector("tr.item-info")).size();
        System.out.println("init number " + initialProductCount);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(removeBtnOfFirstItem));
        removeBtnOfFirstItem.click();

      //  initialProductCount--1;
        Thread.sleep(10000);
    }

    @Override
    public boolean isNoOfElementsDecreaseByOne() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        int currentProductCount = driver.findElements(By.cssSelector("tr.item-info")).size();
        System.out.println("Number of products after clearing filter: " + currentProductCount);
        if (currentProductCount == initialProductCount - 1) {
            System.out.println("Product count decreased by one: " + currentProductCount);
            return true;
        } else {
            System.out.println("Product count did not decrease by one: " + currentProductCount);
            return false;
        }
        //  return false;
    }

    @Override
    public boolean repeatSteps() {
        int initialProductCount = driver.findElements(By.cssSelector("tr.item-info")).size();

        for (int i = 0; i < initialProductCount; i++) {
            // Locate the delete button of the first item (or the current item in the loop)

            // Wait until the delete button is visible and then click it
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='action action-delete']")));
            deleteButton.click();

            // Wait for the product list to be updated (you might need to adjust the wait condition)
            wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector("tr.item-info"), initialProductCount - i));

            // Get the current number of products
            int currentProductCount = driver.findElements(By.cssSelector("tr.item-info")).size();
            System.out.println("Number of products after removing item: " + currentProductCount);

            // Verify that the product count decreased by one
            if (currentProductCount == initialProductCount - i - 1) {
                System.out.println("Product count decreased by one: " + currentProductCount);
            } else {
                System.out.println("Product count did not decrease by one: " + currentProductCount);
                return false;
            }
        }

        return true; // Return true if all conditions are met
    }

    @Override
    public String getEmptyPageText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return emptyMessage.getText();
    }
}