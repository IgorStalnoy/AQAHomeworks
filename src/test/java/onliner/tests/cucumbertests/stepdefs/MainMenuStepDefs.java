package onliner.tests.cucumbertests.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import onliner.pages.MainPage;
import onliner.pages.MainPageImpl;
import onliner.webdriver.Browser;
import org.testng.Assert;

public class MainMenuStepDefs {
    MainPage mainPage;

    @Before
    public void initPage() {
        mainPage = new MainPageImpl();
    }

    @Given("main page is opened")
    public void mainPageIsOpened() {
        mainPage.openPage();
        Assert.assertTrue(mainPage.isOpened());
    }

    @When("user move mouse to the {string} menu item")
    public void userMoveMouseToTheMenuItem(String menuName) {
        mainPage.moveMouseToTopMenuElement(menuName);
    }

    @Then("sub menu is opened")
    public void subMenuIsOpened() {
        Assert.assertTrue(mainPage.isDropDownMenuVisible());
    }

    @And("{string} submenu is shown")
    public void submenuIsShown(String subMenuName) {
        Assert.assertTrue(mainPage.isSubMenuDisplayed(subMenuName));
    }

    @And("all {string} submenu categories displayed")
    public void allSubmenuCategoriesDisplayed(String menuName) {
        Assert.assertTrue(mainPage.isAllSubMenuItemsDisplayed(menuName));
    }

    @After
    public void browserClose() {
        Browser.close();
    }
}
