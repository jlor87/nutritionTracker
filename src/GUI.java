import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.sql.*;


public class GUI {
	JTextField loginUsernameField = new JTextField(25);
    JPasswordField loginPasswordField = new JPasswordField(25);
    JTextField createUsernameField = new JTextField(25);
    JPasswordField createPasswordField = new JPasswordField(25);
    JTextField searchField = new JTextField(20);
    
    JTextArea outputArea = new JTextArea(10, 30);
    JScrollPane scrollPane = new JScrollPane(outputArea);
    
    ChoiceHandler cHandler = new ChoiceHandler(loginUsernameField, loginPasswordField);
	public String yourChoice;
	public String searchItem;
	
	JFrame titleWindow = new JFrame();  
	JFrame loginWindow = new JFrame();
	JFrame createWindow = new JFrame();
	JFrame mainWindow = new JFrame();
	JFrame searchWindow = new JFrame();
	JFrame setGoalsWindow = new JFrame();
	//these variables are at the class level because they will have to be turned on/off (set visible/non visible) depending on where the user is in the application

	private final String userFile = "users.txt";   //text file for usernames and passwords

	private final Connection connectionToMySQL;
    private Session session;
	private int retrievedUserId = -1;

	// Constructor
	public GUI(Connection connectionToMySQL){
		this.connectionToMySQL = connectionToMySQL;
	}
	
	public void setSession(Session session) {
        this.session = session;
    }

    
	public void makeTitleScreen() {
		
	JPanel titlePanel = new JPanel();
	JPanel buttonPanel = new JPanel();
	
	titleWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	titleWindow.setSize(800, 600);
	titleWindow.setLayout(new BorderLayout());
	titleWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
	
        JLabel titleLabel = new JLabel();
        titleLabel.setText("Nutrition Tracker  CEN4010  Josh Lor, Sam Gray, Haivan Benjamin");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
	    titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
	    titleLabel.setForeground(Color.YELLOW);
	    
	
	    
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(Color.DARK_GRAY);

        // Create buttons
        JButton startButton = new JButton("Start");
        startButton.addActionListener(cHandler);
		startButton.setActionCommand("goLoginScreen");
        JButton exitButton = new JButton("Exit");

        // Add buttons to the panel
        buttonPanel.add(startButton);
        buttonPanel.add(exitButton);

        // Set background for the title area
        titlePanel.setBackground(Color.BLUE);
        titlePanel.add(titleLabel);

        // Add the title label and button panel to the window
        titleWindow.add(titlePanel, BorderLayout.NORTH);
        titleWindow.add(buttonPanel, BorderLayout.SOUTH);

        // Center the window on screen
        titleWindow.setLocationRelativeTo(null);
        titleWindow.setVisible(true);
        // Make the window visible
        
        exitButton.addActionListener(e -> System.exit(0));
	}
	
	public void displayTitleScreen() {
		titleWindow.setVisible(true);
		}
	
	public void removeTitleScreen() {
		titleWindow.setVisible(false);
	}
	
	
    public void makeLoginScreen() {
    	
    	JPanel loginPanel = new JPanel();
    	JLabel loginTitleLabel = new JLabel();
    	
    	loginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	loginWindow.setSize(800, 600);
    	loginWindow.setLayout(new BorderLayout());
    	loginWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
    	
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

    	// Login button
    	gbc.gridx = 0;
    	gbc.gridy = 2;
    	gbc.gridwidth = 1;  
    	gbc.anchor = GridBagConstraints.CENTER;
    	
    	JButton loginButton = new JButton();
    	loginButton.setText("Login");
    	loginButton.addActionListener(cHandler);
    	loginButton.setActionCommand("tryToLogin");
    	loginPanel.add(loginButton, gbc);
    	
    	
    	gbc.gridx = 1;
    	gbc.gridy = 2;
    	gbc.gridwidth = 1;  
    	gbc.anchor = GridBagConstraints.WEST;
    	
    	JButton createButton = new JButton();
    	createButton.setText("Create");
    	createButton.addActionListener(cHandler);
    	createButton.setActionCommand("createAccount");
    	loginPanel.add(createButton, gbc);
    	
    	loginTitleLabel.setText("Login or Create Account");
    	loginTitleLabel.setHorizontalAlignment(JLabel.CENTER);
    	loginTitleLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
    	loginTitleLabel.setForeground(Color.BLUE);
    	
    	loginWindow.add(loginTitleLabel, BorderLayout.NORTH);
    	loginWindow.add(loginPanel, BorderLayout.CENTER);
    	
    	
    	loginPanel.setVisible(true);
    	loginTitleLabel.setVisible(true);
    	loginWindow.setVisible(false);
    }
    	
    	
    
    
    
    
    public void displayLoginScreen() {
    	loginWindow.setVisible(true);
    	}
    	
    public void removeLoginScreen() {
    	loginWindow.setVisible(false);
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
    
    
public void displayCreateScreen() {
	createWindow.setVisible(true);
	}
	
public void removeCreateScreen() {
	createWindow.setVisible(false);
	}
    
    
    
public void makeMainScreen() {
    // Main window setup
    mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainWindow.setSize(800, 600);
    mainWindow.setLayout(new BorderLayout());
    mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);

    // Title label at the top
    JLabel mainLabel = new JLabel("Main Navigation");
    mainLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
    mainLabel.setHorizontalAlignment(JLabel.CENTER);
    mainWindow.add(mainLabel, BorderLayout.NORTH);

    // Panel for buttons with GridLayout
    JPanel mainbuttonPanel = new JPanel();
    mainbuttonPanel.setLayout(new GridLayout(8, 1, 10, 10));  // 8 rows, 1 column, spacing of 10px

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
    	    button.setFont(new Font("Arial", Font.PLAIN, 18));

    	    // Set ActionListener and ActionCommand for each button
    	    button.addActionListener(cHandler);
    	    button.setActionCommand(label.replace(" ", "").toLowerCase());

    	    mainbuttonPanel.add(button);
    	}

    JPanel wrapperPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 300));
    wrapperPanel.add(mainbuttonPanel);
  
    
    // Add the button panel to the center of the main window
    mainWindow.add(wrapperPanel, BorderLayout.CENTER);

    // Display the main window
    mainWindow.setLocationRelativeTo(null);  // Center window on screen
    mainWindow.setVisible(false);
}
    



public void displayMainScreen() {
	mainWindow.setVisible(true);
	}
	
public void removeMainScreen() {
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

public void displaySearchScreen() {
	searchWindow.setVisible(true);
	}
	
public void removeSearchScreen() {
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
    String[] macronutrients = {"Water", "Calories", "Carbohydrates", "Protein", "Monounsaturated Fat",
                               "Polyunsaturated Fat", "Saturated Fat", "Fiber"};
    for (String item : macronutrients) {
        JButton button = new JButton(item);
        button.addActionListener(e -> showInputDialog(item)); // Show dialog on click
        macronutrientsPanel.add(button);
    }

    // Vitamins Panel
    JPanel vitaminsPanel = new JPanel(new GridLayout(0, 1));
    vitaminsPanel.setBorder(BorderFactory.createTitledBorder("Vitamins"));
    String[] vitamins = {"Vitamin A", "Vitamin B1", "Vitamin B2", "Vitamin B3", "Vitamin B5", "Vitamin B6",
                         "Vitamin B7", "Vitamin B9", "Vitamin B12", "Vitamin C", "Vitamin D", "Vitamin E", "Vitamin K"};
    for (String item : vitamins) {
        JButton button = new JButton(item);
        button.addActionListener(e -> showInputDialog(item)); // Show dialog on click
        vitaminsPanel.add(button);
    }

    // Minerals Panel
    JPanel mineralsPanel = new JPanel(new GridLayout(0, 1));
    mineralsPanel.setBorder(BorderFactory.createTitledBorder("Minerals"));
    String[] minerals = {"Calcium", "Chloride", "Choline", "Chromium", "Copper", "Fluoride", "Iodine",
                         "Iron", "Magnesium", "Manganese", "Molybdenum", "Phosphorus", "Potassium", "Selenium", "Sodium", "Zinc"};
    for (String item : minerals) {
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


private void showInputDialog(String nutrient) {
    String input = JOptionPane.showInputDialog(setGoalsWindow, "Enter your goal for " + nutrient + ":", "Set Goal", JOptionPane.PLAIN_MESSAGE);
    if (input != null && !input.trim().isEmpty()) {
        System.out.println("Goal for " + nutrient + ": " + input); // Example of handling the input
        // You could save or process the input here as needed
    }
}



public void displaySetGoalsScreen() {
	setGoalsWindow.setVisible(true);
	}
	
public void removeSetGoalsScreen() {
	setGoalsWindow.setVisible(false);
	}


	
    public class ChoiceHandler implements ActionListener {
        private JTextField usernameField;
        private JPasswordField passwordField;
        private String yourChoice;

        // Constructor to initialize fields
        public ChoiceHandler(JTextField usernameField, JPasswordField passwordField) {
            this.usernameField = usernameField;
            this.passwordField = passwordField;
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            yourChoice = event.getActionCommand();

            switch (yourChoice) {
                case "goLoginScreen":
                    removeTitleScreen();
                    displayLoginScreen();
                
                    break;

                case "tryToLogin":
                    // Retrieve the text from the fields
                    String username = usernameField.getText();
                    char[] password = passwordField.getPassword();
                    String loginPasswordString = new String(password);
                   
                    if (checkCredentials(username, loginPasswordString)) {
                        System.out.println("Successful login");
                        removeLoginScreen();
                        displayMainScreen();
                        } else {
                    	JOptionPane.showMessageDialog(null, "ERROR: invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
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
                    if (createAccount(createUsername, passwordString)) {
                        System.out.println("Account created successfully");
                        JOptionPane.showMessageDialog(null, "account created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        System.out.println("Username already exists");
                        JOptionPane.showMessageDialog(null, "ERROR: user already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case "searchforfooditem": 
                	displaySearchScreen();
                    removeMainScreen();
                	break;
                case "goMainScreen":
                		removeSearchScreen();
                		removeSetGoalsScreen();
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
            }
        }
		
		
		}




    private boolean createAccount(String username, String password) {
		String query = "INSERT INTO users (username, password, weight, height, sex, exercise) VALUES(?, ?, ?, ?, ?, ?)";

		try{
			PreparedStatement preparedStatement = connectionToMySQL.prepareStatement(query); // Using prepared statements as good practice against SQL injections
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			preparedStatement.setInt(3, 150);
			preparedStatement.setInt(4, 69);
			preparedStatement.setString(5, "M");
			preparedStatement.setString(6, "average");

			// Execute and retrieve result
			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("User created successfully!");
				return true;
			} else {
				System.out.println("Failed to create user!");
				return false;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}

		return false; // Error occurred
    }
    
    
    
    
    
    
    
    private boolean checkCredentials(String username, String password) {
		ResultSet resultSet = null;
		String query = "SELECT password, userId FROM users WHERE username = ?";
		String retrievedPassword = "";

		try{
			PreparedStatement preparedStatement = connectionToMySQL.prepareStatement(query); // Using prepared statements as good practice against SQL injections
			preparedStatement.setString(1, username);

			// Execute SQL query and retrieve the result
			resultSet = preparedStatement.executeQuery();

			// Process the result
			while (resultSet.next()) {
				retrievedPassword = resultSet.getString("password");
				retrievedUserId = resultSet.getInt("userId");
			}

			if(retrievedPassword.equals(password)){
				return true;
			}
			else{
				return false;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}

		return false; // Error occurred
    }

	public int getRetrievedUserId() {
		return retrievedUserId;
	}
}
