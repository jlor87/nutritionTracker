import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class APITest {
    private User user;
    private API api;
    @Before
    public void setUp() {
        user = new User();
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
        Assert.assertEquals(Double.class, ((Object) api.getAmount()).getClass());
        // Still need to verify that the data in User actually is changed to the values from the API
    }

}
