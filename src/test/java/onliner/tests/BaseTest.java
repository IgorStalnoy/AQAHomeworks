package onliner.tests;

import onliner.pages.CatalogPage;
import onliner.pages.MainPage;
import onliner.pages.desktop.DesktopCatalogPageImpl;
import onliner.pages.desktop.DesktopMainPageImpl;
import onliner.pages.mobile.MobileCatalogPageImpl;
import onliner.pages.mobile.MobileMainPageImpl;
import onliner.utils.Configuration;
import onliner.webdriver.Browser;
import onliner.webdriver.DevicesEnum;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    WebDriver webDriver;

    @BeforeMethod
    public void driverInitialize() {
        webDriver = Browser.getDriver();
    }

    @BeforeMethod
    public MainPage setMainPage() {
        if (Configuration.getProperties().getProperty("device").equals(DevicesEnum.DESKTOP.getValue())) {
            return new DesktopMainPageImpl();
        } else {
            return new MobileMainPageImpl();
        }
    }

    @BeforeMethod
    public CatalogPage setCatalogPage() {
        if (Configuration.getProperties().getProperty("device").equals(DevicesEnum.DESKTOP.getValue())) {
            return new DesktopCatalogPageImpl();
        } else {
            return new MobileCatalogPageImpl();
        }
    }

    @AfterMethod
    public void cleanUp() {
        Browser.close();
    }

}
