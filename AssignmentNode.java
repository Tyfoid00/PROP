import java.io.IOException;
/**
 * Write a description of class AssignmentNode here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AssignmentNode implements INode
{
    // assign = id , '=' , expr , ';' ;
    private Lexeme id;
    private Lexeme equalSign;
    private ExpressionNode expr;
    private Lexeme semicolon;

    public AssignmentNode(Tokenizer tokenizer) throws IOException, TokenizerException, ParserException{
        // set id
        if (tokenizer.current().token()==Token.IDENT){
            id = tokenizer.current();
            tokenizer.moveNext();
        }else {
        	throw new ParserException("Parse error. Expected identifier, got " + tokenizer.current().value());
        }

        // set equalsign
        if (tokenizer.current().token()==Token.ASSIGN_OP){
            equalSign = tokenizer.current();
            tokenizer.moveNext();
        }else {
            System.out.println("Parse error. Expected '=', got " + tokenizer.current().value());
        }

        // set expr
        expr = new ExpressionNode(tokenizer);

        // set semicolon        
        if (tokenizer.current().token()==Token.SEMICOLON){
            semicolon = tokenizer.current();
            tokenizer.moveNext();
        }else {
            System.out.println("Parse error. Expected ';', got " + tokenizer.current().value());
            System.exit(0);
        }

    }

    public Object evaluate(Object[] args) throws Exception{
        return ""+ id.value() + equalSign.value() + expr.evaluate(args);
    }

    public void buildString(StringBuilder builder, int tabs){
        String theTabs= new String();
        for (int i =0; i < tabs; i++)
            theTabs +="\t";
            
        builder.append(theTabs + "AssignmentNode \n");
        tabs++;
        theTabs +="\t";

       
        builder.append(theTabs + id +  "\n");
       
        builder.append(theTabs + equalSign + "\n");
        expr.buildString(builder, tabs);
        builder.append(theTabs + semicolon + "\n");
    }

}

