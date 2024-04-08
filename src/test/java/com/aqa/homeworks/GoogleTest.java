package com.aqa.homeworks;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class GoogleTest {
    public final static String MAIN_URL = "https://www.onliner.by/";
    public final static String TVS_SECTION_LOCATOR = "//a[@href=\"https://catalog.onliner.by/tv\"]/span[@class=\"project-navigation__text\"]";
    public final static String FILTER_AWAITING_LOADER_LOCATOR = "//div[@class=\"catalog-interaction__state catalog-int" +
            "eraction__state_initial catalog-interaction__state_disabled catalog-interaction__state_control\"]";
    public final static String FILTER_RESULT_LOCATOR = "//div[starts-with(@class,\"catalog-form__descripti" +
            "on catalog-form__description_primary catalog-form__description_base-additional catalog-form__description_font-weight_se" +
            "mibold catalog-form__description_condensed-other\")]/a[@href]";

    @BeforeMethod
    public void driverInitialize() {
//        System.setProperty("webdriver.chrome.driver", "E:\\Java Projects\\AQAHomeworks\\src\\test\\resources\\chromedriver.exe");
//        webDriver = new ChromeDriver();
//        webDriver.get("https://www.google.com/");
    }

    @Test(dataProvider = "filterData")
    public void tvFilterTest(WebDriver webDriver, String filterName) {
        webDriver.manage().window().maximize();
        webDriver.get(MAIN_URL);
        WebDriverWait wait = new WebDriverWait(webDriver, 20);
        WebElement tvsSection = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(TVS_SECTION_LOCATOR)));
        tvsSection.click();
        String filterLocator = "//div[contains(text(),\"" + filterName + "\")][@clas" +
                "s=\"catalog-form__checkbox-sign\"]/../../div[@class=\"i-checkbox__faux\"]";
        WebElement filter = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(filterLocator)));
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        ((JavascriptExecutor) webDriver).executeScript(scrollElementIntoMiddle, filter);
        filter.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(FILTER_AWAITING_LOADER_LOCATOR)));
        List<WebElement> filterResultList = webDriver.findElements(By.xpath(FILTER_RESULT_LOCATOR));
        boolean isOnlyFilteredItemsDisplayed = filterResultList.stream()
                .noneMatch(l -> l.getText().contains(filterName));
        webDriver.quit();
    }
//
//    @DataProvider
//    public





//    @AfterMethod
//    public void cleanUp() {
//        if (webDriver != null) {
//            webDriver.quit();
//        }
//    }

}
