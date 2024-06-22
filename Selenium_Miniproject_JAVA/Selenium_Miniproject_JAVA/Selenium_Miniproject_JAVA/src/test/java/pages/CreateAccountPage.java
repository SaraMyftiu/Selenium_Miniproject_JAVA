package pages;

public interface CreateAccountPage {
    void clickCreateAccountLink();
    String isTitleVisible();
    void fillFirstNameField(String firstName);
    void fillLastNameField(String lastName);
    void fillEmailField(String email);
    void fillPasswordField(String password);
    void fillConfirmPasswordField(String confirmPassword);
    void clickCreateAccountButton();
    boolean isSuccessMessageVisible();
    boolean isIconVisible();
    void clickSwitchButton();
    void  doSignout();
}