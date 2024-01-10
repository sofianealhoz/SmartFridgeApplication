package back;

import java.util.List;
import java.util.Scanner;


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
    
    
    public void displayRecipe() {
        System.out.println("Recipe Name: " + name);
        System.out.println("Image URL: " + imageUrl);

        System.out.println("\nIngredients:");
        for (Ingredient ingredient : ingredients) {
            System.out.println("- " + ingredient.getName() + " (" + ingredient.getQuantity() + " " + ingredient.getCategory() + ")");
        }

        System.out.println("\nInstructions:");
        for (int i = 0; i < instructions.size(); i++) {
            System.out.println((i + 1) + ". " + instructions.get(i));
        }

        System.out.println("\nNutrition Information:");
        System.out.println("Calories: " + nutritionInfo.getCalories());
        System.out.println("Protein: " + nutritionInfo.getProtein());
        System.out.println("Carbohydrates: " + nutritionInfo.getCarbs());
        System.out.println("Fat: " + nutritionInfo.getFat());

        System.out.println("\nAllergens:");
        for (String allergen : allergens) {
            System.out.println("- " + allergen);
        }
    }
    
    public void displayListOfRecipes(List<Recipe> recipes) {
    	 for (Recipe recipe : recipes) {
             recipe.displayRecipe();
             System.out.println("\n----------------\n");
         }
	}
    
    public static Recipe chooseRecipe(List<Recipe> recipes, Frigo frigo) {
        Scanner scanner = new Scanner(System.in);

        // Afficher la liste des recettes avec des numéros
        System.out.println("Choisissez une recette en entrant son numéro :");
        for (int i = 0; i < recipes.size(); i++) {
            System.out.println((i + 1) + ". " + recipes.get(i).getName());
            frigo.findMissingIngredients(recipes.get(i));
        }

        // Laisser l'utilisateur choisir un numéro de recette
        int chosenRecipeIndex;
        do {
            System.out.print("Entrez le numéro de la recette choisie (1-" + recipes.size() + "): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Veuillez entrer un numéro valide.");
                scanner.next(); // consommer l'entrée invalide
            }
            chosenRecipeIndex = scanner.nextInt();
        } while (chosenRecipeIndex < 1 || chosenRecipeIndex > recipes.size());

        // Récupérer la recette choisie
        Recipe chosenRecipe = recipes.get(chosenRecipeIndex - 1);

        // Fermer le scanner
        scanner.close();

        return chosenRecipe;
    }
}
   