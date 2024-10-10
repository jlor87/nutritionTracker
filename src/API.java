import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// Everything dealing with API usage is here
public class API {

    private User currentUser;
    private Gson gson;
    private JsonObject responseObject;
    private JsonArray foods;
    private JsonObject firstFood;
    private JsonArray nutrients;
    private JsonObject nutrient;
    private Method[] userMethods;
    private String nutrientName;
    private double amount;

    // Constructor
    public API(User user){
        this.currentUser = user;
    }

    // Class functions
    public void sendAPIRequest(String query) {
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
            gson = new Gson();
            responseObject = gson.fromJson(response.body(), JsonObject.class);
            foods = responseObject.getAsJsonArray("foods");

            // Check if we have results
            if (foods.size() > 0) {
                // Extract details of the first food item
                firstFood = foods.get(0).getAsJsonObject();
                String description = firstFood.get("description").getAsString();
                nutrients = firstFood.getAsJsonArray("foodNutrients");

                // Print the food description
                System.out.println("Food: " + description);

                // Loop through and print nutrients
                for (int i = 0; i < nutrients.size(); i++) {
                    nutrient = nutrients.get(i).getAsJsonObject();
                    String nutrientName = nutrient.get("nutrientName").getAsString();
                    double amount = nutrient.get("value").getAsDouble();
                    String unitName = nutrient.get("unitName").getAsString();
                    System.out.println(nutrientName + ": " + amount + " " + unitName);
                }

            } else {
                System.out.println("No foods found for the query: " + query);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
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
