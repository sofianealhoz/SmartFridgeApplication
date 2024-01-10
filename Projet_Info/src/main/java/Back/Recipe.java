package Back;

import java.util.List;


public class Recipe {
	// Fields to store recipe information
    private String name;
    private String imageUrl;
    private List<Ingredient> ingredients;
    private List<String> instructions;
    private NutritionInfo nutritionInfo;
    private List<String> allergens;

    // Constructor
    public Recipe(String name, String imageUrl, List<Ingredient> ingredientsList,List<String> instructions, NutritionInfo nutritionInfo, List<String> allergens) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.ingredients = ingredientsList;
        this.instructions = instructions;
        this.nutritionInfo = nutritionInfo;
        this.allergens = allergens;  
    }
    
    // Getter methods
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
    public NutritionInfo getNutritionInfo() {
        return nutritionInfo;
    }
    public List<String> getAllergens() {
        return allergens;
    }

    
    // Method to get the local image path using a downloader
    public String getImagePath() {
        String localPath = ImageDownloader.downloadImage(imageUrl);
        return localPath;
    }
    
    // Setter methods
    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }
    public void setNutritionInfo(NutritionInfo nutritionInfo) {
        this.nutritionInfo = nutritionInfo;
    }  
    public void setAllergens(List<String> allergens) {
        this.allergens = allergens;
    }

}
   