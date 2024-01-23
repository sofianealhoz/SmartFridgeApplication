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
import java.util.Iterator;

import java.sql.Date;

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
	// Method to retrieve the list of ingredients for a specific fridge based on
	// fridge_id
	public static List<Ingredient> getIngredientsForFridge(int fridgeId) {
		List<Ingredient> ingredients = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			String sql = "SELECT * FROM ingredients WHERE fridge_id = ?";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setInt(1, fridgeId);
				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						String name = resultSet.getString("name");
						LocalDate expirationDate = resultSet.getDate("expiration_date").toLocalDate();
						int quantity = resultSet.getInt("quantity");
						String category = resultSet.getString("category");
						String unit = resultSet.getString("unit");

						Ingredient ingredient = new Ingredient(name, expirationDate, quantity, category, unit);
						ingredients.add(ingredient);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ingredients;
	}

	public int getFridgeIdFromDatabase(User owner) {
		int fridgeId = -1; // Default value if not found

		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			String sql = "SELECT fridge_id FROM fridges WHERE name = ?";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setString(1, owner.getUsername());

				try (ResultSet resultSet = statement.executeQuery()) {
					if (resultSet.next()) {
						fridgeId = resultSet.getInt("fridge_id");
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return fridgeId;
	}

	// Function to add a fridge to the database
	public static boolean addFridge(Frigo fridge) {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			String sql = "INSERT INTO fridges (name) VALUES (?)";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				// Set the values for the insert statement
				statement.setString(1, fridge.getOwner().getUsername());

				// Execute the insert statement
				int rowsInserted = statement.executeUpdate();

				// Check if the insertion was successful
				return rowsInserted > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Check if a specific ingredient exists in a fridge by fridgeId and ingredient
	// name
	public static boolean hasIngredientInFridge(int fridgeId, String ingredientName) {
		// Assuming you have a method to get the list of ingredients in a specific
		// fridge by fridgeId
		List<Ingredient> fridgeIngredients = DatabaseAccess.getIngredientsForFridge(fridgeId);

		for (Ingredient ingredient : fridgeIngredients) {
			if (ingredient.getName().equals(ingredientName)) {
				return true;
			}
		}
		return false;
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
					NutritionInfo nutritioninfo = returnNutritionInfo(recipe_id);
					List<String> allergens = new ArrayList<>();

					String SQL = "SELECT * FROM ingredientsofrecipe WHERE r_id = " + recipe_id;
					try (PreparedStatement statement1 = connection.prepareStatement(SQL);
							ResultSet result = statement1.executeQuery()) {

						while (result.next()) {

							String ingredient_name = result.getString("name");
							double ingredient_quantity = result.getInt("quantity");
							String dateString = result.getString("expiration_date");
							LocalDate exp_date = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
							String unit = result.getString("unit");

							Ingredient ingredient = new Ingredient(ingredient_name, exp_date, ingredient_quantity,
									"Unknown", unit);
							ingredients.add(ingredient);
						}

					}

					// Ajoutez ici d'autres attributs de la classe Recipe en fonction de votre
					// modèle
					Recipe recipe = new Recipe(recipe_name, image_URL, ingredients, instructions, nutritioninfo,
							allergens);
					recipes.add(recipe);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Gérer les exceptions de manière appropriée dans votre application
		}

		return recipes;
	}

	public static NutritionInfo returnNutritionInfo(int recipe_id) {

		Double calories = null, fat = null, protein = null, carbs = null, fiber = null, sugar = null, sodium = null;

		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			String sql = "SELECT * FROM nutrition_info WHERE recipe_id = " + recipe_id;
			try (PreparedStatement statement = connection.prepareStatement(sql);
					ResultSet resultSet = statement.executeQuery()) {

				while (resultSet.next()) {
					calories = resultSet.getDouble("calories");
					fat = resultSet.getDouble("fat");
					protein = resultSet.getDouble("protein");
					carbs = resultSet.getDouble("carbs");
					fiber = resultSet.getDouble("fiber");
					sugar = resultSet.getDouble("sugar");
					sodium = resultSet.getDouble("sodium");

				}
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Gérer les exceptions de manière appropriée dans votre application
		}

		NutritionInfo nutrition_info = new NutritionInfo(calories, fat, protein, carbs, fiber, sugar, sodium);

		return nutrition_info;
	}

	public static void resetDatabaseRecipe() {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			// Supprimer toutes les lignes de la table "ingredientsofrecipe"
			String deleteIngredientsOfRecipe = "DELETE FROM ingredientsofrecipe";
			try (PreparedStatement deleteIngredientsOfRecipeStatement = connection
					.prepareStatement(deleteIngredientsOfRecipe)) {
				deleteIngredientsOfRecipeStatement.executeUpdate();
			}

			// Supprimer toutes les lignes de la table "recipe"
			String deleteRecipe = "DELETE FROM recipe";
			try (PreparedStatement deleteRecipeStatement = connection.prepareStatement(deleteRecipe)) {
				deleteRecipeStatement.executeUpdate();
			}

			// Supprimer toutes les lignes de la table "recipe"
			String deleteNutritionInfo = "DELETE FROM nutrition_info";
			try (PreparedStatement deleteRecipeStatement = connection.prepareStatement(deleteNutritionInfo)) {
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

	// Method to insert a new row into the fridgeingredients table when adding an
	// ingredient to a fridge
	public static void insertIngredientToFridge(int fridgeId, int ingredientId, double quantity) {
		String sql = "INSERT INTO fridgeingredients (fridge_id, ingredient_id, quantity) VALUES (?, ?, ?)";

		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setInt(1, fridgeId);
				statement.setInt(2, ingredientId);
				statement.setDouble(3, quantity);

				statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void instertNutritionInfo(NutritionInfo nutritionInfo, int recipe_id) {
		try {
			// Établissez la connexion à la base de données
			try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
				String sql = "INSERT INTO nutrition_info (recipe_id, calories, fat, protein, carbs, fiber, sugar, sodium) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

				try (PreparedStatement insertStatement = connection.prepareStatement(sql)) {

					insertStatement.setInt(1, recipe_id);
					insertStatement.setDouble(2, nutritionInfo.getCalories());
					insertStatement.setDouble(3, nutritionInfo.getFat());
					insertStatement.setDouble(4, nutritionInfo.getProtein());
					insertStatement.setDouble(5, nutritionInfo.getCarbs());
					insertStatement.setDouble(6, nutritionInfo.getFiber());
					insertStatement.setDouble(7, nutritionInfo.getSugar());
					insertStatement.setDouble(8, nutritionInfo.getSodium());
					// Ajouter le batch pour l'exécution efficace de plusieurs insertions
					insertStatement.addBatch();

					// Exécuter le batch d'insertions
					insertStatement.executeBatch();
				}
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
		String sql = "INSERT INTO ingredientsofrecipe (name, quantity, expiration_date, r_id, unit) VALUES (?, ?, ?, ?, ?)";

		try (PreparedStatement insertStatement = connection.prepareStatement(sql)) {
			for (Ingredient ingredient : ingredients) {
				insertStatement.setString(1, ingredient.getName());
				insertStatement.setDouble(2, ingredient.getQuantity());
				insertStatement.setDate(3, java.sql.Date.valueOf(ingredient.getExpirationDate()));
				insertStatement.setInt(4, recipe_id);
				insertStatement.setString(5, ingredient.getUnit());
				// Ajouter le batch pour l'exécution efficace de plusieurs insertions
				insertStatement.addBatch();
			}

			// Exécuter le batch d'insertions
			insertStatement.executeBatch();
		}
	}

	// Méthode qui appelle updateIngredientQuantity for a specific fridge
	public static void callUpdateIngredientQuantityForFridge(int fridgeId, String name, double newQuantity) {
		try {
			// Établissez la connexion à la base de données
			try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
				updateIngredientQuantityForFridge(connection, fridgeId, name, newQuantity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Méthode pour mettre à jour la quantité d'un ingrédient dans un frigo
	// spécifique
	private static void updateIngredientQuantityForFridge(Connection connection, int fridgeId, String name,
			double newQuantity) throws SQLException {
		String sql = "UPDATE Ingredients SET quantity = ? WHERE fridge_id = ? AND name = ?";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setDouble(1, newQuantity);
			statement.setInt(2, fridgeId);
			statement.setString(3, name);
			statement.executeUpdate();
		}
	}

	public void addIngredient(String name, double quantity, LocalDate expirationDate, String category, String unit,
			int fridgeId) {
		String sql = "INSERT INTO Ingredients (name, quantity, expiration_date, category, unit, fridge_id) VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, name);
			statement.setDouble(2, quantity);
			statement.setDate(3, java.sql.Date.valueOf(expirationDate));
			statement.setString(4, category);
			statement.setString(5, unit);
			statement.setInt(6, fridgeId);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteIngredientByName(String name, int fridgeId) {
		String sql = "DELETE FROM Ingredients WHERE name = ? AND fridge_id = ?";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, name);
			statement.setInt(2, fridgeId);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
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

	public static List<Ingredient> getExpiredIngredients() {
		List<Ingredient> expiredIngredients = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			String sql = "SELECT * FROM Ingredients WHERE expiration_date < ?";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				// Utilisez la date actuelle pour comparer avec les dates d’expiration
				LocalDate currentDate = LocalDate.now();
				statement.setDate(1, Date.valueOf(currentDate));

				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						String name = resultSet.getString("name");
						LocalDate expirationDate = resultSet.getDate("expiration_date").toLocalDate();
						int quantity = resultSet.getInt("quantity");
						String category = resultSet.getString("category");

						Ingredient ingredient = new Ingredient(name, expirationDate, quantity, category);
						expiredIngredients.add(ingredient);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return expiredIngredients;
	}

	public static List<Ingredient> getSoonExpiredIngredients() {
		List<Ingredient> soonexpiredIngredients = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			String sql = "SELECT * FROM Ingredients WHERE ? < ? AND ? < expiration_date ";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				// Utilisez la date actuelle pour comparer avec les dates d’expiration
				LocalDate currentDate = LocalDate.now();
				LocalDate soonToExpireDate = currentDate.minusWeeks(1); // Date proche de l'expiration
				statement.setDate(1, Date.valueOf(soonToExpireDate));
				statement.setDate(2, Date.valueOf(currentDate));
				statement.setDate(3, Date.valueOf(currentDate));

				try (ResultSet resultSet = statement.executeQuery()) {
					while (resultSet.next()) {
						String name = resultSet.getString("name");
						LocalDate expirationDate = resultSet.getDate("expiration_date").toLocalDate();
						int quantity = resultSet.getInt("quantity");
						String category = resultSet.getString("category");

						Ingredient ingredient = new Ingredient(name, expirationDate, quantity, category);
						soonexpiredIngredients.add(ingredient);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return soonexpiredIngredients;
	}

}
