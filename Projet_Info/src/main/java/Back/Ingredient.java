package Back;

import java.time.LocalDate;

public class Ingredient {
	// Fields to store ingredient information
    private String name;
    private LocalDate expirationDate;
    private double quantity;
    private String category;
	private String unit; // Can be null or empty
   
    // Constructor to initialize
    public Ingredient(String name, LocalDate expirationDate, double quantity, String category, String unit) {
        this.name = name;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
        this.category = category;
		this.unit = unit != null ? unit : ""; // Set unit to empty string if null
    }

    // Overloaded constructor without unit
    public Ingredient(String name, LocalDate expirationDate, int quantity, String category) {
        this(name, expirationDate, quantity, category, ""); // Call the other constructor with an empty unit
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

	// Getter for unit
    public String getUnit() {
        return unit != null ? unit : "";
    }

	public void setUnit(String unit) {
        this.unit = unit != null ? unit : "";
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
	public void updateQuantity(double newQuantity) {
	    this.quantity = newQuantity;
	}

}
