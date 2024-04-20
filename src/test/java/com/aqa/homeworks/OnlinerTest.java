package com.aqa.homeworks;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class OnlinerTest {
    WebDriver webDriver;
    public final static String CHROME_DRIVER_ROOT = "src/test/resources/chromedriver.exe";
    public final static String MAIN_URL = "https://www.onliner.by/";
    public final static String TVS_SECTION_LOCATOR = "//a[@href=\"https://catalog.onliner.by/tv\"]/span[@class=\"project-navigation__text\"]";
    public final static String FILTER_AWAITING_LOADER_LOCATOR = "//div[@class=\"catalog-interaction__state catalog-int" +
            "eraction__state_initial catalog-interaction__state_disabled catalog-interaction__state_control\"]";
    public final static String FILTER_RESULT_LOCATOR = "//div[starts-with(@class,\"catalog-form__descripti" +
            "on catalog-form__description_primary catalog-form__description_base-additional catalog-form__description_font-weight_se" +
            "mibold catalog-form__description_condensed-other\")]/a[@href]";
    public final static String SCROLL_ELEMENT_INTO_MIDDLE_SCRIPT = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
            + "var elementTop = arguments[0].getBoundingClientRect().top;"
            + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
    public final static String OPEN_ALL_BRAND_FILTERS_LOCATOR = "//div[@class=\"catalog-form__row catalog-form__row_condensed-other\"][7]//div[@class=\"input-style__real\"]";

    @BeforeClass
    public void driverInit() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_ROOT);
        this.webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get(MAIN_URL);
    }

    @Test(dataProvider = "filterData", groups = "smoke")
    public void tvFilterTest(String filterName) {
        WebDriverWait wait = new WebDriverWait(webDriver, 5);
        WebElement tvsSection = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(TVS_SECTION_LOCATOR)));
        tvsSection.click();
        WebElement openAllBrandFilters = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(OPEN_ALL_BRAND_FILTERS_LOCATOR)));
        ((JavascriptExecutor) webDriver).executeScript(SCROLL_ELEMENT_INTO_MIDDLE_SCRIPT, openAllBrandFilters);
        openAllBrandFilters.click();
        String filterLocator = "//div[contains(text(),\"" + filterName + "\")]/../../div[@class=\"i-checkbox__faux\"]";
        WebElement filter = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(filterLocator)));
        filter.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(FILTER_AWAITING_LOADER_LOCATOR)));
        List<WebElement> filterResultList = webDriver.findElements(By.xpath(FILTER_RESULT_LOCATOR));
        Assert.assertTrue(filterResultList.size() > 0, "There are no results on the page after applying the filter");
        Assert.assertTrue(filterResultList.stream()
                .allMatch(l -> l.getText().contains(filterName)), "The page displays TVs from a manufacturer other than the applied filter.");
    }

    @DataProvider(name = "filterData")
    public Object[][] filterData() {
        return new Object[][]{
                {"Renova"},
                {"Sony"},
                {"BBK"},
                {"LG"},
                {"Sony"},
                {"BBK"},
                {"LG"},
                {"Philips"},
                {"BBK"},
                {"LG"},
                {"Sony"},
                {"BBK"}
        };
    }

    @AfterTest()
    public void cleanUp() {
        webDriver.quit();
    }
}
