import java.io.IOException;
import java.lang.reflect.*;
import java.util.Scanner; 

//api related imports
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


import com.google.gson.Gson;     //have to download and add json jar file gson-2.8.8.jar to the project build path to use
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;


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
            System.out.println("3. Alter or set user data (height, weight, sex, average daily exercise).");
            System.out.println("4. Display status of current nutrient consumption for the day.\n");

            userChoice = scanner.nextLine();  // Get user input

            switch (userChoice) {
                case "1" ->
                    // User wants to search for food/drink item
                        mainInstance.getFoodInput(newUser, scanner);
                case "2" ->
                    // User wants to set caloric/nutritional goals for the day
                        mainInstance.setGoalData(newUser);
                case "3" ->
                    // User wants to alter user data
                        mainInstance.alterUserData(newUser, scanner);
                case "4" ->
                        mainInstance.outputCurrentConsumption(newUser);
                case "0" ->
                    // User closing app
                        System.out.println("\nClosing the application");
                default -> System.out.println("Not a valid option");
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
    
    public void getFoodInput(User user, Scanner scanner){
    	System.out.println("\nInput food item:");
    	String userInput;
    	userInput = scanner.nextLine();
    	sendAPIRequest(user, userInput); //calling api with user input
    }

    public void sendAPIRequest(User user, String query) {
        // API key and base URL
        final String API_KEY = "OUhmaL4b1NLdkO286efEMTYDBHWw7jfj8TIoyxNm";  
        final String BASE_URL = "https://api.nal.usda.gov/fdc/v1/foods/search";

        // Construct the full URL with the query and API key
        String url = BASE_URL + "?api_key=" + API_KEY + "&query=" + query.replace(" ", "%20");

        // Create an HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // Create a GET request with the constructed URL
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        // Send the request and get the response
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Parse the response JSON using Gson
            Gson gson = new Gson();
            JsonObject responseObject = gson.fromJson(response.body(), JsonObject.class);
            JsonArray foods = responseObject.getAsJsonArray("foods");

            // Check if we have results
            if (foods.size() > 0) {
                // Extract details of the first food item
                JsonObject firstFood = foods.get(0).getAsJsonObject();
                String description = firstFood.get("description").getAsString();
                JsonArray nutrients = firstFood.getAsJsonArray("foodNutrients");

                // Print the food description
                System.out.println("Food: " + description);

                // Loop through and print nutrients
                for (int i = 0; i < nutrients.size(); i++) {
                    JsonObject nutrient = nutrients.get(i).getAsJsonObject();
                    String nutrientName = nutrient.get("nutrientName").getAsString();
                    double amount = nutrient.get("value").getAsDouble();
                    String unitName = nutrient.get("unitName").getAsString();
                    System.out.println(nutrientName + ": " + amount + " " + unitName);
                }

                // Assuming the user has eaten the food, now update their daily consumption
                updateUserConsumption(nutrients, user);
            } else {
                System.out.println("No foods found for the query: " + query);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateUserConsumption(JsonArray nutrients, User currentUser){
        Method[] userMethods = Utility.getMethods();
        int length = userMethods.length;

        for (int i = 0; i < nutrients.size(); i++) {
            JsonObject nutrient = nutrients.get(i).getAsJsonObject();
            String nutrientName = nutrient.get("nutrientName").getAsString();
            nutrientName = nutrientName.replaceAll(" ", "").toLowerCase();
            // System.out.println("nutrientName (before): " + nutrientName);

            // Handles parsing the special case of fatty acids
            if(nutrientName.startsWith("fattyacids")){
                nutrientName = nutrientName.substring(16);
            }

            int commaIndex = nutrientName.indexOf(",");
            if(commaIndex != -1){
                nutrientName = nutrientName.substring(0, commaIndex);
            }
            // System.out.println("nutrientName: " + nutrientName); // Test statement

            double amount = nutrient.get("value").getAsDouble();

            // Use the nutrientName to call the correct setter in the User class, i.e. "Vitamin A" should call "setVitaminA"
            for(int j = 0; j < length; j++){
                String methodName = userMethods[j].getName().toLowerCase();

                if(methodName.contains("set" + nutrientName)){
                    // System.out.println("methodName: " + methodName); // Test statement
                    try{
                        // System.out.println("Found!");
                        userMethods[j].invoke(currentUser, 1, amount); // Update user's consumed nutrients
                        break;
                    } catch(Exception e){
                        System.out.println(e);
                    }
                }
            }
        }
    }

    public void outputCurrentConsumption(User user){
        user.printProgress();
    }

    public void computeRemainingDietaryNeeds(){
        // System does math to subtract nutritional amount from user's goals
    }

    public void displayResultsToUser(){
        // System displays the remaining amount of nutrients/vitamins leftover to the user
    }

}


