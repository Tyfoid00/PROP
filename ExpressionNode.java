import java.io.IOException;
/**
 * Write a description of class ExpressionNode here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ExpressionNode implements INode
{
    // expr = term , [ ( ‘+’ | ‘-’ ) , expr ] ;
    private TermNode term;
    private Lexeme operator; // + or -
    private ExpressionNode expr;

    public ExpressionNode (Tokenizer tokenizer) throws IOException, TokenizerException{
        term = new TermNode(tokenizer);

        if (tokenizer.current().token()==Token.ADD_OP || tokenizer.current().token()==Token.SUB_OP){
            operator = tokenizer.current();
            tokenizer.moveNext();
            expr = new ExpressionNode(tokenizer);
        }

    }

    public Object evaluate(Object[] args) throws Exception{
        if (operator !=null ){
            if (operator.token()== Token.ADD_OP){
                // add.
                return (double)term.evaluate(args) + (double)expr.evaluate(args);
            }else if (operator.token()== Token.SUB_OP){
                // subtract
                return (double)term.evaluate(args) - (double)expr.evaluate(args);
            }
        }
        return term.evaluate(args);
    }

    public void buildString(StringBuilder builder, int tabs){
        String theTabs= new String();
        for (int i =0; i < tabs; i++)
            theTabs +="\t";
            
        builder.append(theTabs + "ExpressionNode \n");
        tabs++;
        theTabs +="\t";
            
        term.buildString(builder,tabs);
        if (operator!=null){
            builder.append(theTabs + operator + "\n");
            expr.buildString(builder,tabs);
        }

    }

}
