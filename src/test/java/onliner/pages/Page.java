package onliner.pages;

import onliner.webdriver.Browser;
import org.openqa.selenium.WebDriver;

public abstract class Page {
    public final String MAIN_URL = "https://www.onliner.by/";
    private final WebDriver webDriver = Browser.getDriver();

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void openPage() {
        getWebDriver().get(MAIN_URL);
    }

}
