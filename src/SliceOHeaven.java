import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SliceOHeaven {
    private static final double PIZZA_BASE_PRICE = 10.0;
    private final String[] pizzasOrdered = new String[10];
    private final String[] pizzaSizesOrdered = new String[10];
    private final String[] sideDishesOrdered = new String[20];
    private final String[] drinksOrdered = new String[20];
    private double totalOrderPrice = 0.0;
    private int orderCount = 0;
    private final Scanner scanner = new Scanner(System.in);
    private String cardNumberToDisplay;
    private static final long BLACKLISTED_NUMBER = 12345678901234L;

    public void takeOrder() {
        boolean continueOrdering = true;
        
        System.out.println("Welcome to Slice-o-Heaven Pizzeria!");
        
        while (continueOrdering && orderCount < 10) {
            processSingleOrder();
            System.out.print("\nWould you like to order another pizza? (Y/N): ");
            continueOrdering = scanner.nextLine().equalsIgnoreCase("Y");
        }
        
        processPayment();
        applyDiscountIfNeeded();
    }

    private void processSingleOrder() {
        System.out.println("\nMenu:");
        int index = 1;
        for (PizzaSelection pizza : PizzaSelection.values()) {
            System.out.printf("%d. %s%n", index++, pizza);
        }
        System.out.println("6. Custom Pizza with a maximum of 10 toppings");
        
        int choice = getValidatedInput(1, 6, "Enter pizza choice (1-6): ");
        handlePizzaSelection(choice);

        System.out.println("\nAvailable Sizes:");
        index = 1;
        for (PizzaSize size : PizzaSize.values()) {
            System.out.printf("%d. %s%n", index++, size);
        }
        int sizeChoice = getValidatedInput(1, 3, "Select size (1-3): ");
        handleSizeSelection(sizeChoice);

        System.out.println("\nSide Dishes:");
        index = 1;
        for (SideDish side : SideDish.values()) {
            System.out.printf("%d. %s%n", index++, side);
        }
        int sideChoice = getValidatedInput(1, 4, "Select side dish (1-4): ");
        handleSideSelection(sideChoice);

        System.out.println("\nDrinks:");
        index = 1;
        for (Drinks drink : Drinks.values()) {
            System.out.printf("%d. %s%n", index++, drink);
        }
        int drinkChoice = getValidatedInput(1, 3, "Select drink (1-3): ");
        handleDrinkSelection(drinkChoice);

        orderCount++;
    }

    private void handlePizzaSelection(int choice) {
        if (choice <= 5) {
            PizzaSelection selected = PizzaSelection.values()[choice - 1];
            pizzasOrdered[orderCount] = selected.toString();
            totalOrderPrice += selected.getPrice();
        } else {
            createCustomPizza();
        }
    }

    private void createCustomPizza() {
        List<PizzaToppings> toppings = new ArrayList<>();
        System.out.println("\nAvailable Toppings:");
        int index = 1;
        for (PizzaToppings topping : PizzaToppings.values()) {
            System.out.printf("%d. %s%n", index++, topping);
        }
        
        System.out.println("Select up to 10 toppings (enter 0 to finish):");
        while (toppings.size() < 10) {
            int choice = getValidatedInput(0, PizzaToppings.values().length, 
                "Select topping (" + (toppings.size() + 1) + "/10): ");
            if (choice == 0) break;
            toppings.add(PizzaToppings.values()[choice - 1]);
        }
        
        double pizzaPrice = PIZZA_BASE_PRICE;
        StringBuilder toppingsDesc = new StringBuilder();
        for (PizzaToppings t : toppings) {
            pizzaPrice += t.getToppingPrice();
            toppingsDesc.append(t.getTopping()).append(", ");
        }
        
        if (toppingsDesc.length() > 0) {
            toppingsDesc.setLength(toppingsDesc.length() - 2);
        }
        
        pizzasOrdered[orderCount] = String.format("Custom Pizza with %s, for €%.1f", 
            toppingsDesc.toString(), pizzaPrice);
        totalOrderPrice += pizzaPrice;
    }

    private void handleSizeSelection(int choice) {
        PizzaSize size = PizzaSize.values()[choice - 1];
        pizzaSizesOrdered[orderCount] = size.toString();
        totalOrderPrice += size.getAddToPizzaPrice();
    }

    private void handleSideSelection(int choice) {
        SideDish side = SideDish.values()[choice - 1];
        sideDishesOrdered[orderCount] = side.toString();
        totalOrderPrice += side.getAddToPizzaPrice();
    }

    private void handleDrinkSelection(int choice) {
        Drinks drink = Drinks.values()[choice - 1];
        drinksOrdered[orderCount] = drink.toString();
        totalOrderPrice += drink.getAddToPizzaPrice();
    }

    private void processPayment() {
        System.out.println("\nPayment Processing:");
        while (true) {
            System.out.print("Enter credit card number (14 digits): ");
            long cardNumber = scanner.nextLong();
            scanner.nextLine();
            
            if (Long.toString(cardNumber).length() != 14) {
                System.out.println("Invalid card number length!");
                continue;
            }
            
            if (cardNumber == BLACKLISTED_NUMBER) {
                System.out.println("This card is blacklisted!");
                continue;
            }
            
            String cardStr = Long.toString(cardNumber);
            cardNumberToDisplay = cardStr.charAt(0) + 
                "************".substring(0, 9) + 
                cardStr.substring(cardStr.length() - 4);
            break;
        }
    }

    private void applyDiscountIfNeeded() {
        System.out.print("\nApply birthday discount? (Y/N): ");
        if (scanner.nextLine().equalsIgnoreCase("Y")) {
            applyBirthdayDiscount();
        }
    }

    private void applyBirthdayDiscount() {
        try {
            System.out.print("Enter birthday (yyyy-MM-dd): ");
            Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine());
            Date today = new Date();
            
            if (isSameDay(birthDate, today)) {
                totalOrderPrice *= 0.5;
                System.out.println("50% birthday discount applied!");
            }
        } catch (Exception e) {
            System.out.println("Invalid date format, discount not applied");
        }
    }

    private boolean isSameDay(Date date1, Date date2) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        return fmt.format(date1).equals(fmt.format(date2));
    }

    private int getValidatedInput(int min, int max, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) return input;
                System.out.println("Invalid input, please try again");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("\n===================== ORDER RECEIPT =====================\n");
        
        for (int i = 0; i < orderCount; i++) {
            receipt.append(String.format("Item %d:%n", i+1))
                   .append("  ").append(pizzasOrdered[i]).append("\n")
                   .append("  ").append(pizzaSizesOrdered[i]).append("\n")
                   .append("  ").append(sideDishesOrdered[i]).append("\n")
                   .append("  ").append(drinksOrdered[i]).append("\n\n");
        }
        
        receipt.append(String.format("Total Price: €%.2f%n", totalOrderPrice))
               .append("Payment Method: ****").append(cardNumberToDisplay.substring(14))
               .append("\n=========================================================");
        
        return receipt.toString();
    }
}