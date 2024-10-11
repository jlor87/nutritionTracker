import org.junit.*;
public class UserTest {
    User user;
    @Test
    // Checks for proper instantiation
    public void testUser(){
        user = new User();
        Assert.assertEquals(0.00, user.weightGetter(), 0.0);
        Assert.assertEquals(0.0, user.heightGetter(), 0.0);
        Assert.assertEquals('0', user.sexGetter(), 0.0);
        Assert.assertEquals("not set", user.exerciseGetter());
        Assert.assertEquals(3.7, user.getWater(0), 0.0);
        Assert.assertEquals(2000, user.getEnergy(0), 0.0);
        Assert.assertEquals(130, user.getCarbohydrate(0), 0.0);
        Assert.assertEquals(38, user.getFiber(0), 0.0);
        Assert.assertEquals(22, user.getMonounsaturatedFat(0), 0.0);
        Assert.assertEquals(22, user.getSaturatedFat(0), 0.0);
        Assert.assertEquals(17, user.getPolyunsaturatedFat(0), 0.0);
        Assert.assertEquals(56, user.getProtein(0), 0.0);
        Assert.assertEquals(900, user.getVitaminA(0), 0.0);
        Assert.assertEquals(1.2, user.getVitaminB1Thiamine(0), 0.0);
        Assert.assertEquals(1.3, user.getVitaminB2Riboflavin(0), 0.0);
        Assert.assertEquals(16, user.getVitaminB3Niacin(0), 0.0);
        Assert.assertEquals(5, user.getVitaminB5PantothenicAcid(0), 0.0);
        Assert.assertEquals(1.3, user.getVitaminB6Pyrodoxine(0), 0.0);
        Assert.assertEquals(30, user.getVitaminB7Biotin(0), 0.0);
        Assert.assertEquals(400, user.getVitaminB9Folate(0), 0.0);
        Assert.assertEquals(2.4, user.getVitaminB12Cyanocobalamin(0), 0.0);
        Assert.assertEquals(90, user.getVitaminC(0), 0.0);
        Assert.assertEquals(15, user.getVitaminD(0), 0.0);
        Assert.assertEquals(15, user.getVitaminE(0), 0.0);
        Assert.assertEquals(120, user.getVitaminK(0), 0.0);
        Assert.assertEquals(1000, user.getCalcium(0), 0.0);
        Assert.assertEquals(2.3, user.getChloride(0), 0.0);
        Assert.assertEquals(550, user.getCholine(0), 0.0);
        Assert.assertEquals(35, user.getChromium(0), 0.0);
        Assert.assertEquals(900, user.getCopper(0), 0.0);
        Assert.assertEquals(4, user.getFluoride(0), 0.0);
        Assert.assertEquals(150, user.getIodine(0), 0.0);
        Assert.assertEquals(8, user.getIron(0), 0.0);
        Assert.assertEquals(400, user.getMagnesium(0), 0.0);
        Assert.assertEquals(2.3, user.getManganese(0), 0.0);
        Assert.assertEquals(45, user.getMolybdenum(0), 0.0);
        Assert.assertEquals(700, user.getPhosphorus(0), 0.0);
        Assert.assertEquals(3400, user.getPotassium(0), 0.0);
        Assert.assertEquals(55, user.getSelenium(0), 0.0);
        Assert.assertEquals(1500, user.getSodium(0), 0.0);
        Assert.assertEquals(11, user.getZinc(0), 0.0);
    }
}
