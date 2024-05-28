package onliner.pages;

import onliner.elements.MainPageTopMenuImpl;
import onliner.utils.WaitUtil;
import org.openqa.selenium.By;

public class MainPageImpl extends Page implements MainPage {
    private final MainPageTopMenuImpl mainPageTopMenu;
    private static final By MAIN_PAGE_LOCATOR = By.xpath("//*[@class='b-main-navigation']");

    public MainPageImpl() {
        this.mainPageTopMenu = new MainPageTopMenuImpl();
    }

    @Override
    public boolean isOpened() {
        WaitUtil.waitUntilElementVisible(MAIN_PAGE_LOCATOR);
        System.out.println(getWebDriver().getCurrentUrl());
        return getWebDriver().findElement(MAIN_PAGE_LOCATOR).isDisplayed() && getWebDriver().getCurrentUrl().equals(MAIN_URL);
    }

    @Override
    public void moveMouseToTopMenuElement(String menuName) {
        mainPageTopMenu.moveToElement(menuName);
    }

    @Override
    public boolean isSubMenuDisplayed(String menuName) {
        return mainPageTopMenu.isSubMenuDisplayed(menuName);
    }

    @Override
    public boolean isDropDownMenuVisible() {
        return mainPageTopMenu.isDropDownMenuVisible();
    }

    @Override
    public boolean isAllSubMenuItemsDisplayed(String menuName) {
        return mainPageTopMenu.isAllSubMenuItemsDisplayed(menuName);
    }
}
