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
    
    private final Scanner scanner = new Scanner(System.in);

    public void takeOrder() {
        System.out.println("Welcome to Slice Heaven!");
        
        String[] validIngredients = {"Mushroom", "Paprika", "Sun-dried tomatoes", 
                                      "Chicken", "Pineapple"};
        int[] choices = {1, 2, 3, 4, 5};
        String[] ingChoices = getInputWithValidation(
            "Please pick any three of the following ingredients: \n" +
            "1. Mushroom 2. Paprika 3. Sun-dried tomatoes \n" +
            "4. Chicken 5. Pineapple\nEnter choices (1,2,3,…): ",
            choices, 3, this::convertIngredientChoice
        );
        
        String[] sizes = {"Large", "Medium", "Small"};
        int[] sizeOptions = {1, 2, 3};
        String pizzaSize = getInputWithValidation(
            "What size should your pizza be? \n" +
            "1. Large 2. Medium 3. Small\nEnter choice (1,2,3): ",
            sizeOptions, 1, this::convertSizeChoice
        );
        
        String extraCheese = askForExtraCheese();
        String[] sideChoices = getInputWithValidation(
            "Following are the side dish options: \n" +
            "1. Calzone 2. Garlic bread 3. Chicken puff \n" +
            "4. Muffin 5. Nothing for me\nEnter choice (1-5): ",
            new int[]{1, 2, 3, 4, 5}, 1, this::convertSideChoice
        );
        String sideDish = sideChoices[0];
        
        String[] drinkOptions = getInputWithValidation(
            "Choose from one of the drinks: \n" +
            "1. Coca Cola 2. Cold coffee 3. Cocoa Drink \n" +
            "4. No drinks for me\nEnter choice (1-4): ",
            new int[]{1, 2, 3, 4}, 1, this::convertDrinkChoice
        );
        String drink = drinkOptions[0];
        
        this.orderDetails = String.join(", ", ingChoices) + " " + pizzaSize;
        this.pizzaIngredients = String.join(", ", ingChoices);
        
        String wantDiscount = askForDiscountConfirmation();
        if (wantDiscount.equalsIgnoreCase("Y")) {
            applyBirthdayDiscount();
        } else {
            processPayment();
        }
        
        this.sides = sideDish;
        this.drinks = drink;
    }

    private <T> T[] getInputWithValidation(
        String prompt, int[] validOptions, int requiredCount, 
        java.util.function.Function<Integer, String> converter
    ) {
        while (true) {
            System.out.print(prompt);
            
            String input = scanner.nextLine().trim();
            String[] numbers = input.split("\\s+");
            
            if (numbers.length != requiredCount) {
                System.out.println("Invalid number of choices. Please try again.");
                continue;
            }
            
            boolean valid = true;
            int[] selected = new int[numbers.length];
            for (String numStr : numbers) {
                int num = Integer.parseInt(numStr);
                if (!contains(validOptions, num)) {
                    valid = false;
                    break;
                }
                selected.add(num);
            }
            
            if (!selected.stream().distinct().count() == requiredCount) {
                System.out.println("Invalid choice(s). Please pick only from the given list:");
                continue;
            }
            
            try {
                return selected.stream()
                    .mapToInt(Integer::intValue)
                    .map(converter)
                    .toArray(String[]::new);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid choice detected. Please try again.");
                continue;
            }
        }
    }

    private boolean contains(int[] array, int value) {
        for (int num : array) {
            if (num == value) return true;
        }
        return false;
    }

    private java.util.function.Function<Integer, String> convertIngredientChoice = num -> {
        switch (num) {
            case 1: return "Mushroom";
            case 2: return "Paprika";
            case 3: return "Sun-dried tomatoes";
            case 4: return "Chicken";
            case 5: return "Pineapple";
            default: throw new IllegalArgumentException("Invalid ingredient choice");
        }
    };

    private java.util.function.Function<Integer, String> convertSizeChoice = num -> {
        switch (num) {
            case 1: return "Large";
            case 2: return "Medium";
            case 3: return "Small";
            default: throw new IllegalArgumentException("Invalid size choice");
        }
    };

    private java.util.function.Function<Integer, String> convertSideChoice = num -> {
        switch (num) {
            case 1: return "Calzone";
            case 2: return "Garlic bread";
            case 3: return "Chicken puff";
            case 4: return "Muffin";
            case 5: return "Nothing for me";
            default: throw new IllegalArgumentException("Invalid side choice");
        }
    };

    private java.util.function.Function<Integer, String> convertDrinkChoice = num -> {
        switch (num) {
            case 1: return "Coca Cola";
            case 2: return "Cold coffee";
            case 3: return "Cocoa Drink";
            case 4: return "No drinks for me";
            default: throw new IllegalArgumentException("Invalid drink choice");
        }
    };

    private String askForExtraCheese() {
        System.out.print("Do you want extra cheese? (Y/N): ");
        return scanner.nextLine().trim();
    }

    private void applyBirthdayDiscount() {
        while (true) {
            System.out.print("Enter your birthday (yyyy-mm-dd): ");
            String birthDateStr = scanner.nextLine().trim();
            
            try {
                Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthDateStr);
                
                Calendar today = Calendar.getInstance();
                Calendar birth = Calendar.getInstance();
                birth.setTime(birthDate);
                
                int age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
                if (birth.get(Calendar.MONTH) > today.get(Calendar.MONTH) ||
                    (birth.get(Calendar.MONTH) == today.get(Calendar.MONTH) && birth.getDate() > today.getDate())) {
                    age--;
                }
                
                Date fiveYearsAgo = new Date(System.currentTimeMillis() - 5 * 365 * 24 * 60 * 60 * 1000);
                Date oneHundredTwtyYearsAgo = new Date(System.currentTimeMillis() - 120 * 365 * 24 * 60 * 60 * 1000);
                if (birthDate.before(fiveYearsAgo) || birthDate.after(oneHundredTwtyYearsAgo)) {
                    System.out.println("Invalid date. You are either too young or too dead to order. Please enter a valid date:");
                    continue;
                }
                
                if (isToday(birthDate)) {
                    System.out.println("Today's birthday discount applied! Total price halved!");
                    this.orderTotal /= 2;
                } else {
                    System.out.println("Discount conditions not met");
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid date format. Please use yyyy-mm-dd format.");
            }
        }
    }

    private boolean isToday(Date birthDate) {
        Calendar today = Calendar.getInstance();
        return today.get(Calendar.YEAR) == birthDate.get(Calendar.YEAR) &&
               today.get(Calendar.MONTH) == birthDate.get(Calendar.MONTH) &&
               today.get(Calendar.DAY_OF_MONTH) == birthDate.getDate();
    }

    private void processPayment() {
        while (true) {
            System.out.print("Enter credit card number (long integer): ");
            long cardNumber = scanner.nextLong();
            scanner.nextLine();
            
            System.out.print("Enter expiry date (MM/YY): ");
            String expiryDate = scanner.nextLine().trim();
            
            System.out.print("Enter CVV (3-digit number): ");
            int cvv = scanner.nextInt();
            scanner.nextLine();
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yy");
            dateFormat.setLenient(false);
            try {
                Date expDate = dateFormat.parse(expiryDate);
                if (expDate.before(new Date())) {
                    System.out.println("Invalid expiry date. Please choose a future date.");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Invalid date format. Please use MM/YY format.");
                continue;
            }
            
            if (!validateCardDetails(cardNumber, expiryDate, cvv)) {
                continue;
            }
            
            break;
        }
        
        validateAndProcessCard(cardNumber, expiryDate, cvv);
    }

    private boolean validateCardDetails(long cardNumber, String expiryDate, int cvv) {
        String cardStr = Long.toString(cardNumber);
        
        if (cardStr.length() != 14) {
            System.out.println("Invalid card number length. Must be 14 digits.");
            return false;
        }
        
        if (cardNumber == BLACKLISTED_NUMBER) {
            System.out.println("Blacklisted card detected. Please try another card.");
            return false;
        }
        
        // 检查CVV
        if (cvv < 100 || cvv > 999) {
            System.out.println("Invalid CVV. Must be 3 digits.");
            return false;
        }
        
        return true;
    }

    private void validateAndProcessCard(long cardNumber, String expiryDate, int cvv) {
        String cardStr = Long.toString(cardNumber);
        
        System.out.println("First digit: " + cardStr.charAt(0));
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

    @Override
    public String toString() {
        return "\n*********************** RECEIPT ​***********************\n" +
               "Order ID: " + orderID + "\n" +
               "Order Details: " + orderDetails + "\n" +
               "Total Price: $" + String.format("%.2f", orderTotal) + "\n" +
               "Ingredients: " + String.join(", ", pizzaIngredients.split(" +")) + "\n" +
               "Size: " + (orderDetails.contains("Large") ? "Large" : 
                               orderDetails.contains("Medium") ? "Medium" : "Small") + "\n" +
               "Extra Cheese: " + (extraCheese.equalsIgnoreCase("Y") ? "Added" : "No") + "\n" +
               "Side: " + sideOfTheDay + "\n" +
               "Drink: " + drinks + "\n" +
               "Payment Method: " + (cardNumberToDisplay != null ? "Credit Card" : "Birthday Discount") + "\n" +
               "Thank you for dining at Slice Heaven!";
    }
}