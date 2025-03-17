public enum SideDish {
    CALZONE("Calzone", 15),
    CHICKEN_PUFF("Chicken Puff", 20),
    MUFFIN("Muffin", 12),
    NOTHING("No side dish", 0);

    private final String sideDishName;
    private final double addToPizzaPrice;

    SideDish(String name, double price) {
        this.sideDishName = name;
        this.addToPizzaPrice = price;
    }

    public String getSideDishName() {
        return sideDishName;
    }

    public double getAddToPizzaPrice() {
        return addToPizzaPrice;
    }

    @Override
    public String toString() {
        return String.format("%s (+€%.0f)", sideDishName, addToPizzaPrice);
    }
}