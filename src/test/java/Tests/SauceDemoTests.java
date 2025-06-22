package Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;

import java.time.Duration;

public class SauceDemoTests {
    private WebDriver driver;
    private final String baseUrl = "https://www.saucedemo.com";

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();

        // Run Chrome in incognito mode to avoid showing the password manager prompt from Google
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) driver.quit();
    }

    @Test(dataProvider = "validUsers")
    public void testSuccessfulLogin(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertTrue(inventoryPage.isPageOpened(), "Inventory page should be visible after login");
    }

    @Test(dataProvider = "checkoutData")
    public void testCompleteCheckoutFlow(String username, String password, String firstName, String lastName, String postalCode) {
        //Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertTrue(inventoryPage.isPageOpened(), "Inventory page should be visible after login");

        // Add item
        driver.findElement(org.openqa.selenium.By.cssSelector(".inventory_item button")).click();

        // Go to cart
        driver.findElement(org.openqa.selenium.By.id("shopping_cart_container")).click();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();

        CheckoutInformationPage infoPage = new CheckoutInformationPage(driver);
        infoPage.enterInformation(firstName, lastName, postalCode);

        CheckoutOverviewPage overviewPage = new CheckoutOverviewPage(driver);
        overviewPage.finishCheckout();

        CheckoutCompletePage completePage = new CheckoutCompletePage(driver);
        Assert.assertTrue(completePage.isCheckoutComplete(), "Checkout complete page should be displayed");
    }

    @DataProvider(name = "validUsers")
    public Object[][] validUsers() {
        return new Object[][] {
                {"standard_user", "secret_sauce"}
        };
    }

    @DataProvider(name = "checkoutData")
    public Object[][] checkoutData() {
        return new Object[][] {
                {"standard_user", "secret_sauce", "John", "Doe", "12345"}
        };
    }
}
