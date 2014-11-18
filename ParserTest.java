
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ParserTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ParserTest
{
    /**
     * Default constructor for test class ParserTest
     */
    public ParserTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void parseTest(){
        try {
            Parser parser = new Parser();
            parser.open("program2.txt");
            INode root = parser.parse();
            StringBuilder builder = new StringBuilder();
            builder.append("PARSE TREE:\n");
            root.buildString(builder, 0);
            builder.append("\nEVALUATION:\n");
            builder.append(root.evaluate(null));
            System.out.println(builder.toString());

        }catch (Exception e){ 
            e.printStackTrace();
        }

    }

    @Test
    public void charTest(){

        for (char input= 'A'; input <= 'z'; input++){
            if (input >= 'A' && input <= 'Z'){
                System.out.println( input + " is char.");
            }else if (input >= 'a' && input <= 'z'){
                System.out.println( input + " is char.");
            }else {
                System.out.println( input + " is not char.");
            }
        }

    }
}
