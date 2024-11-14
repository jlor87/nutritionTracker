import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * This class makes API requests to the USDA's database of food https://api.nal.usda.gov
 * The class is responsible for handling all interactions with the internet
 * 
 */
public class API {


    private User currentUser;
    private JsonArray nutrients = new JsonArray();
    private HashMap<String, JsonArray> allNutrientsFromSearchResult = new HashMap<>();
    private Connection connectionToMySQL;
    private String currentFoodName;


    /**
     * Each API instance has a user attached for food logging purposes
     * @param user the current instance of user that is making a request
     */
    public API(User user){
        this.currentUser = user;
        this.connectionToMySQL = Main.getConnection();
    }

    // Class functions
    public LinkedList<String> sendAPIRequest(String query) {
        System.out.printf(".........Retrieving %s nutrition information.........\n", query);
        final String API_KEY = "OUhmaL4b1NLdkO286efEMTYDBHWw7jfj8TIoyxNm"; // API key allows access to the API
        final String BASE_URL = "https://api.nal.usda.gov/fdc/v1/foods/search";

        String url = BASE_URL + "?api_key=" + API_KEY + "&query=" + query.replace(" ", "%20"); // Construct the full URL

        HttpClient client = HttpClient.newHttpClient(); // Create an HttpClient

        // Create a GET request with the constructed URL
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

        LinkedList<String> searchResults = new LinkedList<>();
        allNutrientsFromSearchResult.clear(); // Reset for each new search result

        try {
            // Send the request and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Parse the response JSON using Gson
            Gson gson = new Gson();
            JsonObject responseObject = gson.fromJson(response.body(), JsonObject.class);
            JsonArray foods = responseObject.getAsJsonArray("foods");

            // Check if we have results
            if (foods.size() > 0) {

                for(JsonElement currentFoodElement : foods){
                    // StringBuilder to accumulate the output
                    StringBuilder output = new StringBuilder();
                    output.append(".........Retrieving ").append(query).append(" nutrition information.........\n");

                    // Extract details of the current food item
                    JsonObject currentFoodJSON = currentFoodElement.getAsJsonObject();
                    String description = currentFoodJSON.get("description").getAsString();
                    System.out.println("Food description is: " + description);
                    this.currentFoodName = description;
                    this.nutrients = currentFoodJSON.getAsJsonArray("foodNutrients");
                    if(allNutrientsFromSearchResult.get(currentFoodName) == null) {
                        allNutrientsFromSearchResult.put(currentFoodName, nutrients);

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
                        output.append(".........").append(query).append(" nutrition information complete.........\n");
                        output.append("CLICK THE 'ADD' BUTTON TO ADD THIS FOOD ITEM TO YOUR CONSUMPTION.\n");
                        searchResults.add(output.toString());
                    }
                }
            } else {
                searchResults.add("No foods found for the query: " + query + "\n");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            searchResults.add("An error occurred while retrieving nutrition information.\n");
        }

        // Return the output as a string
        return searchResults;
    }

    // Getters
    public JsonArray getNutrients(String foodName) {
        System.out.println("foodName to be retrieved");
        JsonArray jsonArray = allNutrientsFromSearchResult.get(foodName);
        if(jsonArray != null) {
            System.out.println("nutrients for that food to be returned" + jsonArray.toString());
        }
        else{
            System.out.println("Error! Could not find the food and its nutrients!");
        }
        return allNutrientsFromSearchResult.get(foodName);
    }
}
