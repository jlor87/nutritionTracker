import java.lang.reflect.*;

/**
 * This class exists to provide added Java-related functionality to the app.
 * 
 * We needed a way to: 
 * 1. take the nutrient names from the API (such as "Vitamin A")
 * 2. invoke the correct setters for them ie. setVitaminA("API's Response"). 
 * 3. To grab name of methods from User class, we use Java's reflection feature, allows us to grab both the methods and their names. 
 * 4. We can then use string manipulation to match the nutrient name to the method name and invoke the correct setters.
 */
public class Utility {
    private static Method[] userMethods;
    private static Method[] foodMethods;

    // Grab all the methods in the User class and store them into the array, userMethods
    static{
        try {
            Class<?> userClass = User.class; // Use generics for type safety
            userMethods = userClass.getDeclaredMethods();
        }
        catch (Throwable e) {
            System.err.println(e);
        }
    }

    static{
        try {
            Class<?> foodClass = Food.class; // Use generics for type safety
            foodMethods = foodClass.getDeclaredMethods();
        }
        catch (Throwable e) {
            System.err.println(e);
        }
    }

    public static Method[] getMethods(){ // Returns a publicly accessible list of the methods in User
        return userMethods;
    }

    public static Method[] getFoodMethods(){ // Returns a publicly accessible list of the methods in the Food class
        return foodMethods;
    }
}
