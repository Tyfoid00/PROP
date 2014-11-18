import java.io.IOException;
/**
 * Write a description of class StatementNode here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StatementNode implements INode
{
    // stmts = [ assign , stmts ] ;
    private AssignmentNode assignment; // optional
    private StatementNode statement; // optional

    public StatementNode (Tokenizer tokenizer)  throws IOException, TokenizerException{
        if (tokenizer.current().token() != Token.RIGHT_CURLY){ // bad check?
            assignment = new AssignmentNode(tokenizer);

        }

        if (tokenizer.current().token() != Token.RIGHT_CURLY){ // bad check?
            statement= new StatementNode(tokenizer);
        }

    }

    public Object evaluate(Object[] args) throws Exception{
        return null;
    }

    public void buildString(StringBuilder builder, int tabs){

        String theTabs= new String();
        for (int i =0; i < tabs; i++)
            theTabs +="\t";

        builder.append(theTabs + "StatementNode \n");
        tabs++;
        theTabs +="\t";

        if (assignment !=null){
            assignment.buildString(builder, tabs);
        }

        if (statement!=null){
            statement.buildString(builder, tabs);
        }
    }
}
