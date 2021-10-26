package dictionaries;

public enum UIEndpoint {
    CART("/cart"),
    LOGIN("/login"),
    REGISTER("/register");

    private final String path;

    UIEndpoint(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
