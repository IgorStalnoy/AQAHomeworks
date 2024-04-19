package onliner.pages;

import onliner.elements.CatalogCategoriesSideMenu;
import onliner.elements.CatalogPageCategoriesTopMenu;
import org.openqa.selenium.By;

public interface CatalogPage {
    By CATALOG_LOCATOR = By.xpath("//*[contains(@class,'catalog-navigation__title')][contains(text(),'Каталог')]");

    void openPage();

    boolean isOpened();

    CatalogPageCategoriesTopMenu getCategoriesTopMenu();

    CatalogCategoriesSideMenu getCategoriesSideMenu();
}
