
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
    public void outputCurrentConsumption() {
        StringBuilder sb = new StringBuilder();
        sb.append("Progress:");

        sb.append("\nWater: ").append(currentUser.getWater(1)).append("/").append(currentUser.getWater(0)).append("L, ")
                .append((int)((currentUser.getWater(1) / currentUser.getWater(0)) * 100)).append("%");

        sb.append("\nCalories: ").append(currentUser.getEnergy(1)).append("/").append(currentUser.getEnergy(0)).append("kCal, ")
                .append((int)((currentUser.getEnergy(1) / currentUser.getEnergy(0)) * 100)).append("%");

        sb.append("\nCarbohydrates: ").append(currentUser.getCarbohydrate(1)).append("/").append(currentUser.getCarbohydrate(0)).append("g, ")
                .append((int)((currentUser.getCarbohydrate(1) / currentUser.getCarbohydrate(0)) * 100)).append("%");

        sb.append("\nProtein: ").append(currentUser.getProtein(1)).append("/").append(currentUser.getProtein(0)).append("g, ")
                .append((int)((currentUser.getProtein(1) / currentUser.getProtein(0)) * 100)).append("%");

        sb.append("\nMonounsaturated Fat: ").append(currentUser.getMonounsaturatedFat(1)).append("/").append(currentUser.getMonounsaturatedFat(0)).append("g, ")
                .append((int)((currentUser.getMonounsaturatedFat(1) / currentUser.getMonounsaturatedFat(0)) * 100)).append("%");

        sb.append("\nPolyunsaturated Fat: ").append(currentUser.getPolyunsaturatedFat(1)).append("/").append(currentUser.getPolyunsaturatedFat(0)).append("g, ")
                .append((int)((currentUser.getPolyunsaturatedFat(1) / currentUser.getPolyunsaturatedFat(0)) * 100)).append("%");

        sb.append("\nSaturated Fat: ").append(currentUser.getSaturatedFat(1)).append("/").append(currentUser.getSaturatedFat(0)).append("g, ")
                .append((int)((currentUser.getSaturatedFat(1) / currentUser.getSaturatedFat(0)) * 100)).append("%");

        sb.append("\nFiber: ").append(currentUser.getFiber(1)).append("/").append(currentUser.getFiber(0)).append("g, ")
                .append((int)((currentUser.getFiber(1) / currentUser.getFiber(0)) * 100)).append("%");

        sb.append("\nVitamin A: ").append(currentUser.getVitaminA(1)).append("/").append(currentUser.getVitaminA(0)).append("ug, ")
                .append((int)((currentUser.getVitaminA(1) / currentUser.getVitaminA(0)) * 100)).append("%");

        sb.append("\nVitamin B1: ").append(currentUser.getVitaminB1Thiamine(1)).append("/").append(currentUser.getVitaminB1Thiamine(0)).append("mg, ")
                .append((int)((currentUser.getVitaminB1Thiamine(1) / currentUser.getVitaminB1Thiamine(0)) * 100)).append("%");

        sb.append("\nVitamin B2: ").append(currentUser.getVitaminB2Riboflavin(1)).append("/").append(currentUser.getVitaminB2Riboflavin(0)).append("mg, ")
                .append((int)((currentUser.getVitaminB2Riboflavin(1) / currentUser.getVitaminB2Riboflavin(0)) * 100)).append("%");

        sb.append("\nVitamin B3: ").append(currentUser.getVitaminB3Niacin(1)).append("/").append(currentUser.getVitaminB3Niacin(0)).append("mg, ")
                .append((int)((currentUser.getVitaminB3Niacin(1) / currentUser.getVitaminB3Niacin(0)) * 100)).append("%");

        sb.append("\nVitamin B5: ").append(currentUser.getVitaminB5PantothenicAcid(1)).append("/").append(currentUser.getVitaminB5PantothenicAcid(0)).append("mg, ")
                .append((int)((currentUser.getVitaminB5PantothenicAcid(1) / currentUser.getVitaminB5PantothenicAcid(0)) * 100)).append("%");

        sb.append("\nVitamin B6: ").append(currentUser.getVitaminB6Pyrodoxine(1)).append("/").append(currentUser.getVitaminB6Pyrodoxine(0)).append("mg, ")
                .append((int)((currentUser.getVitaminB6Pyrodoxine(1) / currentUser.getVitaminB6Pyrodoxine(0)) * 100)).append("%");

        sb.append("\nVitamin B7: ").append(currentUser.getVitaminB7Biotin(1)).append("/").append(currentUser.getVitaminB7Biotin(0)).append("ug, ")
                .append((int)((currentUser.getVitaminB7Biotin(1) / currentUser.getVitaminB7Biotin(0)) * 100)).append("%");

        sb.append("\nVitamin B9: ").append(currentUser.getVitaminB9Folate(1)).append("/").append(currentUser.getVitaminB9Folate(0)).append("ug, ")
                .append((int)((currentUser.getVitaminB9Folate(1) / currentUser.getVitaminB9Folate(0)) * 100)).append("%");

        sb.append("\nVitamin B12: ").append(currentUser.getVitaminB12Cyanocobalamin(1)).append("/").append(currentUser.getVitaminB12Cyanocobalamin(0)).append("ug, ")
                .append((int)((currentUser.getVitaminB12Cyanocobalamin(1) / currentUser.getVitaminB12Cyanocobalamin(0)) * 100)).append("%");

        sb.append("\nVitamin C: ").append(currentUser.getVitaminC(1)).append("/").append(currentUser.getVitaminC(0)).append("mg, ")
                .append((int)((currentUser.getVitaminC(1) / currentUser.getVitaminC(0)) * 100)).append("%");

        sb.append("\nVitamin D: ").append(currentUser.getVitaminD(1)).append("/").append(currentUser.getVitaminD(0)).append("ng, ")
                .append((int)((currentUser.getVitaminD(1) / currentUser.getVitaminD(0)) * 100)).append("%");

        sb.append("\nVitamin E: ").append(currentUser.getVitaminE(1)).append("/").append(currentUser.getVitaminE(0)).append("mg, ")
                .append((int)((currentUser.getVitaminE(1) / currentUser.getVitaminE(0)) * 100)).append("%");

        sb.append("\nVitamin K: ").append(currentUser.getVitaminK(1)).append("/").append(currentUser.getVitaminK(0)).append("ug, ")
                .append((int)((currentUser.getVitaminK(1) / currentUser.getVitaminK(0)) * 100)).append("%");

        sb.append("\nCalcium: ").append(currentUser.getCalcium(1)).append("/").append(currentUser.getCalcium(0)).append("mg, ")
                .append((int)((currentUser.getCalcium(1) / currentUser.getCalcium(0)) * 100)).append("%");

        sb.append("\nChloride: ").append(currentUser.getChloride(1)).append("/").append(currentUser.getChloride(0)).append("g, ")
                .append((int)((currentUser.getChloride(1) / currentUser.getChloride(0)) * 100)).append("%");

        sb.append("\nCholine: ").append(currentUser.getCholine(1)).append("/").append(currentUser.getCholine(0)).append("mg, ")
                .append((int)((currentUser.getCholine(1) / currentUser.getCholine(0)) * 100)).append("%");

        sb.append("\nChromium: ").append(currentUser.getChromium(1)).append("/").append(currentUser.getChromium(0)).append("ug, ")
                .append((int)((currentUser.getChromium(1) / currentUser.getChromium(0)) * 100)).append("%");

        sb.append("\nCopper: ").append(currentUser.getCopper(1)).append("/").append(currentUser.getCopper(0)).append("ug, ")
                .append((int)((currentUser.getCopper(1) / currentUser.getCopper(0)) * 100)).append("%");

        sb.append("\nFluoride: ").append(currentUser.getFluoride(1)).append("/").append(currentUser.getFluoride(0)).append("mg, ")
                .append((int)((currentUser.getFluoride(1) / currentUser.getFluoride(0)) * 100)).append("%");

        sb.append("\nIodine: ").append(currentUser.getIodine(1)).append("/").append(currentUser.getIodine(0)).append("ug, ")
                .append((int)((currentUser.getIodine(1) / currentUser.getIodine(0)) * 100)).append("%");

        sb.append("\nIron: ").append(currentUser.getIron(1)).append("/").append(currentUser.getIron(0)).append("mg, ")
                .append((int)((currentUser.getIron(1) / currentUser.getIron(0)) * 100)).append("%");

        sb.append("\nMagnesium: ").append(currentUser.getMagnesium(1)).append("/").append(currentUser.getMagnesium(0)).append("mg, ")
                .append((int)((currentUser.getMagnesium(1) / currentUser.getMagnesium(0)) * 100)).append("%");

        sb.append("\nManganese: ").append(currentUser.getManganese(1)).append("/").append(currentUser.getManganese(0)).append("mg, ")
                .append((int)((currentUser.getManganese(1) / currentUser.getManganese(0)) * 100)).append("%");

        sb.append("\nMolybdenum: ").append(currentUser.getMolybdenum(1)).append("/").append(currentUser.getMolybdenum(0)).append("ug, ")
                .append((int)((currentUser.getMolybdenum(1) / currentUser.getMolybdenum(0)) * 100)).append("%");

        sb.append("\nPhosphorus: ").append(currentUser.getPhosphorus(1)).append("/").append(currentUser.getPhosphorus(0)).append("mg, ")
                .append((int)((currentUser.getPhosphorus(1) / currentUser.getPhosphorus(0)) * 100)).append("%");

        sb.append("\nPotassium: ").append(currentUser.getPotassium(1)).append("/").append(currentUser.getPotassium(0)).append("mg, ")
                .append((int)((currentUser.getPotassium(1) / currentUser.getPotassium(0)) * 100)).append("%");

        sb.append("\nSelenium: ").append(currentUser.getSelenium(1)).append("/").append(currentUser.getSelenium(0)).append("ug, ")
                .append((int)((currentUser.getSelenium(1) / currentUser.getSelenium(0)) * 100)).append("%");

        sb.append("\nSodium: ").append(currentUser.getSodium(1)).append("/").append(currentUser.getSodium(0)).append("mg, ")
                .append((int)((currentUser.getSodium(1) / currentUser.getSodium(0)) * 100)).append("%");

        sb.append("\nZinc: ").append(currentUser.getZinc(1)).append("/").append(currentUser.getZinc(0)).append("mg, ")
                .append((int)((currentUser.getZinc(1) / currentUser.getZinc(0)) * 100)).append("%");

        System.out.println(sb);
    }
    
    
    public void outputCurrentGoals() {
    	StringBuilder sb2 = new StringBuilder();
    	sb2.append("Current Goals:");
    	
    	
sb2.append("\nWater: ").append(currentUser.getWater(0)).append("L");
      
sb2.append("\nCalories: ").append(currentUser.getEnergy(0)).append("kCal");
      
sb2.append("\nCarbohydrates: ").append(currentUser.getCarbohydrate(0)).append("g");
     
sb2.append("\nProtein: ").append(currentUser.getProtein(0)).append("g");
     
sb2.append("\nMonounsaturated Fat: ").append(currentUser.getMonounsaturatedFat(0)).append("g");
     
sb2.append("\nPolyunsaturated Fat: ").append(currentUser.getPolyunsaturatedFat(0)).append("g");
     
sb2.append("\nSaturated Fat: ").append(currentUser.getSaturatedFat(0)).append("g");
       
sb2.append("\nFiber: ").append(currentUser.getFiber(0)).append("g");
      
sb2.append("\nVitamin A: ").append(currentUser.getVitaminA(0)).append("ug");
      
sb2.append("\nVitamin B1: ").append(currentUser.getVitaminB1Thiamine(0)).append("mg");
       
sb2.append("\nVitamin B2: ").append(currentUser.getVitaminB2Riboflavin(0)).append("mg");
        
sb2.append("\nVitamin B3: ").append(currentUser.getVitaminB3Niacin(0)).append("mg");
      
sb2.append("\nVitamin B5: ").append(currentUser.getVitaminB5PantothenicAcid(0)).append("mg");
       
sb2.append("\nVitamin B6: ").append(currentUser.getVitaminB6Pyrodoxine(0)).append("mg");
       
sb2.append("\nVitamin B7: ").append(currentUser.getVitaminB7Biotin(0)).append("ug");
       
sb2.append("\nVitamin B9: ").append(currentUser.getVitaminB9Folate(0)).append("ug");
      
sb2.append("\nVitamin B12: ").append(currentUser.getVitaminB12Cyanocobalamin(0)).append("ug");
       
sb2.append("\nVitamin C: ").append(currentUser.getVitaminC(0)).append("mg");
        
sb2.append("\nVitamin D: ").append(currentUser.getVitaminD(0)).append("ng");
        
sb2.append("\nVitamin E: ").append(currentUser.getVitaminE(0)).append("mg");
     
sb2.append("\nVitamin K: ").append(currentUser.getVitaminK(0)).append("ug");
        
sb2.append("\nCalcium: ").append(currentUser.getCalcium(0)).append("mg");
        
sb2.append("\nChloride: ").append(currentUser.getChloride(0)).append("g");
      
sb2.append("\nCholine: ").append(currentUser.getCholine(0)).append("mg");
       
sb2.append("\nChromium: ").append(currentUser.getChromium(0)).append("ug");
       
sb2.append("\nCopper: ").append(currentUser.getCopper(0)).append("ug");
       
sb2.append("\nFluoride: ").append(currentUser.getFluoride(0)).append("mg");
        
sb2.append("\nIodine: ").append(currentUser.getIodine(0)).append("ug");
        
sb2.append("\nIron: ").append(currentUser.getIron(0)).append("mg");
       
sb2.append("\nMagnesium: ").append(currentUser.getMagnesium(0)).append("mg");
        
sb2.append("\nManganese: ").append(currentUser.getManganese(0)).append("mg");
        
sb2.append("\nMolybdenum: ").append(currentUser.getMolybdenum(0)).append("ug");
       
sb2.append("\nPhosphorus: ").append(currentUser.getPhosphorus(0)).append("mg");
       
sb2.append("\nPotassium: ").append(currentUser.getPotassium(0)).append("mg");
       
sb2.append("\nSelenium: ").append(currentUser.getSelenium(0)).append("ug");
       
sb2.append("\nSodium: ").append(currentUser.getSodium(0)).append("mg");
        
sb2.append("\nZinc: ").append(currentUser.getZinc(0)).append("mg");
    	
    	System.out.println(sb2);
    }
    
    
public void showListOfOptions() {
	
	StringBuilder sb3 = new StringBuilder();
	
	sb3.append("x--------------------------------------------------------------------------------x");
	
	sb3.append("\nListing all metrics:");
	
	sb3.append("\n1.Water");
    
	sb3.append("\n2.Calories");
	      
	sb3.append("\n3.Carbohydrates");
	     
	sb3.append("\n4.Protein");
	     
	sb3.append("\n5.Monounsaturated Fat");
	     
	sb3.append("\n6.Polyunsaturated Fat");
	     
	sb3.append("\n7.Saturated Fat");
	       
	sb3.append("\n8.Fiber");
	      
	sb3.append("\n9.Vitamin A");
	      
	sb3.append("\n10.Vitamin B1");
	       
	sb3.append("\n11.Vitamin B2");
	        
	sb3.append("\n12.Vitamin B3");
	      
	sb3.append("\n13.Vitamin B5");
	       
	sb3.append("\n14.Vitamin B6");
	       
	sb3.append("\n15.Vitamin B7");
	       
	sb3.append("\n16.Vitamin B9");
	      
	sb3.append("\n17.Vitamin B12");
	       
	sb3.append("\n18.Vitamin C");
	        
	sb3.append("\n19.Vitamin D");
	        
	sb3.append("\n20.Vitamin E");
	     
	sb3.append("\n21.Vitamin K");
	        
	sb3.append("\n22.Calcium");
	        
	sb3.append("\n23.Chloride");
	      
	sb3.append("\n24.Choline");
	       
	sb3.append("\n25.Chromium");
	       
	sb3.append("\n26.Copper");
	       
	sb3.append("\n27.Fluoride");
	        
	sb3.append("\n28.Iodine");
	        
	sb3.append("\n29.Iron");
	       
	sb3.append("\n30.Magnesium");
	        
	sb3.append("\n31.Manganese");
	        
	sb3.append("\n32.Molybdenum");
	       
	sb3.append("\n33.Phosphorus");
	       
	sb3.append("\n34.Potassium");
	       
	sb3.append("\n35.Selenium");
	       
	sb3.append("\n36.Sodium");
	        
	sb3.append("\n37.Zinc");
	
	sb3.append("\n38.Exit Menu");
	
	sb3.append("\nx--------------------------------------------------------------------------------x");
	
	System.out.println(sb3);
	
	}
    
    
}
