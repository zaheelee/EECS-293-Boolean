package jsa70.lexer;

public final class Identifier
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
    public String toString()
    {
        return "Identifier{" +
                "ID='" + ID + '\'' +
                '}';
    }
}
