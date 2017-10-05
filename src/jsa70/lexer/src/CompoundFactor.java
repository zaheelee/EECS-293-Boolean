package jsa70.lexer.src;

import java.util.Optional;

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
        token = ParserException.verifyToken(lexer.nextValid());
        DisjunctiveExpression leftId = DisjunctiveExpression.build(token, lexer);

        //AND
        token = ParserException.verifyToken(lexer.nextValid());
        ParserException.verify(Token.Type.AND, token);

        //ID or Expression
        token = ParserException.verifyToken(lexer.nextValid());
        DisjunctiveExpression rightId = DisjunctiveExpression.build(token, lexer);

        //CLOSE
        token = ParserException.verifyToken(lexer.nextValid());
        ParserException.verify(Token.Type.CLOSE, token);

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
