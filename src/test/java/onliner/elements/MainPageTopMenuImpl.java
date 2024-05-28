package onliner.elements;

import onliner.utils.WaitUtil;
import onliner.webdriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class MainPageTopMenuImpl implements MainPageTopMenu {
    private static final String ITEM_PATTERN = "//ul[@class='b-main-navigation']//*[contains(text(),'%s')]";
    private static final String SUB_MENU_PATTERN = "//span[contains(text(),'%s')]";
    private static final By DROP_DOWN_MENU_LOCATOR = By.xpath("//div[@class='b-main-navigation__dropdown b-main-navigation__dropdown_visible']");
    WebDriver webDriver = Browser.getDriver();

    @Override
    public void moveToElement(String menuName) {
        By xPath = By.xpath(String.format(ITEM_PATTERN, menuName));
        WaitUtil.waitUntilElementVisible(xPath);
        WebElement menuItemWebElement = webDriver.findElement(xPath);
        Actions actions = new Actions(webDriver);
        actions.moveToElement(menuItemWebElement).perform();
    }

    @Override
    public boolean isSubMenuDisplayed(String menuName) {
        By locator = By.xpath(String.format(SUB_MENU_PATTERN, menuName));
        WaitUtil.waitUntilElementVisible(locator);
        List<WebElement> webElementList = webDriver.findElements(locator);
        return webElementList.stream().allMatch(WebElement::isDisplayed) && webElementList.size() == 1;
    }

    @Override
    public boolean isDropDownMenuVisible() {
        WaitUtil.waitUntilElementVisible(DROP_DOWN_MENU_LOCATOR);
        List<WebElement> webElementList = webDriver.findElements(DROP_DOWN_MENU_LOCATOR);
        return webElementList.size() == 1;
    }

    @Override
    public boolean isAllSubMenuItemsDisplayed(String menuName) {
        switch (menuName) {
            case "Дома и квартиры" -> {
                By citiesLocator = By.xpath(String.format(SUB_MENU_PATTERN, "Минск"));
                WaitUtil.waitUntilElementVisible(citiesLocator);
                List<WebElement> cities = webDriver.findElements(citiesLocator);
                By roomsLocator = By.xpath(String.format(SUB_MENU_PATTERN, "1-комнатные"));
                WaitUtil.waitUntilElementVisible(roomsLocator);
                List<WebElement> rooms = webDriver.findElements(roomsLocator);
                By priceLocator = By.xpath(String.format(SUB_MENU_PATTERN, "До 30 000 $"));
                WaitUtil.waitUntilElementVisible(priceLocator);
                List<WebElement> price = webDriver.findElements(priceLocator);
                return cities.size() > 1 && cities.stream().anyMatch(WebElement::isDisplayed)
                        && rooms.size() > 1 && rooms.stream().allMatch(WebElement::isDisplayed)
                        && price.size() == 1 && price.stream().allMatch(WebElement::isDisplayed);
            }
            case "Каталог", "Новости", "Автобарахолка" -> {
                return true;
            }
            default -> {
                return false;
            }
        }
    }

}
