package feature.login;

import action.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import unltils.Common;

public class LoginTest extends Common {
    LoginPage loginPage = new LoginPage(driver);

    @BeforeMethod
    public void setupPage() {
        loginPage = new LoginPage(driver);
    }
    public void inputData(String user, String pass) {
        loginPage.enterUsername(user);
        loginPage.enterPassword(pass);
    }
    public void clickLogin() {
        loginPage.clickLogin();
    }
    @Test
    public void inputValidCredentials() {
        inputData("standard_user", "secret_sauce");
        clickLogin();
        Assert.assertTrue(loginPage.isLogoDisplayed(), "Logo is not displayed!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html", "Login failed!");;
        Assert.assertTrue(loginPage.getInventoryItemCount() > 0, "Login failed!");
    }
    @Test
    public void inputEmptyUsername() {
        inputData("", "secret_sauce");
        clickLogin();
        Assert.assertTrue(loginPage.displayErrorButton(), "Website don't display error button!");
        Assert.assertEquals(loginPage.getErrorMessage(),"Epic sadface: Username is required!", "Error message mismatch!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/", "Website still login without Username!");
    }
    @Test
    public void inputInvalidUsername() {
        inputData("invalid_user", "secret_sauce2");
        clickLogin();
        Assert.assertTrue(loginPage.displayErrorButton(), "Website don't display error button!");
        Assert.assertEquals(loginPage.getErrorMessage(),"Epic sadface: Username and password do not match any user in this service", "Error message mismatch!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/", "Website still login with invalid Username!");

    }

    @Test
    public void inputEmptyPassword() {
        inputData("standard_user", "");
        clickLogin();
        Assert.assertTrue(loginPage.displayErrorButton(), "Website don't display error button!");
        Assert.assertEquals(loginPage.getErrorMessage(),"Epic sadface: Password is required", "Error message mismatch!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/", "Website still login without Password!");
    }
    @Test
    public void inputInvalidPassword() {
        inputData("standard_user", "invalid_password");
        clickLogin();
        Assert.assertTrue(loginPage.displayErrorButton(), "Website don't display error button!");
        Assert.assertEquals(loginPage.getErrorMessage(),"Epic sadface: Username and password do not match any user in this service", "Error message mismatch!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/", "Website still login with invalid Password!");

    }

}
