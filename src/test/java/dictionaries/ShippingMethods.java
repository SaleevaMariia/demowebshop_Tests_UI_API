package dictionaries;

public enum ShippingMethods {
    GROUND(0, 10.00),
    NEXT_DAY_AIR(1, 40.00),
    SECOND_DAY_AIR(2, 20.00);

    private int id;
    private double price;

    ShippingMethods(int id, double price) {
        this.id = id;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "ShippingMethods{" +
                "id=" + id +
                ", price=" + price +
                '}';
    }
}
