package Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import pages.utils.Config;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected final String baseUrl = "https://www.saucedemo.com";

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        int implicitTimeout = Config.getInt("implicitTimeout");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitTimeout));

        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) driver.quit();
    }
}
