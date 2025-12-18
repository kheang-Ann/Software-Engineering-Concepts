package Task4;

import java.util.concurrent.Semaphore;

public class DiningDemo {
    public static void main(String[] args) throws InterruptedException {
        final int NUM_PHILOSOPHERS = 5;

        Semaphore[] forks = new Semaphore[NUM_PHILOSOPHERS];
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            forks[i] = new Semaphore(1);
        }

        Thread[] philosophers = new Thread[NUM_PHILOSOPHERS];
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            philosophers[i] = new Thread(new Philosopher(i, forks[i], forks[(i+1)%5]));
        }

        for (Thread p : philosophers) {
            p.start();
        }

        for (Thread p : philosophers) {
            p.join();
        }
    }
}
