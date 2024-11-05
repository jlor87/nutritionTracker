import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This class makes API requests to the USDA's database of food https://api.nal.usda.gov
 * The class is responsible for handling all interactions with the internet
 * 
 */
public class API {

    private User currentUser;
    private JsonArray nutrients = new JsonArray();
    private Method[] userMethods;
    private String nutrientName;

    private Connection connectionToMySQL;
    private String currentFoodName;
    private double amount;

    /**
     * Each API instance has a user attached for food logging purposes
     * @param user the current instance of user that is making a request
     */
    public API(User user, Connection connectionToMySQL){
        this.currentUser = user;
        this.connectionToMySQL = connectionToMySQL;
    }

    // Class functions
    public String sendAPIRequest(String query) {
        System.out.printf(".........Retrieving %s nutrition information.........\n", query);
        final String API_KEY = "OUhmaL4b1NLdkO286efEMTYDBHWw7jfj8TIoyxNm"; // API key allows access to the API
        final String BASE_URL = "https://api.nal.usda.gov/fdc/v1/foods/search";

        String url = BASE_URL + "?api_key=" + API_KEY + "&query=" + query.replace(" ", "%20"); // Construct the full URL

        HttpClient client = HttpClient.newHttpClient(); // Create an HttpClient

        // Create a GET request with the constructed URL
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

        // StringBuilder to accumulate the output
        StringBuilder output = new StringBuilder();
        output.append(".........Retrieving ").append(query).append(" nutrition information.........\n");

        try {
            // Send the request and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Parse the response JSON using Gson
            Gson gson = new Gson();
            JsonObject responseObject = gson.fromJson(response.body(), JsonObject.class);
            JsonArray foods = responseObject.getAsJsonArray("foods");

            // Check if we have results
            if (foods.size() > 0) {
                // Extract details of the first food item
                JsonObject firstFood = foods.get(0).getAsJsonObject();
                String description = firstFood.get("description").getAsString();
                this.currentFoodName = description;
                this.nutrients = firstFood.getAsJsonArray("foodNutrients");

                // Append the food description
                output.append("Food: ").append(description).append("\n");

                // Loop through and append each nutrient
                for (int i = 0; i < nutrients.size(); i++) {
                    JsonObject nutrient = nutrients.get(i).getAsJsonObject();
                    String nutrientName = nutrient.get("nutrientName").getAsString();
                    double amount = nutrient.get("value").getAsDouble();
                    String unitName = nutrient.get("unitName").getAsString();
                    output.append(nutrientName).append(": ").append(amount).append(" ").append(unitName).append("\n");
                }
            } else {
                output.append("No foods found for the query: ").append(query).append("\n");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            output.append("An error occurred while retrieving nutrition information.\n");
        }

        output.append(".........").append(query).append(" nutrition information complete.........\n");
        
        // Return the output as a string
        return output.toString();
    }
    
    /**
     * The purpose of this class is to update the nutrition values of the user who is adding food intake information
     * @param nutrients an array of nutrient information being passed received from the API
     * @param currentUser the current user object making the call
     */

    // Getters

    public String getCurrentFoodName() {
        return currentFoodName;
    }
    public JsonArray getNutrients() {
        return nutrients;
    }
    public Method[] getUserMethods() {
        return userMethods;
    }
    public String getNutrientName() {
        return nutrientName;
    }
    public double getAmount() {
        return amount;
    }
    public User getCurrentUser() {
        return currentUser;
    }
}
