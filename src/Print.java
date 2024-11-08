import java.util.ArrayList;
import java.util.List;

/**
 * All output-related functions are within in this class
 * // Note From Haivan: I actually think we should dissolve this class and put it's functionality into the class that needs this operation done... just a note nothing major
 * // I will create a branch testing out that feature!
 */
public class Print {
    private User currentUser;

    // Constructor
    public Print(User user){
        this.currentUser = user;
    }


    /**
     * Class function
     */
    public List<String> outputCurrentConsumption() {
        // User must pull updated numbers from the database before outputting to the GUI
        currentUser.updateAllFromDatabase();
        System.out.println("User consumption and goals updated. Displaying current status of goals now:");

        StringBuilder macronutrientsSb = new StringBuilder();
        macronutrientsSb.append("Macronutrients Progress:");
        macronutrientsSb.append("\nWater: ").append(currentUser.getWater(1)).append("/").append(currentUser.getWater(0)).append("L, ")
                .append((int)((currentUser.getWater(1) / currentUser.getWater(0)) * 100)).append("%");
        macronutrientsSb.append("\nCalories: ").append(currentUser.getEnergy(1)).append("/").append(currentUser.getEnergy(0)).append("kCal, ")
                .append((int)((currentUser.getEnergy(1) / currentUser.getEnergy(0)) * 100)).append("%");
        macronutrientsSb.append("\nCarbohydrates: ").append(currentUser.getCarbohydrate(1)).append("/").append(currentUser.getCarbohydrate(0)).append("g, ")
                .append((int)((currentUser.getCarbohydrate(1) / currentUser.getCarbohydrate(0)) * 100)).append("%");
        macronutrientsSb.append("\nProtein: ").append(currentUser.getProtein(1)).append("/").append(currentUser.getProtein(0)).append("g, ")
                .append((int)((currentUser.getProtein(1) / currentUser.getProtein(0)) * 100)).append("%");
        macronutrientsSb.append("\nMonounsaturated Fat: ").append(currentUser.getMonounsaturatedFat(1)).append("/").append(currentUser.getMonounsaturatedFat(0)).append("g, ")
                .append((int)((currentUser.getMonounsaturatedFat(1) / currentUser.getMonounsaturatedFat(0)) * 100)).append("%");
        macronutrientsSb.append("\nPolyunsaturated Fat: ").append(currentUser.getPolyunsaturatedFat(1)).append("/").append(currentUser.getPolyunsaturatedFat(0)).append("g, ")
                .append((int)((currentUser.getPolyunsaturatedFat(1) / currentUser.getPolyunsaturatedFat(0)) * 100)).append("%");
        macronutrientsSb.append("\nSaturated Fat: ").append(currentUser.getSaturatedFat(1)).append("/").append(currentUser.getSaturatedFat(0)).append("g, ")
                .append((int)((currentUser.getSaturatedFat(1) / currentUser.getSaturatedFat(0)) * 100)).append("%");
        macronutrientsSb.append("\nFiber: ").append(currentUser.getFiber(1)).append("/").append(currentUser.getFiber(0)).append("g, ")
                .append((int)((currentUser.getFiber(1) / currentUser.getFiber(0)) * 100)).append("%");

        StringBuilder vitaminsSb = new StringBuilder();
        vitaminsSb.append("Vitamins Progress:");
        vitaminsSb.append("\nVitamin A: ").append(currentUser.getVitaminA(1)).append("/").append(currentUser.getVitaminA(0)).append("ug, ")
                .append((int)((currentUser.getVitaminA(1) / currentUser.getVitaminA(0)) * 100)).append("%");
        vitaminsSb.append("\nVitamin B1: ").append(currentUser.getVitaminB1Thiamine(1)).append("/").append(currentUser.getVitaminB1Thiamine(0)).append("mg, ")
                .append((int)((currentUser.getVitaminB1Thiamine(1) / currentUser.getVitaminB1Thiamine(0)) * 100)).append("%");
        vitaminsSb.append("\nVitamin B2: ").append(currentUser.getVitaminB2Riboflavin(1)).append("/").append(currentUser.getVitaminB2Riboflavin(0)).append("mg, ")
                .append((int)((currentUser.getVitaminB2Riboflavin(1) / currentUser.getVitaminB2Riboflavin(0)) * 100)).append("%");
        vitaminsSb.append("\nVitamin B3: ").append(currentUser.getVitaminB3Niacin(1)).append("/").append(currentUser.getVitaminB3Niacin(0)).append("mg, ")
                .append((int)((currentUser.getVitaminB3Niacin(1) / currentUser.getVitaminB3Niacin(0)) * 100)).append("%");
        vitaminsSb.append("\nVitamin B5: ").append(currentUser.getVitaminB5PantothenicAcid(1)).append("/").append(currentUser.getVitaminB5PantothenicAcid(0)).append("mg, ")
                .append((int)((currentUser.getVitaminB5PantothenicAcid(1) / currentUser.getVitaminB5PantothenicAcid(0)) * 100)).append("%");
        vitaminsSb.append("\nVitamin B6: ").append(currentUser.getVitaminB6Pyrodoxine(1)).append("/").append(currentUser.getVitaminB6Pyrodoxine(0)).append("mg, ")
                .append((int)((currentUser.getVitaminB6Pyrodoxine(1) / currentUser.getVitaminB6Pyrodoxine(0)) * 100)).append("%");
        vitaminsSb.append("\nVitamin B7: ").append(currentUser.getVitaminB7Biotin(1)).append("/").append(currentUser.getVitaminB7Biotin(0)).append("ug, ")
                .append((int)((currentUser.getVitaminB7Biotin(1) / currentUser.getVitaminB7Biotin(0)) * 100)).append("%");
        vitaminsSb.append("\nVitamin B9: ").append(currentUser.getVitaminB9Folate(1)).append("/").append(currentUser.getVitaminB9Folate(0)).append("ug, ")
                .append((int)((currentUser.getVitaminB9Folate(1) / currentUser.getVitaminB9Folate(0)) * 100)).append("%");
        vitaminsSb.append("\nVitamin B12: ").append(currentUser.getVitaminB12Cyanocobalamin(1)).append("/").append(currentUser.getVitaminB12Cyanocobalamin(0)).append("ug, ")
                .append((int)((currentUser.getVitaminB12Cyanocobalamin(1) / currentUser.getVitaminB12Cyanocobalamin(0)) * 100)).append("%");
        vitaminsSb.append("\nVitamin C: ").append(currentUser.getVitaminC(1)).append("/").append(currentUser.getVitaminC(0)).append("mg, ")
                .append((int)((currentUser.getVitaminC(1) / currentUser.getVitaminC(0)) * 100)).append("%");
        vitaminsSb.append("\nVitamin D: ").append(currentUser.getVitaminD(1)).append("/").append(currentUser.getVitaminD(0)).append("ng, ")
                .append((int)((currentUser.getVitaminD(1) / currentUser.getVitaminD(0)) * 100)).append("%");
        vitaminsSb.append("\nVitamin E: ").append(currentUser.getVitaminE(1)).append("/").append(currentUser.getVitaminE(0)).append("mg, ")
                .append((int)((currentUser.getVitaminE(1) / currentUser.getVitaminE(0)) * 100)).append("%");
        vitaminsSb.append("\nVitamin K: ").append(currentUser.getVitaminK(1)).append("/").append(currentUser.getVitaminK(0)).append("ug, ")
                .append((int)((currentUser.getVitaminK(1) / currentUser.getVitaminK(0)) * 100)).append("%");

        StringBuilder mineralsSb = new StringBuilder();
        mineralsSb.append("Minerals Progress:");
        mineralsSb.append("\nCalcium: ").append(currentUser.getCalcium(1)).append("/").append(currentUser.getCalcium(0)).append("mg, ")
                .append((int)((currentUser.getCalcium(1) / currentUser.getCalcium(0)) * 100)).append("%");
        mineralsSb.append("\nChloride: ").append(currentUser.getChloride(1)).append("/").append(currentUser.getChloride(0)).append("g, ")
                .append((int)((currentUser.getChloride(1) / currentUser.getChloride(0)) * 100)).append("%");
        mineralsSb.append("\nCholine: ").append(currentUser.getCholine(1)).append("/").append(currentUser.getCholine(0)).append("mg, ")
                .append((int)((currentUser.getCholine(1) / currentUser.getCholine(0)) * 100)).append("%");
        mineralsSb.append("\nChromium: ").append(currentUser.getChromium(1)).append("/").append(currentUser.getChromium(0)).append("ug, ")
                .append((int)((currentUser.getChromium(1) / currentUser.getChromium(0)) * 100)).append("%");
        mineralsSb.append("\nCopper: ").append(currentUser.getCopper(1)).append("/").append(currentUser.getCopper(0)).append("ug, ")
                .append((int)((currentUser.getCopper(1) / currentUser.getCopper(0)) * 100)).append("%");
        mineralsSb.append("\nFluoride: ").append(currentUser.getFluoride(1)).append("/").append(currentUser.getFluoride(0)).append("mg, ")
                .append((int)((currentUser.getFluoride(1) / currentUser.getFluoride(0)) * 100)).append("%");
        mineralsSb.append("\nIodine: ").append(currentUser.getIodine(1)).append("/").append(currentUser.getIodine(0)).append("ug, ")
                .append((int)((currentUser.getIodine(1) / currentUser.getIodine(0)) * 100)).append("%");
        mineralsSb.append("\nIron: ").append(currentUser.getIron(1)).append("/").append(currentUser.getIron(0)).append("mg, ")
                .append((int)((currentUser.getIron(1) / currentUser.getIron(0)) * 100)).append("%");
        mineralsSb.append("\nMagnesium: ").append(currentUser.getMagnesium(1)).append("/").append(currentUser.getMagnesium(0)).append("mg, ")
                .append((int)((currentUser.getMagnesium(1) / currentUser.getMagnesium(0)) * 100)).append("%");
        mineralsSb.append("\nManganese: ").append(currentUser.getManganese(1)).append("/").append(currentUser.getManganese(0)).append("mg, ")
                .append((int)((currentUser.getManganese(1) / currentUser.getManganese(0)) * 100)).append("%");
        mineralsSb.append("\nMolybdenum: ").append(currentUser.getMolybdenum(1)).append("/").append(currentUser.getMolybdenum(0)).append("ug, ")
                .append((int)((currentUser.getMolybdenum(1) / currentUser.getMolybdenum(0)) * 100)).append("%");
        mineralsSb.append("\nPhosphorus: ").append(currentUser.getPhosphorus(1)).append("/").append(currentUser.getPhosphorus(0)).append("mg, ")
                .append((int)((currentUser.getPhosphorus(1) / currentUser.getPhosphorus(0)) * 100)).append("%");
        mineralsSb.append("\nPotassium: ").append(currentUser.getPotassium(1)).append("/").append(currentUser.getPotassium(0)).append("mg, ")
                .append((int)((currentUser.getPotassium(1) / currentUser.getPotassium(0)) * 100)).append("%");
        mineralsSb.append("\nSelenium: ").append(currentUser.getSelenium(1)).append("/").append(currentUser.getSelenium(0)).append("ug, ")
                .append((int)((currentUser.getSelenium(1) / currentUser.getSelenium(0)) * 100)).append("%");
        mineralsSb.append("\nSodium: ").append(currentUser.getSodium(1)).append("/").append(currentUser.getSodium(0)).append("mg, ")
                .append((int)((currentUser.getSodium(1) / currentUser.getSodium(0)) * 100)).append("%");
        mineralsSb.append("\nZinc: ").append(currentUser.getZinc(1)).append("/").append(currentUser.getZinc(0)).append("mg, ")
                .append((int)((currentUser.getZinc(1) / currentUser.getZinc(0)) * 100)).append("%");

        List<String> nutrientProgress = new ArrayList<>();
        nutrientProgress.add(macronutrientsSb.toString());
        nutrientProgress.add(vitaminsSb.toString());
        nutrientProgress.add(mineralsSb.toString());

        return nutrientProgress;
    }
}
