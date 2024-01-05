package back;

public class NutritionInfo {
    private double calories;
    private double fat;
    private double protein;
    private double carbs;
    private double fiber;
    private double sugar;
    private double sodium;
    
    public NutritionInfo(double calories, double fat, double protein, double carbs, double fiber, double sugar, double sodium) {
        this.calories = calories;
        this.fat = fat;
        this.protein = protein;
        this.carbs = carbs;
        this.fiber = fiber;
        this.sugar = sugar;
        this.sodium = sodium;
    }

    
    public double getCalories() {
        return calories;
    }

    public double getFat() {
        return fat;
    }

    public double getProtein() {
        return protein;
    }

    public double getCarbs() {
        return carbs;
    }

    public double getFiber() {
        return fiber;
    }

    public double getSugar() {
        return sugar;
    }

    public double getSodium() {
        return sodium;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public void setFiber(double fiber) {
        this.fiber = fiber;
    }

    public void setSugar(double sugar) {
        this.sugar = sugar;
    }

    public void setSodium(double sodium) {
        this.sodium = sodium;
    }

    @Override
    public String toString() {
        return "NutritionInfo{" +
                "calories=" + calories +
                ", fat=" + fat +
                ", protein=" + protein +
                ", carbs=" + carbs +
                ", fiber=" + fiber +
                ", sugar=" + sugar +
                ", sodium=" + sodium +
                '}';
    }
    
}
