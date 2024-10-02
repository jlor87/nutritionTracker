public class User {

    // Index 0 is dietary goal. Index 1 is consumed for the day
    private double[] VitaminA = new double[2];

    // a getter and setter for EVERY vitamin/nutrient?
    public double getter(){
        return this.VitaminA[1];
    }

    public void setter(double amountConsumed){
        this.VitaminA[1] = this.VitaminA[1] + amountConsumed;
    }
}
