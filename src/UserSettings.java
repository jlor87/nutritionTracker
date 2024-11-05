import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.swing.*;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;
import java.sql.*;

/**
 * Anything related to altering user data is within this class
 *
 */
public class UserSettings {
    private Method[] userMethods;

    private Method[] foodMethods;
    private User currentUser;
    private double weight;
    private int height;
    private char sex;
    private String exercise;
    private Connection connectionToMySQL;

    /**
     * Constructor
     * @param user the user who's settings will be altered
     */
    public UserSettings(User user, Connection connectionToMySQL){
        this.currentUser = user;
        this.connectionToMySQL = connectionToMySQL;
    }

    // Class functions
    
    /**
     * User wants to change profile info. 
     * This method will print to the console the current values set to the user and give the user a list of which value to change if any.
     * @param scanner 
     */
    public void alterUserData(Scanner scanner){
        String userChoice = "";

        // Loop until the user chooses to exit (option 5)
        do {
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
    
    /**
     * Set the user's preferred caloric/nutritional goals for the day
     */
    public void setGoalData(String nutrient, String newVal) {
        double newValue;

        try {
            newValue = Double.parseDouble(newVal); // Convert newVal to a double
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format for the new value. Please enter a valid number.");
            return;
        }

        // Map each nutrient name to the corresponding setter method
        Map<String, Consumer<Double>> nutrientSetters = new HashMap<>();
        nutrientSetters.put("Water", val -> currentUser.setWater(0, val));
        nutrientSetters.put("Calories", val -> currentUser.setEnergy(0, val));
        nutrientSetters.put("Carbohydrates", val -> currentUser.setCarbohyrate(0, val));
        nutrientSetters.put("Protein", val -> currentUser.setProtein(0, val));
        nutrientSetters.put("Monounsaturated Fat", val -> currentUser.setMonounsaturatedFat(0, val));
        nutrientSetters.put("Polyunsaturated Fat", val -> currentUser.setPolyunsaturatedFat(0, val));
        nutrientSetters.put("Saturated Fat", val -> currentUser.setSaturatedFat(0, val));
        nutrientSetters.put("Fiber", val -> currentUser.setFiber(0, val));
        
        // Vitamins
        nutrientSetters.put("Vitamin A", val -> currentUser.setVitaminA(0, val));
        nutrientSetters.put("Vitamin B1", val -> currentUser.setVitaminB1Thiamine(0, val));
        nutrientSetters.put("Vitamin B2", val -> currentUser.setVitaminB2Riboflavin(0, val));
        nutrientSetters.put("Vitamin B3", val -> currentUser.setVitaminB3Niacin(0, val));
        nutrientSetters.put("Vitamin B5", val -> currentUser.setVitaminB5PantothenicAcid(0, val));
        nutrientSetters.put("Vitamin B6", val -> currentUser.setVitaminB6Pyridoxine(0, val));
        nutrientSetters.put("Vitamin B7", val -> currentUser.setVitaminB7Biotin(0, val));
        nutrientSetters.put("Vitamin B9", val -> currentUser.setVitaminB9Folate(0, val));
        nutrientSetters.put("Vitamin B12", val -> currentUser.setVitaminB12Cyanocobalamin(0, val));
        nutrientSetters.put("Vitamin C", val -> currentUser.setVitaminC(0, val));
        nutrientSetters.put("Vitamin D", val -> currentUser.setVitaminD(0, val));
        nutrientSetters.put("Vitamin E", val -> currentUser.setVitaminE(0, val));
        nutrientSetters.put("Vitamin K", val -> currentUser.setVitaminK(0, val));
        
        // Minerals
        nutrientSetters.put("Calcium", val -> currentUser.setCalcium(0, val));
        nutrientSetters.put("Chloride", val -> currentUser.setChloride(0, val));
        nutrientSetters.put("Choline", val -> currentUser.setCholine(0, val));
        nutrientSetters.put("Chromium", val -> currentUser.setChromium(0, val));
        nutrientSetters.put("Copper", val -> currentUser.setCopper(0, val));
        nutrientSetters.put("Fluoride", val -> currentUser.setFluoride(0, val));
        nutrientSetters.put("Iodine", val -> currentUser.setIodine(0, val));
        nutrientSetters.put("Iron", val -> currentUser.setIron(0, val));
        nutrientSetters.put("Magnesium", val -> currentUser.setMagnesium(0, val));
        nutrientSetters.put("Manganese", val -> currentUser.setManganese(0, val));
        nutrientSetters.put("Molybdenum", val -> currentUser.setMolybdenum(0, val));
        nutrientSetters.put("Phosphorus", val -> currentUser.setPhosphorus(0, val));
        nutrientSetters.put("Potassium", val -> currentUser.setPotassium(0, val));
        nutrientSetters.put("Selenium", val -> currentUser.setSelenium(0, val));
        nutrientSetters.put("Sodium", val -> currentUser.setSodium(0, val));
        nutrientSetters.put("Zinc", val -> currentUser.setZinc(0, val));

        // Set goal based on nutrient name
        Consumer<Double> setter = nutrientSetters.get(nutrient);
        if (setter != null) {
            setter.accept(newValue);
            System.out.println("Goal for " + nutrient + " set to " + newValue);
        } else {
            System.out.println("Invalid nutrient name. Please check and try again.");
        }
    }



    public void updateFoodDiary(String foodsConsumedThisDay){
        int userId = currentUser.getUserId();

        LocalDate currentDate = LocalDate.now();
        int currentDay = currentDate.getDayOfMonth();
        int currentMonth = currentDate.getMonth().getValue();
        int currentYear = currentDate.getYear();

        String insertQuery = "INSERT INTO foodcatalogs (userId, yearNum, monthNum, dayNum, foodsConsumedThisDay) VALUES(?, ?, ?, ?, ?)";
        String updateQuery = "UPDATE foodcatalogs SET foodsConsumedThisDay = ? WHERE userId = ? AND yearNum = ? AND monthNum = ? AND dayNum = ?";
        boolean entryAlreadyExists = false;

        // Check if a food diary already exists
        String query = "SELECT * FROM foodcatalogs WHERE userId = ? AND yearNum = ? AND monthNum = ? AND dayNum = ?";
        ResultSet resultSet;
        try
        {
            PreparedStatement preparedStatement = connectionToMySQL.prepareStatement(query); // Using prepared statements as good practice against SQL injections
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, currentYear);
            preparedStatement.setInt(3, currentMonth);
            preparedStatement.setInt(4, currentDay);

            // Execute SQL query and retrieve the result
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                entryAlreadyExists = true;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        // If a food diary already exists, then we update it
        if(entryAlreadyExists){
            try
            {
                PreparedStatement preparedStatement = connectionToMySQL.prepareStatement(updateQuery); // Using prepared statements as good practice against SQL injections
                preparedStatement.setString(1, foodsConsumedThisDay);
                preparedStatement.setInt(2, userId);
                preparedStatement.setInt(3, currentYear);
                preparedStatement.setInt(4, currentMonth);
                preparedStatement.setInt(5, currentDay);


                // Execute and retrieve result
                int rowsAffected = preparedStatement.executeUpdate();
                if(rowsAffected > 0)
                {
                    System.out.println("Food diary updated successfully!");
                }
                else
                {
                    System.out.println("Failed to update food diary!");
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
        else{
            try
            {
                PreparedStatement preparedStatement = connectionToMySQL.prepareStatement(insertQuery); // Using prepared statements as good practice against SQL injections
                preparedStatement.setInt(1, userId);
                preparedStatement.setInt(2, currentYear);
                preparedStatement.setInt(3, currentMonth);
                preparedStatement.setInt(4, currentDay);
                preparedStatement.setString(5, foodsConsumedThisDay);


                // Execute and retrieve result
                int rowsAffected = preparedStatement.executeUpdate();
                if(rowsAffected > 0)
                {
                    System.out.println("Food diary inserted successfully!");
                }
                else
                {
                    System.out.println("Failed to insert food diary!");
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void updateUserConsumption(JsonArray nutrients, String foodName){
        userMethods = Utility.getMethods();
        foodMethods = Utility.getFoodMethods();
        int length = userMethods.length;
        int length2 = nutrients.size();
        String nutrientName = "";
        double amount = 0.00;

        // Add the food's name to the user's daily food diary
        String revisedFoodName = foodName.replace(" ", "-");
        Food newFood = new Food(revisedFoodName);
        currentUser.addFood(newFood);
        updateFoodDiary(currentUser.getDailyFoodsConsumed());

        for (int i = 0; i < length2; i++) {
            JsonObject nutrient = nutrients.get(i).getAsJsonObject();
            nutrientName = nutrient.get("nutrientName").getAsString();
            nutrientName = nutrientName.replaceAll(" ", "").toLowerCase();
            // System.out.println("nutrientName (before): " + nutrientName);

            // Handles parsing the special case of fatty acids
            if(nutrientName.startsWith("fattyacids")){
                nutrientName = nutrientName.substring(16);
            }
            // The actual nutrient name starts after the comma, so remove the first half of the string
            int commaIndex = nutrientName.indexOf(",");
            if(commaIndex != -1){
                nutrientName = nutrientName.substring(0, commaIndex);
            }
            // System.out.println("nutrientName: " + nutrientName); // Test statement

            amount = nutrient.get("value").getAsDouble();

            // Use the nutrientName to call the correct setter in the User class, i.e. "Vitamin A" should call "setVitaminA"
            for(int j = 0; j < length; j++){
                String methodName = userMethods[j].getName().toLowerCase();

                if(methodName.contains("set" + nutrientName)){
                    // System.out.println("methodName: " + methodName); // Test statement
                    try{
                        // System.out.println("Found!");
                        userMethods[j].invoke(currentUser, 1, amount); // Update user's consumed nutrients
                        foodMethods[j].invoke(newFood, 1, amount); // Update the new food class with the nutrients recieved
                        break;
                    } catch(Exception e){
                        System.out.println(e);
                    }
                }
            }

        }
    }
}
