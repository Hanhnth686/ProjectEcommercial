package action;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ui.InventoryUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventoryManagement {
    WebDriver driver;

    public InventoryManagement(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToInventoryPage() {
        driver.navigate().to("https://www.saucedemo.com/inventory.html");
    }

    public boolean isLogoInventoryDisplayed() {
        return driver.findElement(InventoryUI.APP_LOGO).isDisplayed();
    }

    public int getInventoryItemCount() {
        return driver.findElements(InventoryUI.INVENTORY_ITEM).size();
    }

    public void clickAddToCartButton() {
        driver.findElement(InventoryUI.ADDTOCART_BUTTON).click();
    }

    public boolean isAddToCartButtonDisplayed() {
        return driver.findElement(InventoryUI.ADDTOCART_BUTTON).isDisplayed();
    }

    public boolean isAddToCartButtonEnabled() {
        return driver.findElement(InventoryUI.ADDTOCART_BUTTON).isEnabled();
    }

    public int getAddToCartButtonCount() {
        return driver.findElements(InventoryUI.ADDTOCART_BUTTON).size();
    }

    public boolean isAddToCartButtonHide() {
        return driver.findElements(InventoryUI.ADDTOCART_BUTTON).isEmpty();
    }

    public boolean isRemoveButtonDisplayed() {
        return driver.findElement(InventoryUI.REMOVE_BUTTON).isDisplayed();
    }

    public boolean isRemoveButtonHide() {
        return driver.findElements(InventoryUI.REMOVE_BUTTON).isEmpty();
    }

    public int getRemoveButtonCount() {
        return driver.findElements(InventoryUI.REMOVE_BUTTON).size();
    }

    public void clickRemoveButton() {
        driver.findElement(InventoryUI.REMOVE_BUTTON).click();
    }

    public void selectDropdownSortNameAToZ() {
        Select dropdown = new Select(driver.findElement(InventoryUI.INVENTORY_ITEM));
        dropdown.selectByIndex(0);
    }

    public boolean checkSortNameAToZ() {
        List<WebElement> productNameElements = driver.findElements(InventoryUI.INVENTORY_ITEM_NAME);
        List<String> productNameList = new ArrayList<>();
        for (WebElement productName : productNameElements) {
            productNameList.add(productName.getText().trim());
        }

        List<String> sortNameAToZ = new ArrayList<>(productNameList);
        Collections.sort(sortNameAToZ);
        return productNameList.equals(sortNameAToZ);
    }

    public void selectDropdownSortNameZToA() {
        Select dropdown = new Select(driver.findElement(InventoryUI.INVENTORY_ITEM));
        dropdown.selectByIndex(1);
    }

    public boolean checkSortNameZToA() {
        List<WebElement> productNameElements = driver.findElements(InventoryUI.INVENTORY_ITEM_NAME);
        List<String> productNameList = new ArrayList<>();
        for (WebElement productName : productNameElements) {
            productNameList.add(productName.getText().trim());
        }

        List<String> sortNameZToA = new ArrayList<>(productNameList);
        Collections.sort(sortNameZToA, Collections.reverseOrder());
        return productNameList.equals(sortNameZToA);
    }

    public void selectDropdownSortPriceLowToHigh() {
        Select dropdown = new Select(driver.findElement(InventoryUI.INVENTORY_ITEM));
        dropdown.selectByIndex(2);
    }

    public boolean checkSortPriceLowToHigh() {
        List<WebElement> priceElements = driver.findElements(InventoryUI.INVENTORY_ITEM_PRICE);
        List<Double> priceList = new ArrayList<>();
        for (WebElement price : priceElements) {
            String priceText = price.getText().replace("$", "").trim();
            priceList.add(Double.parseDouble(priceText));
        }

        List<Double> sortPriceLowToHigh = new ArrayList<>(priceList);
        Collections.sort(sortPriceLowToHigh);
        return priceList.equals(sortPriceLowToHigh);
    }

    public void selectDropdownSortPriceHighToLow() {
        Select dropdown = new Select(driver.findElement(InventoryUI.INVENTORY_ITEM));
        dropdown.selectByIndex(3);
    }

    public boolean checkSortPriceHighToLow() {
        List<WebElement> priceElements = driver.findElements(InventoryUI.INVENTORY_ITEM_PRICE);
        List<Double> priceList = new ArrayList<>();
        for (WebElement price : priceElements) {
            String priceText = price.getText().replace("$", "").trim();
            priceList.add(Double.parseDouble(priceText));
        }

        List<Double> sortPriceLowToHigh = new ArrayList<>(priceList);
        sortPriceLowToHigh.sort(Collections.reverseOrder());
        return priceList.equals(sortPriceLowToHigh);
    }

}
