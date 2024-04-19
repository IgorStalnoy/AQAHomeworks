package onliner.elements.enums;

public enum MainPageTopMenuEnum {
    CATALOG("Каталог"),
    NEWS("Новости");
    private final String value;

    MainPageTopMenuEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
