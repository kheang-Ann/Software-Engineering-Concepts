
import java.util.Scanner;

public class Task1 {

    private static int counter = 1;
    private static boolean thread1Turn = true; // Thread1 starts first
    private static boolean ascending = true;   // Start by counting upward

    public static void main(String[] args) throws Exception {

        int N;
        System.out.print("Enter the maximum count: ");
        try (Scanner sc = new Scanner(System.in)) {
            N = sc.nextInt();
        }

        Object lock = new Object();

        Thread thread1 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (!thread1Turn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException ignored) {
                        }
                    }

                    // If done, print, notify other thread and break
                    if (counter == 1 && !ascending) {
                        System.out.println("Thread-1: " + counter);
                        thread1Turn = false; // allow other thread to wake and finish
                        lock.notifyAll();
                        break;
                    }

                    // Display current value
                    System.out.println("Thread-1: " + counter);

                    // Update counter
                    if (ascending) {
                        counter++;
                    } else {
                        counter--;
                    }

                    // If reach N, change direction
                    if (counter > N) {
                        counter = N;
                        ascending = false;
                        counter--; // start descending
                    }

                    thread1Turn = false;
                    lock.notifyAll();
                }
            }
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (thread1Turn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException ignored) {
                        }
                    }

                    // If done, print, notify other thread and break
                    if (counter == 1 && !ascending) {
                        // don't print here to avoid printing the final value twice
                        thread1Turn = true; // allow other thread to wake and finish
                        lock.notifyAll();
                        break;
                    }

                    // Display current value
                    System.out.println("Thread-2: " + counter);

                    // Update counter
                    if (ascending) {
                        counter++;
                    } else {
                        counter--;
                    }

                    // If reach N, change direction (mirror handling from thread1)
                    if (counter > N) {
                        counter = N;
                        ascending = false;
                        counter--; // start descending
                    }

                    thread1Turn = true;
                    lock.notifyAll();
                }
            }
        });
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Program finished.");
    }
}
