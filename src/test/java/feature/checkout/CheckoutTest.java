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
        inventoryManagement = new InventoryManagement(driver);
        checkout = new Checkout(driver);

        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        inventoryManagement.clickAddToCartButton();
        cart.navigateToCartURL();
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
    public double parsePrice(String priceText) {
        priceText = priceText.replaceAll("[^\\d.]", "");
        return Double.parseDouble(priceText);
    }
    public void testTotalAmountCalculation() {
        checkout.inputUserInformation("Anna", "Le", "56789");

        String itemTotalText = checkout.getItemTotalText();
        String taxText = checkout.getTaxText();
        String totalText = checkout.getFinalTotalText();

        double itemTotal = parsePrice(itemTotalText);
        double tax = parsePrice(taxText);
        double total = parsePrice(totalText);

        Assert.assertEquals(itemTotal + tax, total, 0.01);

        System.out.println("Item Total: " + itemTotal);
        System.out.println("Tax: " + tax);
        System.out.println("Total: " + total);

        Assert.assertTrue(itemTotalText.contains("$"), "Item total missing $");
        Assert.assertTrue(taxText.contains("$"), "Tax missing $");
        Assert.assertTrue(totalText.contains("$"), "Total missing $");
    }

    @Test
    public void testBackHomeButtonDisplayed() {
        checkout.inputUserInformation("Back", "Test", "00000");
        checkout.clickFinishOrder();
        Assert.assertTrue(checkout.isBackHomeButtonDisplayed(), "Back Home button not visible!");
    }
}
