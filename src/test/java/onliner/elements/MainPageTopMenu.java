package onliner.elements;

public interface MainPageTopMenu {
    void moveToElement(String menuName);

    boolean isSubMenuDisplayed(String menuName);

    boolean isDropDownMenuVisible();

    boolean isAllSubMenuItemsDisplayed(String menuName);
}
