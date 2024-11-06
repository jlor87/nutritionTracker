import java.util.LinkedList;

/**
 * All user data (dietary goals, preferences, user info, etc.) is stored here **? separate class for preferences?**
 *
 */
public class Food {

    private String name;
    // ***** Macros *****
    private double[] water = new double[2]; // liters
    private double[] energy = new double[2]; // kCal
    private double[] carbohyrate = new double[2]; // grams
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
    public Food (String name){
        this.name = name;

        setWater(0, 0);
        setEnergy(0, 0);
        setCarbohyrate(0, 0);
        setFiber(0, 0);
        setMonounsaturatedFat(0, 0);
        setSaturatedFat(0, 0);
        setPolyunsaturatedFat(0, 0);
        setProtein(0, 0);
        setVitaminA(0, 0);
        setVitaminB1Thiamine(0, 0);
        setVitaminB2Riboflavin(0, 0);
        setVitaminB3Niacin(0, 0);
        setVitaminB5PantothenicAcid(0, 0);
        setVitaminB6Pyridoxine(0, 0);
        setVitaminB7Biotin(0, 0);
        setVitaminB9Folate(0, 0);
        setVitaminB12Cyanocobalamin(0, 0);
        setVitaminC(0, 0);
        setVitaminD(0, 0);
        setVitaminE(0, 0);
        setVitaminK(0, 0);
        setCalcium(0, 0);
        setChloride(0, 0);
        setCholine(0, 0);
        setChromium(0, 0);
        setCopper(0, 0);
        setFluoride(0, 0);
        setIodine(0, 0);
        setIron(0, 0);
        setMagnesium(0, 400);
        setManganese(0, 0);
        setMolybdenum(0, 0);
        setPhosphorus(0, 0);
        setPotassium(0, 0);
        setSelenium(0, 0);
        setSodium(0, 0);
        setZinc(0, 0);
    }

    public String getName(){
        return this.name;
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
        return carbohyrate[option];
    }
    public void setCarbohyrate(int option, double value){
        if(option == 0){ // Change daily goal
            carbohyrate[option] = value;
        }
        else if(option == 1){ // Update amount consumed
            carbohyrate[option] = carbohyrate[option] + value;
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

}