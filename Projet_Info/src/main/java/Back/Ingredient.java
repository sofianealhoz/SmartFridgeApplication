package Back;

import java.time.LocalDate;

public class Ingredient {
	// Fields to store ingredient information
    private String name;
    private LocalDate expirationDate;
    private double quantity;
    private String category;
   
    // Constructor to initialize
    public Ingredient(String name, LocalDate expirationDate, double quantity, String category) {
        this.name = name;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
        this.category = category;
    }
    
    // Getter method for the name of the ingredient
    public String getName() {
        return name;
    }

	// Constructor to initialize
	public Ingredient(String name, LocalDate expirationDate, int quantity, String category) {
		this.name = name;
		this.expirationDate = expirationDate;
		this.quantity = quantity;
		this.category = category;
	}

	// Getter method for the name of the ingredient
	public String getName() {
		return name;
	}

	// Getter method for the expiration date of the ingredient
	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	// Getter method for the quantity of the ingredient
	public double getQuantity() {
		return quantity;
	}

	// Getter method for the category of the ingredient
	public String getCategory() {
		return category;
	}

	// Setter method to update the category of the ingredient
	public void setCategory(String category) {
		this.category = category;
	}

	// Add the equals method to compare two ingredients based on their names
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Ingredient ingredient = (Ingredient) obj;
		return name.equals(ingredient.name);
	}

	public void setQuantity(double d) {
		this.quantity = d;
	}
}
