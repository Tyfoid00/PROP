import java.io.IOException;
/**
 * Write a description of class Parser here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Parser implements IParser
{
    private Tokenizer tokenizer;
    /**
     * Constructor for objects of class Parser
     */
    public Parser()
    {
        tokenizer = new Tokenizer();
    }

    /**
     * Opens a file for parsing.
     */
    public void open(String fileName) throws IOException, TokenizerException {
        tokenizer.open(fileName);
    }

    /**
     * Parses a program from file returning a parse tree (the root node of a parse tree).
     */
    public INode parse() throws IOException, TokenizerException, ParserException{

        INode root;

        tokenizer.moveNext();
        if (tokenizer.current().token() == Token.LEFT_CURLY)
            root = new BlockNode(tokenizer);
        else 
            root = new AssignmentNode(tokenizer); // should really be statement-node, but that will lead to a different parse tree..
        return root;
    }

    /**
     * Closes the file and releases any system resources associated with it.
     */
    public void close() throws IOException {
        tokenizer.close();
    }
}
