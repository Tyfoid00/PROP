import java.util.HashMap;
import java.io.IOException;
/**
 * Write a description of class Tokenizer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tokenizer implements ITokenizer
{
    private Lexeme current;
    private Scanner scanner;
    private HashMap<Character, Token> dictionary;
    /**
     * Constructor for objects of class Tokenizer
     */

    /*
    NULL, IDENT, INT_LIT, ADD_OP, SUB_OP, MULT_OP, DIV_OP, ASSIGN_OP, 
    LEFT_PAREN, RIGHT_PAREN, SEMICOLON, LEFT_CURLY, RIGHT_CURLY, EOF
     */
    public Tokenizer()
    {
        scanner = new Scanner();
        dictionary = new HashMap<Character, Token>();
        dictionary.put('+', Token.ADD_OP);
        dictionary.put('-', Token.SUB_OP);
        dictionary.put('*', Token.MULT_OP);
        dictionary.put('/', Token.DIV_OP);
        dictionary.put('=', Token.ASSIGN_OP);
        dictionary.put('(', Token.LEFT_PAREN);
        dictionary.put(')', Token.RIGHT_PAREN);
        dictionary.put(';', Token.SEMICOLON);
        dictionary.put('{', Token.LEFT_CURLY);
        dictionary.put('}', Token.RIGHT_CURLY);
    }

    /**
     * Opens a file for tokenizing.
     */
    public void open(String fileName) throws IOException, TokenizerException{
        scanner.open(fileName);
        scanner.moveNext(); // advance one character.
    }

    /**
     * Returns the current token in the stream.
     */
    public Lexeme current(){
        return current;
    }

    /**
     * Moves current to the next token in the stream.
     */
    public void moveNext() throws IOException, TokenizerException{
        
        while (Character.isWhitespace(scanner.current())){ // skip whitespace.
            scanner.moveNext();
        }

        char c = scanner.current();

        if (c == Scanner.EOF){ // handle end of file.
            current = new Lexeme(Scanner.EOF, Token.EOF); 

        }else if (dictionary.containsKey(c)){ // handle dictionary items.
            current = new Lexeme(c, dictionary.get(c)); 
            scanner.moveNext();
        }else if (Character.isDigit(c)){ // handle integer literals.
            double number=0;
            
            while (Character.isDigit(c)){
                number*=10; // each pass, multiply the number with 10..
                number +=  Character.getNumericValue(c);
                scanner.moveNext();
                c = scanner.current();
            }
            current = new Lexeme(number, Token.INT_LIT); 

        }else if (Character.isLetter(c)){ // handle identifiers.
            String word = new String();
            while (Character.isLetter(c)){
                word+=c;
                scanner.moveNext();
                c = scanner.current();
            }
            current = new Lexeme (word, Token.IDENT);

        }else{
            System.out.println("Tokenizer error! Unknown token:" + c);
        }

        // still unsure about null ...

    }

    /**
     * Closes the file and releases any system resources associated with it.
     */
    public void close() throws IOException{
        scanner.close();
    }
}
