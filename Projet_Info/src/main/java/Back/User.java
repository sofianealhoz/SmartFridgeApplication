package Back;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private Frigo fridge; // Each user has only one fridge
    private List<String> allergies; // List to store allergies

    public User(String username,DatabaseAccess databaseAccess) {
        this.username = username;
        this.fridge = new Frigo(this, databaseAccess); // Initialize with a new Frigo
        this.allergies = new ArrayList<>(); // Initialize allergies list
    }

    // Getter for the fridge
    public Frigo getFridge() {
        return fridge;
    }

    public String getUsername(){
        return username;
    }
    
    // Setter for the fridge
    public void setFridge(Frigo fridge) {
        this.fridge = fridge;
    }

    // Getter for allergies
    public List<String> getAllergies() {
        return allergies;
    }

    // Method to add an allergy
    public void addAllergy(String allergy) {
        allergies.add(allergy);
    }

    // Method to remove an allergy
    public void removeAllergy(String allergy) {
        allergies.remove(allergy);
    }
}
