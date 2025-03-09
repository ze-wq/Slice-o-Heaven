import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.Locale;

class SliceOHeaven {
    private static final long BLACKLISTED_NUMBER = 12345678901234L;
    
    private String storeName = "Slice Heaven";
    private String storeAddress = "123 Pizza Lane";
    private String storeEmail = "support@sliceheaven.com";
    private String storePhone = "555-123-4567";
    
    private String orderID = "DEF-SOH-099";
    private String orderDetails = "Default Order";
    private double orderTotal = 15.00;
    private String pizzaIngredients = "Mozzarella Cheese";
    private String sides = "";
    private String drinks = "";
    private String pizzaPrice = "10.00";
    private String cardNumberToDisplay;
    
    private String pizzaOfTheDay = "";
    private String sideOfTheDay = "";
    private double specialPrice = 0.00;
    
    private Scanner scanner = new Scanner(System.in);

    public void takeOrder() {
        System.out.println("Welcome to Slice Heaven!");
        
        String[] ingredients = askForIngredients();
        String pizzaSize = askForSize();
        String extraCheese = askForExtraCheese();
        String sideDish = askForSide();
        String drinksSelection = askForDrink();
        String wantDiscount = askForDiscountConfirmation();

        calculateOrderTotal(pizzaSize, extraCheese, ingredients);

        if (wantDiscount.equalsIgnoreCase("Y")) {
            applyBirthdayDiscount();
        } else {
            processPayment();
        }

        generateReceipt();
    }

    private String[] askForIngredients() {
        System.out.print("Enter 3 pizza ingredients (separated by spaces): ");
        return scanner.nextLine().split(" +");
    }

    private String askForSize() {
        System.out.print("Choose pizza size (Small/Medium/Large): ");
        return scanner.nextLine().trim();
    }

    private String askForExtraCheese() {
        System.out.print("Do you want extra cheese? (Y/N): ");
        return scanner.nextLine().trim();
    }

    private String askForSide() {
        System.out.print("Select a side dish (Calzone/Garlic bread/None): ");
        return scanner.nextLine().trim();
    }

    private String askForDrink() {
        System.out.print("Choose a drink (Cold Coffee/Cocoa drink/Coke/None): ");
        return scanner.nextLine().trim();
    }

    private String askForDiscountConfirmation() {
        System.out.print("Would you like to use today's half-price offer? (Y/N): ");
        return scanner.nextLine().trim();
    }

    private void calculateOrderTotal(String pizzaSize, String extraCheese, String[] ingredients) {
        double basePrice = 10.00;
        switch (pizzaSize.toLowerCase()) {
            case "medium" -> basePrice = 12.00;
            case "large" -> basePrice = 15.00;
        }
        this.orderTotal = basePrice + (extraCheese.equalsIgnoreCase("Y") ? 2.00 : 0.00);
        this.orderDetails = String.join(", ", ingredients) + " " + pizzaSize;
    }

    private void applyBirthdayDiscount() {
        System.out.print("Enter your birthday (yyyy-mm-dd): ");
        String birthDateStr = scanner.nextLine().trim();
        
        try {
            Date birthDate = new SimpleDateFormat("yyyy-mm-dd").parse(birthDateStr);
            Calendar today = Calendar.getInstance();
            Calendar birth = Calendar.getInstance();
            birth.setTime(birthDate);
            
            int age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
            if (birth.get(Calendar.MONTH) > today.get(Calendar.MONTH) ||
                (birth.get(Calendar.MONTH) == today.get(Calendar.MONTH) && birth.getDate() > today.getDate())) {
                age--;
            }
            
            if (age < 18 && isToday(birthDate)) {
                System.out.println("Today's birthday discount applied! Total price halved!");
                this.orderTotal /= 2;
            } else {
                System.out.println("Discount conditions not met");
            }
        } catch (Exception e) {
            System.out.println("Invalid date format");
        }
    }

    private boolean isToday(Date birthDate) {
        Calendar today = Calendar.getInstance();
        return today.get(Calendar.YEAR) == birthDate.getYear() &&
               today.get(Calendar.MONTH) == birthDate.getMonth() &&
               today.get(Calendar.DAY_OF_MONTH) == birthDate.getDate();
    }

    private void processPayment() {
        System.out.print("Enter credit card number (long integer): ");
        long cardNumber = scanner.nextLong();
        scanner.nextLine();
        
        System.out.print("Enter expiry date (MM/YY): ");
        String expiryDate = scanner.nextLine().trim();
        
        System.out.print("Enter CVV (3-digit number): ");
        int cvv = scanner.nextInt();
        scanner.nextLine();
        
        validateAndProcessCard(cardNumber, expiryDate, cvv);
    }

    private void validateAndProcessCard(long cardNumber, String expiryDate, int cvv) {
        String cardStr = Long.toString(cardNumber);
        
        if (cardStr.length() != 14) {
            System.out.println("Invalid card number length");
            return;
        }
        
        System.out.println("First digit: " + cardStr.charAt(0));
        
        if (cardNumber == BLACKLISTED_NUMBER) {
            System.out.println("Blacklisted card. Please try another one.");
            return;
        }
        
        System.out.println("Last four digits: " + cardStr.substring(cardStr.length() - 4));
        
        StringBuilder masked = new StringBuilder();
        masked.append(cardStr.charAt(0));
        for (int i = 1; i < cardStr.length() - 4; i++) {
            masked.append('*');
        }
        masked.append(cardStr.substring(cardStr.length() - 4));
        System.out.println("Masked card: " + masked);
        
        this.cardNumberToDisplay = masked.toString();
    }

    private void generateReceipt() {
        System.out.println("\n*********************** RECEIPT ​***********************");
        System.out.println("Order ID: " + orderID);
        System.out.println("Order Details: " + orderDetails);
        System.out.println("Total Price: $" + String.format("%.2f", orderTotal));
        System.out.println("Ingredients: " + String.join(", ", pizzaIngredients.split(" +")));
        System.out.println("Size: " + 
                            (orderDetails.contains("Small") ? "Small" : 
                             orderDetails.contains("Medium") ? "Medium" : "Large"));
        System.out.println("Extra Cheese: " + (extraCheese.equalsIgnoreCase("Y") ? "Added" : "No"));
        System.out.println("Side: " + sideOfTheDay);
        System.out.println("Drink: " + drinks);
        System.out.println("Payment Method: " + 
                            (cardNumberToDisplay != null ? "Credit Card" : "Birthday Discount"));
        System.out.println("Thank you for dining at Slice Heaven!");
    }

    public void specialOfTheDay(String pizza, String side, double price) {
        System.out.println("\nToday's Special:");
        System.out.println("Pizza: " + pizza + " - $" + String.format("%.2f", price));
        System.out.println("Side: " + side);
    }
}