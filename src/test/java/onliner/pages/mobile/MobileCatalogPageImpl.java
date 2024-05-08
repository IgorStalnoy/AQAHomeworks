package onliner.pages.mobile;

import onliner.elements.CatalogCategoriesSideMenu;
import onliner.elements.CatalogPageCategoriesTopMenu;
import onliner.elements.enums.CatalogTopMenuEnum;
import onliner.elements.enums.ComputersAndNetworksSideMenuEnum;
import onliner.elements.enums.MainPageTopMenuEnum;
import onliner.pages.CatalogPage;
import onliner.utils.WaitUtil;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

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

    public WebElement getTopMenuItemByName(String name) {
        return WaitUtil.getWebElementAfterFluentWait(categoriesTopMenu.getItemLocator(name));
    }

    public List<WebElement> getAllTopMenuItemsByName(List<String> sectionsNames) {
        List<WebElement> sections = new ArrayList<>();
        for (String name : sectionsNames) {
            try {
                sections.add(getTopMenuItemByName(name));
            } catch (NoSuchElementException | TimeoutException ex) {
                System.out.println(name + " element was not found on the page");
            }
        }
        return sections;
    }

    public WebElement getSideMenuItemByName(String name) {
        return WaitUtil.getWebElementAfterFluentWait(categoriesSideMenu.getItemLocator(name));
    }

    public List<WebElement> getAllSideMenuItemsByName(List<String> sectionsNames) {
        List<WebElement> sections = new ArrayList<>();
        for (String name : sectionsNames) {
            try {
                sections.add(getSideMenuItemByName(name));
            } catch (NoSuchElementException | TimeoutException ex) {
                System.out.println("\"" + name + "\" element was not found on the page");
            }
        }
        return sections;
    }

    public void clickOnSideMenuItem(ComputersAndNetworksSideMenuEnum computersAndNetworksSideMenuEnum) {
        categoriesSideMenu.clickOnItem(computersAndNetworksSideMenuEnum);
    }

    public boolean isAnySideMenuSubcategoryExist() {
        return categoriesSideMenu.isAnySubcategoryExist();
    }

    public List<WebElement> getSideMenuAllSubCategoriesContainsCountAndPriceList() {
        return categoriesSideMenu.getAllSubCategoriesContainsCountAndPrice();
    }

    public List<WebElement> getSideMenuAllSubCategoriesList() {
        return categoriesSideMenu.getAllSubCategories();
    }


    public List<WebElement> getSideMenuAllSubCategoriesContainsNameList() {
        return categoriesSideMenu.getAllSubCategoriesContainsNameList();
    }

    public boolean isCategoriesSideMenuItemVisibleOnPage(ComputersAndNetworksSideMenuEnum computersAndNetworksSideMenuEnum) {
        return categoriesSideMenu.isItemVisibleOnPage(computersAndNetworksSideMenuEnum);
    }

    public List<String> getAllSideMenuItems() {
        return categoriesSideMenu.getAllItems();
    }

    public boolean isSideMenuDisplayed() {
        return getCategoriesSideMenu().isDisplayed();
    }

    public void clickOnTopMenuItem(CatalogTopMenuEnum catalogTopMenuEnum) {
        getCategoriesTopMenu().clickOnItem(catalogTopMenuEnum);
    }

    public List<String> getAllTopMenuItems() {
        return categoriesTopMenu.getAllItemsList();
    }

    public boolean isTopMenuDisplayed() {
        return categoriesTopMenu.isDisplayed();
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
