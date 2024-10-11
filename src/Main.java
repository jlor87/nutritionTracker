/**
 * This class is where the whole application is instantiated
 * 
 */
public class Main {

    // The Main class is only responsible for starting the application
    public static void main(String[] args) {
        User newUser = new User();
        Session newSession = new Session(newUser);
        newSession.startSession();
        newSession.menu();
    }
}


