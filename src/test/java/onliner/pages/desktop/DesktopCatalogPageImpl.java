package onliner.pages.desktop;

import onliner.elements.CatalogPageCategoriesTopMenu;
import onliner.elements.CatalogCategoriesSideMenu;
import onliner.elements.enums.MainPageTopMenuEnum;
import onliner.pages.CatalogPage;
import onliner.utils.WaitUtil;

public class DesktopCatalogPageImpl extends DesktopMainPageImpl implements CatalogPage {
    private static final MainPageTopMenuEnum MENU_ELEMENT = MainPageTopMenuEnum.CATALOG;
    private static final int DEFAULT_PAGE_WAIT_IN_SECONDS = 10;
    private final CatalogPageCategoriesTopMenu categoriesTopMenu;
    private final CatalogCategoriesSideMenu categoriesSideMenu;

    public DesktopCatalogPageImpl() {
        this.categoriesTopMenu = new CatalogPageCategoriesTopMenu();
        this.categoriesSideMenu = new CatalogCategoriesSideMenu();
    }

    @Override
    public void openPage() {
        getMainPageTopMenu().clickOnItem(MENU_ELEMENT);
    }

    @Override
    public boolean isOpened() {
        WaitUtil.waitUntilElementVisible(CATALOG_LOCATOR, DEFAULT_PAGE_WAIT_IN_SECONDS);
        return getWebDriver().findElement(CATALOG_LOCATOR).isDisplayed();
    }

    public CatalogPageCategoriesTopMenu getCategoriesTopMenu() {
        return categoriesTopMenu;
    }

    public CatalogCategoriesSideMenu getCategoriesSideMenu() {
        return categoriesSideMenu;
    }
}
