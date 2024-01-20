package Back;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.time.format.DateTimeFormatter;

public class DatabaseAccess {

	// Définissez les informations de connexion à votre base de données
	private static final String URL = "jdbc:mysql://127.0.0.1/prinfo";
	private static final String USER = "root";
	private static final String PASSWORD = "root";

	/*
	 * public static void main(String[] args) { try { // Établissez la connexion à
	 * la base de données try (Connection connection =
	 * DriverManager.getConnection(URL, USER, PASSWORD)) { // Exemple d'insertion de
	 * données dans la table Recipe // insertRecipe(connection, "Nom de la recette",
	 * "URL de l'image", // "[\"ingrédient1\", \"ingrédient2\"]", "allergène");
	 * 
	 * // Exemple d'insertion de données dans la table Ingredients
	 * insertIngredient(connection, "Nom de l'ingrédient", 100, "2024-01-17",
	 * "Catégorie de l'ingrédient");
	 * 
	 * // Exemple de suppression d'un ingrédient par son nom // try { //
	 * deleteIngredientByName(connection, "Nom de l'ingrédient"); // } catch
	 * (SQLException e) { // e.printStackTrace(); // }
	 * 
	 * // Exemple de sélection de données dans la table Recipe //
	 * selectRecipes(connection); } } catch (SQLException e) { e.printStackTrace();
	 * }
	 * 
	 * }
	 */

	// Méthode pour récupérer la liste des ingrédients présents dans la base de
	// données
	public static List<Ingredient> getIngredientsList() {
		List<Ingredient> ingredients = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			String sql = "SELECT * FROM Ingredients";
			try (PreparedStatement statement = connection.prepareStatement(sql);
					ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					String name = resultSet.getString("name");
					LocalDate expirationDate = resultSet.getDate("expiration_date").toLocalDate();
					int quantity = resultSet.getInt("quantity");
					String category = resultSet.getString("category");

					Ingredient ingredient = new Ingredient(name, expirationDate, quantity, category);
					ingredients.add(ingredient);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ingredients;
	}

	// Retourne la liste de recette stockée dans la base de données :
	public static List<Recipe> returnRecipeList() {
		List<Recipe> recipes = new ArrayList<>();
		List<Ingredient> ingredients = new ArrayList<>();
		
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM Recipe";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    
                    int recipe_id = resultSet.getInt("recipe_id");
                    String recipe_name = resultSet.getString("name");
                    String image_URL = resultSet.getString("imageURL");
                    String[] instructionsArray = resultSet.getString("instructions_list").split("\n");
                    List<String> instructions = Arrays.asList(instructionsArray);
                    NutritionInfo nutritioninfo = new NutritionInfo(0, 0, 0, 0, 0, 0, 0);
                    List<String> allergens = new ArrayList<>();
                    
                    
                    String SQL = "SELECT * FROM ingredientsofrecipe WHERE r_id = " + recipe_id;
                    try (PreparedStatement statement1 = connection.prepareStatement(SQL);
                            ResultSet result = statement1.executeQuery()) {
                    		
                    	while (result.next()) {
                    		
                    		String ingredient_name = result.getString("name");
                    		double ingredient_quantity = result.getInt("quantity"); 
                    		String dateString = result.getString("expiration_date");
                    		LocalDate exp_date = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
                    		
                    		Ingredient ingredient = new Ingredient(ingredient_name, exp_date, ingredient_quantity, "Unknown");
                    		ingredients.add(ingredient);
                    	}
                    		
                    }
                    
                    // Ajoutez ici d'autres attributs de la classe Recipe en fonction de votre modèle
                    Recipe recipe = new Recipe(recipe_name, image_URL, ingredients ,instructions, nutritioninfo, allergens);
                    recipes.add(recipe);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérer les exceptions de manière appropriée dans votre application
        }
		
		
		return recipes;
	}

	 public static void resetDatabaseRecipe() {
	        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
	            // Supprimer toutes les lignes de la table "ingredientsofrecipe"
	            String deleteIngredientsOfRecipe = "DELETE FROM ingredientsofrecipe";
	            try (PreparedStatement deleteIngredientsOfRecipeStatement = connection.prepareStatement(deleteIngredientsOfRecipe)) {
	                deleteIngredientsOfRecipeStatement.executeUpdate();
	            }

	            // Supprimer toutes les lignes de la table "recipe"
	            String deleteRecipe = "DELETE FROM recipe";
	            try (PreparedStatement deleteRecipeStatement = connection.prepareStatement(deleteRecipe)) {
	                deleteRecipeStatement.executeUpdate();
	            }

	            System.out.println("Database reset successful.");
	        } catch (SQLException e) {
	            e.printStackTrace(); // Gérer les exceptions de manière appropriée dans votre application
	        }
	    }
	
	// Méthode pour insérer une liste de recettes dans la base de données
	public static void insertRecipe(Recipe recipe, int recipe_id) {
		String sql = "INSERT INTO Recipe (recipe_id, name, imageUrl, allergens_list, instructions_list) "
				+ "VALUES (?, ?, ?, ?, ?)";

		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			String allergens = String.join("\n", recipe.getAllergens());
			String instructions = String.join("\n", recipe.getInstructions());

			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				// Paramètres de la requête
				statement.setInt(1, recipe_id);
				statement.setString(2, recipe.getName());
				statement.setString(3, recipe.getImageUrl());
				statement.setString(4, allergens);
				statement.setString(5, instructions);

				// Exécution de la requête
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void callInsertIngredient(String name, String quantity, String date, String category) {
		try {
			// Établissez la connexion à la base de données
			try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
				insertIngredient(connection, name, quantity, date, category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void callInsertListOfIngredient(List<Ingredient> ingredients, int recipe_id) {
		try {
			// Établissez la connexion à la base de données
			try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
				insertListOfIngredients(connection, ingredients, recipe_id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void insertListOfIngredients(Connection connection, List<Ingredient> ingredients, int recipe_id)
			throws SQLException {
		String sql = "INSERT INTO ingredientsofrecipe (name, quantity, expiration_date, r_id) VALUES (?, ?, ?, ?)";

		try (PreparedStatement insertStatement = connection.prepareStatement(sql)) {
			for (Ingredient ingredient : ingredients) {
				insertStatement.setString(1, ingredient.getName());
				insertStatement.setDouble(2, ingredient.getQuantity());
				insertStatement.setDate(3, java.sql.Date.valueOf(ingredient.getExpirationDate()));
				insertStatement.setInt(4, recipe_id);

				// Ajouter le batch pour l'exécution efficace de plusieurs insertions
				insertStatement.addBatch();
			}

			// Exécuter le batch d'insertions
			insertStatement.executeBatch();
		}
	}

	// Méthode pour insérer des données dans la table Ingredients
	private static void insertIngredient(Connection connection, String name, String d, String localDate,
			String category) throws SQLException {
		String sql = "INSERT INTO Ingredients (name, quantity, expiration_date, category) VALUES (?, ?, ?, ?)";
		try (PreparedStatement insertStatement = connection.prepareStatement(sql)) {
			insertStatement.setString(1, name);
			insertStatement.setString(2, d);
			insertStatement.setString(3, localDate);
			insertStatement.setString(4, category);
			insertStatement.executeUpdate();
		}
	}

	// Méthode qui appelle updateIngredientIngredient
	public static void callUpdateIngredientQuantity(String name, int newQuantity) {
		try {
			// Établissez la connexion à la base de données
			try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
				updateIngredientQuantity(connection, name, newQuantity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void updateIngredientQuantity(Connection connection, String name, int newQuantity)
			throws SQLException {
		String sql = "UPDATE Ingredients SET quantity = ? WHERE name = ?";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, newQuantity);
			statement.setString(2, name);
			statement.executeUpdate();
		}
	}

	// Méthode qui appelle deleteIngredientByName
	public static void callDeleteIngredientByName(String name) {
		try {
			// Établissez la connexion à la base de données
			try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
				deleteIngredientByName(connection, name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Méthode pour supprimer un ingrédient à partir de son nom
	private static void deleteIngredientByName(Connection connection, String ingredientName) throws SQLException {
		String sql = "DELETE FROM Ingredients WHERE name = ?";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, ingredientName);
			statement.executeUpdate();
		}
	}

	// Méthode pour sélectionner toutes les recettes de la table Recipe
	private static void selectRecipes(Connection connection) throws SQLException {
		String sql = "SELECT * FROM Recipe";
		try (PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery()) {
			while (resultSet.next()) {
				int recipeId = resultSet.getInt("recipe_id");
				String name = resultSet.getString("name");
				String imageURL = resultSet.getString("imageURL");
				String ingredientsList = resultSet.getString("ingredients_list");
				String allergensList = resultSet.getString("allergens_list");

				// Faites ce que vous voulez avec les données récupérées, par exemple,
				// imprimez-les
				System.out.println("Recipe ID: " + recipeId);
				System.out.println("Name: " + name);
				System.out.println("Image URL: " + imageURL);
				System.out.println("Ingredients List: " + ingredientsList);
				System.out.println("Allergens List: " + allergensList);
				System.out.println();
			}
		}
	}
}
