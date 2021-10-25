package dictionaries;

public enum PaymentMethods {
    CASH_OD_DELIVERY(0, 7.00),
    CHECK(1, 5.00),
    CREDIT_CARD(2, 0.00),
    PURCHASE_ORDER(3, 0.00);

    private int id;
    private double price;

    PaymentMethods(int id, double price) {
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
        return "PaymentMethods{" +
                "id=" + id +
                ", price=" + price +
                '}';
    }
}
