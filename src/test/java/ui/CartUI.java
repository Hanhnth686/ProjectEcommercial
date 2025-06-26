package ui;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;

public class CartUI {
    public static final By CART_ITEM = By.className("cart_item");
    public static final By CART_ITEM_NAME = By.className("inventory_item_name");
    public static final By CART_ITEM_QUANTITY = By.className("cart_quantity");
    public static final By CART_TOTAL_QUANTITY = By.className("shopping_cart_badge");
    public static final By CART_ITEM_PRICE = By.className("inventory_item_price");
    public static final By CART_CHECKOUT_BUTTON = By.id("checkout");
    public static final By CART_ITEM_DESCRIPTION = By.className("inventory_item_desc");
    public static final By CONTINUE_SHOPPING_BUTTON = By.xpath("//button[@id='continue-shopping']");
    public static final By CART_REMOVE_BUTTON = By.xpath("//button[contains(text(),'Remove')]");
}
