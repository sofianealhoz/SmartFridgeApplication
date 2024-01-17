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

    private static final String API_KEY = "5f5db107afb640229621a0890d778538";

    // Searches for recipes based on a list of ingredients.
    public static List<Recipe> searchRecipes(List<Ingredient> ingredients) {
        List<Recipe> recipes = new ArrayList<>();

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
