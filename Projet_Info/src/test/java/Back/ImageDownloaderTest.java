package Back;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;

import static org.junit.Assert.assertTrue;

public class ImageDownloaderTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void testDownloadImage() {
        // Sample image URL (replace it with a real image URL for testing)
        String imageUrl = "https://via.placeholder.com/150";

        // Invoke the downloadImage method
        String filePath = ImageDownloader.downloadImage(imageUrl);

        try {
            // Check that the file was created
            File outputFile = new File(filePath);
            assertTrue(outputFile.exists());

        } finally {
            // Clean up: delete the temporary file
            if (filePath != null) {
                File outputFile = new File(filePath);
                outputFile.delete();
            }
        }
    }
}

