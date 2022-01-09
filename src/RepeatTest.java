import java.io.IOException;
import java.io.PrintWriter;

public class RepeatTest {

    public static void main(String[] args) throws IOException {
        String fileName = "replayFile.txt";
        PrintWriter writer = new PrintWriter(fileName);
        writer.print("This is a test\nLine 2\nLine 3\nLine 4");
        writer.close();

        Deck.repeat();
    }

}
