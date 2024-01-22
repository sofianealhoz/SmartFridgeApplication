package Back;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RecipeFormatter {

    // Standardizes recipe instructions from HTML format
    public String standardizeFromHTML(String htmlInstructions) {
        Document doc = Jsoup.parse(htmlInstructions);
        Elements elements = doc.select("li"); // assuming list items contain the steps
        List<String> steps = elements.eachText();
        return formatAsBulletPoints(steps);
    }

    // Standardizes recipe instructions from plain text format
    public String standardizeFromPlainText(String plainTextInstructions) {
        List<String> steps = Arrays.asList(plainTextInstructions.split("\n")); // split by new line
        return formatAsBulletPoints(steps);
    }

    private String formatAsBulletPoints(List<String> steps) {
        System.out.println("Original Steps: " + steps); // Debug statement

        String formattedInstructions = steps.stream()
                    .filter(step -> !step.trim().isEmpty()) // Filter out empty lines
                    .map(step -> removeExistingBullets(step).trim())
                    .map(step -> step.startsWith("•") ? step : "• " + step) // Add your bullet
                    .collect(Collectors.joining("\n"));

        System.out.println("Formatted Instructions: " + formattedInstructions); // Debug statement
        return formattedInstructions;
    }

    private String removeExistingBullets(String step) {
        // Remove HTML list tags, markdown bullets, or other bullet symbols if present
        step = step.replaceAll("<li>", "").replaceAll("</li>", ""); // Remove HTML list tags
        step = step.replaceAll("^\\*\\s+", ""); // Remove markdown bullets
        step = step.replaceAll("^-\\s+", ""); // Remove dash-style bullets
        // Add more replacements as needed based on the formats you encounter
        return step;
    }


}