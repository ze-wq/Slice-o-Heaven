import java.util.ArrayDeque;
import java.util.Deque;

public class OrderQueue {
    private Deque<Order> queue = new ArrayDeque<>();

    public void enqueue(Order order) {
        queue.add(order);
        System.out.println("Order added to queue: " + order);
    }

    public void processOrders() {
        System.out.println("\nProcessing Orders:");
        while (!queue.isEmpty()) {
            Order order = queue.poll();
            System.out.println("Processing: " + order);
        }
    }
}

class Order {
    private double totalPrice;

    public Order(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order for €" + totalPrice;
    }
}