package onliner.elements.enums;

public enum CatalogTopMenuEnum {
    ELECTRONICS("Электроника"),
    COMPUTERS_AND_NETWORKS("Компьютеры и сети");
    private final String value;

    CatalogTopMenuEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
