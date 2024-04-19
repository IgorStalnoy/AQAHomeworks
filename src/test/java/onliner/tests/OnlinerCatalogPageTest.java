package onliner.tests;

import onliner.elements.enums.CatalogTopMenuEnum;
import onliner.elements.enums.ComputersAndNetworksSideMenuEnum;
import onliner.pages.CatalogPage;
import onliner.pages.MainPage;
import onliner.utils.WaitUtil;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

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
    public void allCatalogPageSectionsDisplayedTest(List<String> sectionsNames) {
        catalogPage.openPage();
        Assert.assertTrue(catalogPage.getCategoriesTopMenu().isDisplayed(), " Catalog page categories top menu isn't displayed");
        List<WebElement> sections = new ArrayList<>();
        StringBuilder errorMessage = new StringBuilder();
        for (String name : sectionsNames) {
            try {
                sections.add(WaitUtil.getWebElementAfterFluentWait(catalogPage.getCategoriesTopMenu().getItemLocator(name)));
            } catch (NoSuchElementException | TimeoutException ex) {
                errorMessage.append("'").append(name).append("', ");
            }
        }
        Assert.assertEquals(sections.size(), 10, errorMessage + " sections are not found on the page");
    }

    @Test(dataProvider = "computersAndNetworksSideMenuSections")
    public void computersAndNetworksContainSideMenuSectionsTest(List<String> subsectionsNames) {
        catalogPage.openPage();
        Assert.assertTrue(catalogPage.getCategoriesTopMenu().isDisplayed(),
                " Catalog page categories top menu isn't displayed");
        catalogPage.getCategoriesTopMenu().clickOnItem(CatalogTopMenuEnum.COMPUTERS_AND_NETWORKS);
        Assert.assertTrue(catalogPage.getCategoriesSideMenu().isDisplayed(),
                "Catalog page side menu isn't displayed after " +
                        "click on COMPUTERS_AND_NETWORKS button");
        List<WebElement> sections = new ArrayList<>();
        StringBuilder errorMessage = new StringBuilder();
        for (String name : subsectionsNames) {
            try {
                sections.add(WaitUtil.getWebElementAfterFluentWait(catalogPage.getCategoriesSideMenu().getItemLocator(name)));
            } catch (NoSuchElementException | TimeoutException ex) {
                errorMessage.append("'").append(name).append("', ");
            }
        }
        Assert.assertEquals(sections.size(), 4, errorMessage + " sections are not found on the page");
    }

    @Test
    public void allComponentsParamsDisplayedTest() {
        catalogPage.openPage();
        Assert.assertTrue(catalogPage.getCategoriesTopMenu().isDisplayed(),
                " Catalog page categories top menu isn't displayed");
        catalogPage.getCategoriesTopMenu().clickOnItem(CatalogTopMenuEnum.COMPUTERS_AND_NETWORKS);
        Assert.assertTrue(catalogPage.getCategoriesSideMenu().isDisplayed(),
                " catalog menu isn't displayed after click on COMPUTERS_AND_NETWORKS");
        Assert.assertTrue(catalogPage.getCategoriesSideMenu().isItemVisibleOnPage(ComputersAndNetworksSideMenuEnum.COMPONENTS),
                "COMPONENTS item isn't displayed on the side menu");
        catalogPage.getCategoriesSideMenu().clickOnItem(ComputersAndNetworksSideMenuEnum.COMPONENTS);
        try {
            WaitUtil.getWebElementsAfterFluentWait(catalogPage.getCategoriesSideMenu().getAllSubCategoriesLocator());
        } catch (NoSuchElementException | TimeoutException ex) {
            Assert.fail("No such subcategory elements were found after click on COMPONENTS button");
        }
        SoftAssert softAssert = new SoftAssert();
        List<WebElement> elementsNames = new ArrayList<>();
        try {
            WaitUtil.getWebElementsAfterFluentWait(catalogPage.getCategoriesSideMenu().getAllSubCategoriesContainsNameLocator());
            elementsNames.addAll(webDriver.findElements(catalogPage.getCategoriesSideMenu().getAllSubCategoriesContainsNameLocator()));
        } catch (NoSuchElementException | TimeoutException ex) {
            softAssert.fail("No such subcategory elements with subcategory name were found");
        }
        softAssert.assertEquals(elementsNames.size(), 15, "Not all subcategory elements have name");
        List<WebElement> elementsParams;
        try {
            elementsParams = WaitUtil.getWebElementsAfterFluentWait(catalogPage.getCategoriesSideMenu().getAllSubCategoriesContainsCountAndPriceLocator());
            elementsParams.addAll(webDriver.findElements(catalogPage.getCategoriesSideMenu().getAllSubCategoriesContainsCountAndPriceLocator()));
        } catch (NoSuchElementException | TimeoutException ex) {
            softAssert.fail("No such subcategory elements with with price or count were found");
        }
        softAssert.assertAll("Not all subcategory elements have price or count");
    }

    @DataProvider
    public static Object[][] catalogPageSections() {
        List<String> sections = List.of("Электроника", "Компьютеры и сети", "Бытовая техника", "Стройка и ремонт",
                "Дом и сад", "Авто и мото", "Красота и спорт", "Детям и мамам", "Работа и офис", "Еда");
        return new Object[][]{{sections}};
    }

    @DataProvider
    public static Object[][] computersAndNetworksSideMenuSections() {
        List<String> sections = List.of("Ноутбуки, компьютеры, мониторы", "Комплектующие", "Хранение данных", "Сетевое оборудование");
        return new Object[][]{{sections}};
    }

}
