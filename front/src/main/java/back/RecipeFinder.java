package back;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RecipeFinder {

    private static final String API_KEY = "bde71878bd254e889f1c97348688c78d";

    public static List<Recipe> searchRecipes(List<Ingredient> ingredients) {
        List<Recipe> recipes = new ArrayList<>();

        if (ingredients.isEmpty()) {
            System.out.println("No ingredients specified for recipe search.");
            return recipes;
        }

        StringBuilder urlBuilder = new StringBuilder("https://api.spoonacular.com/recipes/findByIngredients?");
        urlBuilder.append("ingredients=");
        for (Ingredient ingredient : ingredients) {
            urlBuilder.append(ingredient.getName()).append(",");
        }
        urlBuilder.deleteCharAt(urlBuilder.length() - 1);
        urlBuilder.append("&number=1");
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

            // Parse the API response and create Recipe objects
            // You'll need to implement this part based on the API response structure
            // For this example, I'm assuming a JSON response.

            // Parse JSON and create Recipe objects here...
            // Example:
            // JSONArray recipesArray = new JSONArray(response.toString());
            // for (int i = 0; i < recipesArray.length(); i++) {
            //     JSONObject recipeObject = recipesArray.getJSONObject(i);
            //     String name = recipeObject.getString("name");
            //     JSONArray ingredientsArray = recipeObject.getJSONArray("ingredients");
            //     List<Ingredient> ingredientsList = new ArrayList<>();
            //     for (int j = 0; j < ingredientsArray.length(); j++) {
            //         JSONObject ingredientObject = ingredientsArray.getJSONObject(j);
            //         // Parse ingredient details and create Ingredient objects
            //         // ...
            //     }
            //     String instructions = recipeObject.getString("instructions");
            //     Recipe recipe = new Recipe(name, ingredientsList, instructions);
            //     recipes.add(recipe);
            // }

            conn.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return recipes;
    }
}
