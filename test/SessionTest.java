import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.*;
public class SessionTest {
    private User user;
    private Session session;
    @Before
    public void setUp() {
        user = new User();
        session = new Session(user);
    }
    @Test
    public void testStartSession(){
        session.startSession();
        Assert.assertEquals(session.getCurrentUser(), user);
        Assert.assertNotNull(session.getScanner());
        Assert.assertNotNull(session.getUserSettings());
        Assert.assertNotNull(session.getApi());
        Assert.assertNotNull(session.getPrint());
    }

    @Test
    public void testMenu(){
        String fakeInput = "0\n"; // Simulates typing "0" and the enter button
        InputStream inputStream = new ByteArrayInputStream(fakeInput.getBytes());
        System.setIn(inputStream); // Tells Java that the next input is the fakeInput from above
        ByteArrayOutputStream resultingOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(resultingOutput)); // Whatever menu() spews out will get stored into resultingOutput

        session.startSession();
        session.menu();
        String finalOutput = resultingOutput.toString();
        Assert.assertTrue(finalOutput.contains("Welcome to the nutrition app."));
        Assert.assertTrue(finalOutput.contains("Closing the application"));
    }

    @Test
    public void testGetFoodInput(){
        String fakeInput = "banana\n"; // Simulates typing "banana" and the enter button
        InputStream inputStream = new ByteArrayInputStream(fakeInput.getBytes());
        System.setIn(inputStream); // Tells Java that the next input is the fakeInput from above
        ByteArrayOutputStream resultingOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(resultingOutput)); // Whatever getFoodInput() spews out will get stored into resultingOutput

        session.startSession();
        session.getFoodInput(session.getScanner());
        String finalOutput = resultingOutput.toString();
        Assert.assertTrue(finalOutput.contains("Input food item:"));
        Assert.assertTrue(finalOutput.contains("You entered: banana"));
    }
}
