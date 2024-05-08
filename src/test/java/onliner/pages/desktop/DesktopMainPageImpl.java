package onliner.pages.desktop;

import onliner.elements.desktop.DesktopMainPageTopMenuImpl;
import onliner.pages.MainPage;
import onliner.pages.Page;
import onliner.utils.WaitUtil;
import org.openqa.selenium.By;

public class DesktopMainPageImpl extends Page implements MainPage {
    private final DesktopMainPageTopMenuImpl mainPageTopMenu;
    private static final int DEFAULT_PAGE_WAIT_IN_SECONDS = 10;
    private static final By MAIN_PAGE_LOCATOR = By.xpath("//*[@class='b-main-navigation']");

    public DesktopMainPageImpl() {
        this.mainPageTopMenu = new DesktopMainPageTopMenuImpl();
    }

    @Override
    public boolean isOpened() {
        WaitUtil.waitUntilElementVisible(MAIN_PAGE_LOCATOR, DEFAULT_PAGE_WAIT_IN_SECONDS);
        System.out.println(getWebDriver().getCurrentUrl());
        return getWebDriver().findElement(MAIN_PAGE_LOCATOR).isDisplayed() && getWebDriver().getCurrentUrl().equals(MAIN_URL);
    }

    @Override
    public DesktopMainPageTopMenuImpl getMainPageTopMenu() {
        return mainPageTopMenu;
    }
}
