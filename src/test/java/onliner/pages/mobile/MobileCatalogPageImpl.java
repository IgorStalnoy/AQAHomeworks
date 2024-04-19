package onliner.pages.mobile;

import onliner.elements.CatalogCategoriesSideMenu;
import onliner.elements.CatalogPageCategoriesTopMenu;
import onliner.elements.enums.MainPageTopMenuEnum;
import onliner.pages.CatalogPage;
import onliner.utils.WaitUtil;

public class MobileCatalogPageImpl extends MobileMainPageImpl implements CatalogPage {
    private static final MainPageTopMenuEnum MENU_ELEMENT = MainPageTopMenuEnum.CATALOG;
    private static final int DEFAULT_PAGE_WAIT_IN_SECONDS = 10;
    private final CatalogPageCategoriesTopMenu categoriesTopMenu;
    private final CatalogCategoriesSideMenu categoriesSideMenu;

    public MobileCatalogPageImpl() {
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

    @Override
    public CatalogPageCategoriesTopMenu getCategoriesTopMenu() {
        return categoriesTopMenu;
    }

    @Override
    public CatalogCategoriesSideMenu getCategoriesSideMenu() {
        return categoriesSideMenu;
    }
}
