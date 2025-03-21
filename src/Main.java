import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HandleOrders handleOrders = new HandleOrders();
        OrderLogs orderLogs = new OrderLogs();
        OrderQueue orderQueue = new OrderQueue();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== WELCOME TO SLICE-O-HEAVEN PIZZERIA ===");
        System.out.print("Please enter your customer name: ");
        String customerName = scanner.nextLine().trim();

        while (true) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Place a New Order");
            System.out.println("2. View Order Logs");
            System.out.println("3. Process Orders in Queue");
            System.out.println("4. Display Custom Pizzas");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    handleOrders.takeOrder(customerName);
                    break;
                case 2:
                    orderLogs.displayLogs();
                    break;
                case 3:
                    orderQueue.processOrders();
                    break;
                case 4:
                    handleOrders.displayCustomPizzas();
                    break;
                case 5:
                    System.out.println("Thank you for visiting!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
