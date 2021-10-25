package dictionaries;

public enum SubMenu {
    DESKTOPS("Desktops"),
    NOTEBOOKS("Notebooks"),
    ACCESSORIES("Accessories"),
    CAMERA_PHOTO("Camera, photo"),
    CELL_PHONES("Cell phones");

    private String value;

    SubMenu(String value){
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
