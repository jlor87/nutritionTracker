import org.junit.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSettingsTest {
    Connection connectionToMySQL;
    User user;
    int retrievedUserId;
    UserSettings userSettings;

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
        userSettings = new UserSettings(user);
    }

    @Test
    public void testUserSettingsConstructor(){
        Assert.assertNotNull(userSettings.getCurrentUser());
        Assert.assertNotNull(userSettings.getConnectionToMySQL());
    }

    @Test
    public void testSetGoalData(){
        userSettings.setGoalData("Water", "1000");
        Assert.assertEquals(1000, user.getWater(0), 0.00);
    }

    @Test
    public void testUpdateFoodDiary(){
        Assert.assertTrue(userSettings.updateFoodDiary("banana apple orange"));
    }

    @Test
    public void testUpdateUserConsumption(){
        API api = new API(user);
        api.sendAPIRequest("banana");
        //Assert.assertTrue(userSettings.updateUserConsumption());
    }


}
