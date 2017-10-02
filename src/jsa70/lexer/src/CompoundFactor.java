package jsa70.lexer.src;

public class CompoundFactor implements Factor
{
    private final DisjunctiveExpression LEFT_EXPRESSION;
    private final DisjunctiveExpression RIGHT_EXPRESSION;


    private CompoundFactor(DisjunctiveExpression leftExpression, DisjunctiveExpression rightExpression)
    {
        LEFT_EXPRESSION = leftExpression;
        RIGHT_EXPRESSION = rightExpression;
    }

    public static final CompoundFactor build(LocationalToken token, DisjunctiveLexer lexer)
            throws ParserException
    {
        //OPEN
        ParserException.verify(Token.Type.OPEN, token);
        //ID or Expression
        DisjunctiveExpression leftId = DisjunctiveExpression.build(lexer.nextValid().get(), lexer);
        //AND
        ParserException.verify(Token.Type.AND, lexer.nextValid().get());
        //ID or Expression
        DisjunctiveExpression rightId = DisjunctiveExpression.build(lexer.nextValid().get(), lexer);
        //CLOSE
        ParserException.verify(Token.Type.CLOSE, lexer.nextValid().get());

        return new CompoundFactor(leftId, rightId);
    }

    @Override
    public ConjunctiveRepresentation conjunctiveRepresentation()
    {
        StringBuilder builder = new StringBuilder();
        builder.append('(');
        builder.append(LEFT_EXPRESSION.conjunctiveRepresentation());
        builder.append(" or ");
        builder.append(RIGHT_EXPRESSION.conjunctiveRepresentation());
        builder.append(')');
        return new ConjunctiveRepresentation(builder.toString(), true);
    }

    @Override
    public String toString()
    {
        return "(" + LEFT_EXPRESSION + " and " + RIGHT_EXPRESSION + ")";
    }
}
