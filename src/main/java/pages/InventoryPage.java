package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage {
    private WebDriver driver;

    @FindBy(id = "inventory_container")
    private WebElement inventoryContainer;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened() {
        return inventoryContainer.isDisplayed();
    }
}
