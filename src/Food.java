import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * All user data (dietary goals, preferences, user info, etc.) is stored here **? separate class for preferences?**
 *
 */
public class Food {

    private Connection connectionToMySQL = Main.getConnection();
    private int userId;
    private String name;

    // ***** Macros *****
    private double water = 0.0; // liters
    private double energy = 0.0; // kCal
    private double carbohydrate = 0.0; // grams
    private double monounsaturatedFat = 0.0; // grams
    private double saturatedFat = 0.0; // grams
    private double polyunsaturatedFat = 0.0; // grams
    private double protein = 0.0; // grams
    private double fiber = 0.0; // grams

    // ***** Vitamins *****
    private double vitaminA = 0.0; // micrograms
    private double vitaminB1Thiamine = 0.0; // milligrams
    private double vitaminB2Riboflavin = 0.0; // milligrams
    private double vitaminB3Niacin = 0.0; // milligrams
    private double vitaminB5PantothenicAcid = 0.0; // milligrams
    private double vitaminB6Pyridoxine = 0.0; // milligrams
    private double vitaminB7Biotin = 0.0; // micrograms
    private double vitaminB9Folate = 0.0; // micrograms
    private double vitaminB12Cyanocobalamin = 0.0; // micrograms
    private double vitaminC = 0.0; // milligrams
    private double vitaminD = 0.0; // micrograms
    private double vitaminE = 0.0; // milligrams
    private double vitaminK = 0.0; // micrograms

    // ***** Minerals *****
    private double choline = 0.0; // milligrams
    private double calcium = 0.0; // milligrams
    private double chloride = 0.0; // grams
    private double chromium = 0.0; // micrograms
    private double copper = 0.0; // micrograms
    private double fluoride = 0.0; // milligrams
    private double iodine = 0.0; // micrograms
    private double iron = 0.0; // milligrams
    private double magnesium = 0.0; // milligrams
    private double manganese = 0.0; // milligrams
    private double molybdenum = 0.0; // micrograms
    private double phosphorus = 0.0; // milligrams
    private double potassium = 0.0; // milligrams
    private double selenium = 0.0; // micrograms
    private double sodium = 0.0; // milligrams
    private double zinc = 0.0; // milligrams


    /**
     * Constructor
     */
    public Food (String name, int userId){
        this.name = name;
        this.userId = userId;
    }

    private void setCarbohyrate(double carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public String getName(){
        return this.name;
    }

    // Getters and setters

    public int getUserId() {
        return userId;
    }

    public double getCalcium() {
        return calcium;
    }

    public double getCarbohydrate() {
        return carbohydrate;
    }

    public double getChloride() {
        return chloride;
    }

    public double getCholine() {
        return choline;
    }

    public double getChromium() {
        return chromium;
    }

    public double getCopper() {
        return copper;
    }

    public double getEnergy() {
        return energy;
    }

    public double getFiber() {
        return fiber;
    }

    public double getFluoride() {
        return fluoride;
    }

    public double getIodine() {
        return iodine;
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWater() {
        return water;
    }

    public void setWater(double water) {
        this.water = water;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public void setCarbohydrate(double carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public double getMonounsaturatedFat() {
        return monounsaturatedFat;
    }

    public void setMonounsaturatedFat(double monounsaturatedFat) {
        this.monounsaturatedFat = monounsaturatedFat;
    }

    public double getSaturatedFat() {
        return saturatedFat;
    }

    public void setSaturatedFat(double saturatedFat) {
        this.saturatedFat = saturatedFat;
    }

    public double getPolyunsaturatedFat() {
        return polyunsaturatedFat;
    }

    public void setPolyunsaturatedFat(double polyunsaturatedFat) {
        this.polyunsaturatedFat = polyunsaturatedFat;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public void setFiber(double fiber) {
        this.fiber = fiber;
    }

    public double getVitaminA() {
        return vitaminA;
    }

    public void setVitaminA(double vitaminA) {
        this.vitaminA = vitaminA;
    }

    public double getVitaminB1Thiamine() {
        return vitaminB1Thiamine;
    }

    public void setVitaminB1Thiamine(double vitaminB1Thiamine) {
        this.vitaminB1Thiamine = vitaminB1Thiamine;
    }

    public double getVitaminB2Riboflavin() {
        return vitaminB2Riboflavin;
    }

    public void setVitaminB2Riboflavin(double vitaminB2Riboflavin) {
        this.vitaminB2Riboflavin = vitaminB2Riboflavin;
    }

    public double getVitaminB3Niacin() {
        return vitaminB3Niacin;
    }

    public void setVitaminB3Niacin(double vitaminB3Niacin) {
        this.vitaminB3Niacin = vitaminB3Niacin;
    }

    public double getVitaminB5PantothenicAcid() {
        return vitaminB5PantothenicAcid;
    }

    public void setVitaminB5PantothenicAcid(double vitaminB5PantothenicAcid) {
        this.vitaminB5PantothenicAcid = vitaminB5PantothenicAcid;
    }

    public double getVitaminB6Pyridoxine() {
        return vitaminB6Pyridoxine;
    }

    public void setVitaminB6Pyridoxine(double vitaminB6Pyridoxine) {
        this.vitaminB6Pyridoxine = vitaminB6Pyridoxine;
    }

    public double getVitaminB7Biotin() {
        return vitaminB7Biotin;
    }

    public void setVitaminB7Biotin(double vitaminB7Biotin) {
        this.vitaminB7Biotin = vitaminB7Biotin;
    }

    public double getVitaminB9Folate() {
        return vitaminB9Folate;
    }

    public void setVitaminB9Folate(double vitaminB9Folate) {
        this.vitaminB9Folate = vitaminB9Folate;
    }

    public double getVitaminB12Cyanocobalamin() {
        return vitaminB12Cyanocobalamin;
    }

    public void setVitaminB12Cyanocobalamin(double vitaminB12Cyanocobalamin) {
        this.vitaminB12Cyanocobalamin = vitaminB12Cyanocobalamin;
    }

    public double getVitaminC() {
        return vitaminC;
    }

    public void setVitaminC(double vitaminC) {
        this.vitaminC = vitaminC;
    }

    public double getVitaminD() {
        return vitaminD;
    }

    public void setVitaminD(double vitaminD) {
        this.vitaminD = vitaminD;
    }

    public double getVitaminE() {
        return vitaminE;
    }

    public void setVitaminE(double vitaminE) {
        this.vitaminE = vitaminE;
    }

    public double getVitaminK() {
        return vitaminK;
    }

    public void setVitaminK(double vitaminK) {
        this.vitaminK = vitaminK;
    }

    public void setCholine(double choline) {
        this.choline = choline;
    }

    public void setCalcium(double calcium) {
        this.calcium = calcium;
    }

    public void setChloride(double chloride) {
        this.chloride = chloride;
    }

    public void setChromium(double chromium) {
        this.chromium = chromium;
    }

    public void setCopper(double copper) {
        this.copper = copper;
    }

    public void setFluoride(double fluoride) {
        this.fluoride = fluoride;
    }

    public void setIodine(double iodine) {
        this.iodine = iodine;
    }

    public void setIron(double iron) {
        this.iron = iron;
    }

    public void setMagnesium(double magnesium) {
        this.magnesium = magnesium;
    }

    public double getManganese() {
        return manganese;
    }

    public void setManganese(double manganese) {
        this.manganese = manganese;
    }

    public double getMolybdenum() {
        return molybdenum;
    }

    public void setMolybdenum(double molybdenum) {
        this.molybdenum = molybdenum;
    }

    public double getPhosphorus() {
        return phosphorus;
    }

    public void setPhosphorus(double phosphorus) {
        this.phosphorus = phosphorus;
    }

    public double getPotassium() {
        return potassium;
    }

    public void setPotassium(double potassium) {
        this.potassium = potassium;
    }

    public double getSelenium() {
        return selenium;
    }

    public void setSelenium(double selenium) {
        this.selenium = selenium;
    }

    public double getSodium() {
        return sodium;
    }

    public void setSodium(double sodium) {
        this.sodium = sodium;
    }

    public double getZinc() {
        return zinc;
    }

    public void setZinc(double zinc) {
        this.zinc = zinc;
    }

    public double getIron() {
        return iron;
    }

    public double getMagnesium() {
        return magnesium;
    }


    // Helper Functions
    public boolean insertFoodIntoDB(){
        String sql = "INSERT IGNORE INTO food (foodName, water, energy, carbohydrate, monounsaturatedFat, saturatedFat, polyunsaturatedFat, protein, fiber, " +
                "vitaminA, vitaminB1Thiamine, vitaminB2Riboflavin, vitaminB3Niacin, vitaminB5PantothenicAcid, vitaminB6Pyridoxine, vitaminB7Biotin, vitaminB9Folate, vitaminB12Cyanocobalamin, vitaminC, vitaminD, vitaminE, vitaminK, " +
                "choline, calcium, chloride, chromium, copper, fluoride, iodine, iron, magnesium, manganese, molybdenum, phosphorus, potassium, selenium, sodium, zinc, userId) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connectionToMySQL.prepareStatement(sql);

            // Bind parameters (starting from 1 and going up to 37)
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, water);
            preparedStatement.setDouble(3, energy);
            preparedStatement.setDouble(4, carbohydrate);
            preparedStatement.setDouble(5, monounsaturatedFat);
            preparedStatement.setDouble(6, saturatedFat);
            preparedStatement.setDouble(7, polyunsaturatedFat);
            preparedStatement.setDouble(8, protein);
            preparedStatement.setDouble(9, fiber);
            preparedStatement.setDouble(10, vitaminA);
            preparedStatement.setDouble(11, vitaminB1Thiamine);
            preparedStatement.setDouble(12, vitaminB2Riboflavin);
            preparedStatement.setDouble(13, vitaminB3Niacin);
            preparedStatement.setDouble(14, vitaminB5PantothenicAcid);
            preparedStatement.setDouble(15, vitaminB6Pyridoxine);
            preparedStatement.setDouble(16, vitaminB7Biotin);
            preparedStatement.setDouble(17, vitaminB9Folate);
            preparedStatement.setDouble(18, vitaminB12Cyanocobalamin);
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
            preparedStatement.setInt(39, userId);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
