package com.aqa.homeworks;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    public final static String MAIN_URL = "https://www.onliner.by/";
    public final static String TVS_SECTION_LOCATOR = "//a[@href=\"https://catalog.onliner.by/tv\"]/span[@class=\"project-navigation__text\"]";

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "E:\\Java Projects\\AQAHomeworks\\src\\test\\resources\\chromedriver.exe");
        ChromeDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get(MAIN_URL);
        WebDriverWait wait = new WebDriverWait(webDriver, 20);
        WebElement tvsSection = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(TVS_SECTION_LOCATOR)));
        tvsSection.click();
        WebElement philips = webDriver.findElement(By.xpath("//div[contains(text(),\"Philips\")][@clas" +
                "s=\"catalog-form__checkbox-sign\"]/../../div[@class=\"i-checkbox__faux\"]"));
        System.out.println(philips.getLocation());
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        ((JavascriptExecutor) webDriver).executeScript(scrollElementIntoMiddle, philips);
        philips.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"catalog-interaction__state catalog-int" +
                "eraction__state_initial catalog-interaction__state_disabled catalog-interaction__state_control\"]")));
        List<WebElement> links = webDriver.findElements(By.xpath("//div[starts-with(@class,\"catalog-form__descripti" +
                "on catalog-form__description_primary catalog-form__description_base-additional catalog-form__description_font-weight_se" +
                "mibold catalog-form__description_condensed-other\")]/a[@href]"));
        links.forEach(l -> System.out.println(l.getText()));
        Boolean bbb = links.stream()
                .noneMatch(l -> l.getText().contains("Philips1"));

//        get(1).getText().contains("Philips"));
        System.out.println(bbb);
        webDriver.quit();
    }

}