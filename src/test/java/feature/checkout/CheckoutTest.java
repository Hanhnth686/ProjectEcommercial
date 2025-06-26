package feature.checkout;

import action.Cart;
import action.Checkout;
import action.InventoryManagement;
import action.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import unltils.CommonCart;

public class CheckoutTest extends CommonCart {
    LoginPage loginPage;
    InventoryManagement inventoryManagement;
    Cart cart;
    Checkout checkout;

    @BeforeMethod
    public void setupPage() {
        loginPage = new LoginPage(driver);
        cart = new Cart(driver);
        checkout = new Checkout(driver);

        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        Assert.assertTrue(inventoryManagement.isLogoInventoryDisplayed(), "Login failed: Logo not displayed!");
        inventoryManagement.clickAddToCartButton();
        driver.navigate().to("https://www.saucedemo.com/cart.html");
        Assert.assertTrue(driver.getCurrentUrl().contains("cart"), "Not navigated to cart!");
        cart.clickCheckoutButton();
    }

    @Test
    public void testUserInfoCheckout() {
        checkout.inputUserInformation("John", "Doe", "12345");
        Assert.assertTrue(checkout.isFinishButtonDisplayed(), "Finish button not displayed!");
    }

    @Test
    public void testFinishOrderAndCompleteHeader() {
        checkout.inputUserInformation("Jane", "Smith", "98765");
        checkout.clickFinishOrder();
        Assert.assertEquals(checkout.getCompleteHeaderText(), "Thank you for your order!", "Complete header mismatch!");
    }

    @Test
    public void testTotalAmountCalculation() {
        checkout.inputUserInformation("Anna", "Le", "56789");
        String itemTotal = checkout.getItemTotalText();
        String tax = checkout.getTaxText();
        String total = checkout.getFinalTotalText();

        System.out.println("Item Total: " + itemTotal);
        System.out.println("Tax: " + tax);
        System.out.println("Total: " + total);

        Assert.assertTrue(itemTotal.contains("$"), "Item total missing $");
        Assert.assertTrue(tax.contains("$"), "Tax missing $");
        Assert.assertTrue(total.contains("$"), "Total missing $");
    }

    @Test
    public void testBackHomeButtonDisplayed() {
        checkout.inputUserInformation("Back", "Test", "00000");
        checkout.clickFinishOrder();
        Assert.assertTrue(checkout.isBackHomeButtonDisplayed(), "Back Home button not visible!");
    }
}
