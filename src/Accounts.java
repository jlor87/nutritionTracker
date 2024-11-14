import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// This class deals with user login, authentication, account creation, etc.
public class Accounts {
    static Connection connectionToMySQL = Main.getConnection();

    public static int checkCredentials(String username, String password) {
        ResultSet resultSet;
        String query = "SELECT password, userId FROM users WHERE username = ?";
        String retrievedPassword = "";
        int retrievedUserId = -1; //

        try
        {
            PreparedStatement preparedStatement = connectionToMySQL.prepareStatement(query); // Using prepared statements as good practice against SQL injections
            preparedStatement.setString(1, username);

            // Execute SQL query and retrieve the result
            resultSet = preparedStatement.executeQuery();

            // Process the result
            while(resultSet.next())
            {
                retrievedPassword = resultSet.getString("password");
                retrievedUserId = resultSet.getInt("userId");
                System.out.println("the retrieved userId is: " + retrievedUserId);
            }

            if(retrievedPassword.equals(password)) {
                return retrievedUserId;
            }
            else{
                return -1; // Error code for wrong password
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return -2; // Error code for something wrong happening
        }
    }

    public static int createAccount(String username, String password) {
        int retrievedUserId = -1;
        String query = "INSERT IGNORE INTO users (username, password, weight, height, sex, exercise) VALUES(?, ?, ?, ?, ?, ?)";
        try
        {
            PreparedStatement preparedStatement = connectionToMySQL.prepareStatement(query); // Using prepared statements as good practice against SQL injections
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setInt(3, 150);
            preparedStatement.setInt(4, 69);
            preparedStatement.setString(5, "M");
            preparedStatement.setString(6, "average");

            // Execute and retrieve result
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            if(rowsAffected > 0)
            {
                System.out.println("User created successfully!");
            }
            else
            {
                System.out.println("Failed to create user!");
                return -2; // Username already exists!
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return -1; // Error occurred during registration
        }

        // Need to retrieve the userId of the newly created user to set up initial goals and daily consumption
        String query2 = "SELECT userId FROM users WHERE username = ? AND password = ?";
        try
        {
            PreparedStatement preparedStatement = connectionToMySQL.prepareStatement(query2); // Using prepared statements as good practice against SQL injections
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            // Execute and retrieve result
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                retrievedUserId = resultSet.getInt("userId");
                // Continue processing with userId
                System.out.println("Created userId: " + retrievedUserId);
            }
            else
            {
                System.out.println("Error retrieving userId.");
                return -1; // Error occurred during registration
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return -1; // Error occurred during registration
        }

        // Set nutrient goals to initial values
        String query3 = "INSERT INTO nutrientGoals (userId, water, energy, carbohydrate, monounsaturatedFat, saturatedFat, polyunsaturatedFat, protein, fiber, vitaminA, vitaminB1Thiamine, vitaminB2Riboflavin, vitaminB3Niacin, vitaminB5PantothenicAcid, vitaminB6Pyridoxine, vitaminB7Biotin, vitaminB9Folate, vitaminB12Cyanocobalamin, vitaminC, vitaminD, vitaminE, vitaminK, choline, calcium, chloride, chromium, copper, fluoride, iodine, iron, magnesium, manganese, molybdenum, phosphorus, potassium, selenium, sodium, zinc)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try
        {
            PreparedStatement preparedStatement = connectionToMySQL.prepareStatement(query3); // Using prepared statements as good practice against SQL injections
            preparedStatement.setDouble(1, retrievedUserId); // userId
            preparedStatement.setDouble(2, 3.7);             // water
            preparedStatement.setDouble(3, 2000);            // energy
            preparedStatement.setDouble(4, 130);             // carbohydrate
            preparedStatement.setDouble(5, 22);              // monounsaturatedFat
            preparedStatement.setDouble(6, 22);              // saturatedFat
            preparedStatement.setDouble(7, 17);              // polyunsaturatedFat
            preparedStatement.setDouble(8, 56);              // protein
            preparedStatement.setDouble(9, 38);              // fiber
            preparedStatement.setDouble(10, 900);            // vitaminA
            preparedStatement.setDouble(11, 1.2);            // vitaminB1Thiamine
            preparedStatement.setDouble(12, 1.3);            // vitaminB2Riboflavin
            preparedStatement.setDouble(13, 16);             // vitaminB3Niacin
            preparedStatement.setDouble(14, 5);              // vitaminB5PantothenicAcid
            preparedStatement.setDouble(15, 1.3);            // vitaminB6Pyridoxine
            preparedStatement.setDouble(16, 30);             // vitaminB7Biotin
            preparedStatement.setDouble(17, 400);            // vitaminB9Folate
            preparedStatement.setDouble(18, 2.4);            // vitaminB12Cyanocobalamin
            preparedStatement.setDouble(19, 90);             // vitaminC
            preparedStatement.setDouble(20, 15);             // vitaminD
            preparedStatement.setDouble(21, 15);             // vitaminE
            preparedStatement.setDouble(22, 120);            // vitaminK
            preparedStatement.setDouble(23, 550);            // choline
            preparedStatement.setDouble(24, 1000);           // calcium
            preparedStatement.setDouble(25, 2.3);            // chloride
            preparedStatement.setDouble(26, 35);             // chromium
            preparedStatement.setDouble(27, 900);            // copper
            preparedStatement.setDouble(28, 4);              // fluoride
            preparedStatement.setDouble(29, 150);            // iodine
            preparedStatement.setDouble(30, 8);              // iron
            preparedStatement.setDouble(31, 400);            // magnesium
            preparedStatement.setDouble(32, 2.3);            // manganese
            preparedStatement.setDouble(33, 45);             // molybdenum
            preparedStatement.setDouble(34, 700);            // phosphorus
            preparedStatement.setDouble(35, 3400);           // potassium
            preparedStatement.setDouble(36, 55);             // selenium
            preparedStatement.setDouble(37, 1500);           // sodium
            preparedStatement.setDouble(38, 11);             // zinc

            // Execute and retrieve result
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0)
            {
                System.out.println("Initial nutrient goals set successfully!");
            }
            else
            {
                System.out.println("Failed to initialize nutrient goals!");
                return -1; // Error occurred during registration
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return -1; // Error occurred during registration
        }

        // Set daily consumption to 0
        String query4 = "INSERT INTO currentConsumption (userId, water, energy, carbohydrate, monounsaturatedFat, saturatedFat, polyunsaturatedFat, protein, fiber, vitaminA, vitaminB1Thiamine, vitaminB2Riboflavin, vitaminB3Niacin, vitaminB5PantothenicAcid, vitaminB6Pyridoxine, vitaminB7Biotin, vitaminB9Folate, vitaminB12Cyanocobalamin, vitaminC, vitaminD, vitaminE, vitaminK, choline, calcium, chloride, chromium, copper, fluoride, iodine, iron, magnesium, manganese, molybdenum, phosphorus, potassium, selenium, sodium, zinc)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try
        {
            PreparedStatement preparedStatement = connectionToMySQL.prepareStatement(query4); // Using prepared statements as good practice against SQL injections
            preparedStatement.setDouble(1, retrievedUserId);
            for(int i = 2; i <= 38; i++)
            {
                preparedStatement.setDouble(i, 0.00);
            }

            // Execute and retrieve result
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();

            if(rowsAffected > 0)
            {
                System.out.println("Current consumption set successfully!");
                return retrievedUserId;
            }
            else
            {
                System.out.println("Failed to set current consumption to 0!");
                return -1; // Error occurred during registration
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return -1; // Error occurred during registration
        }
    }
}
