// SliceOHeaven.java
public class SliceOHeaven {
    public String storeName;
    public String storeAddress;
    public String storeEmail;
    public String storePhone;
        
    private static final String DEF_ORDER_ID = "DEF-SOH-099";
    private static final String DEF_PIZZA_INGREDIENTS = "Mozzarella Cheese";
    private static final double DEF_ORDER_TOTAL = 15.00;
       
    private String orderID;
    private String orderDetails;
    private double orderTotal;
    private String pizzaIngredients;
    private String sides;
    private String drinks;
    private String storeMenu;
    private double pizzaPrice;
        
    public SliceOHeaven() {
            this.orderID = DEF_ORDER_ID;
            this.pizzaIngredients = DEF_PIZZA_INGREDIENTS;
            this.orderTotal = DEF_ORDER_TOTAL;
            this.sides = "";
            this.drinks = "";
            this.storeMenu = "";
            this.pizzaPrice = 0.0;
    }
        
    public SliceOHeaven(String id, String ingredients, double total) {
            this(); 
            this.orderID = id;
            this.pizzaIngredients = ingredients;
            this.orderTotal = total;
    }
        
        
    public String getOrderID() { return orderID; }
    public void setOrderID(String orderID) { this.orderID = orderID; }
        
    public String getOrderDetails() { return orderDetails; }
    public void setOrderDetails(String orderDetails) { this.orderDetails = orderDetails; }
        
    public double getOrderTotal() { return orderTotal; }
    public void setOrderTotal(double orderTotal) { this.orderTotal = orderTotal; }
        
    public String getPizzaIngredients() { return pizzaIngredients; }
    public void setPizzaIngredients(String pizzaIngredients) { 
        this.pizzaIngredients = pizzaIngredients;
    }
        
    public String getSides() { return sides; }
    public void setSides(String sides) { this.sides = sides; }
        
    public String getDrinks() { return drinks; }
    public void setDrinks(String drinks) { this.drinks = drinks; }
        
    public String getStoreMenu() { return storeMenu; }
    public void setStoreMenu(String storeMenu) { this.storeMenu = storeMenu; }
        
    public double getPizzaPrice() { return pizzaPrice; }
    public void setPizzaPrice(double pizzaPrice) { this.pizzaPrice = pizzaPrice; }
        
    public void takeOrder(String id, String details, double total) {
        orderID = id;
        orderDetails = details;
        orderTotal = total;
            
        System.out.println("Order accepted!");
        System.out.println("Order is being prepared");
            
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Order is ready for pickup!");
        }
            
        System.out.println("Your order is ready!");
        makePizza();
        printReceipt();
    }
        
    private void makePizza() {
        System.out.println("Making your pizza: " + orderDetails);
        System.out.println("Adding ingredients: " + pizzaIngredients);
        System.out.println("Baking the pizza...");
            
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Something went wrong while preparing your order!");
        }
            
        System.out.println("Your pizza is ready!");
    }
        
    private void printReceipt() {
        System.out.println("********RECEIPT********");
        System.out.println("Order ID: " + orderID);
        System.out.println("Order Details: " + orderDetails);
        System.out.println("Order Total: " + orderTotal);
    }
    private static final String BLACKLISTED_NUMBER = "12345678901234";
    private String cardNumberToDisplay;

    private String pizzaOfTheDay;
    private String sideOfTheDay;
    private double specialPrice;

    public void processCardPayment(String cardNumber, String expiryDate, int cvv) {
        int cardLength = cardNumber.length();
        if (cardLength == 14) {
            System.out.println("Card accepted");
        } else {
            System.out.println("Invalid card");
        }

        int firstCardDigit = Integer.parseInt(cardNumber.substring(0, 1));
        System.out.println("First digit: " + firstCardDigit);

        if (cardNumber.equals(BLACKLISTED_NUMBER)) {
            System.out.println("Card is blacklisted. Please use another card");
        }

        String lastFour = cardNumber.substring(cardNumber.length() - 4);
        int lastFourDigits = Integer.parseInt(lastFour);
        System.out.println("Last four digits: " + lastFourDigits);

        StringBuilder masked = new StringBuilder();
        masked.append(cardNumber.charAt(0));
        for (int i = 1; i < cardNumber.length() - 4; i++) {
            masked.append('*');
        }
        masked.append(cardNumber.substring(cardNumber.length() - 4));
        this.cardNumberToDisplay = masked.toString();
        System.out.println("Masked card number: " + cardNumberToDisplay);
    }

    public void specialOfTheDay(String pizzaOfTheDay, String sideOfTheDay, double specialPrice) {
        this.pizzaOfTheDay = pizzaOfTheDay;
        this.sideOfTheDay = sideOfTheDay;
        this.specialPrice = specialPrice;
        
        System.out.println("\nToday's Special:");
        System.out.println("Pizza: " + this.pizzaOfTheDay);
        System.out.println("Side: " + this.sideOfTheDay);
        System.out.printf("Special Price: $%.2f\n", this.specialPrice);
    }
}
