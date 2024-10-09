import java.util.Scanner;

// The session class is responsible for actually running the program via the startSession() function
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
    public void startSession(){
        // Initialize these utility classes for later usage
        userSettings = new UserSettings(currentUser);
        print = new Print(currentUser);
        scanner = new Scanner(System.in);
        api = new API(currentUser);

        // Open the menu
        menu();
    }
    public void menu(){     // menu() is the center of the app (due to the do-while loop)
        System.out.println("Welcome to the nutrition app.");
        System.out.println("Skipping existing user login or new user creation functionality...");

        do {
            System.out.println("\nSelect an action for the system to perform from the list below.");
            System.out.println("0. Exit the application.");
            System.out.println("1. Search for food/drink item.");
            System.out.println("2. Set caloric/nutritional intake goals for the day.");
            System.out.println("3. Alter or set user data (height, weight, sex, average daily exercise).");
            System.out.println("4. Display status of current nutrient consumption for the day.\n");

            userChoice = scanner.nextLine();  // Get user input

            switch (userChoice) {
                case "1" ->
                    // User wants to search for food/drink item
                        getFoodInput(scanner);
                case "2" ->
                    // User wants to set caloric/nutritional goals for the day
                        userSettings.setGoalData();
                case "3" ->
                    // User wants to alter user data
                        userSettings.alterUserData(scanner);
                case "4" ->
                    // User wants to check current daily progress
                        print.outputCurrentConsumption();
                case "0" ->
                    // User closing app
                        System.out.println("\nClosing the application");
                default -> System.out.println("Not a valid option");
            }
        } while (!userChoice.equals("0"));

        // App closes at this point
        scanner.close();
    }
    public void getFoodInput(Scanner scanner){
        System.out.println("\nInput food item:");
        String userInput;
        userInput = scanner.nextLine();
        api.sendAPIRequest(userInput); //calling api with user input
    }

}
