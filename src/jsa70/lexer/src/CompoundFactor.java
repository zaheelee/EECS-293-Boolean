package jsa70.lexer.src;

import java.util.Optional;

public class CompoundFactor implements Factor
{
    private final Identifier LEFT_EXPRESSION;
    private final Identifier RIGHT_EXPRESSION;


    private CompoundFactor(Identifier leftExpression, Identifier rightExpression)
    {
        LEFT_EXPRESSION = leftExpression;
        RIGHT_EXPRESSION = rightExpression;
    }

    public static final CompoundFactor build(LocationalToken token, DisjunctiveLexer lexer)
            throws ParserException
    {
        //OPEN
        ParserException.verify(Token.Type.OPEN, token);
        //ID
        Identifier leftId = Identifier.build(lexer.nextValid().get());
        //AND
        ParserException.verify(Token.Type.AND, lexer.nextValid().get());
        //ID
        Identifier rightId = Identifier.build(lexer.nextValid().get());
        //CLOSE
        ParserException.verify(Token.Type.CLOSE, lexer.nextValid().get());

        return new CompoundFactor(leftId, rightId);
    }

    @Override
    public String toString()
    {
        return "CompoundFactor{" +
                "LEFT_EXPRESSION=" + LEFT_EXPRESSION +
                ", RIGHT_EXPRESSION=" + RIGHT_EXPRESSION +
                '}';
    }
}
