package pages;

public interface ShoppingCartPage {
    void addItemsToShoppingCart() ;
    boolean isSuccessfulMessageShown() throws InterruptedException;
    void openShoppingCartLink();
    String getShoppingPageText();
    boolean checkSumOfPrices() throws InterruptedException;
    void deleteFirstITem() throws InterruptedException;
    boolean isNoOfElementsDecreaseByOne();
    boolean repeatSteps();
    String getEmptyPageText();
}
