import java.io.IOException;
/**
 * Write a description of class BlockNode here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BlockNode implements INode
{
    // block = ‘{’ , stmts , ‘}’ ;
    private Lexeme leftCurly;
    private StatementNode statement;
    private Lexeme rightCurly;

    public BlockNode(Tokenizer tokenizer)  throws IOException, TokenizerException, ParserException{
        if (tokenizer.current().token()==Token.LEFT_CURLY){
            leftCurly = tokenizer.current();
            tokenizer.moveNext();
        }else {
            System.out.println("Parse error. Expected '(', got " + tokenizer.current().value());
        }

        statement = new StatementNode(tokenizer);

        if (tokenizer.current().token()==Token.RIGHT_CURLY){
            rightCurly = tokenizer.current();
            tokenizer.moveNext();
        }else {
            System.out.println("Parse error. Expected ')', got " + tokenizer.current().value());
        }

    }

    public Object evaluate(Object[] args) throws Exception{
        return null;
    }

    public void buildString(StringBuilder builder, int tabs){
        String theTabs= new String();
        for (int i =0; i < tabs; i++)
            theTabs +="\t";
            
        builder.append(theTabs + "BlockNode \n");
        tabs++;
        
        builder.append(theTabs + leftCurly + "\n");
        statement.buildString(builder, tabs);
        builder.append(theTabs + rightCurly + "\n");
    }
}
