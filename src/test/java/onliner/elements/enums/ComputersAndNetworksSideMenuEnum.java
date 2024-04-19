package onliner.elements.enums;

public enum ComputersAndNetworksSideMenuEnum {
    NOTEBOOKS_AND_COMPUTERS_AND_MONITORS("Ноутбуки, компьютеры, мониторы"),
    COMPONENTS("Комплектующие");
    private final String value;

    ComputersAndNetworksSideMenuEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

