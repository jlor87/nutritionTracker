import org.junit.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class GUITest {
    Connection connectionToMySQL;
    GUI gui;

    @Before public void setUp(){
        connectionToMySQL = Main.getConnection();
    }
    // Due to time constraints, proper creation of GUI elements can be verified visually
    // This test verifies if the helper methods in the class are functioning properly
    @Test
    public void testGUI(){
        gui = new GUI(connectionToMySQL);

        // Delete test account before creation of a new test account
        String query = "DELETE FROM users WHERE username = ?";
        try {
            PreparedStatement preparedStatement = connectionToMySQL.prepareStatement(query);

            // Set the parameter for the username
            preparedStatement.setString(1, "test");

            // Execute the delete operation and get the number of affected rows
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                // Show success
                System.out.println("User deleted successfully.");
            } else {
                System.out.println("Error deleting user or user not found.");
            }

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Check if account creation works
        // Assert.assertTrue(gui.createAccount("test", "test"));

        // Check login functionality using the new credentials
        // Assert.assertTrue(gui.checkCredentials("test", "test"));

        // Check if user and session is properly instantiated
        gui.createSession();
        Assert.assertNotNull(gui.getCurrentUser());
        Assert.assertNotNull(gui.getSession());
    }
}
