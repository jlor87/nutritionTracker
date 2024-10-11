
import java.util.Scanner;

/**
 * Anything related to altering user data is within this class
 *
 */
public class UserSettings
{

    private User currentUser;
    private double weight;
    private int height;
    private char sex;
    private String exercise;

    /**
     * Constructor
     *
     * @param user the user who's settings will be altered
     */
    public UserSettings(User user)
    {
        this.currentUser = user;
    }

    // Class functions
    /**
     * User wants to change profile info. This method will print to the console the current values set to the user and give the user a list of which value to change if any.
     *
     * @param scanner
     */
    public void alterUserData(Scanner scanner)
    {
        String userChoice = "";

        // Loop until the user chooses to exit (option 5)
        do
        {
            // Get current user data
            weight = currentUser.weightGetter();
            height = currentUser.heightGetter();
            sex = currentUser.sexGetter();
            exercise = currentUser.exerciseGetter();

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

            switch(userChoice)
            {
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
                    switch(exerciseChoice)
                    {
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
        }
        while(!userChoice.equals("5"));  // Loop until the user chooses to exit
    }

    /**
     * Set the user's preferred caloric/nutritional goals for the day
     */
    public void setGoalData(Scanner scanner, outputHandler print)
    {
        int choice;
        double newValue;

        print.outputCurrentGoals();

        print.outputUserSettingsMenu();

        do
        {

            System.out.print("Enter the number that corresponds with the metric that you would like to set a new goal for, '0' will return you to the main menu: ");
            choice = scanner.nextInt();

            switch(choice)
            {
                case 0:
                    return;
                    
                case 1:
                    System.out.print("What would you like to set your daily goal to for this metric?: ");
                    newValue = scanner.nextInt();
                    currentUser.setWater(0, newValue);
                    break;

                case 2:
                    System.out.print("What would you like to set your daily goal to for this metric?: ");
                    newValue = scanner.nextInt();
                    currentUser.setEnergy(0, newValue);
                    break;

                case 3:
                    System.out.print("What would you like to set your daily goal to for this metric?: ");
                    newValue = scanner.nextInt();
                    currentUser.setCarbohyrate(0, newValue);
                    break;

                case 4:
                    System.out.print("What would you like to set your daily goal to for this metric?: ");
                    newValue = scanner.nextInt();
                    currentUser.setProtein(0, newValue);
                    break;

                case 5:
                    System.out.print("What would you like to set your daily goal to for this metric?: ");
                    newValue = scanner.nextInt();
                    currentUser.setMonounsaturatedFat(0, newValue);
                    break;

                case 6:
                    System.out.print("What would you like to set your daily goal to for this metric?: ");
                    newValue = scanner.nextInt();
                    currentUser.setPolyunsaturatedFat(0, newValue);
                    break;

                case 7:
                    System.out.print("What would you like to set your daily goal to for this metric?: ");
                    newValue = scanner.nextInt();
                    currentUser.setSaturatedFat(0, newValue);
                    break;

                case 8:
                    System.out.print("What would you like to set your daily goal to for this metric?: ");
                    newValue = scanner.nextInt();
                    currentUser.setFiber(0, newValue);
                    break;

                case 9:
                    System.out.print("What would you like to set your daily goal to for this metric?: ");
                    newValue = scanner.nextInt();
                    currentUser.setVitaminA(0, newValue);
                    break;

                case 10:
                    System.out.print("What would you like to set your daily goal to for this metric?: ");
                    newValue = scanner.nextInt();
                    currentUser.setVitaminB1Thiamine(0, newValue);
                    break;

                case 11:
                    System.out.print("What would you like to set your daily goal to for this metric?: ");
                    newValue = scanner.nextInt();
                    currentUser.setVitaminB2Riboflavin(0, newValue);
                    break;

                case 12:
                    System.out.print("What would you like to set your daily goal to for this metric?: ");
                    newValue = scanner.nextInt();
                    currentUser.setVitaminB3Niacin(0, newValue);
                    break;

                case 13:
                    System.out.print("What would you like to set your daily goal to for this metric?: ");
                    newValue = scanner.nextInt();
                    currentUser.setVitaminB5PantothenicAcid(0, newValue);
                    break;

                case 14:
                    System.out.print("What would you like to set your daily goal to for this metric?: ");
                    newValue = scanner.nextInt();
                    currentUser.setVitaminB6Pyridoxine(0, newValue);
                    break;

                case 15:
                    System.out.print("What would you like to set your daily goal to for this metric?: ");
                    newValue = scanner.nextInt();
                    currentUser.setVitaminB7Biotin(0, newValue);
                    break;

                case 16:
                    System.out.print("What would you like to set your daily goal to for this metric?: ");
                    newValue = scanner.nextInt();
                    currentUser.setVitaminB9Folate(0, newValue);
                    break;

                case 17:
                    System.out.print("What would you like to set your daily goal to for this metric?: ");
                    newValue = scanner.nextInt();
                    currentUser.setVitaminB12Cyanocobalamin(0, newValue);
                    break;

                case 18:
                    System.out.print("What would you like to set your daily goal to for this metric?: ");
                    newValue = scanner.nextInt();
                    currentUser.setVitaminC(0, newValue);
                    break;

                case 19:
                    System.out.print("What would you like to set your daily goal to for this metric?: ");
                    newValue = scanner.nextInt();
                    currentUser.setVitaminD(0, newValue);
                    break;

                case 20:
                    System.out.print("What would you like to set your daily goal to for this metric?: ");
                    newValue = scanner.nextInt();
                    currentUser.setVitaminE(0, newValue);
                    break;

                case 21:
                    System.out.print("What would you like to set your daily goal to for this metric?: ");
                    newValue = scanner.nextInt();
                    currentUser.setVitaminK(0, newValue);
                    break;

                case 22:
                    System.out.print("What would you like to set your daily goal to for this metric?: ");
                    newValue = scanner.nextInt();
                    currentUser.setCalcium(0, newValue);
                    break;

                case 23:
                    System.out.print("What would you like to set your daily goal to for this metric?: ");
                    newValue = scanner.nextInt();
                    currentUser.setChloride(0, newValue);
                    break;

                case 24:
                    System.out.print("What would you like to set your daily goal to for this metric?: ");
                    newValue = scanner.nextInt();
                    currentUser.setCholine(0, newValue);
                    break;

                case 25:
                    System.out.print("What would you like to set your daily goal to for this metric?: ");
                    newValue = scanner.nextInt();
                    currentUser.setChromium(0, newValue);
                    break;

                case 26:
                    System.out.print("What would you like to set your daily goal to for this metric?: ");
                    newValue = scanner.nextInt();
                    currentUser.setCopper(0, newValue);
                    break;

                case 27:
                    System.out.print("What would you like to set your daily goal to for this metric?: ");
                    newValue = scanner.nextInt();
                    currentUser.setFluoride(0, newValue);
                    break;

                case 28:
                    System.out.print("What would you like to set your daily goal to for this metric?: ");
                    newValue = scanner.nextInt();
                    currentUser.setIodine(0, newValue);
                    break;

                case 29:
                    System.out.print("What would you like to set your daily goal to for this metric?: ");
                    newValue = scanner.nextInt();
                    currentUser.setIron(0, newValue);
                    break;

                case 30:
                    System.out.print("What would you like to set your daily goal to for this metric?: ");
                    newValue = scanner.nextInt();
                    currentUser.setMagnesium(0, newValue);
                    break;

                case 31:
                    System.out.print("What would you like to set your daily goal to for this metric?: ");
                    newValue = scanner.nextInt();
                    currentUser.setManganese(0, newValue);
                    break;

                case 32:
                    System.out.print("What would you like to set your daily goal to for this metric?: ");
                    newValue = scanner.nextInt();
                    currentUser.setMolybdenum(0, newValue);
                    break;

                case 33:
                    System.out.print("What would you like to set your daily goal to for this metric?: ");
                    newValue = scanner.nextInt();
                    currentUser.setPhosphorus(0, newValue);
                    break;

                case 34:
                    System.out.print("What would you like to set your daily goal to for this metric?: ");
                    newValue = scanner.nextInt();
                    currentUser.setPotassium(0, newValue);
                    break;

                case 35:
                    System.out.print("What would you like to set your daily goal to for this metric?: ");
                    newValue = scanner.nextInt();
                    currentUser.setSelenium(0, newValue);
                    break;

                case 36:
                    System.out.print("What would you like to set your daily goal to for this metric?: ");
                    newValue = scanner.nextInt();
                    currentUser.setSodium(0, newValue);
                    break;

                case 37:
                    System.out.print("What would you like to set your daily goal to for this metric?: ");
                    newValue = scanner.nextInt();
                    currentUser.setZinc(0, newValue);
                    break;

                case 38:
                    System.out.println("Routing back to the main menu");
                    scanner.nextLine();
                    break;

                default:
                    System.out.println("Invalid option. Please select a valid number (1-38).");
                    break;
            } // End case
        }
        while(true);
    } // End Method
}
