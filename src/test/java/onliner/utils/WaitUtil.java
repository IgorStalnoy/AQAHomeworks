package onliner.utils;

import onliner.webdriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitUtil {
    public static void waitUntilElementVisible(By locator, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), timeoutInSeconds);
        wait.ignoring(org.openqa.selenium.TimeoutException.class);
        wait.ignoring(org.openqa.selenium.NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement getWebElementAfterFluentWait(By locator) {
        Wait<WebDriver> wait = new FluentWait<>(Browser.getDriver()).
                withTimeout(Duration.ofSeconds(5)).
                pollingEvery(Duration.ofSeconds(1));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static List<WebElement> getWebElementsAfterFluentWait(By locator) {
        Wait<WebDriver> wait = new FluentWait<>(Browser.getDriver()).
                withTimeout(Duration.ofSeconds(5)).
                pollingEvery(Duration.ofSeconds(1)).
                ignoring(org.openqa.selenium.TimeoutException.class)
                .ignoring(org.openqa.selenium.NoSuchElementException.class);
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

}
