package Back;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class NutritionInfoTest {

    @Test
    public void testGetterAndSetter() {
        NutritionInfo nutritionInfo = new NutritionInfo(200, 10, 20, 30, 5, 15, 500);

        assertEquals(200, nutritionInfo.getCalories(), 0.01);
        assertEquals(10, nutritionInfo.getFat(), 0.01);
        assertEquals(20, nutritionInfo.getProtein(), 0.01);
        assertEquals(30, nutritionInfo.getCarbs(), 0.01);
        assertEquals(5, nutritionInfo.getFiber(), 0.01);
        assertEquals(15, nutritionInfo.getSugar(), 0.01);
        assertEquals(500, nutritionInfo.getSodium(), 0.01);

        nutritionInfo.setCalories(250);
        nutritionInfo.setFat(12);
        nutritionInfo.setProtein(25);
        nutritionInfo.setCarbs(35);
        nutritionInfo.setFiber(8);
        nutritionInfo.setSugar(20);
        nutritionInfo.setSodium(600);

        assertEquals(250, nutritionInfo.getCalories(), 0.01);
        assertEquals(12, nutritionInfo.getFat(), 0.01);
        assertEquals(25, nutritionInfo.getProtein(), 0.01);
        assertEquals(35, nutritionInfo.getCarbs(), 0.01);
        assertEquals(8, nutritionInfo.getFiber(), 0.01);
        assertEquals(20, nutritionInfo.getSugar(), 0.01);
        assertEquals(600, nutritionInfo.getSodium(), 0.01);
    }

    @Test
    public void testToString() {
        NutritionInfo nutritionInfo = new NutritionInfo(200, 10, 20, 30, 5, 15, 500);

        String expectedToString = "NutritionInfo{calories=200.0, fat=10.0, protein=20.0, carbs=30.0, fiber=5.0, sugar=15.0, sodium=500.0}";
        assertEquals(expectedToString, nutritionInfo.toString());
    }
}

