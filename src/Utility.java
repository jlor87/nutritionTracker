import java.lang.reflect.*;

// We need a way to take the nutrient names from the API (such as Vitamin A)
// and invoke the correct setters for them (setVitaminA()). To get the names
// of these methods in User, we will use Java's reflection feature, which will
// allow us to grab methods and their names from the User class. Then we can
// match the nutrient name to the method name and invoke the correct setters.

public class Utility {
    private static Method[] userMethods;

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

    public static Method[] getMethods(){ // Returns a publicly accessible list of the methods in User
        return userMethods;
    }
}
