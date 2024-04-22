package onliner.pages;

import onliner.elements.CatalogCategoriesSideMenu;
import onliner.elements.CatalogPageCategoriesTopMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface CatalogPage {
    By CATALOG_LOCATOR = By.xpath("//*[contains(@class,'catalog-navigation__title')][contains(text(),'Каталог')]");

    void openPage();

    boolean isOpened();

    CatalogPageCategoriesTopMenu getCategoriesTopMenu();

    CatalogCategoriesSideMenu getCategoriesSideMenu();
    public WebElement getTopMenuItemByName(String name);
    public List<WebElement> getAllTopMenuItemsByName(List<String> sectionsNames);
    public WebElement getSideMenuItemByName(String name);

    public List<WebElement> getAllSideMenuItemsByName(List<String> sectionsNames);
}
