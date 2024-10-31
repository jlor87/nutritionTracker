import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class is where the whole application is instantiated
 * 
 */

public class Main {

    // The Main class is only responsible for starting the application
    public static void main(String[] args) {
        // Establish connection to the MySQL database for the whole session
        Connection connectionToMySQL = null;
        try {
            connectionToMySQL = DriverManager.getConnection("jdbc:mysql://34.227.86.98:3306/nutritionTracker?user=publicuser&password=iamnotroot");
            System.out.println("Connection to database successful.");
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        GUI gui = new GUI(connectionToMySQL);
        User newUser = new User(gui.getRetrievedUserId());
        Session newSession = new Session(newUser, gui);

        gui.setSession(newSession); // Pass session to GUI

        // Construct the GUI screens
        gui.makeTitleScreen();
        gui.makeLoginScreen();
        gui.makeCreateScreen();
        gui.makeMainScreen();
        gui.makeSearchItemScreen();
        gui.makeSetGoalsScreen();
        // Display the initial screen
        gui.displayTitleScreen();

        // Start user session
        newSession.startSession();
        newSession.menu();
    }
}


