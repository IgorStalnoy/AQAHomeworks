package onliner.pages;

import onliner.elements.CatalogCategoriesSideMenu;
import onliner.elements.CatalogPageCategoriesTopMenu;
import onliner.elements.enums.CatalogTopMenuEnum;
import onliner.elements.enums.ComputersAndNetworksSideMenuEnum;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface CatalogPage {
    By CATALOG_LOCATOR = By.xpath("//*[contains(@class,'catalog-navigation__title')][contains(text(),'Каталог')]");

    void openPage();

    boolean isOpened();

    boolean isTopMenuDisplayed();

    CatalogPageCategoriesTopMenu getCategoriesTopMenu();

    CatalogCategoriesSideMenu getCategoriesSideMenu();

    void clickOnTopMenuItem(CatalogTopMenuEnum catalogTopMenuEnum);

    void clickOnSideMenuItem(ComputersAndNetworksSideMenuEnum computersAndNetworksSideMenuEnum);

    boolean isSideMenuDisplayed();

    boolean isAnySideMenuSubcategoryExist();

    List<WebElement> getSideMenuAllSubCategoriesContainsNameList();

    List<WebElement> getSideMenuAllSubCategoriesList();
    List<WebElement> getSideMenuAllSubCategoriesContainsCountAndPriceList();
    WebElement getTopMenuItemByName(String name);

    List<WebElement> getAllTopMenuItemsByName(List<String> sectionsNames);

    List<String> getAllTopMenuItems();

    WebElement getSideMenuItemByName(String name);

    List<WebElement> getAllSideMenuItemsByName(List<String> sectionsNames);

    List<String> getAllSideMenuItems();

    boolean isCategoriesSideMenuItemVisibleOnPage(ComputersAndNetworksSideMenuEnum computersAndNetworksSideMenuEnum);
}
