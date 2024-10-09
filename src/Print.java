
// All output-related functions are within in this class
public class Print {
    User currentUser;
    public Print(User user){
        this.currentUser = user;
    }

    // Need to fully migrate the printProgress() function from the User class into this class
    public void outputCurrentConsumption(){
        currentUser.printProgress();
    }
}
