package dictionaries;

public enum Gender {
    MALE("Male"), FEMALE("Female");

    private String value;

    @Override
    public String toString() {
        return value == MALE.getValue() ? "M" : "F";
    }

    Gender(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
