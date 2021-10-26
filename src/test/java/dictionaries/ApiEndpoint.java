package dictionaries;

public enum ApiEndpoint {
    LOGIN("/login"),
    REGISTER("/register");

    private final String path;

    ApiEndpoint(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
