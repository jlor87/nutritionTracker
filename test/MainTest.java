import org.junit.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class MainTest {
    @Test
    public void testMain(){
        String[] args = new String[0];

        String fakeInput = "0\n"; // Simulates typing "0" and the enter button
        InputStream inputStream = new ByteArrayInputStream(fakeInput.getBytes());
        System.setIn(inputStream); // Tells Java that the next input is the fakeInput from above
        ByteArrayOutputStream resultingOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(resultingOutput)); // Whatever the output is, it will get stored into resultingOutput

        Main.main(args);
        String finalOutput = resultingOutput.toString();
        // If you can reach the menu, then main() works, i.e. the User is created and Session has been started
        Assert.assertTrue(finalOutput.contains("Welcome to the nutrition app."));
    }
}
