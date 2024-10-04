import java.math.*;
import java.util.Scanner; 
// need external JSON parser

public class Main {
	static String userChoice = "dummyValue";
	
    public static void main(String[] args) {
    	Main mainInstance = new Main();
    	User newUser = new User();
    	
    	System.out.println("Welcome to the nutrition app.");
        System.out.println("Skipping existing user login or new user creation functionality...\n");
        
        
        Scanner scanner = new Scanner(System.in);  

        do {
            System.out.println("\nSelect an action for the system to perform from the list below.");
            System.out.println("0. Exit the application.");
            System.out.println("1. Search for food/drink item.");
            System.out.println("2. Set caloric/nutritional intake goals for the day.");
            System.out.println("3. Alter or set user data (height, weight, sex, average daily exercise).\n");

            userChoice = scanner.nextLine();  // Get user input

            if (userChoice.equals("1")) {
                // User wants to search for food/drink item
                mainInstance.getFoodInput(newUser);
            } else if (userChoice.equals("2")) {
                // User wants to set caloric/nutritional goals for the day
                mainInstance.setGoalData(newUser);
            } else if (userChoice.equals("3")) {
                // User wants to alter user data
                mainInstance.alterUserData(newUser, scanner);
            } else if (userChoice.equals("0")) {
                // User closing app
                System.out.println("\nClosing the application");
            } else {
                System.out.println("Not a valid option");
            }

        } while (!userChoice.equals("0"));  

        scanner.close();  
    }
    
    
    

    
    public void setGoalData(User user){
        // User wants to set caloric/nutritonal goals for the day
    	System.out.println("\nTo be implemented");
    }
    
    public void alterUserData(User user, Scanner scanner){
        // User wants to change profile info. This method will print to the console the current values set to the user and give the user a list of which value to change if any.
    	
        String userChoice = "";

        // Loop until the user chooses to exit (option 5)
        do {
            // Get current user data
            double weight = user.weightGetter();
            int height = user.heightGetter();
            char sex = user.sexGetter();
            String exercise = user.exerciseGetter();

            // Display current metrics
            System.out.println("\nYour data is set as follows:");
            System.out.printf("Weight: %.2f lbs\n", weight);
            System.out.printf("Height: %d inches\n", height);
            System.out.printf("Sex: %c\n", sex);
            System.out.printf("Exercise Level: %s\n", exercise);

            // Ask which metric to update
            System.out.printf("\nWhich of the user data metrics would you like to update?\n"
                    + "1. Weight\n"
                    + "2. Height\n"
                    + "3. Sex\n"
                    + "4. Exercise level\n"
                    + "5. Exit menu\n");

            userChoice = scanner.nextLine();

            
            switch (userChoice) {
                case "1":
                    // Update weight
                    System.out.print("Enter your new weight (lbs): ");
                    weight = Double.parseDouble(scanner.nextLine());
                    user.weightSetter(weight);
                    System.out.println("Weight updated successfully.");
                    break;

                case "2":
                    // Update height
                    System.out.print("Enter your new height (inches): ");
                    height = Integer.parseInt(scanner.nextLine());
                    user.heightSetter(height);
                    System.out.println("Height updated successfully.");
                    break;

                case "3":
                    // Update sex
                    System.out.print("Enter your sex (M/F): ");
                    sex = scanner.nextLine().charAt(0); // get first character
                    user.sexSetter(sex);
                    System.out.println("Sex updated successfully.");
                    break;

                case "4":
                    // Update exercise level based on numeric options
                    System.out.println("Select your new exercise level:");
                    System.out.println("1. NONE");
                    System.out.println("2. LIGHT");
                    System.out.println("3. MODERATE");
                    System.out.println("4. HARD");
                    System.out.println("5. EXTREME");

                    String exerciseChoice = scanner.nextLine();
                    switch (exerciseChoice) {
                        case "1":
                            exercise = "NONE";
                            break;
                        case "2":
                            exercise = "LIGHT";
                            break;
                        case "3":
                            exercise = "MODERATE";
                            break;
                        case "4":
                            exercise = "HARD";
                            break;
                        case "5":
                            exercise = "EXTREME";
                            break;
                        default:
                            System.out.println("Invalid choice. Defaulting to NONE.");
                            exercise = "NONE";
                            break;
                    }
                    user.exerciseSetter(exercise);
                    System.out.println("Exercise level updated to " + exercise + " successfully.");
                    break;

                case "5":
                    // Exit the menu
                    System.out.println("Exiting the menu...");
                    break;

                default:
                    System.out.println("Invalid option. Please select a valid number (1-5).");
                    break;
            }
        } while (!userChoice.equals("5"));  // Loop until the user chooses to exit

       
    
    
    
    
    }
    
    public void getFoodInput(User user){
    	System.out.println("\nTo be implemented");
    	// User enters "apple"
    }

    public void sendAPIRequest(){
        // System sends API request for "apple" to X API
        // Which API??
    }

    public void parseData(){
        // Retrieve data from the API request, parse it, and then put that data into the User object
        // Use a Java library to help with parsing the data?
    }

    public void computeRemainingDietaryNeeds(){
        // System does math to subtract nutritional amount from user's goals
    }

    public void displayResultsToUser(){
        // System displays the remaining amount of nutrients/vitamins leftover to the user
    }

}


