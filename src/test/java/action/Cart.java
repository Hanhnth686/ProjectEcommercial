package action;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ui.CartUI;
import ui.InventoryUI;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    WebDriver driver;

    public Cart(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToCartURL() {
        driver.navigate().to("https://www.saucedemo.com/cart.html");
    }

    public int getCartItemCount() {
        return driver.findElements(CartUI.CART_ITEM).size();
    }

    public List<String> cartProductDisplayed() {
        List<WebElement> cartItems = driver.findElements(CartUI.CART_ITEM);
        List<String> tempCartItem = new ArrayList<>();
        int temp_total_quantity = 0;

        for (WebElement cartItem : cartItems) {
            String expectedName = cartItem.findElement(InventoryUI.INVENTORY_ITEM_NAME).getText();
            String expectedPrice = cartItem.findElement(InventoryUI.INVENTORY_ITEM_PRICE).getText();
            String expectedDescription = cartItem.findElement(InventoryUI.ITEM_DESCRIPTION).getText();
            String expectedQuantity = cartItem.findElement(CartUI.CART_ITEM_QUANTITY).getText();
            temp_total_quantity += Integer.parseInt(expectedQuantity);

            String actualName = cartItem.findElement(InventoryUI.INVENTORY_ITEM_NAME).getText();
            String actualPrice = cartItem.findElement(InventoryUI.INVENTORY_ITEM_PRICE).getText();
            String actualDescription = cartItem.findElement(InventoryUI.ITEM_DESCRIPTION).getText();

            if (!actualName.equals(expectedName)) {
                tempCartItem.add("Name mismatch: expected " + expectedName + " but got " + actualName);
            }
            if (!actualPrice.equals(expectedPrice)) {
                tempCartItem.add("Price mismatch: expected " + expectedPrice + " but got " + actualPrice);
            }
            if (!actualDescription.equals(expectedDescription)) {
                tempCartItem.add("Description mismatch: expected " + expectedDescription + " but got " + actualDescription);
            }
        }

        if (temp_total_quantity != CartItemQuantity()) {
            tempCartItem.add("Total quantity mismatch!");
        }

        return tempCartItem;
    }

    public boolean errorProductDisplayed() {
        List<String> errors = cartProductDisplayed();
        for (String error : errors) {
            System.out.println("Lỗi sản phẩm trong giỏ hàng: " + error);
        }
        return errors.isEmpty();
    }

    public int CartItemQuantity() {
        return driver.findElements(CartUI.CART_TOTAL_QUANTITY).size();
    }

    public boolean isRemoveButtonDisplayed() {
        return driver.findElement(CartUI.CART_REMOVE_BUTTON).isDisplayed();
    }

    public boolean isCheckoutButtonDisplayed() {
        return driver.findElement(CartUI.CART_CHECKOUT_BUTTON).isDisplayed();
    }

    public void clickCheckoutButton() {
        driver.findElement(CartUI.CART_CHECKOUT_BUTTON).click();
    }

    public boolean isLogoDisplayed() {
        return driver.findElement(InventoryUI.APP_LOGO).isDisplayed();
    }

    public boolean isContinueButtonDisplayed() {
        return driver.findElement(CartUI.CONTINUE_SHOPPING_BUTTON).isDisplayed();
    }
}
