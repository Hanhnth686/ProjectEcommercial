package ui;

import org.openqa.selenium.By;

public class InventoryUI { // Lớp InventoryUI đại diện cho trang Inventory
    public static final By APP_LOGO = By.className("app_logo");
    public static final By INVENTORY_ITEM = By.className("inventory_item");
    public static final By INVENTORY_ITEM_NAME = By.className("inventory_item_name");
    public static final By INVENTORY_ITEM_PRICE = By.className("inventory_item_price");
    public static final By ADDTOCART_BUTTON = By.cssSelector("#add-to-cart-sauce-labs-backpack");
    public static final By REMOVE_BUTTON = By.xpath("//button[@id='remove-sauce-labs-backpack']");
    public static final By ITEM_DESCRIPTION = By.className("inventory_item_desc");
}
