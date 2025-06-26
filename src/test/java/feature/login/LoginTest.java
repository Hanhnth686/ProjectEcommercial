package feature.login;

import action.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import unltils.CommonLogin;
import unltils.ExcelUntils;

import java.util.List;
import java.util.Map;

public class LoginTest extends CommonLogin {
    public LoginPage loginPage = new LoginPage(driver);
    String excelFilePath = "dataLogin.xlsx";

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

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        List<Map<String, String>> excelData = ExcelUntils.readExcelData(excelFilePath, "Sheet1");
        Object[][] data = new Object[excelData.size()][1];
        for (int i = 0; i < excelData.size(); i++) {
            data[i][0] = excelData.get(i);
        }
        return data;
    }

    @Test(dataProvider = "loginData")
    public void inputValidCredentials(Map<String, String> rowData) {
        String username = rowData.getOrDefault("Username", "");
        String password = rowData.getOrDefault("Password", "");

        inputData(username, password);
        clickLogin();

        Assert.assertTrue(loginPage.isLogoDisplayed(), "Logo is not displayed!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html", "Login failed!");
        Assert.assertTrue(loginPage.getInventoryItemCount() > 0, "Login failed!");
    }

    @Test
    public void inputEmptyUsername(Map<String, String> rowData) {
        String username = rowData.getOrDefault("Username", "");
        String password = rowData.getOrDefault("Password", "");

        inputData(username, password);
        clickLogin();
        Assert.assertTrue(loginPage.displayErrorButton());
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
    }

    @Test
    public void inputInvalidUsername(Map<String, String> rowData) {
        String username = rowData.getOrDefault("Username", "");
        String password = rowData.getOrDefault("Password", "");

        inputData(username, password);
        clickLogin();
        Assert.assertTrue(loginPage.displayErrorButton());
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username and password do not match any user in this service");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
    }

    @Test
    public void inputEmptyPassword(Map<String, String> rowData) {
        String username = rowData.getOrDefault("Username", "");
        String password = rowData.getOrDefault("Password", "");

        inputData(username, password);
        clickLogin();
        Assert.assertTrue(loginPage.displayErrorButton());
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
    }

    @Test
    public void inputInvalidPassword(Map<String, String> rowData) {
        String username = rowData.getOrDefault("Username", "");
        String password = rowData.getOrDefault("Password", "");

        inputData(username, password);
        clickLogin();
        Assert.assertTrue(loginPage.displayErrorButton());
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username and password do not match any user in this service");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
    }
}
