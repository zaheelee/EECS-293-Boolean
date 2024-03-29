package jsa70.lexer.src;

public final class Identifier implements Factor
{
    private final String ID;

    private Identifier(String id)
    {
        ID = id;
    }

    public static final Identifier build(LocationalToken token)
            throws ParserException
    {
        ParserException.verify(Token.Type.ID, token);
        return new Identifier(token.getData().get());
    }

    @Override
    public ConjunctiveRepresentation conjunctiveRepresentation()
    {
        return new ConjunctiveRepresentation(ID, false);
    }

    @Override
    public String toString()
    {
        return ID;
    }
}
