import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * This class makes API requests to the USDA's database of food https://api.nal.usda.gov
 * The class is responsible for handling all interactions with the internet
 * 
 */
public class API {

    private User currentUser;
    private Gson gson;
    private JsonObject responseObject;
    private JsonArray foods;
    private JsonObject firstFood;
    private JsonArray nutrients = new JsonArray();
    private JsonObject nutrient;
    private Method[] userMethods;
    private String nutrientName;
    private double amount;

    /**
     * Each API instance has a user attached for food logging purposes
     * @param user the current instance of user that is making a request
     */
    public API(User user){
        this.currentUser = user;
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
    public void updateUserConsumption(JsonArray nutrients, User currentUser){
        userMethods = Utility.getMethods();
        int length = userMethods.length;
        int length2 = nutrients.size();

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
                        break;
                    } catch(Exception e){
                        System.out.println(e);
                    }
                }
            }
        }
    }

    // Getters
    public Gson getGson(){
        return gson;
    }
    public JsonObject getResponseObject() {
        return responseObject;
    }
    public JsonArray getFoods() {
        return foods;
    }
    public JsonObject getFirstFood() {
        return firstFood;
    }
    public JsonArray getNutrients() {
        return nutrients;
    }
    public JsonObject getNutrient() {
        return nutrient;
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
