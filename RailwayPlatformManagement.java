import java.util.*;

/**
 * Educational mini project demonstrating Queue and PriorityQueue.
 * Compile: javac src/RailwayPlatformManagement.java
 * Run:     java -cp src RailwayPlatformManagement
 */
public class RailwayPlatformManagement {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Queue<Train> waitingQueue = new LinkedList<>();
    // Lower priority number means higher priority; sequence preserves FIFO among equal priorities.
    private static final PriorityQueue<Train> priorityQueue = new PriorityQueue<>(
        Comparator.comparingInt((Train t) -> t.priority).thenComparingLong(t -> t.sequence)
    );
    private static long nextSequence = 1;

    private static class Train {
        final String trainNumber;
        final String trainName;
        final int priority;
        final long sequence;

        Train(String number, String name, int priority, long sequence) {
            this.trainNumber = number;
            this.trainName = name;
            this.priority = priority;
            this.sequence = sequence;
        }

        @Override
        public String toString() {
            String priorityLabel = priority == 1 ? "High" : priority == 2 ? "Medium" : "Normal";
            return trainNumber + " - " + trainName + " [Priority: " + priorityLabel + "]";
        }
    }

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1 -> addTrain();
                case 2 -> allocateNextFIFO();
                case 3 -> addPriorityTrain();
                case 4 -> processPriorityTrain();
                case 5 -> displayFIFOQueue();
                case 6 -> displayPriorityQueue();
                case 7 -> running = false;
                default -> System.out.println("Invalid choice. Please select 1-7.");
            }
            System.out.println();
        }
        System.out.println("Thank you for using the Railway Platform Management System.");
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("=== RAILWAY PLATFORM MANAGEMENT SYSTEM ===");
        System.out.println("1. Add train to normal waiting queue");
        System.out.println("2. Allocate platform to next train (FIFO)");
        System.out.println("3. Add train to priority queue");
        System.out.println("4. Process highest-priority train");
        System.out.println("5. Display normal waiting queue");
        System.out.println("6. Display priority queue");
        System.out.println("7. Exit");
    }

    private static void addTrain() {
        String number = readText("Train number: ");
        String name = readText("Train name: ");
        Train train = new Train(number, name, 3, nextSequence++);
        waitingQueue.offer(train);
        System.out.println("Added to waiting queue: " + train);
    }

    private static void allocateNextFIFO() {
        Train train = waitingQueue.poll();
        if (train == null) {
            System.out.println("No trains are waiting in the normal queue.");
        } else {
            System.out.println("Platform allocated to: " + train);
        }
    }

    private static void addPriorityTrain() {
        String number = readText("Train number: ");
        String name = readText("Train name: ");
        System.out.println("Priority levels: 1 = High/Emergency, 2 = Medium/Special, 3 = Normal");
        int priority = readInt("Priority (1-3): ");
        if (priority < 1 || priority > 3) {
            System.out.println("Invalid priority. Train was not added.");
            return;
        }
        Train train = new Train(number, name, priority, nextSequence++);
        priorityQueue.offer(train);
        System.out.println("Added to priority queue: " + train);
    }

    private static void processPriorityTrain() {
        Train train = priorityQueue.poll();
        if (train == null) {
            System.out.println("No trains are waiting in the priority queue.");
        } else {
            System.out.println("Platform allocated to highest-priority train: " + train);
        }
    }

    private static void displayFIFOQueue() {
        if (waitingQueue.isEmpty()) {
            System.out.println("Normal waiting queue is empty.");
            return;
        }
        System.out.println("Normal waiting queue (front to rear):");
        for (Train train : waitingQueue) System.out.println(" - " + train);
    }

    private static void displayPriorityQueue() {
        if (priorityQueue.isEmpty()) {
            System.out.println("Priority queue is empty.");
            return;
        }
        System.out.println("Priority queue (highest priority first):");
        List<Train> sorted = new ArrayList<>(priorityQueue);
        sorted.sort(Comparator.comparingInt((Train t) -> t.priority).thenComparingLong(t -> t.sequence));
        for (Train train : sorted) System.out.println(" - " + train);
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a whole number.");
            }
        }
    }

    private static String readText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) return value;
            System.out.println("This field cannot be empty.");
        }
    }
}
