import java.io.IOException;
/**
 * Write a description of class FactorNode here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FactorNode implements INode
{
    // factor = int | id | ‘(’ , expr , ‘)’ ;

    private Lexeme intValue; // this

    private Lexeme identifier; // or this

    // or this
    private Lexeme leftParan;
    private ExpressionNode expr;
    private Lexeme rightParan;

    public FactorNode (Tokenizer tokenizer) throws IOException, TokenizerException{
        if (tokenizer.current().token()==Token.INT_LIT){
            intValue = tokenizer.current();
            tokenizer.moveNext();
        }else if (tokenizer.current().token()==Token.IDENT){
            identifier= tokenizer.current();
            tokenizer.moveNext();
        }else if (tokenizer.current().token()==Token.LEFT_PAREN){
            leftParan = tokenizer.current();
            tokenizer.moveNext();
            expr = new ExpressionNode(tokenizer);

            if (tokenizer.current().token()==Token.RIGHT_PAREN){
                rightParan = tokenizer.current();
                tokenizer.moveNext();
            }else {
                System.out.println("Parse error. Expected ')' got " + tokenizer.current().value());
            }
        }else {
            System.out.println("Parse error. Expected identifier or expression, got " + tokenizer.current().value());
        }
    }

    public Object evaluate(Object[] args) throws Exception{
        if (intValue !=null){
            return intValue.value(); 
        }else {
            return expr.evaluate(args);
        }
    }

    public void buildString(StringBuilder builder, int tabs){
        String theTabs= new String();
        for (int i =0; i < tabs; i++)
            theTabs +="\t";

        builder.append(theTabs + "FactorNode \n");
        tabs++;
        theTabs +="\t";

        if (intValue !=null){
            builder.append(theTabs + intValue + "\n");
        }else if (identifier!=null){
            builder.append(theTabs + identifier + "\n");
        }else {
            builder.append(theTabs + leftParan + "\n");
            expr.buildString(builder,tabs);
            builder.append(theTabs + rightParan + "\n");
        }
    }

}
