package jsa70.lexer.src;

public final class DisjunctiveExpression
{
    private final Factor FACTOR;
    private final Boolean POSITIVE;


    private DisjunctiveExpression(Factor factor, Boolean positive)
    {
        FACTOR = factor;
        POSITIVE = positive;
    }

    public static final DisjunctiveExpression build(LocationalToken token, DisjunctiveLexer lexer)
            throws ParserException
    {
        boolean isPositive = true;
        LocationalToken first = token;

        //optional NOT
        if(first.getType().equals(Token.Type.NOT))
        {
            isPositive = false;
            first = lexer.nextValid().get();
        }

        if(first.getType().equals(Token.Type.ID))
        {
            return new DisjunctiveExpression(Identifier.build(first), isPositive);
        }
        //only statement that can throw an exception, as we must exhaust all other possibilities before failing
        return new DisjunctiveExpression(CompoundFactor.build(first, lexer), isPositive);
    }

    public final DisjunctiveExpression negate()
    {
        return new DisjunctiveExpression(FACTOR, !POSITIVE);
    }

    @Override
    public String toString()
    {
        if(POSITIVE)
        {
            return FACTOR.toString();
        }
        return "not " + FACTOR.toString();
    }
}
