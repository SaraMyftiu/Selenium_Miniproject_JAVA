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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.WishListPage;
import utils.BaseBrowser;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class WishListPageImpl extends BaseBrowser implements WishListPage {

    //region
    @FindBy(how = How.XPATH,
            using = "(//a[@class='action remove'])[2]")
    private WebElement removePriceButton;

    @FindBy(how = How.CSS,
            using = "ol.product-items>li:nth-child(1)")
    private WebElement firstProduct;

    @FindBy(how = How.CSS,
            using = "ol.product-items>li:nth-child(2)")
    private WebElement secondProduct;

    @FindBy(how = How.XPATH,
            using = "//div[contains(text(), 'has been added to your Wish List')]")
    private WebElement successMessage;

    @FindBy(how = How.XPATH,
            using ="(//button[@data-action='customer-menu-toggle'])[1]")
    private WebElement switchButton;
    //endregion

    private int initialProductCount;
    public WishListPageImpl(WebDriver driver) {
        BaseBrowser.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Override
    public void clickRemovePriceFilter() {
        initialProductCount = driver.findElements(By.cssSelector("li.item.product>div.product-item-info")).size();
        System.out.println("Initial number of products: " + initialProductCount);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(removePriceButton));
        removePriceButton.click();
    }

    @Override
    public boolean checkIncreasedNumberItems() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        int currentProductCount = driver.findElements(By.cssSelector("li.item.product>div.product-item-info")).size();
        System.out.println("Number of products after clearing filter: " + currentProductCount);
        if (currentProductCount > initialProductCount) {
            System.out.println("Product count increased: " + currentProductCount);
            return currentProductCount > initialProductCount;
        } else {
            System.out.println("Product count did not increase or decreased: " + currentProductCount);
            return false;
        }
    }

    @Override
    public void addTwoFirstItemsInWishListAndCheckIfSuccessMessageIsShown()  {
        List<WebElement> products = driver.findElements(By.cssSelector("li.item.product>div.product-item-info"));

        for (int i = 0; i < Math.min(2, products.size()); i++) {

            //hover over two first products
            WebElement product = products.get(i);
            WebDriverWait waitToHover = new WebDriverWait(driver, Duration.ofSeconds(50));
            waitToHover.until(ExpectedConditions.visibilityOf(product));
            Actions actions = new Actions(driver);
            actions.moveToElement(product).perform();

            //click wishlist foreach two first elements
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
            WebElement wishListButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Add to Wish List']")));
            wishListButton.click();

            //check if success message is shown foreach two first elements
            WebDriverWait waitMessage = new WebDriverWait(driver, Duration.ofSeconds(20));
            waitMessage.until(ExpectedConditions.visibilityOf(successMessage));
            successMessage.isDisplayed();

            //checks if icon is shown foreach two first elements
            WebDriverWait waitIcon = new WebDriverWait(driver, Duration.ofSeconds(50));
            WebElement parentElementIcon = waitIcon.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("div[data-bind='html: $parent.prepareMessageForHtml(message.text)']")));
            //using js to catch before content
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String beforeContent = (String) js.executeScript(
                    "return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');", parentElementIcon);
            if (!beforeContent.isEmpty() && !beforeContent.equals("none")) {
                System.out.println("Before content is displayed " + beforeContent);
            }
            else {
                System.out.println("Before content is not displayed or empty");
            }

            driver.navigate().back();

            products = driver.findElements(By.cssSelector("li.item.product>div.product-item-info"));
        }
    }

    @Override
    public void clickSwitchButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement wishListPageLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.action.details")));
        wishListPageLink.click();
        //go to wish list page

        WebDriverWait waitBtn = new WebDriverWait(driver, Duration.ofSeconds(5));
        waitBtn.until(ExpectedConditions.visibilityOf(switchButton));
        switchButton.click();
    }

    @Override
    public boolean areItemsDisplayedProperly() {
        // Get the wish list items count from the displayed text
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        String wishListText = driver.findElement(By.xpath("(//a[contains(text(),'My Wish List')])[1]")).getText();
        System.out.println("Wish List Text: " + wishListText);

        // Extract the number of items from the text
        int displayedCount = extractNumberFromString(wishListText);
        System.out.println("Displayed Count: " + displayedCount);

        // Get the actual number of items in the wish list
        List<WebElement> wishListItems = driver.findElements(By.xpath("//li[@data-row='product-item']//div[@class='product-item-info']"));
        int actualCount = wishListItems.size();
        System.out.println("Actual Count: " + actualCount);

        // Validate the count
        if (displayedCount == actualCount) {
            System.out.println("The number of items displayed is correct: " + displayedCount);
            return true;
        } else {
            System.out.println("The number of items displayed is incorrect. Expected: " + displayedCount + ", but found: " + actualCount);
        }
        return false;
    }

    private static int extractNumberFromString(String text) {
        String number = text.replaceAll("[^0-9]", "");
        System.out.println("Extracted Number: " + number); // Debug information
        if (number.isEmpty()) {
            System.out.println("No number found in the text."); // Debug information
            throw new NumberFormatException("No number found in the string: " + text);
        }
        return Integer.parseInt(number);
    }
}