import java.util.ArrayList;
import java.util.Scanner;

public class HandleOrders {
    private static final double PIZZA_BASE_PRICE = 10.0;
    private String[] pizzasOrdered = new String[10]; 
    private ArrayList<CustomPizza> customPizzas = new ArrayList<>();
    private String[] pizzaSizesOrdered = new String[10]; 
    private String[] sideDishesOrdered = new String[20]; 
    private String[] drinksOrdered = new String[20]; 
    private double totalOrderPrice = 0.0; 
    private int numberOfPizzasOrdered = 0; 
    private StringBuilder pizzaOrderSummary = new StringBuilder();
    private Scanner input = new Scanner(System.in);
    private OrderLogs orderLogs = new OrderLogs();  
    private OrderQueue orderQueue = new OrderQueue(); 

    public void takeOrder() {
        String orderAnother = "Y";
        int j = 0, m = 0, n = 0, p = 0;

        do {
            int i = 1;
            System.out.println("Welcome to Slice-o-Heaven Pizzeria. Here’s what we serve: \n");
            for (PizzaSelection pizza : PizzaSelection.values()) {
                System.out.println(i + ". " + pizza);
                i++;
            }
            System.out.println("6. Custom Pizza with a maximum of 10 toppings that you choose: \n");

            System.out.println("Please enter your choice (1-6): \n");
            int choice = input.nextInt();
            input.nextLine();

            if (choice > 0 && choice < 6) {
                handlePredefinedPizza(choice, j++);
            } else if (choice == 6) {
                handleCustomPizza(j++);
            }

            handlePizzaSize(m++);
            handleSideDish(n++);
            handleDrink(p++);

            System.out.println("Would you like to order another pizza? (Y/N): \n");
            orderAnother = input.nextLine();

        } while (orderAnother.equalsIgnoreCase("Y"));

        createOrderSummary();
        orderQueue.enqueue(new Order(totalOrderPrice));
        orderLogs.addOrderLog("Order processed: " + totalOrderPrice);
    }

    private void handlePredefinedPizza(int choice, int index) {
        try {
            PizzaSelection pizza = PizzaSelection.values()[choice - 1];
            pizzasOrdered[index] = pizza.toString();
            totalOrderPrice += pizza.getPrice();
            orderLogs.addOrderLog("Added " + pizza + " - €" + pizza.getPrice());
            System.out.println("You have selected " + pizza);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid choice!");
        }
    }

    private void handleCustomPizza(int index) {
        StringBuilder toppings = new StringBuilder();
        double customPrice = PIZZA_BASE_PRICE;

        System.out.println("For your custom pizza, here are the toppings:");
        int k = 1;
        for (PizzaToppings topping : PizzaToppings.values()) {
            System.out.println(k + ". " + topping);
            k++;
        }
        System.out.println("Please enter a maximum of 10 topping choices.\n");

        int l = 1;
        do {
            System.out.println("Enter topping #" + l + ". To stop, type 0: ");
            int toppingChoice = input.nextInt();
            input.nextLine();
            if (toppingChoice == 0) break;
            toppings.append(PizzaToppings.values()[toppingChoice - 1]).append(", ");
            customPrice += PizzaToppings.values()[toppingChoice - 1].getPrice();
            l++;
        } while (l <= 10);

        CustomPizza customPizza = new CustomPizza(toppings.toString().trim(), customPrice);
        customPizzas.add(customPizza);
        totalOrderPrice += customPrice;
        orderLogs.addOrderLog("Added custom pizza: " + customPizza);

        pizzasOrdered[index] = customPizza.toString();
        System.out.println("Custom Pizza Created: " + customPizza);
    }

    private void handlePizzaSize(int index) {
        System.out.println("Here are the pizza sizes options: \n");
        for (PizzaSize pizza : PizzaSize.values()) {
            System.out.println((index + 1) + ". " + pizza);
        }
        System.out.println("Pick one size (1 - 3): \n");
        int sizeChoice = input.nextInt();
        input.nextLine();

        try {
            PizzaSize size = PizzaSize.values()[sizeChoice - 1];
            pizzaSizesOrdered[index] = size.toString() + " - €" + size.getPrice();
            totalOrderPrice += size.getPrice();
            orderLogs.addOrderLog("Added size: " + size);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid size choice!");
        }
    }

    private void handleSideDish(int index) {
        System.out.println("Here are the side dishes options: \n");
        for (SideDish dish : SideDish.values()) {
            System.out.println((index + 1) + ". " + dish);
        }
        System.out.println("Pick one side dish (1 - 4): \n");
        int dishChoice = input.nextInt();
        input.nextLine();

        try {
            SideDish dish = SideDish.values()[dishChoice - 1];
            sideDishesOrdered[index] = dish.toString() + " - €" + dish.getPrice();
            totalOrderPrice += dish.getPrice();
            orderLogs.addOrderLog("Added side dish: " + dish);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid dish choice!");
        }
    }

    private void handleDrink(int index) {
        System.out.println("Here are the drinks options: \n");
        for (Drink drink : Drink.values()) {
            System.out.println((index + 1) + ". " + drink);
        }
        System.out.println("Pick one drink (1 - 3): \n");
        int drinkChoice = input.nextInt();
        input.nextLine();

        try {
            Drink drink = Drink.values()[drinkChoice - 1];
            drinksOrdered[index] = drink.toString() + " - €" + drink.getPrice();
            totalOrderPrice += Double.parseDouble(drink.getPrice());
            orderLogs.addOrderLog("Added drink: " + drink);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid drink choice!");
        }
    }

    public void createOrderSummary() {
        pizzaOrderSummary.append("\nThank you for dining with Slice-o-Heaven. Your order details are as follows: \n");

        for (int i = 0; i < numberOfPizzasOrdered; i++) {
            pizzaOrderSummary.append((i + 1) + pizzasOrdered[i] + "\n");
            pizzaOrderSummary.append(pizzaSizesOrdered[i] + "\n");
            pizzaOrderSummary.append(sideDishesOrdered[i] + "\n");
            pizzaOrderSummary.append(drinksOrdered[i] + "\n \n");
        }

        pizzaOrderSummary.append("ORDER TOTAL: €" + totalOrderPrice + "\n");
    }

    @Override
    public String toString() {
        return pizzaOrderSummary.toString();
    }

    public void displayCustomPizzas() {
        throw new UnsupportedOperationException("Unimplemented method 'displayCustomPizzas'");
    }

    public void takeOrder(String customerName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'takeOrder'");
    }
}