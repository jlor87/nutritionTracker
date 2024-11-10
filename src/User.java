import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * All user data (dietary goals, preferences, user info, etc.) is stored here **? separate class for preferences?** 
 * 
 */
public class User {

    // Assigning new user with dummy values
    private LinkedList<Food> foodCatalog = new LinkedList<>();
    private String dailyFoodsConsumed = "";
    private String username = "";
    private int userId = 0;
    private double userWeight = 0.00; //lbs
    private int userHeight = 0; //inches
    private char userSex = '0'; //m or f
    private String exerciseLevel = "not set"; //none, light, moderate, hard, extreme (different levels to be defined in readME)
    private Connection connectionToMySQL;

    // Index 0 refers to the user's dietary goal. Index 1 refers to the amount consumed for the day.

    // ***** Macros *****
    private double[] water = new double[2]; // liters
    private double[] energy = new double[2]; // kCal
    private double[] carbohydrate = new double[2]; // grams
    private double[] monounsaturatedFat = new double[2]; // grams
    private double[] saturatedFat = new double[2]; // grams
    private double[] polyunsaturatedFat = new double[2]; // grams
    private double[] protein = new double[2]; // grams
    private double[] fiber = new double[2]; // grams

    // ***** Vitamins *****
    private double[] vitaminA = new double[2]; // micrograms
    private double[] vitaminB1Thiamine = new double[2]; // milligrams
    private double[] vitaminB2Riboflavin = new double[2]; // milligrams
    private double[] vitaminB3Niacin = new double[2]; // miligrams
    private double[] vitaminB5PantothenicAcid = new double[2]; // milligrams
    private double[] vitaminB6Pyridoxine = new double[2]; // milligrams
    private double[] vitaminB7Biotin = new double[2]; // micrograms
    private double[] vitaminB9Folate = new double[2]; // micrograms
    private double[] vitaminB12Cyanocobalamin = new double[2]; // micrograms
    private double[] vitaminC = new double[2]; // milligrams
    private double[] vitaminD = new double[2]; // micrograms
    private double[] vitaminE = new double[2]; // milligrams
    private double[] vitaminK = new double[2]; // micrograms

    // ***** Minerals *****
    private double[] choline = new double[2]; // milligrams
    private double[] calcium = new double[2]; // milligrams
    private double[] chloride = new double[2]; // grams
    private double[] chromium = new double[2]; // micrograms
    private double[] copper = new double[2]; // micrograms
    private double[] fluoride = new double[2]; // milligrams
    private double[] iodine = new double[2]; // micrograms
    private double[] iron = new double[2];  // milligrams
    private double[] magnesium = new double[2]; // milligrams
    private double[] manganese = new double[2]; // milligrams
    private double[] molybdenum = new double[2]; // micrograms
    private double[] phosphorus = new double[2]; // milligrams
    private double[] potassium = new double[2]; // milligrams
    private double[] selenium = new double[2]; // micrograms
    private double[] sodium = new double[2]; // milligrams
    private double[] zinc = new double[2]; // milligrams

    /**
     * Constructor
     */
    public User (int userId){
        this.userId = userId;
        this.connectionToMySQL = Main.getConnection(); // needed to grab information the database related to the user

        LocalDate currentDate = LocalDate.now();
        int currentDay = currentDate.getDayOfMonth();
        int currentMonth = currentDate.getMonth().getValue();
        int currentYear = currentDate.getYear();

        // Retrieve food diary from the database for today
        ResultSet resultSet = null;
        String query = "SELECT foodsConsumedThisDay FROM foodcatalogs WHERE userId = ? AND yearNum = ? AND monthNum =? AND dayNum =?";
        String retrievedFoodDiary = "";

        try
        {
            PreparedStatement preparedStatement = connectionToMySQL.prepareStatement(query); // Using prepared statements as good practice against SQL injections
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, currentYear);
            preparedStatement.setInt(3, currentMonth);
            preparedStatement.setInt(4, currentDay);

            // Execute SQL query and retrieve the result
            resultSet = preparedStatement.executeQuery();

            // Process the result
            while(resultSet.next())
            {
                retrievedFoodDiary = resultSet.getString("foodsConsumedThisDay");
                dailyFoodsConsumed = retrievedFoodDiary;
                System.out.println("The retrieved food diary is: " + retrievedFoodDiary);
                StringTokenizer st = new StringTokenizer(retrievedFoodDiary);
                String nameOfFood = "";
                while(st.hasMoreTokens()){
                    nameOfFood = st.nextToken();
                    Food foodItem = new Food(nameOfFood);
                    foodCatalog.add(foodItem);
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        // Every user object will already have the database ready for user info retrieval
        updateAllFromDatabase();
    }

    // Getters and setters for user data (weight, height, sex, exercise level)
    public LinkedList<Food> getFoodCatalog(){
        return foodCatalog;
    }
    public int getUserId() {
        return userId;
    }
    public double weightGetter() {
    	return this.userWeight;
    }
    public void weightSetter(double weight) {
    	this.userWeight = weight;
    }
    public int heightGetter() {
    	return this.userHeight;
    }
    public void heightSetter(int height) {
    	this.userHeight = height;
    }
    public char sexGetter() {
    	return this.userSex;
    }
    public void sexSetter(char sex) {
    	this.userSex = sex;
    }
    public String exerciseGetter() {
    	return this.exerciseLevel;
    }
    public void exerciseSetter(String exercise) {
    	this.exerciseLevel = exercise;
    }

    public void nameSetter(String name){
        this.username = name;
    }

    public String nameGetter(){
        return this.username;
    }

    // One getter and setter for every vitamin/nutrient.
    // An option of 0 refers to the daily goal; an option of 1 refers to the daily amount consumed.
    public double getWater(int option){
        return water[option];
    }
    public void setWater(int option, double value){
        if(option == 0){ // Change daily goal
            water[option] = value;
        }
        else if(option == 1){ // Update amount consumed
            water[option] = water[option] + value;
        }
    }
    public double getEnergy(int option){
        return energy[option];
    }
    public void setEnergy(int option, double value){
        if(option == 0){ // Change daily goal
            energy[option] = value;
        }
        else if(option == 1){ // Update amount consumed
            energy[option] = energy[option] + value;
        }
    }
    public double getCarbohydrate(int option){
        return carbohydrate[option];
    }
    public void setCarbohyrate(int option, double value){
        if(option == 0){ // Change daily goal
            carbohydrate[option] = value;
        }
        else if(option == 1){ // Update amount consumed
            carbohydrate[option] = carbohydrate[option] + value;
        }
    }
    public double getFiber(int option){
        return fiber[option];
    }
    public void setFiber(int option, double value){
        if(option == 0){ // Change daily goal
            fiber[option] = value;
        }
        else if(option == 1){ // Update amount consumed
            fiber[option] = fiber[option] + value;
        }
    }
    public double getMonounsaturatedFat(int option){
        return monounsaturatedFat[option];
    }
    public void setMonounsaturatedFat(int option, double value){
        if(option == 0){ // Change daily goal
            monounsaturatedFat[option] = value;
        }
        else if(option == 1){ // Update amount consumed
            monounsaturatedFat[option] = monounsaturatedFat[option] + value;
        }
    }
    public double getSaturatedFat(int option){
        return saturatedFat[option];
    }
    public void setSaturatedFat(int option, double value){
        if(option == 0){ // Change daily goal
            saturatedFat[option] = value;
        }
        else if(option == 1){ // Update amount consumed
            saturatedFat[option] = saturatedFat[option] + value;
        }
    }
    public double getPolyunsaturatedFat(int option){
        return polyunsaturatedFat[option];
    }
    public void setPolyunsaturatedFat(int option, double value){
        if(option == 0){ // Change daily goal
            polyunsaturatedFat[option] = value;
        }
        else if(option == 1){ // Update amount consumed
            polyunsaturatedFat[option] = polyunsaturatedFat[option] + value;
        }
    }
    public double getProtein(int option){
        return protein[option];
    }
    public void setProtein(int option, double value){
        if(option == 0){ // Change daily goal
            protein[option] = value;
        }
        else if(option == 1){ // Update amount consumed
            protein[option] = protein[option] + value;
        }
    }
    public double getVitaminA(int option){
        return vitaminA[option];
    }
    public void setVitaminA(int option, double value){
        if(option == 0){ // Change daily goal
            vitaminA[option] = value;
        }
        else if(option == 1){ // Update amount consumed
            vitaminA[option] = vitaminA[option] + value;
        }
    }
    public double getVitaminC(int option){
        return vitaminC[option];
    }
    public void setVitaminC(int option, double value){
        if(option == 0){ // Change daily goal
            vitaminC[option] = value;
        }
        else if(option == 1){ // Update amount consumed
            vitaminC[option] = vitaminC[option] + value;
        }
    }
    public double getVitaminD(int option){
        return vitaminD[option];
    }
    public void setVitaminD(int option, double value){
        if(option == 0){ // Change daily goal
            vitaminD[option] = value;
        }
        else if(option == 1){ // Update amount consumed
            vitaminD[option] = vitaminD[option] + value;
        }
    }
    public double getVitaminE(int option){
        return vitaminE[option];
    }
    public void setVitaminE(int option, double value){
        if(option == 0){ // Change daily goal
            vitaminE[option] = value;
        }
        else if(option == 1){ // Update amount consumed
            vitaminE[option] = vitaminE[option] + value;
        }
    }
    public double getVitaminK(int option){
        return vitaminK[option];
    }
    public void setVitaminK(int option, double value){
        if(option == 0){ // Change daily goal
            vitaminK[option] = value;
        }
        else if(option == 1){ // Update amount consumed
            vitaminK[option] = vitaminK[option] + value;
        }
    }
    public double getVitaminB1Thiamine(int option){
        return vitaminB1Thiamine[option];
    }
    public void setVitaminB1Thiamine(int option, double value){
        if(option == 0){ // Change daily goal
            vitaminB1Thiamine[option] = value;
        }
        else if(option == 1){ // Update amount consumed
            vitaminB1Thiamine[option] = vitaminB1Thiamine[option] + value;
        }
    }
    public double getVitaminB2Riboflavin(int option){
        return vitaminB2Riboflavin[option];
    }
    public void setVitaminB2Riboflavin(int option, double value){
        if(option == 0){ // Change daily goal
            vitaminB2Riboflavin[option] = value;
        }
        else if(option == 1){ // Update amount consumed
            vitaminB2Riboflavin[option] = vitaminB2Riboflavin[option] + value;
        }
    }
    public double getVitaminB3Niacin(int option){
        return vitaminB3Niacin[option];
    }
    public void setVitaminB3Niacin(int option, double value){
        if(option == 0){ // Change daily goal
            vitaminB3Niacin[option] = value;
        }
        else if(option == 1){ // Update amount consumed
            vitaminB3Niacin[option] = vitaminB3Niacin[option] + value;
        }
    }
    public double getVitaminB5PantothenicAcid(int option){
        return vitaminB5PantothenicAcid[option];
    }
    public void setVitaminB5PantothenicAcid(int option, double value){
        if(option == 0){ // Change daily goal
            vitaminB5PantothenicAcid[option] = value;
        }
        else if(option == 1){ // Update amount consumed
            vitaminB5PantothenicAcid[option] = vitaminB5PantothenicAcid[option] + value;
        }
    }
    public double getVitaminB6Pyrodoxine(int option){
        return vitaminB6Pyridoxine[option];
    }
    public void setVitaminB6Pyridoxine(int option, double value){
        if(option == 0){ // Change daily goal
            vitaminB6Pyridoxine[option] = value;
        }
        else if(option == 1){ // Update amount consumed
            vitaminB6Pyridoxine[option] = vitaminB6Pyridoxine[option] + value;
        }
    }
    public double getVitaminB7Biotin(int option){
        return vitaminB7Biotin[option];
    }
    public void setVitaminB7Biotin(int option, double value){
        if(option == 0){ // Change daily goal
            vitaminB7Biotin[option] = value;
        }
        else if(option == 1){ // Update amount consumed
            vitaminB7Biotin[option] = vitaminB7Biotin[option] + value;
        }
    }
    public double getVitaminB9Folate(int option){
        return vitaminB9Folate[option];
    }
    public void setVitaminB9Folate(int option, double value){
        if(option == 0){ // Change daily goal
            vitaminB9Folate[option] = value;
        }
        else if(option == 1){ // Update amount consumed
            vitaminB9Folate[option] = vitaminB9Folate[option] + value;
        }
    }
    public double getVitaminB12Cyanocobalamin(int option){
        return vitaminB12Cyanocobalamin[option];
    }
    public void setVitaminB12Cyanocobalamin(int option, double value){
        if(option == 0){ // Change daily goal
            vitaminB12Cyanocobalamin[option] = value;
        }
        else if(option == 1){ // Update amount consumed
            vitaminB12Cyanocobalamin[option] = vitaminB12Cyanocobalamin[option] + value;
        }
    }
    public double getCholine(int option){
        return choline[option];
    }
    public void setCholine(int option, double value){
        if(option == 0){ // Change daily goal
            choline[option] = value;
        }
        else if(option == 1){ // Update amount consumed
            choline[option] = choline[option] + value;
        }
    }
    public double getCalcium(int option){
        return calcium[option];
    }
    public void setCalcium(int option, double value){
        if(option == 0){ // Change daily goal
            calcium[option] = value;
        }
        else if(option == 1){ // Update amount consumed
            calcium[option] = calcium[option] + value;
        }
    }
    public double getChromium(int option){
        return chromium[option];
    }
    public void setChromium(int option, double value){
        if(option == 0){ // Change daily goal
            chromium[option] = value;
        }
        else if(option == 1){ // Update amount consumed
            chromium[option] = chromium[option] + value;
        }
    }
    public double getCopper(int option){
        return copper[option];
    }
    public void setCopper(int option, double value){
        if(option == 0){ // Change daily goal
            copper[option] = value;
        }
        else if(option == 1){ // Update amount consumed
            copper[option] = copper[option] + value;
        }
    }
    public double getFluoride(int option){
        return fluoride[option];
    }
    public void setFluoride(int option, double value){
        if(option == 0){ // Change daily goal
            fluoride[option] = value;
        }
        else if(option == 1){ // Update amount consumed
            fluoride[option] = fluoride[option] + value;
        }
    }
    public double getIodine(int option){
        return iodine[option];
    }
    public void setIodine(int option, double value){
        if(option == 0){ // Change daily goal
            iodine[option] = value;
        }
        else if(option == 1){ // Update amount consumed
            iodine[option] = iodine[option] + value;
        }
    }
    public double getIron(int option){
        return iron[option];
    }
    public void setIron(int option, double value){
        if(option == 0){ // Change daily goal
            iron[option] = value;
        }
        else if(option == 1){ // Update amount consumed
            iron[option] = iron[option] + value;
        }
    }
    public double getMagnesium(int option){
        return magnesium[option];
    }
    public void setMagnesium(int option, double value){
        if(option == 0){ // Change daily goal
            magnesium[option] = value;
        }
        else if(option == 1){ // Update amount consumed
            magnesium[option] = magnesium[option] + value;
        }
    }
    public double getManganese(int option) {
        return manganese[option];
    }
    public void setManganese(int option, double value) {
        if (option == 0) { // Change daily goal
            manganese[option] = value;
        } else if (option == 1) { // Update amount consumed
            manganese[option] += value;
        }
    }
    public double getMolybdenum(int option) {
        return molybdenum[option];
    }
    public void setMolybdenum(int option, double value) {
        if (option == 0) { // Change daily goal
            molybdenum[option] = value;
        } else if (option == 1) { // Update amount consumed
            molybdenum[option] += value;
        }
    }
    public double getPhosphorus(int option) {
        return phosphorus[option];
    }
    public void setPhosphorus(int option, double value) {
        if (option == 0) { // Change daily goal
            phosphorus[option] = value;
        } else if (option == 1) { // Update amount consumed
            phosphorus[option] += value;
        }
    }
    public double getSelenium(int option) {
        return selenium[option];
    }
    public void setSelenium(int option, double value) {
        if (option == 0) { // Change daily goal
            selenium[option] = value;
        } else if (option == 1) { // Update amount consumed
            selenium[option] += value;
        }
    }
    public double getZinc(int option) {
        return zinc[option];
    }
    public void setZinc(int option, double value) {
        if (option == 0) { // Change daily goal
            zinc[option] = value;
        } else if (option == 1) { // Update amount consumed
            zinc[option] += value;
        }
    }
    public double getPotassium(int option) {
        return potassium[option];
    }
    public void setPotassium(int option, double value) {
        if (option == 0) { // Change daily goal
            potassium[option] = value;
        } else if (option == 1) { // Update amount consumed
            potassium[option] += value;
        }
    }
    public double getSodium(int option) {
        return sodium[option];
    }
    public void setSodium(int option, double value) {
        if (option == 0) { // Change daily goal
            sodium[option] = value;
        } else if (option == 1) { // Update amount consumed
            sodium[option] += value;
        }
    }
    public double getChloride(int option) {
        return chloride[option];
    }
    public void setChloride(int option, double value) {
        if (option == 0) { // Change daily goal
            chloride[option] = value;
        } else if (option == 1) { // Update amount consumed
            chloride[option] += value;
        }
    }

    // Helper Functions
    public void addFood(Food food){
        foodCatalog.add(food);

        StringBuilder foodsConsumedThisDay = new StringBuilder();
        for(Food foodEntry : foodCatalog){
            foodsConsumedThisDay.append(foodEntry.getName()).append(" ");
        }

        // The food diary is a single long string
        dailyFoodsConsumed = foodsConsumedThisDay.toString();
    }
    public String getDailyFoodsConsumed(){
        return dailyFoodsConsumed;
    }
    // Updates goals and current consumption in one function. Useful for outputting the most recent info
    public boolean updateAllFromDatabase() {
        System.out.println("Attempting to update user values from the database...");
        String selectQuery1 = "SELECT * FROM currentConsumption WHERE userId = ?";
        String selectQuery2 = "SELECT * FROM nutrientGoals WHERE userId = ?";
        String selectQuery3 = "SELECT * FROM users WHERE userId = ?";

        ResultSet resultSet;

        try {
            // Update current consumption values
            PreparedStatement preparedStatement1 = connectionToMySQL.prepareStatement(selectQuery1);
            preparedStatement1.setInt(1, userId);
            resultSet = preparedStatement1.executeQuery();

            if (resultSet.next()) {
                water[1] = resultSet.getDouble("water");
                energy[1] = resultSet.getDouble("energy");
                carbohydrate[1] = resultSet.getDouble("carbohydrate");
                monounsaturatedFat[1] = resultSet.getDouble("monounsaturatedFat");
                saturatedFat[1] = resultSet.getDouble("saturatedFat");
                polyunsaturatedFat[1] = resultSet.getDouble("polyunsaturatedFat");
                protein[1] = resultSet.getDouble("protein");
                fiber[1] = resultSet.getDouble("fiber");
                vitaminA[1] = resultSet.getDouble("vitaminA");
                vitaminB1Thiamine[1] = resultSet.getDouble("vitaminB1Thiamine");
                vitaminB2Riboflavin[1] = resultSet.getDouble("vitaminB2Riboflavin");
                vitaminB3Niacin[1] = resultSet.getDouble("vitaminB3Niacin");
                vitaminB5PantothenicAcid[1] = resultSet.getDouble("vitaminB5PantothenicAcid");
                vitaminB6Pyridoxine[1] = resultSet.getDouble("vitaminB6Pyridoxine");
                vitaminB7Biotin[1] = resultSet.getDouble("vitaminB7Biotin");
                vitaminB9Folate[1] = resultSet.getDouble("vitaminB9Folate");
                vitaminB12Cyanocobalamin[1] = resultSet.getDouble("vitaminB12Cyanocobalamin");
                vitaminC[1] = resultSet.getDouble("vitaminC");
                vitaminD[1] = resultSet.getDouble("vitaminD");
                vitaminE[1] = resultSet.getDouble("vitaminE");
                vitaminK[1] = resultSet.getDouble("vitaminK");
                choline[1] = resultSet.getDouble("choline");
                calcium[1] = resultSet.getDouble("calcium");
                chloride[1] = resultSet.getDouble("chloride");
                chromium[1] = resultSet.getDouble("chromium");
                copper[1] = resultSet.getDouble("copper");
                fluoride[1] = resultSet.getDouble("fluoride");
                iodine[1] = resultSet.getDouble("iodine");
                iron[1] = resultSet.getDouble("iron");
                magnesium[1] = resultSet.getDouble("magnesium");
                manganese[1] = resultSet.getDouble("manganese");
                molybdenum[1] = resultSet.getDouble("molybdenum");
                phosphorus[1] = resultSet.getDouble("phosphorus");
                potassium[1] = resultSet.getDouble("potassium");
                selenium[1] = resultSet.getDouble("selenium");
                sodium[1] = resultSet.getDouble("sodium");
                zinc[1] = resultSet.getDouble("zinc");
                System.out.println("Updated current user's consumption values from the database.");
            }
            else{
                return false;
            }

            // Update nutrient goals
            PreparedStatement preparedStatement2 = connectionToMySQL.prepareStatement(selectQuery2);
            preparedStatement2.setInt(1, userId);
            resultSet = preparedStatement2.executeQuery();

            if (resultSet.next()) {
                water[0] = resultSet.getDouble("water");
                energy[0] = resultSet.getDouble("energy");
                carbohydrate[0] = resultSet.getDouble("carbohydrate");
                monounsaturatedFat[0] = resultSet.getDouble("monounsaturatedFat");
                saturatedFat[0] = resultSet.getDouble("saturatedFat");
                polyunsaturatedFat[0] = resultSet.getDouble("polyunsaturatedFat");
                protein[0] = resultSet.getDouble("protein");
                fiber[0] = resultSet.getDouble("fiber");
                vitaminA[0] = resultSet.getDouble("vitaminA");
                vitaminB1Thiamine[0] = resultSet.getDouble("vitaminB1Thiamine");
                vitaminB2Riboflavin[0] = resultSet.getDouble("vitaminB2Riboflavin");
                vitaminB3Niacin[0] = resultSet.getDouble("vitaminB3Niacin");
                vitaminB5PantothenicAcid[0] = resultSet.getDouble("vitaminB5PantothenicAcid");
                vitaminB6Pyridoxine[0] = resultSet.getDouble("vitaminB6Pyridoxine");
                vitaminB7Biotin[0] = resultSet.getDouble("vitaminB7Biotin");
                vitaminB9Folate[0] = resultSet.getDouble("vitaminB9Folate");
                vitaminB12Cyanocobalamin[0] = resultSet.getDouble("vitaminB12Cyanocobalamin");
                vitaminC[0] = resultSet.getDouble("vitaminC");
                vitaminD[0] = resultSet.getDouble("vitaminD");
                vitaminE[0] = resultSet.getDouble("vitaminE");
                vitaminK[0] = resultSet.getDouble("vitaminK");
                choline[0] = resultSet.getDouble("choline");
                calcium[0] = resultSet.getDouble("calcium");
                chloride[0] = resultSet.getDouble("chloride");
                chromium[0] = resultSet.getDouble("chromium");
                copper[0] = resultSet.getDouble("copper");
                fluoride[0] = resultSet.getDouble("fluoride");
                iodine[0] = resultSet.getDouble("iodine");
                iron[0] = resultSet.getDouble("iron");
                magnesium[0] = resultSet.getDouble("magnesium");
                manganese[0] = resultSet.getDouble("manganese");
                molybdenum[0] = resultSet.getDouble("molybdenum");
                phosphorus[0] = resultSet.getDouble("phosphorus");
                potassium[0] = resultSet.getDouble("potassium");
                selenium[0] = resultSet.getDouble("selenium");
                sodium[0] = resultSet.getDouble("sodium");
                zinc[0] = resultSet.getDouble("zinc");
                System.out.println("Updated current user's nutrient goals from the database.");
            }
            else{
                return false;
            }

            // Update user information
            PreparedStatement preparedStatement3 = connectionToMySQL.prepareStatement(selectQuery3);
            preparedStatement3.setInt(1, userId);
            resultSet = preparedStatement3.executeQuery();

            if (resultSet.next()) {
                username = resultSet.getString("username");
                weightSetter(resultSet.getDouble("weight"));
                heightSetter(resultSet.getInt("height"));
                sexSetter(resultSet.getString("sex").charAt(0));
                exerciseSetter(resultSet.getString("exercise"));
                System.out.println("Updated current user's profile information from the database.");
                return true;
            }
            else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
