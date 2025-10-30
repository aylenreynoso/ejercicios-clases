package blogs;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        boolean modoConcurrente = false;
        List<BlogReader> blogReaders = List.of(
                new BlogReader("Alice", 1),
                new BlogReader("Bob", 2),
                new BlogReader("Charlie", 3)
        );

        if (modoConcurrente) {
            System.out.println("All users are reading their posts concurrently...");
        } else {
            System.out.println("All users are reading their posts sequentially...");
        }

        long startTime = System.nanoTime();

        List<Thread> threads = new ArrayList<>();

        for (BlogReader blogReader : blogReaders) {
            if (modoConcurrente) {
                Thread thread = new Thread(blogReader);
                threads.add(thread);
                thread.start();
            } else {
                blogReader.run();
            }
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        long endTime = System.nanoTime();
        double elapsedTimeInSeconds = (endTime - startTime) / 1_000_000_000.0;
        System.out.printf("Execution time: %.3f seconds%n", elapsedTimeInSeconds);
    }
}
