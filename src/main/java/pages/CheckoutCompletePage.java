package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage extends BasePage {
    private final By completeHeader = By.className("complete-header");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public boolean isCheckoutComplete() {
        return isVisible(completeHeader) &&
                find(completeHeader).getText().contains("Thank you for your order!");
    }
}
