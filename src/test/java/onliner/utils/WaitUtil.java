package onliner.utils;

import onliner.webdriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class WaitUtil {
    private static final int DEFAULT_FLUENT_WAIT_TIMEOUT = 5;
    private static final int DEFAULT_FLUENT_WAIT_POLLING = 2;

    public static void waitUntilElementVisible(By locator) {
        Wait<WebDriver> wait = new FluentWait<>(Browser.getDriver()).
                withTimeout(Duration.ofSeconds(DEFAULT_FLUENT_WAIT_TIMEOUT)).
                pollingEvery(Duration.ofSeconds(DEFAULT_FLUENT_WAIT_POLLING));
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (NoSuchElementException | TimeoutException ex) {
            ex.getMessage();
        }
    }

}
