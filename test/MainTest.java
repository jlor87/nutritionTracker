import org.junit.*;
import java.sql.Connection;
import java.sql.SQLException;

public class MainTest {
    Connection connectionToMySQL;
    GUI gui;

    @Test
    public void testMain(){
        // Test if Main can successfully connect to the database for the rest of the application
        connectionToMySQL = Main.getConnection();
        Assert.assertNotNull(connectionToMySQL);
        try {
            Assert.assertTrue(connectionToMySQL.isValid(2));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Test if Main can successfully instantiate the GUI
        String[] args = {};
        Main.main(args);
        gui = Main.getGui();
        Assert.assertNotNull(gui);
    }
}
