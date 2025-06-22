package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }

    protected void type(By locator, String text) {
        WebElement element = find(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected void clickWhenClickable(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected boolean isVisible(By locator) {
        return find(locator).isDisplayed();
    }
}
