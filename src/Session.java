import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;


// The session class is responsible for actually running the program via the startSession() function
public class Session {
    String userChoice = "dummyValue";
    User currentUser;
    public Session(User user){
        this.currentUser = user;
    }


    // startSession() is the workhorse of the app (due to the do-while loop)
    public void startSession(){
        UserSettings userSettings = new UserSettings(currentUser);
        Print print = new Print(currentUser);
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
                        getFoodInput(scanner);
                case "2" ->
                    // User wants to set caloric/nutritional goals for the day
                        setGoalData();
                case "3" ->
                    // User wants to alter user data
                        userSettings.alterUserData(scanner);
                case "4" ->
                    // User wants to check current daily progress
                        print.outputCurrentConsumption();
                case "0" ->
                    // User closing app
                        System.out.println("\nClosing the application");
                default -> System.out.println("Not a valid option");
            }

        } while (!userChoice.equals("0"));

        scanner.close();
    }

    // Class helper functions to perform parts of a use case
    public void setGoalData(){
        // User wants to set caloric/nutritonal goals for the day
        System.out.println("\nTo be implemented");
    }
    public void getFoodInput(Scanner scanner){
        System.out.println("\nInput food item:");
        String userInput;
        userInput = scanner.nextLine();
        sendAPIRequest(currentUser, userInput); //calling api with user input
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

}
