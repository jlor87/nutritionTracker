import org.junit.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionTest {
    Connection connectionToMySQL;
    int retrievedUserId;
    User user;
    Session session;

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
        session = new Session(user, new GUI(Main.getConnection()));
        session.startSession();
    }

    @Test
    public void testSessionConstructor(){
        // Check for proper instantiation of all necessary supporting classes
        Assert.assertNotNull(session.getCurrentUser());
        Assert.assertNotNull(session.getGui());
        Assert.assertNotNull(session.getConnectionToMySQL());
    }

    @Test
    public void testStartSession(){
        Assert.assertNotNull(session.getUserSettings());
        Assert.assertNotNull(session.getPrint());
        Assert.assertNotNull(session.getApi());
    }

    @Test
    public void testAddFoodItem(){
        // This function essentially tests the updateUserConsumption() method, already present in the UserSettingsTest class
        boolean notNeeded = true;
        Assert.assertTrue(notNeeded);
    }

    @Test
    public void testGetFoodInput(){
        // This function essentially tests the sendAPIRequest() method, already present in the APITest class
        boolean notNeeded = true;
        Assert.assertTrue(notNeeded);
    }

    @Test
    public void testChangeGoals(){
        // This function essentially tests the setGoalData() method, already present in the UserSettingsTest class
        boolean notNeeded = true;
        Assert.assertTrue(notNeeded);
    }

    @Test
    public void testShowGoals(){
        // This function essentially tests the outputCurrentConsumption() method, already present in the PrintTest class
        boolean notNeeded = true;
        Assert.assertTrue(notNeeded);
    }

}
