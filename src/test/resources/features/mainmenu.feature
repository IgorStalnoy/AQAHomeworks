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
    And sub menu with categories appeared
      | Минск   |
      | Брест   |
      | Витебск |
      | Гомель  |
      | Гродно  |
      | Могилев |
    And sub menu with categories appeared
      | 1-комнатные  |
      | 2-комнатные  |
      | 3-комнатные  |
      | 4+-комнатные |
    And sub menu with categories appeared
      | До 30 000 $     |
      | 30 000–80 000 $ |
      | От 80 000 $     |