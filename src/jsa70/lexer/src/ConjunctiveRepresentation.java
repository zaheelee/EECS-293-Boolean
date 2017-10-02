package jsa70.lexer.src;

public final class ConjunctiveRepresentation
{
    private final String REPRESENTATION;
    private final boolean NEGATION;

    public ConjunctiveRepresentation(String representation, boolean negation)
    {
        REPRESENTATION = representation;
        NEGATION = negation;
    }

    public final String getRepresentation()
    {
        return REPRESENTATION;
    }

    public final boolean getNegation()
    {
        return NEGATION;
    }
}
