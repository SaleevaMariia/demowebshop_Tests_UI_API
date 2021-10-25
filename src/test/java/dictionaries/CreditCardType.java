package dictionaries;

public enum CreditCardType {
    VISA("Visa"),
    MASTER_CARD("MasterCard"),
    DISCOVER("Discover"),
    AMEX("Amex");

    private String value;

    CreditCardType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
