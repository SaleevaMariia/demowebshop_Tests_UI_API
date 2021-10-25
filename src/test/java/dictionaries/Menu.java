package dictionaries;

public enum Menu {
    BOOKS("Books"),
    COMPUTERS("Computers"),
    ELECTRONICS("Electronics"),
    APPAREL_SHOES("Apparel & Shoes"),
    DIGITAL_DOWNLOADS("Digital downloads"),
    JEWELRY("Jewelry");
    private String value;

    Menu(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
