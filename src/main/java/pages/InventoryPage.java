
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {

    private final By inventoryContainer = By.id("inventory_container");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }
    public void addFirstItemToCart() {
        click(By.cssSelector(".inventory_item button"));
    }

    public void goToCart() {
        click(By.id("shopping_cart_container"));
    }
    public boolean isPageOpened() {
        return isVisible(inventoryContainer);
    }
}
