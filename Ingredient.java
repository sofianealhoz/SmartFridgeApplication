package test;

import java.time.LocalDate;

public class Ingredient {
    private String name;
    private LocalDate expirationDate;
    private int quantity;

    public Ingredient(String name, LocalDate expirationDate, int quantity) {
        this.name = name;
        this.expirationDate = expirationDate;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public int getQuantity() {
        return quantity;
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
