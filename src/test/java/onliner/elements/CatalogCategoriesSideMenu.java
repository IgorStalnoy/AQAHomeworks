package onliner.elements;

import onliner.elements.enums.ComputersAndNetworksSideMenuEnum;
import onliner.utils.WaitUtil;
import onliner.webdriver.Browser;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;

public class CatalogCategoriesSideMenu {

    private static final int DEFAULT_ELEMENT_WAIT_IN_SECONDS = 10;
    private static final By CATALOG_SIDE_MENU_LOCATOR = By.xpath("//*[@class='catalog-navigation-list__aside-list']");
    private static final String CATALOG_SIDE_MENU_ITEM_PATTERN = "//*[@class='catalog-navigation-list__aside-list']/*/*[contains(text(),'%s')]";
    private static final By ALL_SUBCATEGORIES_LOCATOR = By.xpath("//*[@class='catalog-navigation-list__dropdown-list']/a");
    private static final By ALL_SUBCATEGORIES_CONTAINS_NAME_LOCATOR = By.xpath("//*[@class='catalog-navigation-list__dropdown-list']" +
            "/a/*/span[contains(@class,'title')][string-length(normalize-space(text()))>0]");
    private static final By ALL_SUBCATEGORIES_CONTAINS_PRICE_AND_COUNT_LOCATOR = By.xpath("//*[@class='catalog-navigation-list__dropdown-list']" +
            "/a/*/span[contains(text(),'товар') and ./span[contains(text(),'от')]]");

    WebDriver webDriver;

    public CatalogCategoriesSideMenu() {
        this.webDriver = Browser.getDriver();
    }

    public void clickOnItem(ComputersAndNetworksSideMenuEnum computersAndNetworksSideMenuEnum) {
        By xPath = By.xpath(String.format(CATALOG_SIDE_MENU_ITEM_PATTERN, computersAndNetworksSideMenuEnum.getValue()));
        WaitUtil.waitUntilElementVisible(xPath, DEFAULT_ELEMENT_WAIT_IN_SECONDS);
        WebElement menuItemWebElement = webDriver.findElement(xPath);
        menuItemWebElement.click();
    }

    public boolean isDisplayed() {
        WaitUtil.waitUntilElementVisible(CATALOG_SIDE_MENU_LOCATOR, DEFAULT_ELEMENT_WAIT_IN_SECONDS);
        return webDriver.findElement(CATALOG_SIDE_MENU_LOCATOR).isDisplayed();
    }

    public By getItemLocator(String itemName) {
        return By.xpath(String.format(CATALOG_SIDE_MENU_ITEM_PATTERN, itemName));
    }

    public boolean isItemVisibleOnPage(ComputersAndNetworksSideMenuEnum computersAndNetworksSideMenuEnum) {
        By xpath = By.xpath(String.format(CATALOG_SIDE_MENU_ITEM_PATTERN, computersAndNetworksSideMenuEnum.getValue()));
        WaitUtil.waitUntilElementVisible(xpath, DEFAULT_ELEMENT_WAIT_IN_SECONDS);
        return webDriver.findElement(xpath).isDisplayed();
    }

    public boolean isAnySubcategoryExist() {
        List<WebElement> subcategoriesList = WaitUtil.getWebElementsAfterFluentWait(ALL_SUBCATEGORIES_LOCATOR);
        return subcategoriesList.size() > 0;
    }
    public List<WebElement> getAllSubCategories() {
        return WaitUtil.getWebElementsAfterFluentWait(ALL_SUBCATEGORIES_CONTAINS_NAME_LOCATOR);
    }
    public List<WebElement> getAllSubCategoriesContainsCountAndPrice() {
        return WaitUtil.getWebElementsAfterFluentWait(ALL_SUBCATEGORIES_CONTAINS_PRICE_AND_COUNT_LOCATOR);
    }

    public List<WebElement> getAllSubCategoriesContainsNameList() {
        return WaitUtil.getWebElementsAfterFluentWait(ALL_SUBCATEGORIES_CONTAINS_NAME_LOCATOR);
    }


}
