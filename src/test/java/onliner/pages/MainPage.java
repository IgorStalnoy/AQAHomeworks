package onliner.pages;

public interface MainPage {
    void openPage();

    boolean isOpened();

    void moveMouseToTopMenuElement(String menuName);

    boolean isSubMenuDisplayed(String menuName);

    boolean isDropDownMenuVisible();

    boolean isAllSubMenuItemsDisplayed(String menuName);
}
