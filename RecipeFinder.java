package test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class RecipeFinder {

    private static final String API_KEY = "bde71878bd254e889f1c97348688c78d";

    public static void searchRecipes(List<Ingredient> ingredients) {
        if (ingredients.isEmpty()) {
            System.out.println("No ingredients specified for recipe search.");
            return;
        }

        StringBuilder urlBuilder = new StringBuilder("https://api.spoonacular.com/recipes/findByIngredients?");
        urlBuilder.append("ingredients=");
        for (Ingredient ingredient : ingredients) {
            urlBuilder.append(ingredient.getName()).append(",");
        }
        urlBuilder.deleteCharAt(urlBuilder.length() - 1);
        urlBuilder.append("&number=5");
        urlBuilder.append("&apiKey=").append(API_KEY);

        try {
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            if (conn.getResponseCode() != 200) {
                throw new IOException("Connection error: " + conn.getResponseCode());
            }

            Scanner scanner = new Scanner(conn.getInputStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }
            scanner.close();

            System.out.println("Recipes found with the specified ingredients:");
            System.out.println(response.toString());

            conn.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
