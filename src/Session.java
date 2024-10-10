import java.util.Scanner;

/**
 * The session class is responsible for actually running the program via the startSession() function
 * The session class will most likely be responsible for rendering the GUI as well
 */
public class Session {
    private String userChoice = "dummyValue";
    private User currentUser;
    private Scanner scanner;
    private UserSettings userSettings;
    private API api;
    private Print print;

    // Constructor
    public Session(User user){
        this.currentUser = user;
    }


    // Class helper functions to perform parts of a use case 
    //**? can someone explain better?**
    public void startSession(){
        // Initialize these utility classes for later usage
        userSettings = new UserSettings(currentUser);
        print = new Print(currentUser);
        scanner = new Scanner(System.in);
        api = new API(currentUser);
    }
    
    /**
     * menu() consists of a do-while loop responsible for keeping the app running
     */
    public void menu(){
        System.out.println("Welcome to the nutrition app.");
        System.out.println("Skipping existing user login or new user creation functionality...");

        do {
            System.out.println("\nSelect an action for the system to perform from the list below.");
            System.out.println("0. Exit the application.");
            System.out.println("1. Search for food/drink item.");
            System.out.println("2. Set caloric/nutritional intake goals for the day.");
            System.out.println("3. Alter or set user data (height, weight, sex, average daily exercise).");
            System.out.println("4. Display status of current nutrient consumption for the day.\n");
            System.out.print("Your choice: ");

            userChoice = scanner.nextLine();  // Get user input

            switch (userChoice) {
                case "1":
                    // User wants to search for food/drink item
                    getFoodInput(scanner);
                    break;
                case "2":
                    // User wants to set caloric/nutritional goals for the day
                    userSettings.setGoalData();
                    break;
                case "3":
                    // User wants to alter user data
                    userSettings.alterUserData(scanner);
                    break;
                case "4":
                    // User wants to check current daily progress
                    print.outputCurrentConsumption();
                    break;
                case "0":
                    // User closing app
                    System.out.println("\nClosing the application");
                    break;
                default:
                    System.out.println("Not a valid option");
                    break;
            }
        } while (!userChoice.equals("0"));

        // User chose 0 app closes
        scanner.close();
    }
    
    /**
     * This function is responsible for retrieving the food that the user of the app has consumed or plans to consume
     * @param scanner takes in one scanner object created by the calling function to read in foods provided by the user
     */
    public void getFoodInput(Scanner scanner){
        System.out.print("\nInput food item: ");
        String userInput = scanner.nextLine();
        System.out.println("You entered: " + userInput);
        api.sendAPIRequest(userInput); // Calling api with user's food input
        api.updateUserConsumption(api.getNutrients(), currentUser);
    }

    // Getters
    public User getCurrentUser(){
        return currentUser;
    }
    public Scanner getScanner(){
        return scanner;
    }
    public UserSettings getUserSettings(){
        return userSettings;
    }
    public API getApi() {
        return api;
    }
    public Print getPrint() {
        return print;
    }
}
