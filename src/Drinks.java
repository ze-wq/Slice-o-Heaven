enum Drinks {
    COCA_COLA("Coca Cola", 8),
    COCOA_DRINK("Cocoa Drink", 10),
    NOTHING("No drinks", 0);

    private final String drinkName;
    private final double addToPizzaPrice;

    Drinks(String name, double price) {
        this.drinkName = name;
        this.addToPizzaPrice = price;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public double getAddToPizzaPrice() {
        return addToPizzaPrice;
    }

    public String toString() {
        return drinkName + " - " + addToPizzaPrice;
    }

    
}