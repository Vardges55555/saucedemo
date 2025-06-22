package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutInformationPage extends BasePage {
    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By continueButton = By.id("continue");

    public CheckoutInformationPage(WebDriver driver) {
        super(driver);
    }

    public void enterInformation(String first, String last, String postal) {
        type(firstName, first);
        type(lastName, last);
        type(postalCode, postal);
        clickWhenClickable(continueButton);
    }
}
