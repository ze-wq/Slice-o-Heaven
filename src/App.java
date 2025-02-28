public class App {
    public static void main(String[] args) throws Exception {
        SliceOHeaven pizzeria = new SliceOHeaven();
        
        pizzeria.takeOrder("001", "1 Pepperoni Pizzas", 11.99);
        
        pizzeria.specialOfTheDay("Hawaiian", "Cheese Sticks", 5.50);
        
        pizzeria.processCardPayment("11111111111111", "3/2", 123);
    }
}