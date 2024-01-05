package back;

import java.time.LocalDate;

public class Ingredient {
    private String name;
    private LocalDate expirationDate;
    private double quantity;
    private String category;
   

    public Ingredient(String name, LocalDate expirationDate, int quantity, String category) {
        this.name = name;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public double getQuantity() {
        return quantity;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    
    // Add the equals method to compare two ingredients
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ingredient ingredient = (Ingredient) obj;
        return name.equals(ingredient.name);
    }
}
