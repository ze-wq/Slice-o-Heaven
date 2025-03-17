public enum PizzaToppings {
    HAM("Ham", 2),
    PEPPERONI("Pepperoni", 2),
    BEEF("Beef", 2),
    CHICKEN("Chicken", 2),
    SAUSAGE("Sausage", 2),
    PINEAPPLE("Pineapple", 1),
    ONION("Onion", 0.5),
    TOMATOES("Tomatoes", 0.4),
    GREEN_PEPPER("Green Pepper", 0.5),
    BLACK_OLIVES("Black Olives", 0.5),
    SPINACH("Spinach", 0.5),
    CHEDDAR_CHEESE("Cheddar Cheese", 0.8),
    MOZZARELLA_CHEESE("Mozzarella Cheese", 0.8),
    FETA_CHEESE("Feta Cheese", 1),
    PARMESAN_CHEESE("Parmesan Cheese", 1);

    private final String topping;
    private final double toppingPrice;

    PizzaToppings(String topping, double price) {
        this.topping = topping;
        this.toppingPrice = price;
    }

    public String getTopping() { return topping; }
    public double getToppingPrice() { return toppingPrice; }

    @Override
    public String toString() {
        return String.format("%s (€%.1f)", topping, toppingPrice);
    }
}