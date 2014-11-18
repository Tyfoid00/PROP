

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
/**
 * The test class TokenizerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TokenizerTest
{
    /**
     * Default constructor for test class TokenizerTest
     */
    public TokenizerTest()
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
    public void moveNextText(){
        Tokenizer tokenizer = new Tokenizer();
        try{
            tokenizer.open("program2.txt");
            
            tokenizer.moveNext();
            while(tokenizer.current().token()!=Token.EOF){
                
                tokenizer.moveNext();
            }
            
            tokenizer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
