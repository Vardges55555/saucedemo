package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage {
    private WebDriver driver;

    @FindBy(className = "complete-header")
    private WebElement completeHeader;

    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isCheckoutComplete() {
        return completeHeader.isDisplayed() && completeHeader.getText().contains("Thank you for your order!");
    }
}
