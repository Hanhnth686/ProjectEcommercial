package action;

import org.openqa.selenium.WebDriver;
import ui.LoginPageUI;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        driver.findElement(LoginPageUI.USERNAME_FIELD).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(ui.LoginPageUI.PASSWORD_FIELD).sendKeys(password);
    }

    public boolean isLoginButtonEnabled() {
        return driver.findElement(LoginPageUI.LOGIN_BUTTON).isEnabled();
    }

    public void clickLogin() {
        driver.findElement(LoginPageUI.LOGIN_BUTTON).click();
    }

    public boolean displayErrorButton() {
        return driver.findElement(LoginPageUI.ERROR_MESSAGE).isEnabled();
    }

    public String getErrorMessage() {
        return driver.findElement(LoginPageUI.ERROR_MESSAGE).getText();
    }

    public boolean isLogoDisplayed() {
        return driver.findElement(LoginPageUI.APP_LOGO).isDisplayed();
    }

    public int getInventoryItemCount() {
        return driver.findElements(LoginPageUI.INVENTORY_ITEM).size();
    }
}
