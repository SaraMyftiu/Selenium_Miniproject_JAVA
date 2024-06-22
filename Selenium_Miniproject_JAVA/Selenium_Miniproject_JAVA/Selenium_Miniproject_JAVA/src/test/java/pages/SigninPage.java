package pages;

public interface SigninPage {
    void clickSignInLink();
    void enterEmail(String email);
    void enterPassword(String password);
    void clickSignInButton();
    boolean checkUsername();
}
