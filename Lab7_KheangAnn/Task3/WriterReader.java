package Task3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintWriter;

public class WriterReader {
    public static void writerProcess(PipedOutputStream out) {
        try (PrintWriter writer = new PrintWriter(out, true)) {
            for (int i = 1; i <= 5; i++) {
                String message = "Message " + i;
                writer.println(message);
                System.out.println("Write: " + message);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void readerProcess(PipedInputStream in) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println("Read: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException,
            InterruptedException {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out = new PipedOutputStream(in);

        Thread writerThread = new Thread(() -> writerProcess(out));
        Thread readerThread = new Thread(() -> readerProcess(in));

        writerThread.start();
        readerThread.start();

        writerThread.join();
        readerThread.join();
    }
}
