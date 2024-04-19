package onliner.elements.mobile;

import onliner.elements.MainPageTopMenu;
import onliner.elements.enums.MainPageTopMenuEnum;
import onliner.utils.WaitUtil;
import onliner.webdriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MobileMainPageTopMenuImpl implements MainPageTopMenu {
    private static final By HAMBURGER_MENU_LOCATOR = By.xpath("//div[@class='header-style__toggle']/../*[2]");
    private static final By SIDE_MENU_LOCATOR = By.xpath("//span[contains(text(),'Каталог')][@class='header-style__sign']");
    private static final String ITEM_PATTERN = "//span[@class='header-style__sign'][contains(text(),'%s')]/../..";
    private static final int DEFAULT_ELEMENT_WAIT_IN_SECONDS = 10;
    WebDriver webDriver;

    public MobileMainPageTopMenuImpl() {
        this.webDriver = Browser.getDriver();
    }

    public void openSideMenu() {
        WaitUtil.waitUntilElementVisible(HAMBURGER_MENU_LOCATOR, DEFAULT_ELEMENT_WAIT_IN_SECONDS);
        WebElement sideMenu = webDriver.findElement(HAMBURGER_MENU_LOCATOR);
        sideMenu.click();
    }

    public void clickOnItem(MainPageTopMenuEnum MainPageSideMenuEnum) {
        openSideMenu();
        WaitUtil.waitUntilElementVisible(SIDE_MENU_LOCATOR, DEFAULT_ELEMENT_WAIT_IN_SECONDS);
        WebElement menuItemWebElement = webDriver.findElement(By.xpath(String.format(ITEM_PATTERN, MainPageSideMenuEnum.getValue())));
        menuItemWebElement.click();
    }
}
