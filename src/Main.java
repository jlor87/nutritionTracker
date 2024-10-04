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
        
        
        Scanner scanner = new Scanner(System.in);  // Scanner declared once

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
                mainInstance.alterUserData(newUser);
            } else if (userChoice.equals("0")) {
                // User closing app
                System.out.println("\nClosing the application");
            } else {
                System.out.println("Not a valid option");
            }

        } while (!userChoice.equals("0"));  // Use .equals() to compare string values

        scanner.close();  // Close the scanner after the loop ends
    }
    
    
    

    
    public void setGoalData(User user){
        // User wants to set caloric/nutritonal goals for the day
    	System.out.println("\nTo be implemented");
    }
    
    public void alterUserData(User user){
        // User wants to change profile info. This method will print to the console the current values set to the user and give the user a list of which value to change if any.
    	double weight = user.weightGetter();
    	int height = user.heightGetter();
    	char sex = user.sexGetter();
    	String exercise = user.exerciseGetter();
    	int userChoice = 0;
    	//display the current metrics to the user
    	System.out.println("Your data is set as follows:\n");
    	System.out.printf("Weight: %.2f lbs\n", weight);  
    	System.out.printf("Height: %d inches\n", height);    
    	System.out.printf("Sex: %c\n", sex);             
    	System.out.printf("Exercise Level: %s\n", exercise);
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


