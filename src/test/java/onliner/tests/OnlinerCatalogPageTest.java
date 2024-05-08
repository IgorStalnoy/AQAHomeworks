package onliner.tests;

import onliner.elements.enums.CatalogTopMenuEnum;
import onliner.elements.enums.ComputersAndNetworksSideMenuEnum;
import onliner.pages.CatalogPage;
import onliner.pages.MainPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class OnlinerCatalogPageTest extends BaseTest {
    private MainPage mainPage;
    private CatalogPage catalogPage;

    @BeforeMethod
    public void driverInit() {
        driverInitialize();
        this.mainPage = setMainPage();
        this.catalogPage = setCatalogPage();
        mainPage.openPage();
    }

    @Test
    public void mainPageCanBeOpenedTest() {
        Assert.assertTrue(mainPage.isOpened(), "Main page isn't opened after click by catalog menu item");
    }

    @Test(dataProvider = "catalogPageSections")
    public void allCatalogPageSectionsDisplayedTest(String sectionsName) {
        catalogPage.openPage();
        Assert.assertTrue(catalogPage.isTopMenuDisplayed(), " Catalog page categories top menu isn't displayed");
        Assert.assertTrue(catalogPage.getAllTopMenuItems().contains(sectionsName), sectionsName + " section are not displayed on the page");
    }

    @Test(dataProvider = "computersAndNetworksSideMenuSections")
    public void computersAndNetworksContainSideMenuSectionsTest(String subsectionsName) {
        catalogPage.openPage();
        Assert.assertTrue(catalogPage.isTopMenuDisplayed(),
                " Catalog page categories top menu isn't displayed");
        catalogPage.clickOnTopMenuItem(CatalogTopMenuEnum.COMPUTERS_AND_NETWORKS);
        Assert.assertTrue(catalogPage.isSideMenuDisplayed(),
                "Catalog page side menu isn't displayed after " +
                        "click on COMPUTERS_AND_NETWORKS button");
        Assert.assertTrue(catalogPage.getAllSideMenuItems().contains(subsectionsName), subsectionsName + " is not displayed on the page");
    }

    @Test
    public void allComponentsParamsDisplayedTest() {
        catalogPage.openPage();
        Assert.assertTrue(catalogPage.isTopMenuDisplayed(),
                " Catalog page categories top menu isn't displayed");
        catalogPage.getCategoriesTopMenu().clickOnItem(CatalogTopMenuEnum.COMPUTERS_AND_NETWORKS);
        Assert.assertTrue(catalogPage.isSideMenuDisplayed(),
                " catalog menu isn't displayed after click on COMPUTERS_AND_NETWORKS");
        Assert.assertTrue(catalogPage.isCategoriesSideMenuItemVisibleOnPage(ComputersAndNetworksSideMenuEnum.COMPONENTS),
                "COMPONENTS item isn't displayed on the side menu");
        catalogPage.clickOnSideMenuItem(ComputersAndNetworksSideMenuEnum.COMPONENTS);
        Assert.assertTrue(catalogPage.isAnySideMenuSubcategoryExist(), "No such subcategory " +
                "elements were found after click on COMPONENTS button");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(catalogPage.getSideMenuAllSubCategoriesContainsNameList().size() > 0, "No such subcategory" +
                " elements with subcategory name were found");
        softAssert.assertEquals(catalogPage.getSideMenuAllSubCategoriesList().size(), 15, "Not all subcategory elements have name");
        softAssert.assertTrue(catalogPage.getSideMenuAllSubCategoriesContainsCountAndPriceList().size() > 0,
                "No such subcategory elements with with price or count were found");
        softAssert.assertEquals(catalogPage.getSideMenuAllSubCategoriesContainsCountAndPriceList().size(), 15
                , "Not all subcategory elements have price or count");
        softAssert.assertAll();
    }

    @DataProvider
    public static Object[][] catalogPageSections() {
        return new Object[][]{{"Электроника"}, {"Компьютеры и сети"}, {"Бытовая техника"}, {"Стройка и ремонт"},
                {"Дом и сад"}, {"Авто и мото"}, {"Красота и спорт"}, {"Детям и мамам"}, {"Работа и офис"}, {"Еда"}};
    }

    @DataProvider
    public static Object[][] computersAndNetworksSideMenuSections() {
        return new Object[][]{{"Ноутбуки, компьютеры, мониторы"}, {"Комплектующие"}, {"Хранение данных"}, {"Сетевое оборудование"}};
    }

}
