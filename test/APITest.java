import org.junit.*;

public class APITest {
    User currentUser;
    API api;

    @Before
    public void setUp(){
        currentUser = new User(1);
        api = new API(currentUser);
    }

    // Tests if the API method can properly connect and retrieve the correct information from external resources
    @Test
    public void testSendAPIRequest(){
        String expectedOutput = ".........Retrieving banana nutrition information.........\n" +
                "Food: BANANA\n" +
                "Protein: 12.5 G\n" +
                "Total lipid (fat): 6.25 G\n" +
                "Carbohydrate, by difference: 40.6 G\n" +
                "Energy: 312.0 KCAL\n" +
                "Total Sugars: 6.25 G\n" +
                "Fiber, total dietary: 6.2 G\n" +
                "Calcium, Ca: 125.0 MG\n" +
                "Iron, Fe: 1.12 MG\n" +
                "Sodium, Na: 594.0 MG\n" +
                "Vitamin A, IU: 0.0 IU\n" +
                "Vitamin C, total ascorbic acid: 15.0 MG\n" +
                "Cholesterol: 0.0 MG\n" +
                "Fatty acids, total trans: 0.0 G\n" +
                "Fatty acids, total saturated: 0.0 G\n" +
                "Fatty acids, total monounsaturated: 3.12 G\n" +
                "Fatty acids, total polyunsaturated: 3.12 G\n" +
                ".........banana nutrition information complete.........\n";
        Assert.assertEquals(expectedOutput, api.sendAPIRequest("banana"));
    }
}
