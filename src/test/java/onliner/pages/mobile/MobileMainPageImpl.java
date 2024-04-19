package onliner.pages.mobile;

import onliner.elements.mobile.MobileMainPageTopMenuImpl;
import onliner.pages.MainPage;
import onliner.pages.Page;
import onliner.utils.WaitUtil;
import org.openqa.selenium.By;

public class MobileMainPageImpl extends Page implements MainPage {
    private final MobileMainPageTopMenuImpl mainPageTopMenu;
    private static final int DEFAULT_PAGE_WAIT_IN_SECONDS = 10;
    private static final By MAIN_PAGE_LOCATOR = By.xpath("//a[@href='https://www.onliner.by']/img");

    public MobileMainPageImpl() {
        this.mainPageTopMenu = new MobileMainPageTopMenuImpl();
    }

    @Override
    public boolean isOpened() {
        WaitUtil.waitUntilElementVisible(MAIN_PAGE_LOCATOR, DEFAULT_PAGE_WAIT_IN_SECONDS);
        System.out.println(getWebDriver().getCurrentUrl());
        return getWebDriver().findElement(MAIN_PAGE_LOCATOR).isDisplayed() && getWebDriver().getCurrentUrl().equals(MAIN_URL);
    }

    @Override
    public MobileMainPageTopMenuImpl getMainPageTopMenu() {
        return mainPageTopMenu;
    }

}
