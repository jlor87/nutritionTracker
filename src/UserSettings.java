import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
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
    private Connection connectionToMySQL;

    /**
     * Constructor
     * @param user the user who's settings will be altered
     */
    public UserSettings(User user){
        this.currentUser = user;
        this.connectionToMySQL = Main.getConnection();
    }

    // Class functions
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

    public boolean updateFoodDiary(String foodsConsumedThisDay){
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
                    return true;
                }
                else
                {
                    System.out.println("Failed to update food diary!");
                    return false;
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                return false;
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
                    return true;
                }
                else
                {
                    System.out.println("Failed to insert food diary!");
                    return false;
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
                return false;
            }
        }
    }

    public boolean updateUserConsumption(JsonArray nutrients, String foodName){
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

        // Deal with each nutrient one by one
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
            for (int j = 0; j < length; j++) {
                String methodName = userMethods[j].getName().toLowerCase();

                if (methodName.contains("set" + nutrientName)) {
                    try {
                        // Update user's consumed nutrients
                        userMethods[j].invoke(currentUser, 1, amount);
                        foodMethods[j].invoke(newFood, 1, amount);

                        // Retrieve the updated value using the getter method
                        double newAmount = 0.00;
                        for (int k = 0; k < length; k++) {
                            String methodName2 = userMethods[k].getName().toLowerCase();
                            if (methodName2.contains("get" + nutrientName)) {
                                System.out.println("invoking " + methodName2);
                                newAmount = (double) userMethods[k].invoke(currentUser, 1);
                                System.out.println("new amount is: " + newAmount);
                                break;
                            }
                        }

                        // Update the nutrient value in the database
                        String updateQuery = "UPDATE currentConsumption SET " + nutrientName + " = ? WHERE userId = ?";
                        try {
                            PreparedStatement preparedStatement = connectionToMySQL.prepareStatement(updateQuery);
                            preparedStatement.setDouble(1, newAmount);
                            preparedStatement.setInt(2, currentUser.getUserId()); // Replace `currentUser.getUserId()` with how you access the user's ID

                            int rowsAffected = preparedStatement.executeUpdate();
                            if (rowsAffected > 0) {
                                System.out.println("User nutrient updated!");
                                return true;
                            } else {
                                System.out.println("Failed to update consumption!");
                            }
                            preparedStatement.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }

    // Getters
    public User getCurrentUser() {
        return currentUser;
    }

    public Connection getConnectionToMySQL() {
        return connectionToMySQL;
    }
}
