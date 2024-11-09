import java.sql.Connection;
import java.util.List;


/**
 * The session class is responsible for actually running the program via the startSession() function
 * The session class will most likely be responsible for rendering the GUI as well
 */
public class Session {
    private User currentUser;
    private UserSettings userSettings;
    private API api;
    private Print print;
    private GUI gui;
    private Connection connectionToMySQL;

    // Constructor
    public Session(User user, GUI gui){
        this.currentUser = user;
        this.gui = gui;
        this.connectionToMySQL = Main.getConnection();
    }

    // Class functions
    /**
     * This function is responsible for retrieving the food that the user of the app has consumed or plans to consume
     * @param userInput takes in the name of the food item entered by the user in the Search Food Item option of the menu
     */
    public void addFoodItem(String userInput) {
        userSettings.updateUserConsumption(api.getNutrients(), api.getCurrentFoodName());
        gui.outputArea.append(String.format("Food Item %s added to daily consumption\n", userInput));
    }

    public void changeGoals(String nutrient, String newVal) {

        userSettings.setGoalData(nutrient, newVal);
    }

    public void getFoodInput(String userInput){
        System.out.print("\nInput food item: ");
        System.out.println("You entered: " + userInput);
        String result = api.sendAPIRequest(userInput); // Calling api with user's food input
        gui.outputArea.append(result + "\n");
        gui.outputArea.append("CLICK THE 'ADD' BUTTON TO ADD THIS FOOD ITEM TO YOUR CONSUMPTION.\n");
    }
    
    public void showGoals() {
    	 List<String> goalStrings = print.outputCurrentConsumption();
         String macronutrientsProgress = goalStrings.get(0);
         String vitaminsProgress = goalStrings.get(1);
         String mineralsProgress = goalStrings.get(2);
         gui.macronutrientsField.setText(macronutrientsProgress);
         gui.vitaminsField.setText(vitaminsProgress);
         gui.mineralsField.setText(mineralsProgress);
    }

    public void startSession(){
        // Initialize these utility classes for later usage
        userSettings = new UserSettings(currentUser);
        print = new Print(currentUser);
        api = new API(currentUser);
    }

    // Getters
    public API getApi() {
        return api;
    }

    public Connection getConnectionToMySQL() {
        return connectionToMySQL;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public GUI getGui() {
        return gui;
    }

    public Print getPrint() {
        return print;
    }

    public UserSettings getUserSettings() {
        return userSettings;
    }

}
