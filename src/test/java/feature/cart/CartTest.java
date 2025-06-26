package feature.cart;

import action.Cart;
import action.InventoryManagement;
import action.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import unltils.CommonCart;

public class CartTest extends CommonCart {
    LoginPage loginPage;
    InventoryManagement inventoryManagement;
    Cart cart = new Cart(driver);

    @BeforeMethod
    public void setupPage() {
        cart = new Cart(driver);
        loginPage = new LoginPage(driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        inventoryManagement.clickAddToCartButton();
        inventoryManagement.clickAddToCartButton();
    }
    @Test
    public void checkDisplayed() {
        cart.navigateToCartURL();
        Assert.assertTrue(cart.isLogoDisplayed(), "Logo is not displayed!");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html", "Login failed!");
        Assert.assertTrue(cart.errorProductDisplayed(), "Cart item information mismatch!");

        Assert.assertEquals(cart.getCartItemCount(), 2, "Cart item count mismatch!");
        Assert.assertTrue(cart.isRemoveButtonDisplayed(), "Remove button is not displayed!");
        Assert.assertTrue(cart.isContinueButtonDisplayed(), "Continue shopping button is not displayed!");
        Assert.assertTrue(cart.isCheckoutButtonDisplayed(), "Checkout button is not displayed!");
    }
    @Test
    public void clickCheckout() {
        cart.clickCheckoutButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html", "URL mismatch!");
    }
}
