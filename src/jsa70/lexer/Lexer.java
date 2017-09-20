package jsa70.lexer;

import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer
{
    //TODO figure out how to init
    private final Matcher MATCHER;

    //TODO figure out how to init
    private static Pattern tokenPatterns;

    static
    {
        //TODO
        tokenPatterns = new Pattern();
    }


    //TODO
    public Lexer(String input)
    {
        //TODO fix this, I don't think it should use input like this
        //maybe you do?
        MATCHER = tokenPatterns.matcher(input);
    }

    //TODO
    public boolean hasNext()
    {
        return true;
    }

    //TODO
    public LocationalToken next()
    {
        return null;
    }

    //TODO
    public Optional<LocationalToken> nextValid(Set<Token.Type> validTypes, Set<Token.Type> invalidTypes)
            throws ParserException
    {
        return null;
    }
}
