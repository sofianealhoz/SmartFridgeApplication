package Back;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

public class ImageDownloader {

    // Download an image from a URL and return the path where it was saved
    public static String downloadImage(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            BufferedImage image = ImageIO.read(url);
            String fileName = UUID.randomUUID().toString() + ".png"; // Generate a random file name
            File outputFile = new File(System.getProperty("java.io.tmpdir"), fileName); // Save in the system 
            ImageIO.write(image, "png", outputFile);
            return outputFile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
