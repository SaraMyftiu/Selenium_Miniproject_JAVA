package pages;

public interface CheckFiltersPage {
    void hoverOnPopup();
    void clickJacketMenuOption();
    void clickColorDropdown();
    void chooseOneColor();
    boolean checkBorderRedInProducts();
    boolean checkThePriceMatch() throws InterruptedException;
    void clickPriceDropdown();
    void chooseFirstOption() ;
    String getProductsListSize();
}
