package ui;

import org.openqa.selenium.By;

public class InventoryUI { // Lớp InventoryUI đại diện cho trang Inventory
    public static final By INVENTORY_ITEM = By.className("inventory_item");
    public static final By REMOVE_BUTTON = By.xpath("//button[@id='remove-sauce-labs-backpack']");
}
