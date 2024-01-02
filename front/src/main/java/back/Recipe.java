package back;

import java.util.List;

public class Recipe {
    private String name;
    private List<Ingredient> ingredients;
    private String instructions;
    private String imageUrl; // URL to the image

    public Recipe(String name, List<Ingredient> ingredients, String instructions, String imageUrl) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.imageUrl = imageUrl;
    }

    // ... other methods ...

    public String getImageUrl() {
        return imageUrl;
    }

    // Method to download the image from the imageUrl and return the local path
    public String getImagePath() {
        // This method should handle the download of the image and then
        // return the path to where the image is stored locally.
        // For this example, let's assume you have a utility method that handles the download.
        String localPath = ImageDownloader.downloadImage(imageUrl);
        return localPath;
    }


    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

	

}
