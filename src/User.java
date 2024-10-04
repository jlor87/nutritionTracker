public class User {

	//assigning user data with dummy values
    private double userWeight = 0.00; //lbs
    private int userHeight = 0; //inches
    private char userSex = '0'; //m or f
    private String exerciseLevel = "not set"; //none, light, moderate, hard, extreme (different levels to be defined in readME)
	
	
	// Index 0 is dietary goal. Index 1 is consumed for the day
    private double[] VitaminA = new double[2];

    
//Getters and setters for user data (weight, height, sex, exercise level)
    public double weightGetter() {
    	return this.userWeight;
    }
    
    public void weightSetter(int weight) {
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
    
    
    
    
    // a getter and setter for EVERY vitamin/nutrient?
    
    
    public double getter(){
        return this.VitaminA[1];
    }

    public void setter(double amountConsumed){
        this.VitaminA[1] = this.VitaminA[1] + amountConsumed;
    }













}
