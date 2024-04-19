package onliner.webdriver;

import onliner.utils.Configuration;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class Browser {
    private static final int DEFAULT_WAIT_IN_SECONDS = 5;
    private static WebDriver driver;

    private Browser() {
    }

    private static void initDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = BrowserFactory.createDriver(BrowserType.valueOf(Configuration.getProperties().getProperty("browser")));
        driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_IN_SECONDS, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    public static void close() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
