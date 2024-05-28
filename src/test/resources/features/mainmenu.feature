@Onliner
Feature: Check submenu elements exist

  Scenario:
    Given main page is opened
    When user move mouse to the "Автобарахолка" menu item
    Then sub menu is opened
    And "Отзывы о марке" submenu is shown
    And "Новые авто" submenu is shown
    And "С пробегом" submenu is shown

  Scenario:
    Given main page is opened
    When user move mouse to the "Дома и квартиры" menu item
    Then sub menu is opened
    And all "Дома и квартиры" submenu categories displayed