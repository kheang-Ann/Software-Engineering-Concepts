package Task2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumer {
    public static void producer(BlockingQueue<String> queue) {
        try {
            for (int i = 1; i <= 5; i++) {
                String item = "Item " + i;
                queue.put(item);
                System.out.println("Produced: " + item);
                Thread.sleep(100);
            }
            queue.put("STOP");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void consumer(BlockingQueue<String> queue) {
        try {
            while (true) {
                String item = queue.take();
                if ("STOP".equals(item)) {
                    break;
                }
                System.out.println("Consumed: " + item);
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();

        Thread producerThread = new Thread(() -> producer(queue));
        Thread consumerThread = new Thread(() -> consumer(queue));

        producerThread.start();
        consumerThread.start();

        producerThread.join();
        consumerThread.join();
    }
}
