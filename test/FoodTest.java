import org.junit.*;

import java.lang.reflect.Method;

public class FoodTest {
    Method[] methods = Utility.getFoodMethods();

    @Test
    public void testFoodConstructor(){
        Food food = new Food("Test");
        Assert.assertEquals(food.getName(), "Test");
        for(Method method : methods){
            try {
                // Only check methods that are getters (this also tests the setters in the constructor if they work too)
                if (method.getName().startsWith("get") && method.getParameterCount() == 0) {
                    // Invoke the method on the food object and check if the return type is a number
                    Object returnValue = method.invoke(food);

                    if (returnValue instanceof Number) {
                        // Check if the number is 0, which is the expected, default value when a new Food class is created
                        Assert.assertEquals(((Number) returnValue).doubleValue(), 0.0);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                Assert.fail("Exception occurred while testing!");
            }
        }
    }
}
