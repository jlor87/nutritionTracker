import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

public class APITest {
    private User user;
    private API api;
    @Before
    public void setUp() {
        user = new User(1);
        api = new API(user);
    }
    @Test
    public void testSendAPIRequest(){
        api.sendAPIRequest("banana");
        Assert.assertNotNull(api.getGson());
        Assert.assertNotNull(api.getResponseObject());
        Assert.assertNotNull(api.getFoods());
        Assert.assertNotNull(api.getFirstFood());
        Assert.assertNotNull(api.getNutrients());
        Assert.assertNotNull(api.getNutrient());
    }

    @Test
    public void testUpdateUserConsumption(){
        api.sendAPIRequest("banana");
        api.updateUserConsumption(api.getNutrients(), user);
        Assert.assertNotNull(api.getUserMethods());
        Assert.assertTrue(!api.getNutrientName().isEmpty() // Isn't empty
                && api.getNutrientName().equals(api.getNutrientName().toLowerCase()) // Is all lowercase
                && !api.getNutrientName().contains(" ")); // Doesn't have whitespace
        Assert.assertEquals(Double.class, ((Object) api.getAmount()).getClass()); // Checks if data received from the API is in the correct format

        // Verify that the data in User actually is changed to the recieved value from the API
        Method[] userMethods = api.getUserMethods();
        Double finalAmount = -1.0;
        for (Method currentMethod : userMethods) {
            if (currentMethod.getName().toLowerCase().contains("get" + api.getNutrientName())) { // call the correct getter for the last updated value
                try {
                    finalAmount = (Double) currentMethod.invoke(api.getCurrentUser(), 1);
                    break;
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
        Assert.assertEquals(finalAmount, api.getAmount(), 0.0);
    }

}
