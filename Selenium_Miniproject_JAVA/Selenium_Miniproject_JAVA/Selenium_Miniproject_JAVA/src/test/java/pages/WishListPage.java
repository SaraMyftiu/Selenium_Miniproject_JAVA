package pages;

public interface WishListPage {
    void clickRemovePriceFilter();
    boolean checkIncreasedNumberItems();
    void addTwoFirstItemsInWishListAndCheckIfSuccessMessageIsShown();
    void clickSwitchButton();
    boolean areItemsDisplayedProperly();
}
