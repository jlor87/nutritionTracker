import java.sql.Connection;
import java.util.LinkedList;
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
     */
    public void addFoodItem(String foodName, int whichOutputArea) {
        userSettings.updateUserConsumption(api.getNutrients(foodName), foodName);
        if(whichOutputArea == 1){
            gui.outputArea.append(String.format("Food Item %s added to daily consumption\n", foodName));
        }
        else if(whichOutputArea == 2){
            gui.outputArea2.append(String.format("Food Item %s added to daily consumption\n", foodName));
        }
        else{
            gui.outputArea3.append(String.format("Food Item %s added to daily consumption\n", foodName));
        }

    }

    public void addCustomFoodItem(String output, int whichOutputArea) {
        userSettings.updateCustomFoodUserConsumption(output);
        if(whichOutputArea == 1){
            gui.customAndSearchedFoodsOutputArea1.append(String.format("Added to daily consumption!\n"));
        }
        else if(whichOutputArea == 2){
            gui.customAndSearchedFoodsOutputArea2.append(String.format("Added to daily consumption!\n"));
        }
        else{
            gui.customAndSearchedFoodsOutputArea3.append(String.format("Added to daily consumption!\n"));
        }

    }

    public void changeGoals(String nutrient, String newVal) {

        userSettings.setGoalData(nutrient, newVal);
    }

    public void getFoodInput(String userInput){
        System.out.print("\nInput food item: ");
        System.out.println("You entered: " + userInput);
        LinkedList<String> searchResults = api.sendAPIRequest(userInput); // Calling api with user's food input
        gui.setSearchResults(searchResults);
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
