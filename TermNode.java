import java.io.IOException;
/**
 * Write a description of class TermNode here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TermNode implements INode
{
    // term = factor , [ ( ‘*’ | ‘/’) , term] ;
    private FactorNode factor;
    private Lexeme operator; // '*' or '/'
    private TermNode term;

    public TermNode(Tokenizer tokenizer) throws IOException, TokenizerException{
        factor = new FactorNode(tokenizer);

        if (tokenizer.current().token()==Token.MULT_OP || tokenizer.current().token()==Token.DIV_OP){
            operator = tokenizer.current();
            tokenizer.moveNext();
            term = new TermNode(tokenizer);
        }
    }

    public Object evaluate(Object[] args) throws Exception{
        if (operator !=null){
            if (operator.token() == Token.MULT_OP){
                return (double)factor.evaluate(args) * (double)term.evaluate(args);
            }            
            if (operator.token() == Token.DIV_OP){
                return (double)factor.evaluate(args) / (double)term.evaluate(args);
            }
        }
        return factor.evaluate(args);
    }

    public void buildString(StringBuilder builder, int tabs){
        String theTabs= new String();
        for (int i =0; i < tabs; i++)
            theTabs +="\t";

        builder.append(theTabs + "TermNode \n");
        tabs++;
        theTabs +="\t";

        factor.buildString(builder, tabs);
        if (operator!=null){
            builder.append(theTabs + operator + "\n");
            term.buildString(builder,tabs);
        }

    }

}
