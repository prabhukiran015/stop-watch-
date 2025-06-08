import java.util.Scanner;

public class StopwatchApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long startTime = 0;
        long endTime = 0;
        boolean running = false;

        System.out.println("Welcome to the Stopwatch App!");
        System.out.println("Commands: START, STOP, EXIT");

        while (true) {
            System.out.print("Enter command: ");
            String input = scanner.nextLine().trim().toUpperCase();

            switch (input) {
                case "START":
                    if (!running) {
                        startTime = System.currentTimeMillis();
                        running = true;
                        System.out.println("Stopwatch started...");
                    } else {
                        System.out.println("Stopwatch is already running.");
                    }
                    break;

                case "STOP":
                    if (running) {
                        endTime = System.currentTimeMillis();
                        running = false;
                        long elapsedTime = endTime - startTime;
                        double seconds = elapsedTime / 1000.0;
                        System.out.printf("Stopwatch stopped. Elapsed time: %.2f seconds%n", seconds);
                    } else {
                        System.out.println("Stopwatch is not running.");
                    }
                    break;

                case "EXIT":
                    System.out.println("Exiting Stopwatch App. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid command. Please enter START, STOP, or EXIT.");
            }
        }
    }
}