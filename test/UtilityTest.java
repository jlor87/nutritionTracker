import org.junit.*;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UtilityTest {

    private static Method[] userMethods;
    private static Method[] foodMethods;

    @Before
    public void setUp() {
        // Initialize methods arrays for testing
        userMethods = Utility.getMethods();
        foodMethods = Utility.getFoodMethods();
    }

    @Test
    public void testUserMethodsNotNull() {
        // Ensure that the userMethods array is not null and contains methods
        assertNotNull(userMethods);
        assertTrue(userMethods.length > 0);
    }

    @Test
    public void testFoodMethodsNotNull() {
        // Ensure that the foodMethods array is not null and contains methods
        assertNotNull(foodMethods);
        assertTrue(foodMethods.length > 0);
    }

    @Test
    public void testUserMethodNamesContainSettersAndGetters() {
        // Check that userMethods contain expected setters and getters
        List<String> methodNames = Arrays.stream(userMethods)
                .map(Method::getName)
                .toList();
        assertTrue(methodNames.contains("setVitaminA"));
        assertTrue(methodNames.contains("getVitaminC"));
    }

    @Test
    public void testFoodMethodNamesContainSettersAndGetters() {
        // Check that foodMethods contain expected setter and getters
        List<String> methodNames = Arrays.stream(foodMethods)
                .map(Method::getName)
                .toList();
        assertTrue(methodNames.contains("setVitaminA"));
        assertTrue(methodNames.contains("getVitaminC"));
    }
}

