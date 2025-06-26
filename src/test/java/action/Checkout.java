package action;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.CheckoutUI;

import java.time.Duration;

public class Checkout {
    WebDriver driver;
    WebDriverWait wait;

    public Checkout(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void inputUserInformation(String firstName, String lastName, String postalCode) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(CheckoutUI.FIRST_NAME_INPUT)).sendKeys(firstName);
        driver.findElement(CheckoutUI.LAST_NAME_INPUT).sendKeys(lastName);
        driver.findElement(CheckoutUI.POSTAL_CODE_INPUT).sendKeys(postalCode);
        driver.findElement(CheckoutUI.CONTINUE_BUTTON).click();
    }

    public boolean isErrorMessageDisplayed() {
        return driver.findElement(CheckoutUI.ERROR_MESSAGE).isDisplayed();
    }

    public boolean isFinishButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(CheckoutUI.FINISH_BUTTON)).isDisplayed();
    }

    public void clickFinishOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(CheckoutUI.FINISH_BUTTON)).click();
    }

    public String getItemTotalText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(CheckoutUI.ITEM_TOTAL)).getText().trim();
    }

    public String getTaxText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(CheckoutUI.TAX)).getText().trim();
    }

    public String getFinalTotalText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(CheckoutUI.TOTAL)).getText().trim();
    }

    public String getCompleteHeaderText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(CheckoutUI.COMPLETE_HEADER)).getText().trim();
    }

    public boolean isBackHomeButtonDisplayed() {
        return driver.findElement(CheckoutUI.BACK_HOME_BUTTON).isDisplayed();
    }

    public void clickBackHomeButton() {
        driver.findElement(CheckoutUI.BACK_HOME_BUTTON).click();
    }
}
