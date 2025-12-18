package Task4;

import java.util.concurrent.Semaphore;

class Philosopher implements Runnable {
    private final int id;
    private final Semaphore leftFork;
    private final Semaphore rightFork;

    public Philosopher(int id, Semaphore leftFork, Semaphore rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 3; i++) {
                System.out.println("P" + id + ": thinking");
                Thread.sleep(300);

                System.out.println("P" + id + ": hungry");

                if (id == 4) {
                    rightFork.acquire();
                    leftFork.acquire();
                } else {
                    leftFork.acquire();
                    rightFork.acquire();
                }

                System.out.println("P" + id + ": eating");
                Thread.sleep(500);

                rightFork.release();
                leftFork.release();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}