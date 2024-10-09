import java.lang.reflect.*;

// This class exists to provide added Java-related functionality to the app.

// We needed a way to take the nutrient names from the API (such as "Vitamin A")
// and invoke the correct setters for them (setVitaminA()). To grab the names
// of these methods from the User class, we use Java's reflection feature, which will
// allow us to grab both the methods and their names. We can then use string manipulation to
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
