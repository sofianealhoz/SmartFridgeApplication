package Back;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class RecipeFinder {

    private static final String API_KEY = "43d2fa3384d347b2b8bab5785b2edff7";

    // Searches for recipes based on a list of ingredients.
    public static List<Recipe> searchRecipes(List<Ingredient> ingredients) {
        List<Recipe> recipes = new ArrayList<>();
        List<Ingredient> chocolateCakeIngredients = Arrays.asList(
            new Ingredient("Flour", LocalDate.of(2024, 6, 30), 500, "Bakery"),
            new Ingredient("Sugar", LocalDate.of(2024, 12, 31), 200, "Bakery"),
            new Ingredient("Eggs", LocalDate.of(2024, 5, 15), 3, "Dairy"),
            new Ingredient("Cocoa Powder", LocalDate.of(2024, 11, 20), 100, "Bakery")
        );

        // Instructions for the chocolate cake
        List<String> chocolateCakeInstructions = Arrays.asList(
            "Preheat the oven to 180°C.",
            "Mix flour, sugar, and cocoa powder.",
            "Beat in eggs one at a time.",
            "Pour batter into a greased pan.",
            "Bake for 35 minutes or until a toothpick comes out clean."
        );

        // Nutritional information for the chocolate cake (example values)
        NutritionInfo chocolateCakeNutrition = new NutritionInfo(350, 15, 5, 50, 2, 35, 0.2);

        // Allergens for the chocolate cake
        List<String> chocolateCakeAllergens = Arrays.asList("Gluten", "Eggs");

        // Creating the chocolate cake recipe
        Recipe chocolateCake = new Recipe(
            "Chocolate Cake", 
            "http://example.com/images/chocolate-cake.jpg",
            chocolateCakeIngredients, 
            chocolateCakeInstructions, 
            chocolateCakeNutrition, 
            chocolateCakeAllergens
        );

        // Create a list of recipes and add the chocolate cake recipe
        recipes.add(chocolateCake);

        // Check if the ingredient list is empty
        if (ingredients.isEmpty()) {
            System.out.println("No ingredients specified for recipe search.");
            return recipes;
        }

        // Building the URL for the Spoonacular API request
        StringBuilder urlBuilder = new StringBuilder("https://api.spoonacular.com/recipes/findByIngredients?");
        urlBuilder.append("ingredients=");
        for (Ingredient ingredient : ingredients) {
            urlBuilder.append(ingredient.getName()).append(",");
        }
        urlBuilder.deleteCharAt(urlBuilder.length() - 1);
        urlBuilder.append("&number=10");
        urlBuilder.append("&apiKey=").append(API_KEY);

        // Making the HTTP request to the API
        try {
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Check for successful response
            if (conn.getResponseCode() != 200) {
                throw new IOException("Connection error: " + conn.getResponseCode());
            }

            // Reading the response from the API
            Scanner scanner = new Scanner(conn.getInputStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }
            scanner.close();

            // Parsing the JSON response to extract recipe details
            JSONArray recipesArray = new JSONArray(response.toString());
            for (int i = 0; i < recipesArray.length(); i++) {
                JSONObject recipeObject = recipesArray.getJSONObject(i);
                int recipeId = recipeObject.getInt("id");

                // Fetching detailed information for each recipe
                Recipe detailedRecipe = fetchRecipeDetails(recipeId);
                if (detailedRecipe != null) {
                    recipes.add(detailedRecipe);
                }
            }

            conn.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return recipes;
    }

    // Fetches detailed recipe information including ingredients, instructions, nutrition, and allergies.
    private static Recipe fetchRecipeDetails(int recipeId) {
        String detailUrl = "https://api.spoonacular.com/recipes/" + recipeId + "/information?includeNutrition=true&apiKey=" + API_KEY;
        try {
            URL url = new URL(detailUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Check for successful response
            if (conn.getResponseCode() != 200) {
                throw new IOException("Connection error: " + conn.getResponseCode());
            }

            // Reading the detailed response from the API
            Scanner scanner = new Scanner(conn.getInputStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }
            scanner.close();

            // Parsing the JSON response for detailed recipe information
            JSONObject recipeDetail = new JSONObject(response.toString());

            // Extracting ingredients from the JSON response
            JSONArray ingredientsArray = recipeDetail.optJSONArray("extendedIngredients");
            List<Ingredient> ingredientList = new ArrayList<>();
            if (ingredientsArray != null) {
                for (int i = 0; i < ingredientsArray.length(); i++) {
                    JSONObject ingredientObject = ingredientsArray.getJSONObject(i);
                    String ingredientName = ingredientObject.optString("name", "Unknown");
                    ingredientList.add(new Ingredient(ingredientName, LocalDate.now(), 0, "Unknown"));
                }
            }

            // Extracting title, image, instructions from the JSON response
            String title = recipeDetail.getString("title");
            String imageUrl = recipeDetail.getString("image");
            String rawInstructions = recipeDetail.optString("instructions", "");
            List<String> instructionList = Arrays.asList(rawInstructions.split("\n")); 

            // Extracting nutrition info and allergens
            JSONObject nutritionObject = recipeDetail.optJSONObject("nutrition");
            NutritionInfo nutritionInfo = extractNutritionInfo(nutritionObject);
            List<String> allergens = extractAllergens(recipeDetail);
            
            // Creating a Recipe object with the extracted information
            Recipe recipe = new Recipe(title, imageUrl, ingredientList, instructionList, nutritionInfo, allergens);
            return recipe;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Extracts nutrition information from the JSON object.
    private static NutritionInfo extractNutritionInfo(JSONObject nutritionObject) {
        if (nutritionObject == null) {
            return new NutritionInfo(0, 0, 0, 0, 0, 0, 0);
        }
        double calories = nutritionObject.optDouble("calories", 0);
        double fat = nutritionObject.optDouble("fat", 0);
        double protein = nutritionObject.optDouble("protein", 0);
        double carbs = nutritionObject.optDouble("carbohydrates", 0);
        double fiber = nutritionObject.optDouble("fiber", 0);
        double sugar = nutritionObject.optDouble("sugar", 0);
        double sodium = nutritionObject.optDouble("sodium", 0);

        return new NutritionInfo(calories, fat, protein, carbs, fiber, sugar, sodium);
    }

    // Extracts allergen information from the JSON object.
    private static List<String> extractAllergens(JSONObject recipeDetail) {
        JSONArray allergensArray = recipeDetail.optJSONArray("allergens");
        List<String> allergens = new ArrayList<>();
        if (allergensArray != null) {
            for (int i = 0; i < allergensArray.length(); i++) {
                allergens.add(allergensArray.getString(i));
            }
        }
        return allergens;
    }
}
