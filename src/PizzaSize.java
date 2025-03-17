public enum PizzaSize {
    LARGE("Large", 10),
    MEDIUM("Medium", 5),
    SMALL("Small", 0);

    private final String pizzaSize;
    private final double addToPizzaPrice;

    PizzaSize(String size, double price) {
        this.pizzaSize = size;
        this.addToPizzaPrice = price;
    }

    public String getPizzaSize() {
        return pizzaSize;
    }

    public double getAddToPizzaPrice() {
        return addToPizzaPrice;
    }

    @Override
    public String toString() {
        return String.format("%s (+€%.0f)", pizzaSize, addToPizzaPrice);
    }
}