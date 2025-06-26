package ui;

import org.openqa.selenium.By;

public class CheckoutUI {
    // Checkout: Your Information
    public static final By FIRST_NAME_INPUT = By.id("first-name");
    public static final By LAST_NAME_INPUT = By.id("last-name");
    public static final By POSTAL_CODE_INPUT = By.id("postal-code");
    public static final By CONTINUE_BUTTON = By.id("continue");
    public static final By ERROR_MESSAGE = By.cssSelector(".error-message-container");

    // Checkout: Overview
    public static final By FINISH_BUTTON = By.id("finish");
    public static final By ITEM_TOTAL = By.className("summary_subtotal_label");
    public static final By TAX = By.className("summary_tax_label");
    public static final By TOTAL = By.className("summary_total_label");

    // Checkout: Complete
    public static final By COMPLETE_HEADER = By.className("complete-header");
    public static final By BACK_HOME_BUTTON = By.id("back-to-products");
}
