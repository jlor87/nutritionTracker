import org.junit.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserTest {
    Connection connectionToMySQL;
    int retrievedUserId;
    User user;
    Method[] methods;

    @Before
    public void setUp(){
        connectionToMySQL = Main.getConnection();
        String query = "SELECT userId FROM users WHERE username = ?";
        try
        {
            PreparedStatement preparedStatement = connectionToMySQL.prepareStatement(query);
            preparedStatement.setString(1, "test");

            // Execute and retrieve result
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                retrievedUserId = resultSet.getInt("userId");
            }
            else
            {
                System.out.println("Error retrieving userId.");
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        user = new User(retrievedUserId);

        methods = Utility.getMethods();
    }

    @Test
    public void testUserConstructor(){
        // Check all getters (implying setters too), and if all default nutrient intake values are zero
        for(Method method : methods){
            if(method.getName().startsWith("get")
                    && !method.getName().equals("getFoodCatalog")
                    && !method.getName().equals("getUserId")
                    && !method.getName().equals("getDailyFoodsConsumed")
            ){
                try {
                    Object value = method.invoke(user, 1);
                    Assert.assertTrue(value instanceof Number);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        // Check if Food Catalog is initialized
        Assert.assertNotNull(user.getFoodCatalog());
    }

    @Test
    public void testAddFood(){
        user.addFood(new Food("banana"));
        Assert.assertTrue(user.getDailyFoodsConsumed().contains("banana"));
    }

    @Test
    public void testUpdateAllFromDatabase(){
        Assert.assertTrue(user.updateAllFromDatabase());
    }
}
