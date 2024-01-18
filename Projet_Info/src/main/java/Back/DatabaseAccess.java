package Back;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

	// Méthode pour récupérer la liste des ingrédients présents dans la base de données
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
	
	// Méthode pour insérer des données dans la table Recipe
	public void insertRecipe(Connection connection, String name, String imageURL, String ingredientsList,
			String allergensList) throws SQLException {
		String sql = "INSERT INTO Recipe (name, imageURL, ingredients_list, allergens_list) VALUES (?, ?, ?, ?)";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, name);
			statement.setString(2, imageURL);
			statement.setString(3, ingredientsList);
			statement.setString(4, allergensList);
			statement.executeUpdate();
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
	
	private static void updateIngredientQuantity(Connection connection, String name, int newQuantity) throws SQLException {
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
