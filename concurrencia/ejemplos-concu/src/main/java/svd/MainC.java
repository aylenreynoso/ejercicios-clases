package svd;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainC {
    public static void main(String[] args) throws IOException {
        List<String> inputImages = List.of("cat.jpg", "monalisa.jpg");

        List<Thread> threads = new ArrayList<>();

        long startTime = System.nanoTime();

        for (int i =0; i<inputImages.size(); i++) {
            final int j = i;
            Thread thread = new Thread(() -> {
                String inputImagePath = inputImages.get(j);
                InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(inputImagePath);
                if (inputStream == null) {
                    throw new IllegalArgumentException("Image not found in resources folder");
                }
                try {
                    BufferedImage img = ImageIO.read(inputStream);

                    SVDImageCompressor compressor = new ConcurrentSVDCompressor();
                    BufferedImage compressedImage = compressor.compress(img);

                    String outputImagePath = String.format("output-%s", inputImagePath);

                    ImageIO.write(compressedImage, "jpg", new File(outputImagePath));
                    System.out.println("SVD compression complete. Output saved to " + outputImagePath);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            threads.add(thread);
            thread.start();

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

