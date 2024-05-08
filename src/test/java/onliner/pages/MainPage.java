package onliner.pages;

import onliner.elements.MainPageTopMenu;

public interface MainPage {


    void openPage();

    boolean isOpened();

    MainPageTopMenu getMainPageTopMenu();
}
