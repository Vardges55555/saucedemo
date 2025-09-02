package Tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;

public class SauceDemoTests extends BaseTest {

    @Test(dataProvider = "validUsers")
    public void testSuccessfulLogin(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertTrue(inventoryPage.isPageOpened(), "Inventory page should be visible after login");
    }

    @Test(dataProvider = "checkoutData")
    public void testCompleteCheckoutFlow(String username, String password, String firstName, String lastName, String postalCode) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertTrue(inventoryPage.isPageOpened(), "Inventory page should be visible after login");

        inventoryPage.addFirstItemToCart();
        inventoryPage.goToCart();

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
