package jsa70.lexer;

public final class LocationalToken
{

    private final Token TOKEN;
    private final Integer LOCATION;

    public LocationalToken(Token token, Integer location)
    {
        TOKEN = token;
        LOCATION = location;
    }

    public Token getToken()
    {
        return TOKEN;
    }

    public Token.Type getType()
    {
        return TOKEN.getType();
    }

    public String getData()
    {
        return TOKEN.getData();
    }

    public Integer getLocation()
    {
        return LOCATION;
    }

}
