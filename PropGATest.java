

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class PropGATest {
	
	public static Scanner myScanner = new Scanner();
	public static Tokenizer myTokenizer;
	public static Parser myParser; 
	public static String inputFileName = "C:/Users/Yaqin/workspace/PROPGA/src/prop/assignment0/program1.txt";
	public static String outPutFileName = "C:/Users/Yaqin/workspace/PROPGA/src/prop/assignment0/outPutFile.txt";
	public static String inputFileEmptyName = "C:/Users/Yaqin/workspace/PROPGA/src/prop/assignment0/AnEmptyFile.txt";
	public static String fileWithIncompleteAssignment = "C:/Users/Yaqin/workspace/PROPGA/src/prop/assignment0/fileWithIncompleteAssignment.txt"; 
	//public public static String fileWithIncompleteAssignmentWithoutSemicolon = "C:/Users/Yaqin/workspace/PROPGA/src/prop/assignment0/fileWithIncompleteAssignment.txt"; 
	
	
	
	
	@Test 
	public void testScanner() throws IOException {
		myScanner.open(inputFileName);
		myScanner.moveNext(); 
		assertEquals(myScanner.current(), 'a');
		myScanner.moveNext(); 
		myScanner.moveNext();
		assertEquals(myScanner.current(), '=');
		myScanner.close(); 
	}	
	
	@Test
	public void testTokenizer() throws IOException, TokenizerException{
		myTokenizer = new Tokenizer();
		myTokenizer.open(inputFileName); 	
		Lexeme  myLexeme = myTokenizer.current();
		
		for(int i=0; i < 13; i++){
			myTokenizer.moveNext();
			myLexeme = myTokenizer.current();
			System.out.println(myLexeme.token());
			System.out.println(myLexeme.value());
		}
		
		//System.out.println("**************" + myLexeme + "**********");
				
		myTokenizer.close(); 		
	}
	
	/*
	 * To test a file with correct content.
	 */
	@Test 
	public void testParserWithProperInputFile() throws Exception{
		Parser parser = new Parser();
        parser.open(inputFileName);
        INode root = parser.parse();
        StringBuilder builder = new StringBuilder();
        builder.append("PARSE TREE:\n");
        root.buildString(builder, 0);
        builder.append("\nEVALUATION:\n");
        builder.append(root.evaluate(null));
        System.out.println(builder.toString());  
	}
	
	
	/*
	 * To test a inputFileName which is null. DOESN'T WORK! NEED TO FIX IT!
	 */
	@Test (expected= IllegalArgumentException.class)
    public void parseTestNullInputFileName() throws Exception{        
            Parser parser = new Parser();
            parser.open(null);
            INode root = parser.parse();
            StringBuilder builder = new StringBuilder();
            builder.append("PARSE TREE:\n");//fixed
            root.buildString(builder, 0);//not fixed from here and to the end of this test
            builder.append("\nEVALUATION:\n");
            builder.append(root.evaluate(null));
            System.out.println(builder.toString());       

    }
	
	
	/*
	/*
	 * To test an empty file; doesn't work, need to be fixed!
	 */
	@Test (expected= ParserException.class)
    public void parseTestEmptyFile() throws Exception{        
        Parser parser = new Parser();
        parser.open(inputFileEmptyName);
        INode root = parser.parse();
        StringBuilder builder = new StringBuilder();
        builder.append("PARSE TREE:\n");//fixed
        root.buildString(builder, 0);//not fixed from here and to the end of this test
        builder.append("\nEVALUATION:\n");
        builder.append(root.evaluate(null));
        System.out.println(builder.toString());   
    }
	
	
	/*
	 * To test an incomplete assignment: DOESN'T WORK! NEED TO BE FIXED
	 */
	
	@Test (expected= ParserException.class)
	public void testIncompleteAssignment() throws Exception{
		Parser parser = new Parser();
        parser.open(fileWithIncompleteAssignment);
        INode root = parser.parse();
        StringBuilder builder = new StringBuilder();
        builder.append("PARSE TREE:\n");//fixed
        root.buildString(builder, 0);//not fixed from here and to the end of this test
        builder.append("\nEVALUATION:\n");
        builder.append(root.evaluate(null));
        System.out.println(builder.toString());
		
	}
	
	/*
	 * To test an incomplete assignment without semicolon: DOESN'T WORK! NEED TO BE FIXED
	 */
	
	/*@Test 
	public void testIncompleteAssignment() throws Exception{
		Parser parser = new Parser();
        parser.open(fileWithIncompleteAssignmentWithoutSemicolon);
        INode root = parser.parse();
        StringBuilder builder = new StringBuilder();
        builder.append("PARSE TREE:\n");//fixed
        root.buildString(builder, 0);//not fixed from here and to the end of this test
        builder.append("\nEVALUATION:\n");
        builder.append(root.evaluate(null));
        System.out.println(builder.toString());
		
	}*/
}
  