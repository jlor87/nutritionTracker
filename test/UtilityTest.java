import org.junit.*;
import java.lang.reflect.*;
public class UtilityTest {
    User user;
    @Before
    public void setUp(){
        user = new User(1);
    }
    @Test
    public void testGetMethods(){
        Method[] userMethods = Utility.getMethods();

        // Use all setter methods to set values to 5.0
        for (Method userMethod : userMethods) {
            try {
                if(userMethod.getName().contains("set")) {
                    userMethod.invoke(user, 0, 5.0); // test value of 5.0
                    userMethod.invoke(user, 1, 5.0);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        // If the returned values are all 5.0, then both the setters and getters work normally
        for (Method userMethod : userMethods) {
            try{
                // Test getters to see if they work
                if(userMethod.getName().contains("get")){
                    Double valueOfIndexZero = (Double) userMethod.invoke(user, 0);
                    Double valueOfIndexOne = (Double) userMethod.invoke(user, 1);
                    Assert.assertEquals(5.0, valueOfIndexZero, 0.0);
                    Assert.assertEquals(5.0, valueOfIndexOne, 0.0);
                }
            } catch (Exception e){
                System.out.println(e);
            }
        }


    }
}
