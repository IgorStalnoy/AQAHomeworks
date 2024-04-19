package onliner.elements.desktop;

import onliner.elements.MainPageTopMenu;
import onliner.elements.enums.MainPageTopMenuEnum;
import onliner.utils.WaitUtil;
import onliner.webdriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DesktopMainPageTopMenuImpl implements MainPageTopMenu {
    private static final int DEFAULT_ELEMENT_WAIT_IN_SECONDS = 10;
    private static final String ITEM_PATTERN = "//ul[@class='b-main-navigation']//*[contains(text(),'%s')]";
    WebDriver webDriver;

    public DesktopMainPageTopMenuImpl() {
        this.webDriver = Browser.getDriver();
    }

    public void clickOnItem(MainPageTopMenuEnum mainPageTopMenuEnum) {
        By xPath = By.xpath(String.format(ITEM_PATTERN, mainPageTopMenuEnum.getValue()));
        WaitUtil.waitUntilElementVisible(xPath, DEFAULT_ELEMENT_WAIT_IN_SECONDS);
        WebElement menuItemWebElement = webDriver.findElement(xPath);
        menuItemWebElement.click();
    }

}
