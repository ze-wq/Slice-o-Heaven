import java.util.ArrayDeque;
import java.util.Deque;

public class OrderLogs {
    private Deque<String> logs = new ArrayDeque<>();

    public void addOrderLog(String log) {
        logs.push(log);
        System.out.println("LOG: " + log);
    }

    public void displayLogs() {
        System.out.println("\nOrder Logs:");
        if (logs.isEmpty()) {
            System.out.println("No logs found.");
            return;
        }
        for (String log : logs) {
            System.out.println(log);
        }
    }
}