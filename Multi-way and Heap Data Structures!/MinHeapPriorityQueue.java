import java.util.Scanner;

public class MinHeapPriorityQueue {

    private class Task {
        String description;
        int waitTime; // In minutes

        public Task(String description, int waitTime) {
            this.description = description;
            this.waitTime = waitTime;
        }
    }

    private Task[] heap;
    private int size;

    // Constructor to initialize the heap with a specified capacity
    public MinHeapPriorityQueue(int capacity) {
        heap = new Task[capacity];
        size = 0;
    }

    // Helper methods to maintain heap property
    private void heapifyUp(int index) {
        while (index > 0 && heap[index].waitTime < heap[(index - 1) / 2].waitTime) {
            Task temp = heap[index];
            heap[index] = heap[(index - 1) / 2];
            heap[(index - 1) / 2] = temp;
            index = (index - 1) / 2;
        }
    }

    private void heapifyDown(int index) {
        int smallest = index;
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;

        if (leftChild < size && heap[leftChild].waitTime < heap[smallest].waitTime) {
            smallest = leftChild;
        }
        if (rightChild < size && heap[rightChild].waitTime < heap[smallest].waitTime) {
            smallest = rightChild;
        }

        if (smallest != index) {
            Task temp = heap[index];
            heap[index] = heap[smallest];
            heap[smallest] = temp;
            heapifyDown(smallest);
        }
    }

    // Method to add a task with a description and wait time
    public void addTask(String description, int waitTime) {
        if (size == heap.length) {
            System.out.println("Heap is full.");
            return;
        }
        heap[size] = new Task(description, waitTime);
        heapifyUp(size);
        size++;
    }

    // Method to get the next task (the one with the shortest wait time)
    public Task peekNextTask() {
        if (size == 0) {
            System.out.println("No tasks available.");
            return null;
        }
        return heap[0];
    }

    // Method to remove and return the next task (the one with the shortest wait time)
    public Task removeNextTask() {
        if (size == 0) {
            System.out.println("No tasks available.");
            return null;
        }
        Task nextTask = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);
        return nextTask;
    }

    // Method to display all tasks sorted by wait time
    public void displayTasks() {
        if (size == 0) {
            System.out.println("No tasks available.");
            return;
        }
        System.out.println("Tasks sorted by wait time:");
        for (int i = 0; i < size; i++) {
            System.out.println("Description: " + heap[i].description + ", Wait Time: " + heap[i].waitTime + " minutes");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MinHeapPriorityQueue queue = new MinHeapPriorityQueue(10);

        while (true) {
            System.out.println("\n1. Add Task");
            System.out.println("2. View Next Task");
            System.out.println("3. Remove Next Task");
            System.out.println("4. Display All Tasks");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character after the integer input

            switch (choice) {
                case 1:
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter task wait time (in minutes): ");
                    int waitTime = scanner.nextInt();
                    queue.addTask(description, waitTime);
                    break;
                case 2:
                    Task nextTask = queue.peekNextTask();
                    if (nextTask != null) {
                        System.out.println("Next Task: " + nextTask.description + " (Wait time: " + nextTask.waitTime + " minutes)");
                    }
                    break;
                case 3:
                    Task removedTask = queue.removeNextTask();
                    if (removedTask != null) {
                        System.out.println("Removed Task: " + removedTask.description + " (Wait time: " + removedTask.waitTime + " minutes)");
                    }
                    break;
                case 4:
                    queue.displayTasks();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }
}
