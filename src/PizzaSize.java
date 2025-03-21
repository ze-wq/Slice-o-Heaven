enum PizzaSize {
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

    public String toString() {
        return pizzaSize + " - " + addToPizzaPrice;
    }

    double getPrice() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPrice'");
    }
    
}