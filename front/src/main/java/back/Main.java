package back;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
    	List<Recipe> recipes = new ArrayList<>();
        Frigo monFrigo = new Frigo();
        Ingredient meat = new Ingredient("meat", LocalDate.of(2024, 12, 1), 3, null);
        Ingredient eggs = new Ingredient("eggs", LocalDate.of(2024, 5, 20), 10, null);
        monFrigo.addIngredient(meat);
        monFrigo.addIngredient(eggs);

        // Fetch and display the current ingredients
        monFrigo.displayContents();

        // Use the RecipeFinder to find recipes based on the ingredients
        recipes = RecipeFinder.searchRecipes(monFrigo.getIngredients());
        Recipe chosenRecipe = Recipe.chooseRecipe(recipes, monFrigo);
        chosenRecipe.displayRecipe();


    }
}


