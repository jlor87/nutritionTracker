import org.junit.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Scanner;

public class UserSettingsTest {
    User user;
    UserSettings userSettings;
    Session session;
    @Before
    public void setUp(){
        user = new User(1);
        Connection dummyConnection = null;
        GUI gui = new GUI(dummyConnection);
        session = new Session(user, gui, dummyConnection);
        session.startSession();
        userSettings = session.getUserSettings();
    }

    @Test
    public void testAlterUserData(){
        String fakeInput = "1\n150\n2\n70\n3\nF\n4\n5\n5"; // Simulates all the options
        InputStream inputStream = new ByteArrayInputStream(fakeInput.getBytes());
        System.setIn(inputStream); // Tells Java that the next input is the fakeInput from above
//        ByteArrayOutputStream resultingOutput = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(resultingOutput)); // Whatever getFoodInput() spews out will get stored into resultingOutput

        Scanner scanner = new Scanner(System.in);

        userSettings.alterUserData(scanner);
        Assert.assertEquals(150, user.weightGetter(), 0.0);
        Assert.assertEquals(70, user.heightGetter(), 0.0);
        Assert.assertEquals('F', user.sexGetter());
        Assert.assertEquals("EXTREME", user.exerciseGetter());
    }

//    Not implemented yet, so no test will be implemented yet either
//    @Test
//    public void testSetGoalData(){

//    }
}
