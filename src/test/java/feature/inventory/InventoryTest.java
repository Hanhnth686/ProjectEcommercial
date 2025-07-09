package feature.inventory;

import action.InventoryManagement;
import action.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import unltils.CommonLogin;

public class InventoryTest extends CommonLogin {
    LoginPage loginPage;
    InventoryManagement inventoryManagement = new InventoryManagement(driver);

    @BeforeMethod
    public void setupPage() {
        inventoryManagement = new InventoryManagement(driver);
        loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        inventoryManagement.navigateToInventoryPage();
    }

    @Test
    public void addToCartOneItem() {
        inventoryManagement.clickAddToCartButton();
        Assert.assertTrue(inventoryManagement.isAddToCartButtonHide(), "Add to cart button sholud not enabled!");
        Assert.assertTrue(inventoryManagement.isRemoveButtonDisplayed(), "Add to cart unsuccessfully!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html", "URL mismatch!");
        Assert.assertTrue(inventoryManagement.getRemoveButtonCount() == 1, "Add to cart unsuccessfully!");
    }

    @Test
    public void removeOneItem() {
        inventoryManagement.clickRemoveButton();
        Assert.assertTrue(inventoryManagement.isRemoveButtonHide(), "Remove unsuccessfully!");
        Assert.assertTrue(inventoryManagement.isAddToCartButtonDisplayed(), "Remove unsuccessfully!");
        Assert.assertTrue(inventoryManagement.isAddToCartButtonEnabled(), "Remove unsuccessfully!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html", "URL mismatch!");
        Assert.assertTrue(inventoryManagement.getAddToCartButtonCount() == 1, "Remove unsuccessfully!");
    }

    @Test
    public void sortProductsNameAToZ() {
        inventoryManagement.selectDropdownSortNameAToZ();
        Assert.assertTrue(inventoryManagement.checkSortNameAToZ(), "Sort name A to Z is not successfully!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html", "URL mismatch!");
    }

    @Test
    public void sortProductsNameZToA() {
        inventoryManagement.selectDropdownSortNameZToA();
        Assert.assertTrue(inventoryManagement.checkSortNameZToA(), "Sort name Z to A is not successfully!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html", "URL mismatch!");
    }

    @Test
    public void sortProductsPriceLowToHigh() {
        inventoryManagement.selectDropdownSortPriceLowToHigh();
        Assert.assertTrue(inventoryManagement.checkSortPriceLowToHigh(), "Sort price low to high is not successfully!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html", "URL mismatch!");
    }

    @Test
    public void sortProductsPriceHighToLow() {
        inventoryManagement.selectDropdownSortPriceHighToLow();
        Assert.assertTrue(inventoryManagement.checkSortPriceHighToLow(), "Sort price high to low is not successfully!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html", "URL mismatch!");
    }
}
