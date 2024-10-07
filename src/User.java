public class User {

	// Assigning user data with dummy values
    private double userWeight = 0.00; //lbs
    private int userHeight = 0; //inches
    private char userSex = '0'; //m or f
    private String exerciseLevel = "not set"; //none, light, moderate, hard, extreme (different levels to be defined in readME)


    // Getters and setters for user data (weight, height, sex, exercise level)
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

    public void printCurrentConsumption(){
        StringBuilder sb = new StringBuilder();
        sb.append("Total Consumption Today:").
                append("\nWater: ").append(water[1]).append("L").
                append("\nCarbohydrates: ").append(carbohyrate[1]).append("g").
                append("\nProtein: ").append(protein[1]).append("g").
                append("\nMonosaturated Fat: ").append(monounsaturatedFat[1]).append("g").
                append("\nPolyunsaturated Fat: ").append(polyunsaturatedFat[1]).append("g").
                append("\nSaturated Fat: ").append(saturatedFat[1]).append("g").
                append("\nFiber: ").append(fiber[1]).append("g").

                append("\nVitamin A: ").append(vitaminA[1]).append("ug RAE").
                append("\nVitamin B1: ").append(vitaminB1Thiamine[1]).append("mg").
                append("\nVitamin B2: ").append(vitaminB2Riboflavin[1]).append("mg").
                append("\nVitamin B3: ").append(vitaminB3Niacin[1]).append("mg").
                append("\nVitamin B5: ").append(vitaminB5PantothenicAcid[1]).append("mg").
                append("\nVitamin B6: ").append(vitaminB6Pyridoxine[1]).append("mg").
                append("\nVitamin B7: ").append(vitaminB7Biotin[1]).append("ug").
                append("\nVitamin B9: ").append(vitaminB9Folate[1]).append("ug").
                append("\nVitamin B12: ").append(vitaminB12Cyanocobalamin[1]).append("ug").
                append("\nVitamin C: ").append(vitaminC[1]).append("mg").
                append("\nVitamin D: ").append(vitaminD[1]).append("ng/mL").
                append("\nVitamin E: ").append(vitaminE[1]).append("mg").
                append("\nVitamin K: ").append(vitaminK[1]).append("ug").

                append("\nCalcium: ").append(calcium[1]).append("mg").
                append("\nChloride: ").append(chloride[1]).append("g").
                append("\nCholine: ").append(choline[1]).append("mg").
                append("\nChromium: ").append(chromium[1]).append("ug").
                append("\nCopper: ").append(copper[1]).append("ug").
                append("\nFluoride: ").append(flouride[1]).append("mg").
                append("\nIodine: ").append(iodine[1]).append("ug").
                append("\nIron: ").append(iron[1]).append("mg").
                append("\nMagnesium: ").append(magnesium[1]).append("mg").
                append("\nManganese: ").append(manganese[1]).append("mg").
                append("\nMolybdenum: ").append(molybdenum[1]).append("ug").
                append("\nPhosphorus: ").append(phosphorus[1]).append("mg").
                append("\nPotassium: ").append(potassium[1]).append("mg").
                append("\nSelenium: ").append(selenium[1]).append("ug").
                append("\nSodium: ").append(sodium[1]).append("mg").
                append("\nZinc: ").append(zinc[1]).append("mg");



        System.out.println(sb);
    }

    // Although standard protocol is to have private variables at the top of the class,
    // there were simply too many private variables, making it hard to find the class's functional methods.
    // Therefore, I have placed all the private variables, getters, and setters for all the nutrients below.
    // Index 0 refers to the user's dietary goal. Index 1 refers to the amount consumed for the day.

    // ***** Macros *****
    private double[] water = new double[2]; // liters
    private double[] energy = new double[2]; // kCal
    private double[] carbohyrate = new double[2]; // grams
    private double[] fiber = new double[2]; // grams
    private double[] monounsaturatedFat = new double[2]; // grams
    private double[] saturatedFat = new double[2]; // grams
    private double[] polyunsaturatedFat = new double[2]; // grams
    private double[] protein = new double[2]; // grams


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
    private double[] chromium = new double[2]; // micrograms
    private double[] copper = new double[2]; // micrograms
    private double[] flouride = new double[2]; // milligrams
    private double[] iodine = new double[2]; // micrograms
    private double[] iron = new double[2];  // milligrams
    private double[] magnesium = new double[2]; // milligrams
    private double[] manganese = new double[2]; // milligrams
    private double[] molybdenum = new double[2]; // micrograms
    private double[] phosphorus = new double[2]; // milligrams
    private double[] selenium = new double[2]; // micrograms
    private double[] zinc = new double[2]; // milligrams
    private double[] potassium = new double[2]; // milligrams
    private double[] sodium = new double[2]; // milligrams
    private double[] chloride = new double[2]; // grams


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
    public double getCarbs(int option){
        return carbohyrate[option];
    }
    public void setCarbs(int option, double value){
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
    public double getFlouride(int option){
        return flouride[option];
    }
    public void setFlouride(int option, double value){
        if(option == 0){ // Change daily goal
            flouride[option] = value;
        }
        else if(option == 1){ // Update amount consumed
            flouride[option] = flouride[option] + value;
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
