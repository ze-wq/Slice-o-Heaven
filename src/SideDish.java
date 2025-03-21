enum SideDish {
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

    public String toString() {
        return sideDishName + " - " + addToPizzaPrice;
    }

    double getPrice() {
        throw new UnsupportedOperationException("Unimplemented method 'getPrice'");
    }
    
}