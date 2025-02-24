public class SliceOHeaven {
    public String storeName;
    public String storeAddress;
    public String storeEmail;
    public String storePhone;
    private String storeMenu;
    private String pizzaIngredients;
    private double pizzaPrice;
    private String sides;
    private String drinks;
    private String orderID;
    private String orderDetails;
    private double orderTotal;
    
    public void takeOrder(String id, String details, double total){
        orderID = id;
        orderDetails = details;
        orderTotal = total;
        System.out.println("Order accepted!");
        System.out.println("Order is being prepared");
        try{
            Thread.sleep(5000);
        } catch (InterruptedException e){
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

    private void printReceipt(){
        System.out.println("********RECEIPT********");
        System.out.println("Order ID: " + orderID);
        System.out.println("Order Details: " + orderDetails);
        System.out.println("Order Total: " + orderTotal);
    }
}