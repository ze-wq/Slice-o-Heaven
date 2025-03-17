public enum PizzaSelection {
    PEPPERONI("Pepperoni", "Lots of pepperoni and extra cheese", 18),
    HAWAIIAN("Hawaiian", "Pineapple, ham, and extra cheese", 22),
    VEGGIE("Veggie", "Green pepper, onion, tomatoes, mushroom, and black olives", 25),
    BBQ_CHICKEN("BBQ Chicken", "Chicken in BBQ sauce, bacon, onion, green pepper, and cheddar cheese", 35),
    EXTRAVAGANZA("Extravaganza", "Pepperoni, ham, Italian sausage, beef, onions, green pepper, mushrooms, black olives, and extra cheese", 45);

    private final String pizzaName;
    private final String pizzaToppings;
    private final double price;

    PizzaSelection(String name, String toppings, double price) {
        this.pizzaName = name;
        this.pizzaToppings = toppings;
        this.price = price;
    }

    public String getPizzaName() { return pizzaName; }
    public String getPizzaToppings() { return pizzaToppings; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return String.format("%s Pizza with %s, for €%.0f", pizzaName, pizzaToppings, price);
    }
}