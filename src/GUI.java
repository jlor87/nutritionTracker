
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.sql.*;
import java.util.LinkedList;

public class GUI
{

    JTextField loginUsernameField = new JTextField(25);
    JPasswordField loginPasswordField = new JPasswordField(25);
    JTextField createUsernameField = new JTextField(25);
    JPasswordField createPasswordField = new JPasswordField(25);
    JTextField searchField = new JTextField(20);
    JTextArea outputArea = new JTextArea(10, 30);
    JScrollPane scrollPane = new JScrollPane(outputArea);
    JTextArea macronutrientsField = new JTextArea("Macronutrients: ");
    JTextArea vitaminsField = new JTextArea("Vitamins: ");
    JTextArea mineralsField = new JTextArea("Minerals: ");
    JTextArea foodsConsumedField = new JTextArea("Foods Consumed:");
    ChoiceHandler cHandler = new ChoiceHandler(loginUsernameField, loginPasswordField);
    public String searchItem;
    JFrame titleWindow = new JFrame();
    JFrame loginWindow = new JFrame();
    JFrame createWindow = new JFrame();
    JFrame mainWindow = new JFrame();
    JFrame searchWindow = new JFrame();
    JFrame setGoalsWindow = new JFrame();
    JFrame statusWindow = new JFrame();
    JFrame alterUserDataWindow = new JFrame();
    JFrame catalogFoodIntakeWindow = new JFrame();
    JFrame customFoodWindow = new JFrame();
    JTextArea informationField = new JTextArea();

    //these variables are at the class level because they will have to be turned on/off (set visible/non visible) depending on where the user is in the application
    private final Connection connectionToMySQL;
    private Session session;
    private User currentUser;
    private int retrievedUserId = -1; // dummy value before user logs in

    // Constructor
    public GUI(Connection connectionToMySQL)
    {
        this.connectionToMySQL = connectionToMySQL;
    }

    // All functions that make screens in ABC order
    public void makeAlterUserDataScreen() {
        // Frame setup
        alterUserDataWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        alterUserDataWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        alterUserDataWindow.setSize(400, 300);
        alterUserDataWindow.setLayout(new BorderLayout(10, 10));

        // Title at the top
        JLabel titleLabel = new JLabel("Alter User Data", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        alterUserDataWindow.add(titleLabel, BorderLayout.NORTH);

        // Center panel for buttons
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 2, 10, 10)); // 2x2 grid for the buttons

        // Button for Weight
        JButton weightButton = new JButton("Weight");
        weightButton.addActionListener(e ->
        {
            String weightInput = JOptionPane.showInputDialog(alterUserDataWindow, "Enter your weight in pounds:");
            if(weightInput != null)
            {
                try
                {
                    double weight = Double.parseDouble(weightInput);
                    // Save weight to a variable
                    System.out.println("Weight saved: " + weight);

                    String query = "UPDATE nutritionTracker.users SET weight = " + weight + " WHERE userId = " + currentUser.getUserId();

                    try
                    {
                        PreparedStatement preparedStatement = connectionToMySQL.prepareStatement(query);
                        // Execute and retrieve result
                        int rowsAffected = preparedStatement.executeUpdate();
                        preparedStatement.close();

                        if(rowsAffected > 0)
                        {
                            System.out.println("User created successfully!");
                        }
                        else
                        {
                            System.out.println("Failed to create user!");// make a popup
                        }
                    }
                    catch(SQLException ex)
                    {
                        ex.printStackTrace();
                    }

                }
                catch(NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(alterUserDataWindow, "Please enter a valid number.");
                }
            }
        });
        centerPanel.add(weightButton);

        // Button for Height
        JButton heightButton = new JButton("Height");
        heightButton.addActionListener(e ->
        {
            String heightInput = JOptionPane.showInputDialog(alterUserDataWindow, "Enter your height in inches:");
            if(heightInput != null)
            {
                try
                {
                    double height = Double.parseDouble(heightInput);
                    // Save height to a variable
                    System.out.println("Height saved: " + height);

                    String query = "UPDATE nutritionTracker.users SET height = " + height + " WHERE userId = " + currentUser.getUserId();

                    try
                    {
                        PreparedStatement preparedStatement = connectionToMySQL.prepareStatement(query);
                        // Execute and retrieve result
                        int rowsAffected = preparedStatement.executeUpdate();
                        preparedStatement.close();

                        if(rowsAffected > 0)
                        {
                            System.out.println("User created successfully!");
                        }
                        else
                        {
                            System.out.println("Failed to create user!");// make a popup
                        }
                    }
                    catch(SQLException ex)
                    {
                        ex.printStackTrace();
                    }
                }
                catch(NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(alterUserDataWindow, "Please enter a valid number.");
                }
            }
        });
        centerPanel.add(heightButton);

        // Button for Sex
        JButton sexButton = new JButton("Sex: M"); // Default to "M"
        sexButton.addActionListener(new ActionListener()
        {
            private String sex = "M";

            @Override
            public void actionPerformed(ActionEvent e)
            {
                sex = sex.equals("M") ? "F" : "M";
                sexButton.setText("Sex: " + sex);
                // Save sex to a variable
                System.out.println("Sex saved: " + sex);

                String query = "UPDATE nutritionTracker.users SET sex = '" + sex + "' WHERE userId = " + currentUser.getUserId();

                try
                {
                    PreparedStatement preparedStatement = connectionToMySQL.prepareStatement(query);
                    // Execute and retrieve result
                    int rowsAffected = preparedStatement.executeUpdate();
                    preparedStatement.close();

                    if(rowsAffected > 0)
                    {
                        System.out.println("User created successfully!");
                    }
                    else
                    {
                        System.out.println("Failed to create user!");// make a popup
                    }
                }
                catch(SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        });
        centerPanel.add(sexButton);

        // Button for Exercise Level
        JButton exerciseLevelButton = new JButton("Exercise Level: None"); // Default to "None"
        exerciseLevelButton.addActionListener(new ActionListener()
        {
            private String[] levels =
            {
                "None", "Light", "Moderate", "Hard", "Extreme"
            };
            private int levelIndex = 0;

            @Override
            public void actionPerformed(ActionEvent e)
            {
                levelIndex = (levelIndex + 1) % levels.length;
                String level = levels[levelIndex];
                exerciseLevelButton.setText("Exercise Level: " + level);
                // Save exercise level to a variable
                System.out.println("Exercise Level saved: " + level);

                String query = "UPDATE nutritionTracker.users SET exercise = '" + level + "' WHERE userId = " + currentUser.getUserId();

                try
                {
                    PreparedStatement preparedStatement = connectionToMySQL.prepareStatement(query);
                    // Execute and retrieve result
                    int rowsAffected = preparedStatement.executeUpdate();
                    preparedStatement.close();

                    if(rowsAffected > 0)
                    {
                        System.out.println("User created successfully!");
                    }
                    else
                    {
                        System.out.println("Failed to create user!");// make a popup
                    }
                }
                catch(SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        });
        centerPanel.add(exerciseLevelButton);

        // Add the center panel to the frame
        alterUserDataWindow.add(centerPanel, BorderLayout.CENTER);

        // Exit button centered at the bottom
        JPanel buttonPanel = new JPanel();
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(cHandler);
        exitButton.setActionCommand("goMainScreen");
        buttonPanel.add(exitButton);
        alterUserDataWindow.add(buttonPanel, BorderLayout.SOUTH);

        // Set frame visibility to true
        alterUserDataWindow.setVisible(false);
    }
    public void makeCatalogFoodIntakeScreen() {
        // Create the main frame
        catalogFoodIntakeWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        catalogFoodIntakeWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        catalogFoodIntakeWindow.setLayout(new BorderLayout(10, 10));

        // Title at the top
        JLabel titleLabel = new JLabel("Daily Food Consumption", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        catalogFoodIntakeWindow.add(titleLabel, BorderLayout.NORTH);

        // Center panel for sections
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(6, 1, 0, 10)); // Adjust for labels and text areas with scroll panes

        // Foods consumed section
        JLabel foodsConsumedLabel = new JLabel("Foods Consumed:", JLabel.CENTER);
        foodsConsumedLabel.setFont(new Font("Arial", Font.BOLD, 16));
        centerPanel.add(foodsConsumedLabel);

        foodsConsumedField = new JTextArea();
        foodsConsumedField.setEditable(false);
        foodsConsumedField.setLineWrap(true);
        foodsConsumedField.setWrapStyleWord(true);
        JScrollPane foodsConsumedScrollPane = new JScrollPane(foodsConsumedField);
        centerPanel.add(foodsConsumedScrollPane);

        catalogFoodIntakeWindow.add(centerPanel, BorderLayout.CENTER);

        // Exit button centered at the bottom
        JPanel buttonPanel = new JPanel();
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(cHandler);
        exitButton.setActionCommand("goMainScreen");
        buttonPanel.add(exitButton);
        catalogFoodIntakeWindow.add(buttonPanel, BorderLayout.SOUTH);

        // Set frame visibility to true
        catalogFoodIntakeWindow.setVisible(false);
    }
    public void makeCreateScreen() {

        JPanel createPanel = new JPanel();
        JLabel createTitleLabel = new JLabel();

        createWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createWindow.setSize(800, 600);
        createWindow.setLayout(new BorderLayout());
        createWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);

        createPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.insets = new Insets(5, 5, 5, 5);  // Adds padding around components
        gbc1.fill = GridBagConstraints.NONE;

        // Username label
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        createPanel.add(new JLabel("Username:"), gbc1);

        // Username text field
        gbc1.gridx = 1;
        createPanel.add(createUsernameField, gbc1);

        // Password label
        gbc1.gridx = 0;
        gbc1.gridy = 1;
        createPanel.add(new JLabel("Password:"), gbc1);

        // Password text field
        gbc1.gridx = 1;
        createPanel.add(createPasswordField, gbc1);

        // Login button
        gbc1.gridx = 0;
        gbc1.gridy = 2;
        gbc1.gridwidth = 1;
        gbc1.anchor = GridBagConstraints.CENTER;

        JButton submitButton = new JButton();
        submitButton.setText("Submit");
        submitButton.addActionListener(cHandler);
        submitButton.setActionCommand("tryToCreate");
        createPanel.add(submitButton, gbc1);

        gbc1.gridx = 1;
        gbc1.gridy = 2;
        gbc1.gridwidth = 1;
        gbc1.anchor = GridBagConstraints.WEST;

        JButton exitButton = new JButton();
        exitButton.setText("Exit");
        exitButton.addActionListener(cHandler);
        exitButton.setActionCommand("backToLogin");
        createPanel.add(exitButton, gbc1);

        createTitleLabel.setText("Create Account");
        createTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        createTitleLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        createTitleLabel.setForeground(Color.BLUE);

        createWindow.add(createTitleLabel, BorderLayout.NORTH);
        createWindow.add(createPanel, BorderLayout.CENTER);

        createPanel.setVisible(true);
        createTitleLabel.setVisible(true);
        createWindow.setVisible(false);
    }
    public void makeCustomFoodItemScreen() {
        customFoodWindow.setLayout(new BorderLayout());
        customFoodWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Title
        JLabel titleLabel = new JLabel("Make Custom Food Item", JLabel.CENTER);
        customFoodWindow.add(titleLabel, BorderLayout.NORTH);

        // Panel for Food Name
        JPanel namePanel = new JPanel(new FlowLayout());
        JLabel nameLabel = new JLabel("Food Name:");
        JTextField nameField = new JTextField(20); // Text field for food name
        namePanel.add(nameLabel);
        namePanel.add(nameField);
        customFoodWindow.add(namePanel, BorderLayout.NORTH);

        // Main Panel for Input Fields
        JPanel inputPanel = new JPanel(new GridLayout(1, 3));

        // Macronutrients Panel
        JPanel macroPanel = new JPanel(new GridLayout(0, 1));
        macroPanel.setBorder(BorderFactory.createTitledBorder("Macronutrients"));

        // Automatically set text fields to "0"
        JTextField waterField = new JTextField("0");
        JTextField energyField = new JTextField("0");
        JTextField carbohydrateField = new JTextField("0");
        JTextField monounsaturatedFatField = new JTextField("0");
        JTextField saturatedFatField = new JTextField("0");
        JTextField polyunsaturatedFatField = new JTextField("0");
        JTextField proteinField = new JTextField("0");
        JTextField fiberField = new JTextField("0");

        macroPanel.add(new JLabel("Water (L):"));
        macroPanel.add(waterField);
        macroPanel.add(new JLabel("Energy (kCal):"));
        macroPanel.add(energyField);
        macroPanel.add(new JLabel("Carbohydrate (g):"));
        macroPanel.add(carbohydrateField);
        macroPanel.add(new JLabel("Monounsaturated Fat (g):"));
        macroPanel.add(monounsaturatedFatField);
        macroPanel.add(new JLabel("Saturated Fat (g):"));
        macroPanel.add(saturatedFatField);
        macroPanel.add(new JLabel("Polyunsaturated Fat (g):"));
        macroPanel.add(polyunsaturatedFatField);
        macroPanel.add(new JLabel("Protein (g):"));
        macroPanel.add(proteinField);
        macroPanel.add(new JLabel("Fiber (g):"));
        macroPanel.add(fiberField);

        inputPanel.add(macroPanel);

        // Vitamins Panel
        JPanel vitaminPanel = new JPanel(new GridLayout(0, 1));
        vitaminPanel.setBorder(BorderFactory.createTitledBorder("Vitamins"));

        JTextField vitaminAField = new JTextField("0");
        JTextField vitaminB1Field = new JTextField("0");
        JTextField vitaminB2Field = new JTextField("0");
        JTextField vitaminB3Field = new JTextField("0");
        JTextField vitaminB5Field = new JTextField("0");
        JTextField vitaminB6Field = new JTextField("0");
        JTextField vitaminB7Field = new JTextField("0");
        JTextField vitaminB9Field = new JTextField("0");
        JTextField vitaminB12Field = new JTextField("0");
        JTextField vitaminCField = new JTextField("0");
        JTextField vitaminDField = new JTextField("0");
        JTextField vitaminEField = new JTextField("0");
        JTextField vitaminKField = new JTextField("0");

        vitaminPanel.add(new JLabel("Vitamin A (ug):"));
        vitaminPanel.add(vitaminAField);
        vitaminPanel.add(new JLabel("Vitamin B1 (mg):"));
        vitaminPanel.add(vitaminB1Field);
        vitaminPanel.add(new JLabel("Vitamin B2 (mg):"));
        vitaminPanel.add(vitaminB2Field);
        vitaminPanel.add(new JLabel("Vitamin B3 (mg):"));
        vitaminPanel.add(vitaminB3Field);
        vitaminPanel.add(new JLabel("Vitamin B5 (mg):"));
        vitaminPanel.add(vitaminB5Field);
        vitaminPanel.add(new JLabel("Vitamin B6 (mg):"));
        vitaminPanel.add(vitaminB6Field);
        vitaminPanel.add(new JLabel("Vitamin B7 (ug):"));
        vitaminPanel.add(vitaminB7Field);
        vitaminPanel.add(new JLabel("Vitamin B9 (ug):"));
        vitaminPanel.add(vitaminB9Field);
        vitaminPanel.add(new JLabel("Vitamin B12 (ug):"));
        vitaminPanel.add(vitaminB12Field);
        vitaminPanel.add(new JLabel("Vitamin C (mg):"));
        vitaminPanel.add(vitaminCField);
        vitaminPanel.add(new JLabel("Vitamin D (ug):"));
        vitaminPanel.add(vitaminDField);
        vitaminPanel.add(new JLabel("Vitamin E (mg):"));
        vitaminPanel.add(vitaminEField);
        vitaminPanel.add(new JLabel("Vitamin K (ug):"));
        vitaminPanel.add(vitaminKField);

        inputPanel.add(vitaminPanel);

        // Minerals Panel
        JPanel mineralPanel = new JPanel(new GridLayout(0, 1));
        mineralPanel.setBorder(BorderFactory.createTitledBorder("Minerals"));

        JTextField cholineField = new JTextField("0");
        JTextField calciumField = new JTextField("0");
        JTextField chlorideField = new JTextField("0");
        JTextField chromiumField = new JTextField("0");
        JTextField copperField = new JTextField("0");
        JTextField fluorideField = new JTextField("0");
        JTextField iodineField = new JTextField("0");
        JTextField ironField = new JTextField("0");
        JTextField magnesiumField = new JTextField("0");
        JTextField manganeseField = new JTextField("0");
        JTextField molybdenumField = new JTextField("0");
        JTextField phosphorusField = new JTextField("0");
        JTextField potassiumField = new JTextField("0");
        JTextField seleniumField = new JTextField("0");
        JTextField sodiumField = new JTextField("0");
        JTextField zincField = new JTextField("0");

        mineralPanel.add(new JLabel("Choline (mg):"));
        mineralPanel.add(cholineField);
        mineralPanel.add(new JLabel("Calcium (mg):"));
        mineralPanel.add(calciumField);
        mineralPanel.add(new JLabel("Chloride (g):"));
        mineralPanel.add(chlorideField);
        mineralPanel.add(new JLabel("Chromium (ug):"));
        mineralPanel.add(chromiumField);
        mineralPanel.add(new JLabel("Copper (ug):"));
        mineralPanel.add(copperField);
        mineralPanel.add(new JLabel("Fluoride (mg):"));
        mineralPanel.add(fluorideField);
        mineralPanel.add(new JLabel("Iodine (ug):"));
        mineralPanel.add(iodineField);
        mineralPanel.add(new JLabel("Iron (mg):"));
        mineralPanel.add(ironField);
        mineralPanel.add(new JLabel("Magnesium (mg):"));
        mineralPanel.add(magnesiumField);
        mineralPanel.add(new JLabel("Manganese (mg):"));
        mineralPanel.add(manganeseField);
        mineralPanel.add(new JLabel("Molybdenum (ug):"));
        mineralPanel.add(molybdenumField);
        mineralPanel.add(new JLabel("Phosphorus (mg):"));
        mineralPanel.add(phosphorusField);
        mineralPanel.add(new JLabel("Potassium (mg):"));
        mineralPanel.add(potassiumField);
        mineralPanel.add(new JLabel("Selenium (ug):"));
        mineralPanel.add(seleniumField);
        mineralPanel.add(new JLabel("Sodium (mg):"));
        mineralPanel.add(sodiumField);
        mineralPanel.add(new JLabel("Zinc (mg):"));
        mineralPanel.add(zincField);

        inputPanel.add(mineralPanel);

        customFoodWindow.add(inputPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton createFoodButton = new JButton("Create Food");
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(cHandler);
        exitButton.setActionCommand("goMainScreen");

        createFoodButton.addActionListener(e -> {
            try {
                // Get all input values from text fields
                String foodName = nameField.getText();
                double water = Double.parseDouble(waterField.getText());
                double energy = Double.parseDouble(energyField.getText());
                double carbohydrate = Double.parseDouble(carbohydrateField.getText());
                double monounsaturatedFat = Double.parseDouble(monounsaturatedFatField.getText());
                double saturatedFat = Double.parseDouble(saturatedFatField.getText());
                double polyunsaturatedFat = Double.parseDouble(polyunsaturatedFatField.getText());
                double protein = Double.parseDouble(proteinField.getText());
                double fiber = Double.parseDouble(fiberField.getText());

                double vitaminA = Double.parseDouble(vitaminAField.getText());
                double vitaminB1 = Double.parseDouble(vitaminB1Field.getText());
                double vitaminB2 = Double.parseDouble(vitaminB2Field.getText());
                double vitaminB3 = Double.parseDouble(vitaminB3Field.getText());
                double vitaminB5 = Double.parseDouble(vitaminB5Field.getText());
                double vitaminB6 = Double.parseDouble(vitaminB6Field.getText());
                double vitaminB7 = Double.parseDouble(vitaminB7Field.getText());
                double vitaminB9 = Double.parseDouble(vitaminB9Field.getText());
                double vitaminB12 = Double.parseDouble(vitaminB12Field.getText());
                double vitaminC = Double.parseDouble(vitaminCField.getText());
                double vitaminD = Double.parseDouble(vitaminDField.getText());
                double vitaminE = Double.parseDouble(vitaminEField.getText());
                double vitaminK = Double.parseDouble(vitaminKField.getText());

                double choline = Double.parseDouble(cholineField.getText());
                double calcium = Double.parseDouble(calciumField.getText());
                double chloride = Double.parseDouble(chlorideField.getText());
                double chromium = Double.parseDouble(chromiumField.getText());
                double copper = Double.parseDouble(copperField.getText());
                double fluoride = Double.parseDouble(fluorideField.getText());
                double iodine = Double.parseDouble(iodineField.getText());
                double iron = Double.parseDouble(ironField.getText());
                double magnesium = Double.parseDouble(magnesiumField.getText());
                double manganese = Double.parseDouble(manganeseField.getText());
                double molybdenum = Double.parseDouble(molybdenumField.getText());
                double phosphorus = Double.parseDouble(phosphorusField.getText());
                double potassium = Double.parseDouble(potassiumField.getText());
                double selenium = Double.parseDouble(seleniumField.getText());
                double sodium = Double.parseDouble(sodiumField.getText());
                double zinc = Double.parseDouble(zincField.getText());

                // Insert the data into the database
                String sql = "INSERT INTO food (foodName, water, energy, carbohydrate, monounsaturatedFat, saturatedFat, polyunsaturatedFat, protein, fiber, " +
                        "vitaminA, vitaminB1Thiamine, vitaminB2Riboflavin, vitaminB3Niacin, vitaminB5PantothenicAcid, vitaminB6Pyridoxine, vitaminB7Biotin, vitaminB9Folate, vitaminB12Cyanocobalamin, vitaminC, vitaminD, vitaminE, vitaminK, " +
                        "choline, calcium, chloride, chromium, copper, fluoride, iodine, iron, magnesium, manganese, molybdenum, phosphorus, potassium, selenium, sodium, zinc, userId) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try {
                    PreparedStatement preparedStatement = connectionToMySQL.prepareStatement(sql);

                    // Bind parameters (starting from 1 and going up to 37)
                    preparedStatement.setString(1, foodName);
                    preparedStatement.setDouble(2, water);
                    preparedStatement.setDouble(3, energy);
                    preparedStatement.setDouble(4, carbohydrate);
                    preparedStatement.setDouble(5, monounsaturatedFat);
                    preparedStatement.setDouble(6, saturatedFat);
                    preparedStatement.setDouble(7, polyunsaturatedFat);
                    preparedStatement.setDouble(8, protein);
                    preparedStatement.setDouble(9, fiber);
                    preparedStatement.setDouble(10, vitaminA);
                    preparedStatement.setDouble(11, vitaminB1);
                    preparedStatement.setDouble(12, vitaminB2);
                    preparedStatement.setDouble(13, vitaminB3);
                    preparedStatement.setDouble(14, vitaminB5);
                    preparedStatement.setDouble(15, vitaminB6);
                    preparedStatement.setDouble(16, vitaminB7);
                    preparedStatement.setDouble(17, vitaminB9);
                    preparedStatement.setDouble(18, vitaminB12);
                    preparedStatement.setDouble(19, vitaminC);
                    preparedStatement.setDouble(20, vitaminD);
                    preparedStatement.setDouble(21, vitaminE);
                    preparedStatement.setDouble(22, vitaminK);
                    preparedStatement.setDouble(23, choline);
                    preparedStatement.setDouble(24, calcium);
                    preparedStatement.setDouble(25, chloride);
                    preparedStatement.setDouble(26, chromium);
                    preparedStatement.setDouble(27, copper);
                    preparedStatement.setDouble(28, fluoride);
                    preparedStatement.setDouble(29, iodine);
                    preparedStatement.setDouble(30, iron);
                    preparedStatement.setDouble(31, magnesium);
                    preparedStatement.setDouble(32, manganese);
                    preparedStatement.setDouble(33, molybdenum);
                    preparedStatement.setDouble(34, phosphorus);
                    preparedStatement.setDouble(35, potassium);
                    preparedStatement.setDouble(36, selenium);
                    preparedStatement.setDouble(37, sodium);
                    preparedStatement.setDouble(38, zinc);
                    preparedStatement.setInt(39, retrievedUserId);

                    preparedStatement.executeUpdate();
                    JOptionPane.showMessageDialog(customFoodWindow, "Food item created and added to the database!");

                    // Clear the input fields after successful insertion
                    nameField.setText("");
                    waterField.setText("0");
                    energyField.setText("0");
                    carbohydrateField.setText("0");
                    monounsaturatedFatField.setText("0");
                    saturatedFatField.setText("0");
                    polyunsaturatedFatField.setText("0");
                    proteinField.setText("0");
                    fiberField.setText("0");
                    vitaminAField.setText("0");
                    vitaminB1Field.setText("0");
                    vitaminB2Field.setText("0");
                    vitaminB3Field.setText("0");
                    vitaminB5Field.setText("0");
                    vitaminB6Field.setText("0");
                    vitaminB7Field.setText("0");
                    vitaminB9Field.setText("0");
                    vitaminB12Field.setText("0");
                    vitaminCField.setText("0");
                    vitaminDField.setText("0");
                    vitaminEField.setText("0");
                    vitaminKField.setText("0");
                    cholineField.setText("0");
                    calciumField.setText("0");
                    chlorideField.setText("0");
                    chromiumField.setText("0");
                    copperField.setText("0");
                    fluorideField.setText("0");
                    iodineField.setText("0");
                    ironField.setText("0");
                    magnesiumField.setText("0");
                    manganeseField.setText("0");
                    molybdenumField.setText("0");
                    phosphorusField.setText("0");
                    potassiumField.setText("0");
                    seleniumField.setText("0");
                    sodiumField.setText("0");
                    zincField.setText("0");

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(customFoodWindow, "Error saving to the database: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(customFoodWindow, "Please enter valid numbers for all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        exitButton.addActionListener(e -> customFoodWindow.dispose());

        buttonPanel.add(createFoodButton);
        buttonPanel.add(exitButton);

        customFoodWindow.add(buttonPanel, BorderLayout.SOUTH);
        customFoodWindow.setSize(800, 600);
        customFoodWindow.setVisible(false);
    }
    public void makeLoginScreen() {
        JPanel loginPanel = new JPanel();
        JLabel loginTitleLabel = new JLabel();

        loginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginWindow.setSize(700, 500);
        loginWindow.setLayout(new BorderLayout());
        loginWindow.getContentPane().setBackground(new Color(142, 241, 142));

        loginPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  // Adds padding around components
        gbc.fill = GridBagConstraints.NONE;

        // Username label
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(new JLabel("Username:"), gbc);

        // Username text field
        gbc.gridx = 1;
        loginPanel.add(loginUsernameField, gbc);

        // Password label
        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(new JLabel("Password:"), gbc);

        // Password text field
        gbc.gridx = 1;
        loginPanel.add(loginPasswordField, gbc);

        // Panel for buttons with BoxLayout to center them horizontally
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        // Add space to center the buttons in the panel
        buttonPanel.add(Box.createHorizontalGlue());

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(cHandler);
        loginButton.setActionCommand("tryToLogin");
        buttonPanel.add(loginButton);

        // Add space between the buttons
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0))); // 10px horizontal space

        // Create account button
        JButton createButton = new JButton("Create");
        createButton.addActionListener(cHandler);
        createButton.setActionCommand("createAccount");
        buttonPanel.add(createButton);

        // Add space to the right to ensure centering
        buttonPanel.add(Box.createHorizontalGlue());

        // Add button panel to the main panel
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Span across two columns
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(buttonPanel, gbc);

        // Title label setup
        loginTitleLabel.setText("<html><br><br><div style='text-align: center;'>Login or Create Account</div></html>");
        loginTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        loginTitleLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        loginTitleLabel.setForeground(new Color(25, 102, 0));

        loginPanel.setBackground(new Color(142, 241, 142));
        buttonPanel.setBackground(new Color(142, 241, 142));

        loginWindow.add(loginTitleLabel, BorderLayout.NORTH);
        loginWindow.add(loginPanel, BorderLayout.CENTER);

        loginWindow.setLocationRelativeTo(null); // Center the window on screen
        loginWindow.setVisible(false);
    }
    public void makeMainScreen() {
        // Main window setup
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(800, 600);
        mainWindow.setLayout(new BorderLayout());
        mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Set the background color of the entire content pane
        mainWindow.getContentPane().setBackground(new Color(142, 241, 142));

        // Title panel to hold the label and add spacing
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
        titlePanel.setBackground(new Color(142, 241, 142));

        // Title label at the top
        JLabel mainLabel = new JLabel("Main Navigation");
        mainLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        mainLabel.setHorizontalAlignment(JLabel.CENTER);
        mainLabel.setOpaque(true);
        mainLabel.setBackground(new Color(142, 241, 142));

        titlePanel.add(mainLabel, BorderLayout.CENTER);
        mainWindow.add(titlePanel, BorderLayout.NORTH);

        // Panel for buttons with BoxLayout
        JPanel mainButtonPanel = new JPanel();
        mainButtonPanel.setLayout(new BoxLayout(mainButtonPanel, BoxLayout.Y_AXIS));
        mainButtonPanel.setBackground(new Color(142, 241, 142));

        // Create and add buttons to the panel
        String[] buttonLabels = {
                "Exit",
                "Search for Food Item",
                "Set Nutrition Goals",
                "Alter User Data",
                "Display Status of Goals",
                "Make Custom Food Item",
                "View Advanced Statistics",
                "Catalog Food Intake"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setMaximumSize(new Dimension(450, 75));
            button.setAlignmentX(JButton.CENTER_ALIGNMENT);
            button.setFont(new Font("Segoe UI", Font.PLAIN, 18));

            // Set ActionListener and ActionCommand for each button
            button.addActionListener(cHandler);
            button.setActionCommand(label.replace(" ", "").toLowerCase());

            mainButtonPanel.add(button);
            mainButtonPanel.add(Box.createVerticalStrut(15)); // Adds space between buttons
        }

        // Wrapper panel for vertical centering
        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.Y_AXIS));
        wrapperPanel.setBackground(new Color(142, 241, 142)); // Set background for wrapper panel
        wrapperPanel.add(Box.createVerticalGlue());  // Adds flexible space above
        wrapperPanel.add(mainButtonPanel);
        wrapperPanel.add(Box.createVerticalGlue());  // Adds flexible space below

        // Add the wrapper panel to the center of the main window
        mainWindow.add(wrapperPanel, BorderLayout.CENTER);

        // Display the main window
        mainWindow.setLocationRelativeTo(null);  // Center window on screen
        mainWindow.setVisible(false);
    }
    public void makeSearchItemScreen() {

        searchWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        searchWindow.setSize(800, 600);
        searchWindow.setLayout(new BorderLayout());
        searchWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JLabel titleLabel = new JLabel("Search For Food Item", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        searchWindow.add(titleLabel, BorderLayout.NORTH);

        // Panel to contain search components and output
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Search panel for "Search:" label, text field, and button
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel searchLabel = new JLabel("Search:");

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(cHandler);
        searchButton.setActionCommand("searchForFood");
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Scrollable output area for food item details
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setEditable(false);

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Panel for "Add Item" and "Exit" buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addItemButton = new JButton("Add Item");
        addItemButton.addActionListener(cHandler);
        addItemButton.setActionCommand("addItem");
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(cHandler);
        exitButton.setActionCommand("goMainScreen");
        buttonPanel.add(addItemButton);
        buttonPanel.add(exitButton);

        // Add components to the center panel
        centerPanel.add(searchPanel);
        centerPanel.add(Box.createVerticalStrut(10)); // Spacing
        centerPanel.add(scrollPane);
        centerPanel.add(Box.createVerticalStrut(10)); // Spacing
        centerPanel.add(buttonPanel);

        // Add centerPanel to the main window's center area
        searchWindow.add(centerPanel, BorderLayout.CENTER);

        // Set the window visible
        searchWindow.setVisible(false);

    }
    public void makeSetGoalsScreen() {
        setGoalsWindow = new JFrame("Set Nutritional Goals");
        setGoalsWindow.setLayout(new BorderLayout(10, 10)); // Use BorderLayout for overall structure

        // Title Label at the top
        JLabel titleLabel = new JLabel("Set Nutritional Goals", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        setGoalsWindow.add(titleLabel, BorderLayout.NORTH);

        // Main panel with a grid layout for three columns
        JPanel mainPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Macronutrients Panel
        JPanel macronutrientsPanel = new JPanel(new GridLayout(0, 1));
        macronutrientsPanel.setBorder(BorderFactory.createTitledBorder("Macronutrients"));
        String[] macronutrients =
        {
            "Water", "Calories", "Carbohydrate", "Protein", "Monounsaturated Fat",
            "Polyunsaturated Fat", "Saturated Fat", "Fiber"
        };
        for(String item : macronutrients)
        {
            JButton button = new JButton(item);
            button.addActionListener(e -> showInputDialog(item)); // Show dialog on click
            macronutrientsPanel.add(button);
        }

        // Vitamins Panel
        JPanel vitaminsPanel = new JPanel(new GridLayout(0, 1));
        vitaminsPanel.setBorder(BorderFactory.createTitledBorder("Vitamins"));
        String[] vitamins =
        {
            "Vitamin A", "Vitamin B1 Thiamine", "Vitamin B2 Riboflavin", "Vitamin B3 Niacin", "Vitamin B5 Pantothenic Acid", "Vitamin B6 Pyridoxine",
            "Vitamin B7 Biotin", "Vitamin B9 Folate", "Vitamin B12 Cyanocobalamin", "Vitamin C", "Vitamin D", "vitaminE", "Vitamin K"
        };
        for(String item : vitamins)
        {
            JButton button = new JButton(item);
            button.addActionListener(e -> showInputDialog(item)); // Show dialog on click
            vitaminsPanel.add(button);
        }

        // Minerals Panel
        JPanel mineralsPanel = new JPanel(new GridLayout(0, 1));
        mineralsPanel.setBorder(BorderFactory.createTitledBorder("Minerals"));
        String[] minerals =
        {
            "Calcium", "Chloride", "Choline", "Chromium", "Copper", "Fluoride", "Iodine",
            "Iron", "Magnesium", "Manganese", "Molybdenum", "Phosphorus", "Potassium", "Selenium", "Sodium", "Zinc"
        };
        for(String item : minerals)
        {
            JButton button = new JButton(item);
            button.addActionListener(e -> showInputDialog(item)); // Show dialog on click
            mineralsPanel.add(button);
        }

        // Add category panels to the main panel
        mainPanel.add(macronutrientsPanel);
        mainPanel.add(vitaminsPanel);
        mainPanel.add(mineralsPanel);

        // Add the main panel to the center of the window
        setGoalsWindow.add(mainPanel, BorderLayout.CENTER);

        // Exit Button at the bottom
        JButton exitButton = new JButton("EXIT");
        exitButton.addActionListener(cHandler);
        exitButton.setActionCommand("goMainScreen");
        JPanel exitPanel = new JPanel();
        exitPanel.add(exitButton); // Add the button to a panel for centered alignment
        setGoalsWindow.add(exitPanel, BorderLayout.SOUTH);

        // Set up the frame
        setGoalsWindow.setSize(600, 500);
        setGoalsWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setGoalsWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setGoalsWindow.setVisible(false);
    }
    public void makeStatusGoalsScreen() {
        // Create the main frame
        statusWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        statusWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        statusWindow.setLayout(new BorderLayout(10, 10));

        // Title at the top
        JLabel titleLabel = new JLabel("Status of Goals", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        statusWindow.add(titleLabel, BorderLayout.NORTH);

        // Main panel for all content
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout(10, 10));

        // Information panel
        JPanel infoPanel = new JPanel(new BorderLayout());
        informationField = new JTextArea();
        informationField.setEditable(false);
        informationField.setLineWrap(true);
        informationField.setWrapStyleWord(true);
        JScrollPane informationScrollPane = new JScrollPane(informationField);
        infoPanel.add(informationScrollPane, BorderLayout.CENTER);
        centerPanel.add(infoPanel, BorderLayout.NORTH);

        // Panel for the 3 sections in a horizontal row
        JPanel horizontalPanel = new JPanel();
        horizontalPanel.setLayout(new GridLayout(1, 3, 10, 0)); // 1 row, 3 columns, spacing between columns

        // Macronutrients section
        JPanel macronutrientsPanel = new JPanel(new BorderLayout());
        JLabel macronutrientsLabel = new JLabel("Macronutrients Progress", JLabel.CENTER);
        macronutrientsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        macronutrientsPanel.add(macronutrientsLabel, BorderLayout.NORTH);

        macronutrientsField = new JTextArea();
        macronutrientsField.setEditable(false);
        macronutrientsField.setLineWrap(true);
        macronutrientsField.setWrapStyleWord(true);
        JScrollPane macronutrientsScrollPane = new JScrollPane(macronutrientsField);
        macronutrientsPanel.add(macronutrientsScrollPane, BorderLayout.CENTER);

        // Vitamins section
        JPanel vitaminsPanel = new JPanel(new BorderLayout());
        JLabel vitaminsLabel = new JLabel("Vitamins Progress", JLabel.CENTER);
        vitaminsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        vitaminsPanel.add(vitaminsLabel, BorderLayout.NORTH);

        vitaminsField = new JTextArea();
        vitaminsField.setEditable(false);
        vitaminsField.setLineWrap(true);
        vitaminsField.setWrapStyleWord(true);
        JScrollPane vitaminsScrollPane = new JScrollPane(vitaminsField);
        vitaminsPanel.add(vitaminsScrollPane, BorderLayout.CENTER);

        // Minerals section
        JPanel mineralsPanel = new JPanel(new BorderLayout());
        JLabel mineralsLabel = new JLabel("Minerals Progress", JLabel.CENTER);
        mineralsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mineralsPanel.add(mineralsLabel, BorderLayout.NORTH);

        mineralsField = new JTextArea();
        mineralsField.setEditable(false);
        mineralsField.setLineWrap(true);
        mineralsField.setWrapStyleWord(true);
        JScrollPane mineralsScrollPane = new JScrollPane(mineralsField);
        mineralsPanel.add(mineralsScrollPane, BorderLayout.CENTER);

        // Add each section panel to the horizontal panel
        horizontalPanel.add(macronutrientsPanel);
        horizontalPanel.add(vitaminsPanel);
        horizontalPanel.add(mineralsPanel);

        // Add the horizontal panel to the center panel
        centerPanel.add(horizontalPanel, BorderLayout.CENTER);

        // Add center panel to the main frame
        statusWindow.add(centerPanel, BorderLayout.CENTER);

        // Exit button centered at the bottom
        JPanel buttonPanel = new JPanel();
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(cHandler);
        exitButton.setActionCommand("goMainScreen");
        buttonPanel.add(exitButton);
        statusWindow.add(buttonPanel, BorderLayout.SOUTH);

        // Set frame visibility to true
        statusWindow.setVisible(false);
    }
    public void makeTitleScreen() {

        JPanel titlePanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        titleWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        titleWindow.setSize(600, 400);
        titleWindow.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel();
        titleLabel.setText("<html><div style='text-align: center;'>The Nutrition Tracker<br>CEN4010<br><br>By Joshua Lor<br>Sam Gray<br>Haivan Benjamin</div></html>");
        titleLabel.setHorizontalAlignment(JLabel.CENTER); // Horizontally centers the text
        titleLabel.setVerticalAlignment(JLabel.TOP); // Aligns the label to the top of the container
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 32));
        titleLabel.setForeground(new Color(240, 240, 0));

        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(new Color(132, 231, 132));

        // Create buttons
        JButton startButton = new JButton("Start");
        startButton.addActionListener(cHandler);
        startButton.setActionCommand("goLoginScreen");
        JButton exitButton = new JButton("Exit");

        // Add buttons to the panel
        buttonPanel.add(startButton);
        buttonPanel.add(exitButton);

        // Set background for the title area
        titlePanel.setBackground(new Color(132, 231, 132));
        titlePanel.add(titleLabel);

        // Fill in the center area with color
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(titlePanel.getBackground()); // Match the background color or set your own color

        // Add the title label and button panel to the window
        titleWindow.add(titlePanel, BorderLayout.NORTH);
        titleWindow.add(centerPanel, BorderLayout.CENTER);
        titleWindow.add(buttonPanel, BorderLayout.SOUTH);

        // Center the window on screen
        titleWindow.setLocationRelativeTo(null);
        titleWindow.setVisible(true);
        // Make the window visible

        exitButton.addActionListener(e -> System.exit(0));
    }

    // All functions that display screens in ABC order
    public void displayAlterUserDataScreen() {
        alterUserDataWindow.setVisible(true);
    }
    public void displayCatalogFoodIntakeScreen() {
        LinkedList<Food> retrievedFoodCatalog = currentUser.getFoodCatalog();
        StringBuilder finalOutput = new StringBuilder();
        for(Food foodEntry : retrievedFoodCatalog)
        {
            String foodName = foodEntry.getName();
            foodName = foodName.replace("-", " ");
            finalOutput.append(foodName).append("\n");
        }
        foodsConsumedField.setText(finalOutput.toString());
        catalogFoodIntakeWindow.setVisible(true);
    }
    public void displayCreateScreen()
    {
        createWindow.setVisible(true);
    }
    public void displayCustomScreen()
    {
        customFoodWindow.setVisible(true);
    }
    public void displayMainScreen()
    {
        mainWindow.setVisible(true);
    }
    public void displayLoginScreen()
    {
        loginWindow.setVisible(true);
    }
    public void displaySearchScreen()
    {
        searchWindow.setVisible(true);
    }
    public void displaySetGoalsScreen()
    {
        setGoalsWindow.setVisible(true);
    }
    public void displayStatusGoalsScreen() {
        currentUser.updateAllFromDatabase();
        StringBuilder userSb = new StringBuilder();
        userSb.append("Name: ").append(currentUser.nameGetter()).append("\n");
        userSb.append("Weight: ").append(currentUser.weightGetter()).append("\n");
        userSb.append("Height: ").append(currentUser.heightGetter()).append("\n");
        userSb.append("Sex: ").append(currentUser.sexGetter()).append("\n");
        userSb.append("Exercise Level: ").append(currentUser.exerciseGetter()).append("\n");
        informationField.setText(userSb.toString());
        statusWindow.setVisible(true);
    }
    public void displayTitleScreen()
    {
        titleWindow.setVisible(true);
    }

    // All functions that remove screens in ABC order
    public void removeAlterUserDataScreen()
    {
        alterUserDataWindow.setVisible(false);
    }
    public void removeCatalogFoodIntakeScreen()
    {
        catalogFoodIntakeWindow.setVisible(false);
    }
    public void removeCreateScreen()
    {
        createWindow.setVisible(false);
    }
    public void removeCustomScreen()
    {
        customFoodWindow.setVisible(false);
    }
    public void removeLoginScreen()
    {
        loginWindow.setVisible(false);
    }
    public void removeMainScreen()
    {
        mainWindow.setVisible(false);
    }
    public void removeSearchScreen()
    {
        searchWindow.setVisible(false);
    }
    public void removeSetGoalsScreen()
    {
        setGoalsWindow.setVisible(false);
    }
    public void removeStatusGoalsScreen()
    {
        statusWindow.setVisible(false);
    }
    public void removeTitleScreen()
    {
        titleWindow.setVisible(false);
    }

    // All class helper functions in ABC order
    public boolean checkCredentials(String username, String password) {
        ResultSet resultSet;
        String query = "SELECT password, userId FROM users WHERE username = ?";
        String retrievedPassword = "";

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
                this.retrievedUserId = resultSet.getInt("userId");
                System.out.println("the retrieved userId is: " + retrievedUserId);
            }

            return retrievedPassword.equals(password);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(loginWindow, "Connection to database failed", "Error", JOptionPane.ERROR_MESSAGE);

            e.printStackTrace();
        }

        return false; // Error occurred
    }
    public class ChoiceHandler implements ActionListener {

        private JTextField usernameField;
        private JPasswordField passwordField;
        private String yourChoice;

        // Constructor to initialize fields
        public ChoiceHandler(JTextField usernameField, JPasswordField passwordField)
        {
            this.usernameField = usernameField;
            this.passwordField = passwordField;
        }

        @Override
        public void actionPerformed(ActionEvent event)
        {
            yourChoice = event.getActionCommand();

            switch(yourChoice)
            {
                case "goLoginScreen":
                    removeTitleScreen();
                    displayLoginScreen();
                    break;

                case "tryToLogin":
                    // Retrieve the text from the fields
                    String username = usernameField.getText().strip();
                    char[] password = passwordField.getPassword();
                    String loginPasswordString = new String(password);

                    if(checkCredentials(username, loginPasswordString))
                    {
                        System.out.println("Login successful.");

                        removeLoginScreen();
                        displayMainScreen();
                        createSession();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Please check your username and password and then try again.");
                    }
                    break;

                case "createAccount":
                    displayCreateScreen();
                    removeLoginScreen();
                    break;

                case "backToLogin":
                    displayLoginScreen();
                    removeCreateScreen();
                    break;

                case "tryToCreate":
                    String createUsername = createUsernameField.getText();
                    char[] createPassword = createPasswordField.getPassword();
                    String passwordString = new String(createPassword);
                    if(createAccount(createUsername, passwordString))
                    {
                        System.out.println("Account created successfully.");
                        JOptionPane.showMessageDialog(null, "Account created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        displayLoginScreen();
                        removeCreateScreen();
                    }
                    else
                    {
                        System.out.println("Username already exists!");
                        JOptionPane.showMessageDialog(null, "ERROR: User already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;

                case "searchforfooditem":
                    displaySearchScreen();
                    removeMainScreen();
                    break;

                case "goMainScreen":
                    removeSearchScreen();
                    removeSetGoalsScreen();
                    removeStatusGoalsScreen();
                    removeAlterUserDataScreen();
                    removeCatalogFoodIntakeScreen();
                    removeCustomScreen();
                    displayMainScreen();
                    break;

                case "searchForFood":
                    searchItem = searchField.getText();
                    session.getFoodInput(searchItem);
                    break;

                case "addItem":
                    searchItem = searchField.getText();
                    session.addFoodItem(searchItem);
                    break;

                case "setnutritiongoals":
                    displaySetGoalsScreen();
                    removeMainScreen();
                    break;

                case "exit":
                    removeMainScreen();
                    displayLoginScreen();
                    break;

                case "displaystatusofgoals":
                    session.showGoals();
                    removeMainScreen();
                    displayStatusGoalsScreen();
                    break;

                case "alteruserdata":
                    removeMainScreen();
                    displayAlterUserDataScreen();
                    break;

                case "makecustomfooditem":
                	removeMainScreen();
                	displayCustomScreen();
                	break;

                // case "viewadvancedstatistics"

                case "catalogfoodintake":
                    removeMainScreen();
                    displayCatalogFoodIntakeScreen();
                    break;
            }
        }

    }
    public boolean createAccount(String username, String password) {
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
                return false;
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
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
                return false;
            }
            resultSet.close();
            preparedStatement.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
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
                return false;
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
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
                return true;
            }
            else
            {
                System.out.println("Failed to set current consumption to 0!");
                return false;
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public void createSession() {
        User newUser = new User(retrievedUserId);
        this.currentUser = newUser;
        System.out.println("current user is set to: " + currentUser.getUserId());
        System.out.println("current user's food diary is: " + currentUser.getDailyFoodsConsumed());
        Session newSession = new Session(newUser, this);
        setSession(newSession); // Pass session to GUI
        newSession.startSession(); // Start the session
    }
    public void setSession(Session session)
    {
        this.session = session;
    }
    public void showInputDialog(String nutrient) {
        String input = JOptionPane.showInputDialog(setGoalsWindow, "Enter your goal for " + nutrient + " in grams:", "Set Goal", JOptionPane.PLAIN_MESSAGE);
        
        if(input != null && !input.trim().isEmpty())
        {
            System.out.println("Goal for " + nutrient + ": " + input); // Example of handling the input
            session.changeGoals(nutrient, input);
        }

        if(nutrient.equals("Calories")){
            nutrient = "energy";
        }
        String revisedNutrient = nutrient.replace(" ", "").toLowerCase();

        // This is where I should put the updated info...

        String query = "UPDATE nutritionTracker.nutrientGoals SET " + revisedNutrient + " = " + input + " WHERE userId = " + currentUser.getUserId() + ";";
        System.out.println(query);

        try
        {
            PreparedStatement preparedStatement = connectionToMySQL.prepareStatement(query);
            // Execute and retrieve result
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();

            if(rowsAffected > 0)
            {
                System.out.println("Updated successfully");
                JOptionPane.showMessageDialog(null, "Goal for " + nutrient + " successfully set to " + input + "!");
            }
            else
            {
                System.out.println("Failed to update info");// make a popup
                JOptionPane.showMessageDialog(null, "Failed to update information.");
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public User getCurrentUser() {
        return currentUser;
    }
    public Session getSession() {
        return session;
    }
}
