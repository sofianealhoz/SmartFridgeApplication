package back;

import java.util.List;


public class Recipe {
    private String name;
    private String imageUrl;
    private List<Ingredient> ingredients;
    private List<String> instructions;
    private NutritionInfo nutritionInfo;
    private List<String> allergens;

    public Recipe(String name, String imageUrl, List<Ingredient> ingredientsList,List<String> instructions, NutritionInfo nutritionInfo, List<String> allergens) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.ingredients = ingredientsList;
        this.instructions = instructions;
        this.nutritionInfo = nutritionInfo;
        this.allergens = allergens;  
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }

    public String getImagePath() {
        String localPath = ImageDownloader.downloadImage(imageUrl);
        return localPath;
    }
    public NutritionInfo getNutritionInfo() {
        return nutritionInfo;
    }

    public void setNutritionInfo(NutritionInfo nutritionInfo) {
        this.nutritionInfo = nutritionInfo;
    }

    public List<String> getAllergens() {
        return allergens;
    }

    public void setAllergens(List<String> allergens) {
        this.allergens = allergens;
    }
}
   