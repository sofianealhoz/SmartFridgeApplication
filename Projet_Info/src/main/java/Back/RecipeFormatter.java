package Back;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecipeFormatter {

    public String standardizeInstructions(String rawInstructions) {
        if (rawInstructions == null || rawInstructions.isEmpty()) {
            return "";
        }

        // Detect if the instructions contain HTML tags
        if (rawInstructions.contains("<") && rawInstructions.contains(">")) {
            return processHtmlInstructions(rawInstructions);
        } else {
            return processNonHtmlInstructions(rawInstructions);
        }
    }

    private String processHtmlInstructions(String htmlInstructions) {
        Document doc = Jsoup.parse(htmlInstructions);
        Elements elements = doc.select("ol li, ul li, p");
        StringBuilder standardizedInstructions = new StringBuilder();
        int counter = 1;

        for (Element element : elements) {
            String text = element.text().trim();
            standardizedInstructions.append(counter++).append(". ").append(text).append("\n");
        }

        return standardizedInstructions.toString().trim();
    }

    private String processNonHtmlInstructions(String rawInstructions) {
        // Split the instructions by line breaks or bullet points
        String[] lines = rawInstructions.split("\\r?\\n|•");
        StringBuilder standardizedInstructions = new StringBuilder();
        int counter = 1;

        for (String line : lines) {
            String text = removeRedundantNumbering(line.trim());

            if (!text.isEmpty()) {
                standardizedInstructions.append(counter++).append(". ").append(text).append("\n");
            }
        }

        return standardizedInstructions.toString().trim();
    }

    private String removeRedundantNumbering(String text) {
        Pattern pattern = Pattern.compile("^\\d+\\.\\s*(\\d+\\.\\s*)?");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            text = text.replaceFirst(pattern.pattern(), "");
        }

        return text.trim();
    }
}
