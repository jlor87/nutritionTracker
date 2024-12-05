
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

/**
 * This class is where the whole application is instantiated
 *
 */
public class Main
{

    private static Connection connectionToMySQL;
    private static GUI gui;

    // Getters
    public static GUI getGui()
    {
        return gui;
    }

    // Utility methods
    public static Connection getConnection()
    {
        if(connectionToMySQL == null)
        {
            try
            {
                connectionToMySQL = DriverManager.getConnection(
                        "jdbc:mysql://34.227.86.98:3306/nutritionTracker?user=publicuser&password=iamnotroot"
                );
                System.out.println("Connection to database successful.");
            }
            catch(SQLException ex)
            {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }
        return connectionToMySQL;
    }

    // The Main class is only responsible for starting the application
    public static void main(String[] args)
    {
        // Establish connection to the MySQL database for the whole session
        connectionToMySQL = getConnection();

        // Set up the GUI
        gui = new GUI();

        // Construct the GUI screens
        gui.makeTitleScreen();
        gui.makeLoginScreen();
        gui.makeCreateScreen();
        gui.makeMainScreen();
        gui.makeSearchItemScreen();
        gui.makeSetGoalsScreen();
        gui.makeStatusGoalsScreen();
        gui.makeAlterUserDataScreen();
        gui.makeCatalogFoodIntakeScreen();
        gui.makeCustomFoodItemScreen();
        gui.makeListOfCustomAndSearchedFoods();
        gui.makeAdvancedStatisticsScreen();
        // Display the initial screen
        gui.displayTitleScreen();
    }
}
