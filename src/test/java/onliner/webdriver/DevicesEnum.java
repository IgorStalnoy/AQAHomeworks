package onliner.webdriver;

public enum DevicesEnum {
    DESKTOP("DESKTOP"),
    MOBILE("MOBILE");

    private final String value;

    DevicesEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
