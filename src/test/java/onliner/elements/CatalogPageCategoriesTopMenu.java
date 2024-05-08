package onliner.elements;

import onliner.elements.enums.CatalogTopMenuEnum;
import onliner.utils.WaitUtil;
import onliner.webdriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CatalogPageCategoriesTopMenu {
    int DEFAULT_ELEMENT_WAIT_IN_SECONDS = 10;
    By TOP_MENU_LOCATOR = By.xpath("//ul[@class='catalog-navigation-classifier']");
    String CATALOG_TOP_SECTION_ITEM_PATTERN = "//ul[@class='catalog-navigation-classifier']/li//*[contains(text(),'%s')]";
    By ALL_CATALOG_TOP_SECTION_ITEMS_LOCATOR = By.xpath("//ul[@class='catalog-navigation-classifier']/li//span[contains(@class," +
            " 'catalog-navigation-classifier__item-title-wrapper')]");
    WebDriver webDriver;

    public CatalogPageCategoriesTopMenu() {
        this.webDriver = Browser.getDriver();
    }

    public void clickOnItem(CatalogTopMenuEnum catalogTopMenuEnum) {
        By xPath = By.xpath(String.format(CATALOG_TOP_SECTION_ITEM_PATTERN, catalogTopMenuEnum.getValue()));
        WaitUtil.waitUntilElementVisible(xPath, DEFAULT_ELEMENT_WAIT_IN_SECONDS);
        WebElement menuItemWebElement = webDriver.findElement(xPath);
        menuItemWebElement.click();
    }


    public By getItemLocator(String itemName) {
        return By.xpath(String.format(CATALOG_TOP_SECTION_ITEM_PATTERN, itemName));
    }

    public boolean isDisplayed() {
        WaitUtil.waitUntilElementVisible(TOP_MENU_LOCATOR, DEFAULT_ELEMENT_WAIT_IN_SECONDS);
        return webDriver.findElement(TOP_MENU_LOCATOR).isDisplayed();
    }

    public List<String> getAllItemsList() {
        return WaitUtil.getWebElementsAfterFluentWait(ALL_CATALOG_TOP_SECTION_ITEMS_LOCATOR).stream().map(WebElement::getText).toList();
    }
}
