package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.utils.Config;

import java.time.Duration;


public class BasePage {
    protected WebDriver driver;
    protected final int DEFAULT_TIMEOUT = Config.getInt("defaultTimeout");

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

    protected void click(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected boolean isVisible(By locator) {
        return find(locator).isDisplayed();
    }
}
