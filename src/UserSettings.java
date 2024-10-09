import java.util.Scanner;

// Anything related to altering user data is within this class
public class UserSettings {
    User currentUser;

    public UserSettings(User user){
        this.currentUser = user;
    }

    public void alterUserData(Scanner scanner){
        // User wants to change profile info. This method will print to the console the current values set to the user and give the user a list of which value to change if any.

        String userChoice = "";

        // Loop until the user chooses to exit (option 5)
        do {
            // Get current user data
            double weight = currentUser.weightGetter();
            int height = currentUser.heightGetter();
            char sex = currentUser.sexGetter();
            String exercise = currentUser.exerciseGetter();

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
                    currentUser.weightSetter(weight);
                    System.out.println("Weight updated successfully.");
                    break;

                case "2":
                    // Update height
                    System.out.print("Enter your new height (inches): ");
                    height = Integer.parseInt(scanner.nextLine());
                    currentUser.heightSetter(height);
                    System.out.println("Height updated successfully.");
                    break;

                case "3":
                    // Update sex
                    System.out.print("Enter your sex (M/F): ");
                    sex = scanner.nextLine().charAt(0); // get first character
                    currentUser.sexSetter(sex);
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
                    currentUser.exerciseSetter(exercise);
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

}
