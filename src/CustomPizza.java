public class CustomPizza {
    private String toppings;
    private double price;

    public CustomPizza(String toppings, double price) {
        this.toppings = toppings;
        this.price = price;
    }

    public String getToppings() { return toppings; }
    public void setToppings(String toppings) { this.toppings = toppings; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    @Override
    public String toString() { return "Custom Pizza: " + toppings + " - €" + price; }
}