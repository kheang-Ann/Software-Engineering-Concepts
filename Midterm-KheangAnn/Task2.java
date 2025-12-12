
import java.util.Scanner;

public class Task2 {

    private int counter = 1;       // shared counter
    private int direction = 1;     // +1 for up, -1 for down
    private final int N;           // max value from user
    private int turn = 1;          // turns: 1 → 2 → 3 → 1 → ...
    private boolean finished = false; // signal threads to stop

    public Task2(int N) {
        this.N = N;
    }

    public synchronized void printNumber(int threadId) {
        while (!finished) {
            while (!finished && turn != threadId) {
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }

            if (finished) {
                break;
            }

            // Stop when counter returns to 1 while decreasing
            if (counter == 1 && direction == -1 && turn == threadId) {
                System.out.println("Thread " + threadId + ": " + counter);
                finished = true;      // signal other threads to exit
                notifyAll();
                break;
            }

            System.out.println("Thread " + threadId + ": " + counter);

            // Switch direction at N
            if (counter == N) {
                direction = -1;
            }

            // Update counter
            counter += direction;

            // Next thread's turn
            turn = (turn % 3) + 1;

            notifyAll();
        }
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter the maximum count: ");
            int N = sc.nextInt();

            Task2 obj = new Task2(N);

            Thread t1 = new Thread(() -> obj.printNumber(1));
            Thread t2 = new Thread(() -> obj.printNumber(2));
            Thread t3 = new Thread(() -> obj.printNumber(3));

            t1.start();
            t2.start();
            t3.start();

            try {
                t1.join();
                t2.join();
                t3.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
